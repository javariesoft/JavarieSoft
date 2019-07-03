/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.Barangstok;
import com.erv.model.StokPeriode;
import com.erv.model.stok;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.h2.api.Trigger;

/**
 *
 * @author erwadi
 */
public class stokDao {

    public static boolean insertIntoSTOK(Connection con, stok s) throws SQLException {
        String sql = "INSERT INTO STOK VALUES(?,?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, s.getIDPENJUALAN());
        statement.setString(2, s.getKODEBARANG());
        statement.setString(3, s.getTANGGAL());
        statement.setInt(4, s.getIN());
        statement.setInt(5, s.getOUT());
        statement.setString(6, s.getKODETRANS());
        statement.setString(7, s.getKODEBATCH());
        boolean i = statement.execute();
        statement.close();
        return !i;
    }

    public static boolean updateSTOK(Connection con, stok s) throws SQLException {
        String sql = "SELECT * FROM STOK WHERE IDPENJUALAN = ? and KODEBARANG=? and KODETRANS=? and KODEBATCH=?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, s.getIDPENJUALAN());
        statement.setString(2, s.getKODEBARANG());
        statement.setString(3, s.getKODETRANS());
        statement.setString(4, s.getKODEBATCH());
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
        if (s.getTANGGAL() != null) {
            entry.updateString("TANGGAL", s.getTANGGAL());
        }
        entry.updateInt("IN", s.getIN());
        entry.updateInt("OUT", s.getOUT());
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromSTOK(Connection con, int id1, String id2, String id3, String id4) throws SQLException {
        String sql = "DELETE FROM STOK WHERE IDPENJUALAN = ? AND KODEBARANG=? AND KODETRANS=? AND KODEBATCH=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id1);
        statement.setString(2, id2);
        statement.setString(3, id3);
        statement.setString(4, id4);
        statement.executeUpdate();
        statement.close();
    }

    public static boolean cekBarang(Connection conn, String kode) {
        boolean hasil = false;
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select KODEBARANG from STOK where KODEBARANG='" + kode + "'");
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    hasil = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return hasil;
    }
    
    public static int getStok(Connection con, String tanggal, String kodebarang){
        String tgltemp[] = tanggal.split("-");
        int bulan = Integer.parseInt(tgltemp[1]);
        int tahun = Integer.parseInt(tgltemp[2]);
        int in=0,out=0;
        int stok=0;
        // 2019-01-01
        String periode = ((bulan - 1)==0?(tahun - 1):tahun)+"."+((bulan - 1)==0?12:(bulan - 1));
        try {
            StokPeriode stokPeriode = StokPeriodeDao.getStokPeriode(con, kodebarang, periode);
            String temp = "select sum(in) as in, sum(out) as out from stok where tanggal = ? and KODEBARANG=?";
            PreparedStatement ps = con.prepareStatement(temp);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                in = rs.getInt(1);
                out = rs.getInt(2);
                stok = stokPeriode.getJumlah() + in - out;
            }
        } catch (SQLException ex) {
            Logger.getLogger(stokDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stok;
    }
    
    public static stok getStok(Connection con, int id, String kodetrans) throws SQLException{
        String sql="select * from stok where idpenjualan=? and kodetrans=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, kodetrans);
        ResultSet rs = ps.executeQuery();
        stok s=null;
        if(rs.next()){
            s=new stok();
            s.setIDPENJUALAN(rs.getInt("IDPENJUALAN"));
            s.setIN(rs.getInt("IN"));
            s.setKODEBARANG(rs.getString("KODEBARANG"));
            s.setKODEBATCH(rs.getString("KODEBATCH"));
            s.setKODETRANS(rs.getString("KODETRANS"));
            s.setOUT(rs.getInt("OUT"));
            s.setTANGGAL(rs.getString("TANGGAL"));
        }
        return s;
    }


    public static class triggerStok implements Trigger {

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
          //mulai  
            Statement stat = conn.createStatement();
            String pesan = "";
            if (newRow != null && oldRow == null) {
                if (Integer.parseInt(newRow[3].toString()) != 0) {
                    stat.execute("Update BARANGSTOK set STOK=STOK+" + Integer.parseInt(newRow[3].toString()) + " where KODEBARANG='" + newRow[1].toString() + "'");
                    pesan += " Stok Barang Bertambah " + newRow[3];
                    if (!newRow[6].toString().equals("")) {
                        Barangstok bs = BarangstokDao.getDetailKodeBarang(conn, newRow[1].toString());
                        String sql = "update BARANGSTOKBATCH set STOK=STOK +" + Integer.parseInt(newRow[3].toString()) + " where IDBARANGSTOK=" + bs.getID() + " AND KODEBATCH='" + newRow[6].toString() + "'";
                        stat.execute(sql);
                        pesan += " Stok Batch Bertambah " + newRow[3];
                    }else{
                        Barangstok bs = BarangstokDao.getDetailKodeBarang(conn, newRow[1].toString());
                        String sql = "update BARANGSTOKBATCH set STOK=STOK +" + Integer.parseInt(newRow[3].toString()) + " where IDBARANGSTOK=" + bs.getID() + " AND KODEBATCH='-'";
                        stat.execute(sql);
                        pesan += " Stok Batch Bertambah " + newRow[3];
                    }
                }
                if (Integer.parseInt(newRow[4].toString()) != 0) {
                    stat.execute("Update BARANGSTOK set STOK=STOK-" + Integer.parseInt(newRow[4].toString()) + " where KODEBARANG='" + newRow[1].toString() + "'");
                    pesan += " Stok Barang Berkurang " + newRow[4];
                    if (!newRow[6].toString().equals("")) {
                        Barangstok bs = BarangstokDao.getDetailKodeBarang(conn, newRow[1].toString());
                        String sql = "update BARANGSTOKBATCH set STOK=STOK -" + Integer.parseInt(newRow[4].toString()) + " where IDBARANGSTOK=" + bs.getID() + " AND KODEBATCH='" + newRow[6].toString() + "'";
                        stat.execute(sql);
                        pesan += " Stok Batch Berkurang " + newRow[4];
                    } else{
                        Barangstok bs = BarangstokDao.getDetailKodeBarang(conn, newRow[1].toString());
                        String sql = "update BARANGSTOKBATCH set STOK=STOK -" + Integer.parseInt(newRow[4].toString()) + " where IDBARANGSTOK=" + bs.getID() + " AND KODEBATCH='-'";
                        stat.execute(sql);
                        pesan += " Stok Batch Berkurang " + newRow[4];
                    }
                }

                System.out.println(pesan);
            } else if (newRow == null && oldRow != null) {
                String sql = "";
                if (Integer.parseInt(oldRow[3].toString()) != 0) {
                    sql = "Update BARANGSTOK set STOK=STOK-" + Integer.parseInt(oldRow[3].toString()) + " where KODEBARANG='" + oldRow[1].toString() + "'";
                    stat.execute(sql);
                     pesan += " Stok Barang Bekurang " + oldRow[3];
                    if (!oldRow[6].toString().equals("")) {
                        Barangstok bs = BarangstokDao.getDetailKodeBarang(conn, oldRow[1].toString());
                        sql = "update BARANGSTOKBATCH set STOK=STOK -" + Integer.parseInt(oldRow[3].toString()) + " where IDBARANGSTOK=" + bs.getID() + " AND KODEBATCH='" + oldRow[6].toString() + "'";
                        stat.execute(sql);
                         pesan += " Stok Batch Bekurang " + oldRow[3];
                    }else {
                        Barangstok bs = BarangstokDao.getDetailKodeBarang(conn, oldRow[1].toString());
                        sql = "update BARANGSTOKBATCH set STOK=STOK -" + Integer.parseInt(oldRow[3].toString()) + " where IDBARANGSTOK=" + bs.getID() + " AND KODEBATCH='-'";
                        stat.execute(sql);
                         pesan += " Stok Batch Bekurang " + oldRow[3];
                    }
                }

                if (Integer.parseInt(oldRow[4].toString()) != 0) {
                    sql = "Update BARANGSTOK set STOK=STOK+" + Integer.parseInt(oldRow[4].toString()) + " where KODEBARANG='" + oldRow[1].toString() + "'";
                    stat.execute(sql);
                    pesan += " Stok Barang Bertambah " + oldRow[4];
                    if (!oldRow[6].toString().equals("")) {
                        Barangstok bs = BarangstokDao.getDetailKodeBarang(conn, oldRow[1].toString());
                        sql = "update BARANGSTOKBATCH set STOK=STOK +" + Integer.parseInt(oldRow[4].toString()) + " where IDBARANGSTOK=" + bs.getID() + " AND KODEBATCH='" + oldRow[6].toString() + "'";
                        stat.execute(sql);
                        pesan += " Stok Batch Bertambah " + oldRow[4];
                    } else {
                        Barangstok bs = BarangstokDao.getDetailKodeBarang(conn, oldRow[1].toString());
                        sql = "update BARANGSTOKBATCH set STOK=STOK +" + Integer.parseInt(oldRow[4].toString()) + " where IDBARANGSTOK=" + bs.getID() + " AND KODEBATCH='" + oldRow[6].toString() + "'";
                        stat.execute(sql);
                        pesan += " Stok Batch Bertambah " + oldRow[4];
                    }
                }
                System.out.println("Delete Stok Update Barang : " + pesan);
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
