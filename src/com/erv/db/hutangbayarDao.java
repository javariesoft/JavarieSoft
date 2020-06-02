/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.hutangbayar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.h2.api.Trigger;

/**
 *
 * @author erwadi
 */
public class hutangbayarDao {

    public static boolean insertIntoHUTANGBAYAR(Connection con, hutangbayar h) throws SQLException {
        String sql = "INSERT INTO HUTANGBAYAR "
                + "VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, h.getID());
        statement.setString(2, h.getKODEHUTANGBAYAR());
        statement.setInt(3, h.getIDHUTANG());
        statement.setString(4, h.getTANGGAL());
        statement.setDouble(5, h.getJUMLAH());
        statement.setString(6, h.getREF());
        boolean hasil = statement.execute();
        statement.close();
        return !hasil;
    }

    public static boolean updateHUTANGBAYAR(Connection con, int keyId, hutangbayar h) throws SQLException {
        String sql = "SELECT * FROM HUTANGBAYAR WHERE ID = ?";
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

        if (h.getKODEHUTANGBAYAR() != null) {
            entry.updateString("KODEHUTANGBAYAR", h.getKODEHUTANGBAYAR());
        }
        entry.updateInt("IDHUTANG", h.getIDHUTANG());
        if (h.getTANGGAL() != null) {
            entry.updateString("TANGGAL", h.getTANGGAL());
        }
        entry.updateDouble("JUMLAH", h.getJUMLAH());
        if (h.getREF() != null) {
            entry.updateString("REF", h.getREF());
        }
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }
    
    public static List<hutangbayar> getHutangBayarIDHutang(Connection c, int idhutang) throws SQLException{
        String sql= "select * from hutangbayar where idhutang=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, idhutang);
        List<hutangbayar> ls = new ArrayList<hutangbayar>();
        hutangbayar hb; 
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            hb = new hutangbayar();
            hb.setID(rs.getInt("ID"));
            hb.setKODEHUTANGBAYAR(rs.getString("KODEHUTANGBAYAR"));
            hb.setIDHUTANG(rs.getInt("IDHUTANG"));
            hb.setTANGGAL(rs.getString("TANGGAL"));
            hb.setJUMLAH(rs.getDouble("JUMLAH"));
            hb.setREF(rs.getString("REF"));
            ls.add(hb);
        }
        return ls;
    }

    public static void deleteFromHUTANGBAYAR(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM HUTANGBAYAR WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static void deleteFromHUTANGBAYAR(Connection con, String ref) throws SQLException {
        String sql = "DELETE FROM HUTANGBAYAR WHERE REF = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, ref);
        statement.executeUpdate();
        statement.close();
    }

    public static int getID(Connection c) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = c.prepareStatement("select max(id) from HUTANGBAYAR");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        rs.close();
        pstmt.close();
        return hasil;
    }

    public static String getKodeHutangBayar(Connection con) {
        String hasil = "";
        int jum = 1;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
//        String sql = "select max(right(kodehutangbayar,4)) from hutangbayar "
//                + "where substring(kodehutangbayar,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(kodehutangbayar,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        
        String sql = "select max(right(kodehutangbayar,4)) from hutangbayar "
                + "where substring(kodehutangbayar,4,2)='" + Util.getthn(tgl).substring(2, 4) + "' and left(kodehutangbayar,2)='HB'";
        
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            //hasil = "HB." + com.erv.function.Util.getbln(tgl) + com.erv.function.Util.getthn(tgl).substring(2,4) + new PrintfFormat("%04d").sprintf(jum);
            hasil = "HB." + com.erv.function.Util.getthn(tgl).substring(2,4) + new PrintfFormat("%04d").sprintf(jum);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }
    
    public static String getKodeHutangBayarRef(Connection con) {
        String hasil = "";
        int jum = 1;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
//        String sql = "select max(right(kodehutangbayar,4)) from hutangbayar "
//                + "where substring(kodehutangbayar,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(kodehutangbayar,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        
        String sql = "select max(right(kodehutangbayar,4)) from hutangbayar "
                + "where substring(kodehutangbayar,4,2)='" + Util.getthn(tgl).substring(2, 4) + "' and left(kodehutangbayar,2)='RH'";
        
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            hasil = "RH." + com.erv.function.Util.getthn(tgl).substring(2,4) + new PrintfFormat("%04d").sprintf(jum);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }

    public static class triggerHutangBayar implements Trigger {

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
                stat.execute("update HUTANG set STATUS='1' where ID=" + oldRow[2].toString() + "");
                if (oldRow[5].toString().equals("0")) {
                    stat.execute("delete from JURNAL where KODEJURNAL='" + oldRow[1].toString() + "'");
                } else {
                    stat.execute("delete from JURNAL where KODEJURNAL='" + oldRow[5].toString() + "'");
                }
                System.out.println("Delete Hutang Bayar, Jurnal");
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
