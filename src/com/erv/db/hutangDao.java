/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.exception.JavarieException;
import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.hutang;
import com.erv.model.hutangbayar;
import com.erv.model.jurnal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.api.Trigger;

/**
 *
 * @author erwadi
 */
public class hutangDao {

    public static boolean insertIntoHUTANG(Connection con, hutang h) throws SQLException {

        String sql = "INSERT INTO HUTANG "
                + "VALUES (?, ?, ?, ?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, h.getID());
        statement.setString(2, h.getKODEHUTANG());
        statement.setInt(3, h.getIDPEMBELIAN());
        statement.setString(4, h.getTANGGAL());
        statement.setDouble(5, h.getJUMLAH());
        statement.setString(6, h.getIDSUPPLIER());
        statement.setString(7, h.getJATUHTEMPO());
        statement.setString(8, h.getKETERANGAN());
        statement.setString(9, h.getSTATUS());
        boolean hs = statement.execute();
        statement.close();
        return !hs;
    }

    public static void bayarHutang(List<hutang> hutangList,
            double jumlahBayar, String tglbayar, String desk, int carabayar, int bank, String kodesupplier) throws JavarieException {
        Connection con = null;
        try {
            con = koneksi.getKoneksiJ();
            con.setAutoCommit(false);
            if (hutangList.isEmpty()) {
                throw new JavarieException("Hutang Tidak Ada");
            }
            double total = 0;
            double jumbayar = jumlahBayar;
            String ref = jurnalDao.getGenKodeJurnal(con, "JH");
            for (Iterator<hutang> it = hutangList.iterator(); it.hasNext();) {
                hutang p = it.next();
                total += p.getJUMLAH();
            }
            if (jumlahBayar > total) {
                throw new JavarieException("Jumlah Bayar Besar Dari Total Hutang");
            }
            for (Iterator<hutang> it = hutangList.iterator(); it.hasNext();) {
                hutang pt = it.next();
                if (jumbayar >= pt.getJUMLAH()) {
                    hutangbayar h = new hutangbayar();
                    h.setID(hutangbayarDao.getID(con));
                    h.setIDHUTANG(pt.getID());
                    h.setKODEHUTANGBAYAR(hutangbayarDao.getKodeHutangBayar(con));
                    h.setTANGGAL(tglbayar);
                    h.setJUMLAH(pt.getJUMLAH());
                    h.setREF(ref);
                    hutangbayarDao.insertIntoHUTANGBAYAR(con, h);
                    pt.setSTATUS("0");
                    hutangDao.updateHUTANG(con, pt.getID(), pt);
                    jumbayar -= pt.getJUMLAH();
                } else {
                    hutangbayar h = new hutangbayar();
                    h.setID(hutangbayarDao.getID(con));
                    h.setIDHUTANG(pt.getID());
                    h.setKODEHUTANGBAYAR(hutangbayarDao.getKodeHutangBayar(con));
                    h.setTANGGAL(tglbayar);
                    h.setJUMLAH(jumbayar);
                    h.setREF(ref);
                    jumbayar = 0;
                    hutangbayarDao.insertIntoHUTANGBAYAR(con, h);
                }
                if (jumbayar == 0) {
                    break;
                }
            }
            insertJurnal(con, ref, tglbayar, desk, carabayar, jumlahBayar, bank, kodesupplier);
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
            }
            throw new JavarieException(ex.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(hutangDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    public static void insertJurnal(Connection con, String ref,
            String tgl, String desk, int caraBayar, double jumlahBayar, int bank, String kodesupplier) throws JavarieException {
        Statement s;
        try {
            jurnal j = new jurnal();
            int IDJurnal = jurnalDao.getIDJurnal(con);
            j.setID(IDJurnal);
            j.setKODEJURNAL(ref);
            j.setTANGGAL(tgl);
            j.setDESKRIPSI(desk);
            jurnalDao.insertIntoJURNAL(con, j);
            /////////////////////////////////////////////////////////
            s = con.createStatement();
            s.execute("insert into RINCIJURNAL values(" + IDJurnal + ",'" + supplierDao.getDetails(con, kodesupplier).getKODEAKUN() + "'," + jumlahBayar + ",0,1,'')");
            if (caraBayar == 0) {
                s.execute("insert into RINCIJURNAL values(" + IDJurnal + ",'" + settingDao.getAkun(con, "KAS") + "',0," + jumlahBayar + ",2,'')");
            } else {
                s.execute("insert into RINCIJURNAL values(" + IDJurnal + ",'" + bankDao.getDetails(con, bank).getKODEAKUN() + "',0," + jumlahBayar + ",2,'')");
            }
            //////////////////////////////////////////////////////////
        } catch (SQLException ex) {
            throw new JavarieException(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new JavarieException(ex.getMessage());
        }
    }

    public static boolean updateHUTANG(Connection con, int keyId, hutang h) throws SQLException {
        String sql = "SELECT * FROM HUTANG WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, keyId);
        ResultSet entry = statement.executeQuery();

        entry.last();
        int rows = entry.getRow();
        entry.beforeFirst();
        if (rows == 0) {
            entry.close();
            statement.close();
            return false;
        }
        entry.next();

        if (h.getKODEHUTANG() != null) {
            entry.updateString("KODEHUTANG", h.getKODEHUTANG());
        }
        entry.updateInt("IDPEMBELIAN", h.getIDPEMBELIAN());
        if (h.getTANGGAL() != null) {
            entry.updateString("TANGGAL", h.getTANGGAL());
        }
        entry.updateDouble("JUMLAH", h.getJUMLAH());
        if (h.getIDSUPPLIER() != null) {
            entry.updateString("IDSUPPLIER", h.getIDSUPPLIER());
        }
        if (h.getJATUHTEMPO() != null) {
            entry.updateString("JATUHTEMPO", h.getJATUHTEMPO());
        }
        if (h.getKETERANGAN() != null) {
            entry.updateString("KETERANGAN", h.getKETERANGAN());
        }
        if (h.getSTATUS() != null) {
            entry.updateString("STATUS", h.getSTATUS());
        }
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromHutang(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM HUTANG WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static int getID(Connection c) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = c.prepareStatement("select max(id) from HUTANG");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        return hasil;
    }

    public static String getKodeHutang(Connection con) {
        String hasil = "";
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
        int jum=1;
//        String sql = "select max(right(kodehutang,4)) from hutang "
//                + "where substring(kodehutang,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(kodehutang,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        
        String sql = "select max(right(kodehutang,4)) from hutang "
                + "where substring(kodehutang,4,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            //hasil = "HA." + com.erv.function.Util.getbln(tgl) + com.erv.function.Util.getthn(tgl).substring(2, 4) + new PrintfFormat("%04d").sprintf(jum);
            hasil = "HA." + com.erv.function.Util.getthn(tgl).substring(2, 4) + new PrintfFormat("%04d").sprintf(jum);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }

    public static hutang getDetails(Connection con, int id) throws SQLException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from HUTANG where ID=?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        hutang ubean = new hutang();
        while (rs.next()) {
            ubean.setID(rs.getInt(1));
            ubean.setKODEHUTANG(rs.getString(2));
            ubean.setIDPEMBELIAN(rs.getInt(3));
            ubean.setTANGGAL(rs.getString(4));
            ubean.setJUMLAH(rs.getDouble(5));
            ubean.setIDSUPPLIER(rs.getString(6));
            ubean.setJATUHTEMPO(rs.getString(7));
            ubean.setKETERANGAN(rs.getString(8));
            ubean.setSTATUS(rs.getString(9));
        }
        return ubean;
    }

    public static List<hutang> getAllHutangBL(Connection con, String idsupplier) throws SQLException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from HUTANG where STATUS=1 and IDSUPPLIER=? order by ID");
        pstmt.setString(1, idsupplier);
        ResultSet rs = pstmt.executeQuery();
        hutang ubean;
        List<hutang> hutangList = new ArrayList<hutang>();
        while (rs.next()) {
            ubean = new hutang();
            ubean.setID(rs.getInt(1));
            ubean.setKODEHUTANG(rs.getString(2));
            ubean.setIDPEMBELIAN(rs.getInt(3));
            ubean.setTANGGAL(rs.getString(4));
            ubean.setJUMLAH(rs.getDouble(5));
            ubean.setIDSUPPLIER(rs.getString(6));
            ubean.setJATUHTEMPO(rs.getString(7));
            ubean.setKETERANGAN(rs.getString(8));
            ubean.setSTATUS(rs.getString(9));
            ubean.setListHutangBayar(hutangbayarDao.getHutangBayarIDHutang(con, ubean.getID()));
            hutangList.add(ubean);
        }
        return hutangList;
    }

    public static hutang getDetailsBeli(Connection con, int id) throws SQLException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from HUTANG where IDPEMBELIAN=?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        hutang ubean = new hutang();
        while (rs.next()) {
            ubean.setID(rs.getInt(1));
            ubean.setKODEHUTANG(rs.getString(2));
            ubean.setIDPEMBELIAN(rs.getInt(3));
            ubean.setTANGGAL(rs.getString(4));
            ubean.setJUMLAH(rs.getDouble(5));
            ubean.setIDSUPPLIER(rs.getString(6));
            ubean.setJATUHTEMPO(rs.getString(7));
            ubean.setKETERANGAN(rs.getString(8));
            ubean.setSTATUS(rs.getString(9));
        }
        return ubean;
    }

    public static double getJumlahHutangSupplier(Connection conn, String idSupplier) throws SQLException {
        double hasil = 0;
        String sql = "SELECT sum(JUMLAH - JUMLAHBAYAR) as JUMLAH from VIEW_HUTANG where IDSUPPLIER=" + idSupplier + " AND STATUS='BELUM LUNAS'";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        if (rs.next()) {
            hasil = rs.getDouble(1);
        }
        return hasil;
    }

    public static double getJumlahHutangBeli(Connection conn, String idBeli) throws SQLException {
        double hasil = 0;
        String sql = "SELECT sum(JUMLAH - JUMLAHBAYAR) as JUMLAH from VIEW_HUTANG where IDREF=" + idBeli + "";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        if (rs.next()) {
            hasil = rs.getDouble(1);
        }
        return hasil;
    }
    
    public static double getJumlahHutangTglStatusSupplier(Connection conn, String vtgl1,String vtgl2,String vstatus,String vidsupplier) throws SQLException {
        double hasil = 0;
        String sql = "SELECT SUM(HUTANG.JUMLAH) AS TOTHUTANG FROM HUTANG WHERE HUTANG.TANGGAL>='"+ vtgl1 +"' AND HUTANG.TANGGAL <='"+ vtgl2 +"' AND HUTANG.STATUS='"+ vstatus +"' AND HUTANG.IDSUPPLIER ='"+ vidsupplier +"'";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        if (rs.next()) {
            hasil = rs.getDouble(1);
        }
        return hasil;
    }

    public static class triggerHutang implements Trigger {

        public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) {
            // initialize the trigger object is necessary
        }

        /**
         * This method is called for each triggered action.
         *
         * @param conn a connection to the database
         * @param oldRow the old row, or null if no old row is available (for
         * INSERT)
         * @param newRow the new row, or null if no new row is available (for
         * DELETE)
         * @throws SQLException if the operation must be undone
         */
        public void fire(Connection conn,
                Object[] oldRow, Object[] newRow)
                throws SQLException {
            if (newRow != null && oldRow == null) {
            } else if (newRow == null && oldRow != null) {
                Statement stat = conn.createStatement();
                stat.execute("delete from HUTANGBAYAR where IDHUTANG=" + oldRow[0].toString() + "");
                System.out.println("Delete Hutang Bayar");
            }
        }

        public void close() {
            // ignore
        }

        public void remove() {
            // ignore
        }
    }
}
