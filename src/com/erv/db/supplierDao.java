/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class supplierDao {

    public static boolean insertIntoSUPPLIER(Connection con, supplier s) throws SQLException {
        String sql = "INSERT INTO SUPPLIER "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, s.getIDSUPPLIER());
        statement.setString(2, s.getNAMA());
        statement.setString(3, s.getALAMAT());
        statement.setString(4, s.getNOHP());
        statement.setFloat(5, s.getBATASKREDIT());
        statement.setString(6, s.getTGLREG());
        statement.setString(7, s.getKODEAKUN());
        statement.setString(8, s.getNPWP());
        statement.setInt(9, s.getSTATUSAKTIF());
        boolean h = statement.execute();
        statement.close();
        return !h;
    }

    public static boolean updateSUPPLIER(Connection con, String keyId, supplier s) throws SQLException {
        String sql = "SELECT * FROM SUPPLIER WHERE IDSUPPLIER = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setString(1, keyId);
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
        if (s.getNAMA() != null) {
            entry.updateString("NAMA", s.getNAMA());
        }
        if (s.getALAMAT() != null) {
            entry.updateString("ALAMAT", s.getALAMAT());
        }
        if (s.getNOHP() != null) {
            entry.updateString("NOHP", s.getNOHP());
        }
        if (s.getKODEAKUN() != null) {
            entry.updateString("KODEAKUN", s.getKODEAKUN());
        }
        entry.updateFloat("BATASKREDIT", s.getBATASKREDIT());

        if (s.getTGLREG() != null) {
            entry.updateString("TGLREG", s.getTGLREG());
        }
        if (s.getNPWP() != null) {
            entry.updateString("NPWP", s.getNPWP());
        }
            entry.updateInt("STATUSAKTIF", s.getSTATUSAKTIF());
        
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromSUPPLIER(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM SUPPLIER WHERE IDSUPPLIER = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static supplier getDetails(Connection con, String id) throws SQLException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from SUPPLIER where IDSUPPLIER=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        supplier ubean = new supplier();
        while (rs.next()) {
            ubean.setIDSUPPLIER(rs.getString(1));
            ubean.setNAMA(rs.getString(2));
            ubean.setALAMAT(rs.getString(3));
            ubean.setNOHP(rs.getString(4));
            ubean.setBATASKREDIT(rs.getFloat(5));
            ubean.setTGLREG(rs.getString(6));
            ubean.setKODEAKUN(rs.getString(7));
            ubean.setNPWP(rs.getString(8));
            ubean.setSTATUSAKTIF(rs.getInt(9));
        }

        return ubean;
    }
//    public static String getKode(Connection c,String kode){
//        String hasil="";
//        String[]h;
//           h=com.erv.function.Util.split(java.sql.Date.valueOf(com.erv.function.Util.toDateStringSql(new Date())).toString(), "-");
//            hasil=kode+h[0]+h[1];
//        return hasil;
//    }

    public static int getID(Connection con) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = con.prepareStatement("select max(cast(IDSUPPLIER as int)) from SUPPLIER");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        return hasil;
    }

    public static List<supplier> getAllFilter(Connection con, String where, int limit) throws SQLException {
        String sql = "select * from supplier where 1=1 ";
        if (where.length() > 0) {
            sql += "AND " + where;
        }
        sql += " limit " + limit;
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List list = new ArrayList();
        while (rs.next()) {
            supplier ubean = new supplier();
            ubean.setIDSUPPLIER(rs.getString(1));
            ubean.setNAMA(rs.getString(2));
            list.add(ubean);
        }
        rs.close();
        pstmt.close();
        return list;
    }
}
