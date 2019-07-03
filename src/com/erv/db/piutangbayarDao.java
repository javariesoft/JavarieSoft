/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.piutangbayar;
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
public class piutangbayarDao {

    public static boolean insertIntoPIUTANGBAYAR(Connection con, piutangbayar p) throws SQLException {
        String sql = "INSERT INTO PIUTANGBAYAR "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, p.getID());
        statement.setString(2, p.getKODEPIUTANGBAYAR());
        statement.setInt(3, p.getIDPIUTANG());
        //statement.setDate(4, p.getTANGGAL());
        statement.setString(4, p.getTANGGAL());
        statement.setDouble(5, p.getJUMLAH());
        statement.setString(6, p.getREF());
        boolean hasil = statement.execute();
        statement.close();
        return !hasil;
    }

    public static boolean updatePIUTANGBAYAR(Connection con, piutangbayar p) throws SQLException {
        String sql = "SELECT * FROM PIUTANGBAYAR WHERE ID = ?";
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

        if (p.getKODEPIUTANGBAYAR() != null) {
            entry.updateString("KODEPIUTANGBAYAR", p.getKODEPIUTANGBAYAR());
        }
        entry.updateInt("IDPIUTANG", p.getIDPIUTANG());
        if (p.getTANGGAL() != null) {
            //entry.updateDate("TANGGAL", p.getTANGGAL());
            entry.updateString("TANGGAL", p.getTANGGAL());
        }
        entry.updateDouble("JUMLAH", p.getJUMLAH());
        if (p.getREF() != null) {
            entry.updateString("REF", p.getREF());
        }
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromPIUTANGBAYAR(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM PIUTANGBAYAR WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static int getID(Connection c) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = c.prepareStatement("select max(id) from PIUTANGBAYAR");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        return hasil;
    }
    
    public static List<piutangbayar> getPiutangBayarIDPiutang(Connection c, int idpiutang) throws SQLException{
        String sql= "select * from piutangbayar where idpiutang=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, idpiutang);
        List<piutangbayar> ls = new ArrayList<piutangbayar>();
        piutangbayar pb = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            pb = new piutangbayar();
            pb.setID(rs.getInt("ID"));
            pb.setKODEPIUTANGBAYAR(rs.getString("KODEPIUTANGBAYAR"));
            pb.setIDPIUTANG(rs.getInt("IDPIUTANG"));
            pb.setTANGGAL(rs.getString("TANGGAL"));
            pb.setJUMLAH(rs.getDouble("JUMLAH"));
            pb.setREF(rs.getString("REF"));
            ls.add(pb);
        }
        return ls;
    }

    public static String getKodePiutangBayar(Connection con) {
        String hasil = "";
        int jum=1;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
//        String sql = "select max(right(kodepiutangbayar,4)) from piutangbayar "
//                + "where substring(kodepiutangbayar,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(kodepiutangbayar,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        
        String sql = "select max(right(kodepiutangbayar,4)) from piutangbayar "
                + "where substring(kodepiutangbayar,4,2)='" + Util.getthn(tgl).substring(2, 4) + "' and left(kodepiutangbayar,2)='PB'";
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            hasil = "PB." + com.erv.function.Util.getthn(tgl).substring(2, 4) + new PrintfFormat("%04d").sprintf(jum);
            rs.close();
            stat.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }
    
    public static String getKodePiutangBayarRef(Connection con) {
        String hasil = "";
        int jum=1;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
//        String sql = "select max(right(kodepiutangbayar,4)) from piutangbayar "
//                + "where substring(kodepiutangbayar,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(kodepiutangbayar,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        
        String sql = "select max(right(kodepiutangbayar,4)) from piutangbayar "
                + "where substring(kodepiutangbayar,4,2)='" + Util.getthn(tgl).substring(2, 4) + "' and left(kodepiutangbayar,2)='RP'";
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            hasil = "RP." + com.erv.function.Util.getthn(tgl).substring(2, 4) + new PrintfFormat("%04d").sprintf(jum);
            rs.close();
            stat.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }
    
    public static class triggerPiutangBayar implements Trigger {

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
                stat.execute("update PIUTANG set STATUS='1' where ID="+oldRow[2].toString()+"");
                if(oldRow[5].toString().equals("0")){
                    stat.execute("delete from JURNAL where KODEJURNAL='" + oldRow[1].toString() + "'");
                }else{
                    stat.execute("delete from JURNAL where KODEJURNAL='" + oldRow[5].toString() + "'");
                }
                
                System.out.println("Delete Piutang Bayar, Jurnal");
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
