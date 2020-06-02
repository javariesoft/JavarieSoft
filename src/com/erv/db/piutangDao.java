/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.exception.JavarieException;
import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.jurnal;
import com.erv.model.piutang;
import com.erv.model.piutangbayar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.h2.api.Trigger;

/**
 *
 * @author erwadi
 */
public class piutangDao {

    public static boolean insertIntoPIUTANG(Connection con, piutang p) throws SQLException {
        String sql = "INSERT INTO PIUTANG "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, p.getID());
        statement.setString(2, p.getKODEPIUTANG());
        statement.setInt(3, p.getIDPENJUALAN());
        statement.setString(4, p.getTANGGAL());
        statement.setDouble(5, p.getJUMLAH());
        statement.setString(6, p.getIDPELANGGAN());
        statement.setString(7, p.getJATUHTEMPO());
        statement.setString(8, p.getKETERANGAN());
        statement.setString(9, p.getSTATUS());
        boolean h = statement.execute();
        statement.close();
        return !h;
    }

    public static void bayarPiutang(Connection con, List<piutang> piutangList,
            double jumlahBayar, String tglbayar, String desk, int carabayar, int bank, String kodePelanggan) throws JavarieException, SQLException, ClassNotFoundException {
        if (piutangList.isEmpty()) {
            throw new JavarieException("Piutang Tidak Ada");
        }
        double total = 0;
        double jumbayar = jumlahBayar;
        String ref = jurnalDao.getGenKodeJurnal(con, "PP");
        for (Iterator<piutang> it = piutangList.iterator(); it.hasNext();) {
            piutang p = it.next();
            total += p.getJUMLAH();
        }
        if (jumlahBayar > total) {
            throw new JavarieException("Jumlah Bayar Besar Dari Total Piutang");
        }
        for (Iterator<piutang> it = piutangList.iterator(); it.hasNext();) {
            piutang pt = it.next();
            if (jumbayar >= pt.getJUMLAH()) {
                piutangbayar h = new piutangbayar();
                h.setID(piutangbayarDao.getID(con));
                h.setIDPIUTANG(pt.getID());
                h.setKODEPIUTANGBAYAR(piutangbayarDao.getKodePiutangBayar(con));
                h.setTANGGAL(tglbayar);
                h.setJUMLAH(pt.getJUMLAH());
                h.setREF(ref);
                piutangbayarDao.insertIntoPIUTANGBAYAR(con, h);
                pt.setSTATUS("0");
                piutangDao.updatePIUTANG(con, pt);
                jumbayar -= pt.getJUMLAH();
            } else {
                piutangbayar h = new piutangbayar();
                h.setID(piutangbayarDao.getID(con));
                h.setIDPIUTANG(pt.getID());
                h.setKODEPIUTANGBAYAR(piutangbayarDao.getKodePiutangBayar(con));
                h.setTANGGAL(tglbayar);
                h.setJUMLAH(jumbayar);
                h.setREF(ref);
                jumbayar = 0;
                piutangbayarDao.insertIntoPIUTANGBAYAR(con, h);
            }
            if (jumbayar == 0) {
                break;
            }
        }
        insertJurnal(con, ref, tglbayar, desk, carabayar, jumlahBayar, bank, kodePelanggan);
    }

    public static void insertJurnal(Connection con, String ref,
            String tgl, String desk, int caraBayar, double jumlahBayar, int bank, String kodePelanggan) throws JavarieException, SQLException, ClassNotFoundException {
        Statement s;
        jurnal j = new jurnal();
        int IDJurnal = jurnalDao.getIDJurnal(con);
        j.setID(IDJurnal);
        j.setKODEJURNAL(ref);
        j.setTANGGAL(tgl);
        j.setDESKRIPSI(desk);
        jurnalDao.insertIntoJURNAL(con, j);
        /////////////////////////////////////////////////////////
        pelangganDao dbplg = new pelangganDao(con);
        s = con.createStatement();
        if (caraBayar == 0) {
            s.execute("insert into RINCIJURNAL values(" + IDJurnal + ",'" + settingDao.getAkun(con, "KAS") + "'," + jumlahBayar + ",0,1,'')");
        } else {
            s.execute("insert into RINCIJURNAL values(" + IDJurnal + ",'" + bankDao.getDetails(con, bank).getKODEAKUN() + "'," + jumlahBayar + ",0,1,'')");
        }
        s.execute("insert into RINCIJURNAL values(" + IDJurnal + ",'" + dbplg.getDetails(kodePelanggan).getKODEAKUN() + "',0," + jumlahBayar + ",2,'')");
        //////////////////////////////////////////////////////////
    }

    public static boolean updatePIUTANG(Connection con, piutang p) throws SQLException {
        String sql = "SELECT * FROM PIUTANG WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, p.getID());
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

        entry.updateInt("IDPENJUALAN", p.getIDPENJUALAN());
        if (p.getTANGGAL() != null) {
            entry.updateString("TANGGAL", p.getTANGGAL());
        }
        if (p.getKODEPIUTANG() != null) {
            entry.updateString("KODEPIUTANG", p.getKODEPIUTANG());
        }
        entry.updateDouble("JUMLAH", p.getJUMLAH());
        if (p.getIDPELANGGAN() != null) {
            entry.updateString("IDPELANGGAN", p.getIDPELANGGAN());
        }
        if (p.getJATUHTEMPO() != null) {
            entry.updateString("JATUHTEMPO", p.getJATUHTEMPO());
        }
        if (p.getKETERANGAN() != null) {
            entry.updateString("KETERANGAN", p.getKETERANGAN());
        }
        if (p.getSTATUS() != null) {
            entry.updateString("STATUS", p.getSTATUS());
        }
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromPIUTANG(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM PIUTANG WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static int getID(Connection c) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = c.prepareStatement("select max(id) from PIUTANG");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        return hasil;
    }

    public static String getKodePiutang(Connection con) {
        String hasil = "";
        int jum = 1;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
//        String sql = "select max(right(kodepiutang,4)) from piutang "
//                + "where substring(kodepiutang,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(kodepiutang,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        
        String sql = "select max(right(kodepiutang,4)) from piutang "
                + "where substring(kodepiutang,4,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
//            hasil = "PA." + com.erv.function.Util.getbln(tgl) + com.erv.function.Util.getthn(tgl).substring(2,4) + new PrintfFormat("%04d").sprintf(jum);
            hasil = "PA." + com.erv.function.Util.getthn(tgl).substring(2,4) + new PrintfFormat("%04d").sprintf(jum);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }

    public static piutang getDetails(Connection con, int id) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from PIUTANG where ID=?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        piutang ubean = new piutang();
        while (rs.next()) {
            ubean.setID(rs.getInt(1));
            ubean.setKODEPIUTANG(rs.getString(2));
            ubean.setIDPENJUALAN(rs.getInt(3));
            ubean.setTANGGAL(rs.getString(4));
            ubean.setJUMLAH(rs.getDouble(5));
            ubean.setIDPELANGGAN(rs.getString(6));
            ubean.setJATUHTEMPO(rs.getString(7));
            ubean.setKETERANGAN(rs.getString(8));
            ubean.setSTATUS(rs.getString(9));
        }
        return ubean;
    }

    public static List<piutang> getAllPiutangPlgBL(Connection con, String idpelanggan) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from PIUTANG where STATUS=1 and IDPELANGGAN=? ORDER BY ID");
        pstmt.setString(1, idpelanggan);
        ResultSet rs = pstmt.executeQuery();
        List<piutang> piutangList = new ArrayList<piutang>();
        piutang ubean;
        while (rs.next()) {
            ubean = new piutang();
            ubean.setID(rs.getInt(1));
            ubean.setKODEPIUTANG(rs.getString(2));
            ubean.setIDPENJUALAN(rs.getInt(3));
            ubean.setTANGGAL(rs.getString(4));
            ubean.setJUMLAH(rs.getDouble(5));
            ubean.setIDPELANGGAN(rs.getString(6));
            ubean.setJATUHTEMPO(rs.getString(7));
            ubean.setKETERANGAN(rs.getString(8));
            ubean.setSTATUS(rs.getString(9));
            ubean.setPiutangBayarList(piutangbayarDao.getPiutangBayarIDPiutang(con, ubean.getID()));
            piutangList.add(ubean);
        }
        return piutangList;
    }

    public static piutang getDetailPiutangperJual(Connection con, int id) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from PIUTANG where IDPENJUALAN=?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        piutang ubean = new piutang();
        if (rs.next()) {
            ubean.setID(rs.getInt(1));
            ubean.setKODEPIUTANG(rs.getString(2));
            ubean.setIDPENJUALAN(rs.getInt(3));
            ubean.setTANGGAL(rs.getString(4));
            ubean.setJUMLAH(rs.getDouble(5));
            ubean.setIDPELANGGAN(rs.getString(6));
            ubean.setJATUHTEMPO(rs.getString(7));
            ubean.setKETERANGAN(rs.getString(8));
            ubean.setSTATUS(rs.getString(9));
            return ubean;
        }
        return null;
    }

    public static double getJumlahPiutangPelanggan(Connection conn, String idpelanggan) throws SQLException {
        double hasil = 0;
        String sql = "SELECT sum(JUMLAH - JUMLAHBAYAR) as JUMLAH from VIEW_PIUTANG where IDPELANGGAN=" + idpelanggan + " AND STATUS='BELUM LUNAS'";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        if (rs.next()) {
            hasil = rs.getDouble(1);
        }
        return hasil;
    }
    
    public static double getJumlahHutangJual(Connection conn, String idjual) throws SQLException {
        double hasil = 0;
        String sql = "SELECT sum(JUMLAH - JUMLAHBAYAR) as JUMLAH from VIEW_PIUTANG where IDPENJUALAN=" + idjual + "";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
        return hasil;
    }
    
    public static double getJumlahPiutangTglStatusPelanggan(Connection conn, String vtgl1,String vtgl2,String vstatus,String vidpelanggan) throws SQLException {
        double hasil = 0;
        String sql = "SELECT SUM(PIUTANG.JUMLAH) AS TOTPIUTANG FROM PIUTANG WHERE PIUTANG.TANGGAL>='"+ vtgl1 +"' AND PIUTANG.TANGGAL <='"+ vtgl2 +"' AND PIUTANG.STATUS='"+ vstatus +"' AND PIUTANG.IDPELANGGAN ='"+ vidpelanggan +"'";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        if (rs.next()) {
            hasil = rs.getDouble(1);
        }
        return hasil;
    }
    

    public static class triggerPiutang implements Trigger {

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
                stat.execute("delete from PIUTANGBAYAR where IDPIUTANG=" + oldRow[0].toString() + "");
                System.out.println("Delete Piutang Bayar");
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
