/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.retur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.h2.api.Trigger;

/**
 *
 * @author erwadi
 */
public class returDao {

    public static boolean insertIntoRETUR(Connection con, retur r) throws SQLException {
        String sql = "INSERT INTO RETUR "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, r.getID());
        statement.setString(2, r.getKODERETUR());
        statement.setString(3, r.getTANGGAL());
        statement.setString(4, r.getKODEPELANGGAN());
        statement.setString(5, r.getKETERANGAN());
        statement.setInt(6, r.getIDPENJUALAN());
        statement.setDouble(7, r.getTAMBAHANTOTALRETUR());
        statement.setInt(8, r.getSTATUS());
        statement.setDouble(9, r.getTOTALRETUR());
        statement.setDouble(10, r.getTOTALDISKON());
        statement.setDouble(11, r.getTOTALPPN());
        statement.setDouble(12, r.getTOTALHPP());
        boolean h = statement.execute();
        statement.close();
        return !h;
    }

    public static boolean updateRETUR(Connection con, retur r) throws SQLException {
        String sql = "SELECT * FROM RETUR WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, r.getID());
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
        if (r.getKODERETUR() != null) {
            entry.updateString("KODERETUR", r.getKODERETUR());
        }
        if (r.getTANGGAL() != null) {
            entry.updateString("TANGGAL", r.getTANGGAL());
        }
        if (r.getKODEPELANGGAN() != null) {
            entry.updateString("KODEPELANGGAN", r.getKODEPELANGGAN());
        }
        if (r.getKETERANGAN() != null) {
            entry.updateString("KETERANGAN", r.getKETERANGAN());
        }
        entry.updateInt("IDPENJUALAN", r.getIDPENJUALAN());
        entry.updateDouble("TAMBAHANTOTALRETUR", r.getTAMBAHANTOTALRETUR());
        entry.updateInt("STATUS", r.getSTATUS());
        entry.updateDouble("TOTALRETUR", r.getTOTALRETUR());
        entry.updateDouble("TOTALDISKON", r.getTOTALDISKON());
        entry.updateDouble("TOTALPPN", r.getTOTALPPN());
        entry.updateDouble("TOTALHPP", r.getTOTALHPP());
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromRETUR(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM RETUR WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static int getID(Connection c) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = c.prepareStatement("select max(id) from RETUR");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        return hasil;
    }

    public static retur getRetur(Connection c, int id) throws SQLException {
        String sql = "select * from retur where id=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        retur r = null;
        while (rs.next()) {
            r = new retur();
            r.setID(rs.getInt(1));
            r.setKODERETUR(rs.getString(2));
            r.setTANGGAL(rs.getString(3));
            r.setKODEPELANGGAN(rs.getString(4));
            r.setKETERANGAN(rs.getString(5));
            r.setIDPENJUALAN(rs.getInt(6));
            r.setTAMBAHANTOTALRETUR(rs.getDouble(7));
            r.setSTATUS(rs.getInt(8));
            r.setTOTALRETUR(rs.getDouble(9));
            r.setTOTALDISKON(rs.getDouble(10));
            r.setTOTALPPN(rs.getDouble(11));
            r.setTOTALHPP(rs.getDouble(12));
            r.setRincireturList(rincireturDao.getRinciRetur(c, r.getID()));
            r.setJurnal(jurnalDao.getJurnalKode(c, r.getKODERETUR()));
        }
        return r;
    }

    public static retur getReturIDJual(Connection c, int idjual) throws SQLException {
        String sql = "select * from retur where idpenjualan=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, idjual);
        ResultSet rs = ps.executeQuery();
        retur r = null;
        while (rs.next()) {
            r = new retur();
            r.setID(rs.getInt(1));
            r.setKODERETUR(rs.getString(2));
            r.setTANGGAL(rs.getString(3));
            r.setKODEPELANGGAN(rs.getString(4));
            r.setKETERANGAN(rs.getString(5));
            r.setIDPENJUALAN(rs.getInt(6));
            r.setTAMBAHANTOTALRETUR(rs.getDouble(7));
            r.setSTATUS(rs.getInt(8));
            r.setTOTALRETUR(rs.getDouble(9));
            r.setTOTALDISKON(rs.getDouble(10));
            r.setTOTALPPN(rs.getDouble(11));
            r.setTOTALHPP(rs.getDouble(12));
            r.setRincireturList(rincireturDao.getRinciRetur(c, r.getID()));
            r.setJurnal(jurnalDao.getJurnalKode(c, r.getKODERETUR()));
        }
        return r;
    }

    
    public static String setRetur(Connection conn) {
        String hasil = "";
        long jum = 1;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
//        String sql = "select max(right(koderetur,4)) from retur "
//                + "where substring(koderetur,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(koderetur,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        
        String sql = "select max(right(koderetur,4)) from retur "
                + "where substring(koderetur,4,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        try {
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            hasil ="RJ."+ com.erv.function.Util.getthn(tgl).substring(2, 4) + "" + new PrintfFormat("%04d").sprintf(jum);
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }

    public static class triggerRetur implements Trigger {

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
                stat.execute("delete from RETURRINCI where IDRETUR=" + oldRow[0].toString() + "");
                stat.execute("delete from STOK where IDPENJUALAN=" + oldRow[0].toString() + " AND KODETRANS='R'");
                stat.execute("delete from jurnal where kodejurnal='" + oldRow[1].toString() + "'");
                stat.execute("delete from piutangbayar where REF='" + oldRow[1].toString() + "'");
                stat.close();
                System.out.println("Delete Retur Rinci,Jurnal, Stok, Piutang");
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
