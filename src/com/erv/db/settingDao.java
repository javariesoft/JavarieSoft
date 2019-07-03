/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author erwadi
 */
public class settingDao {

    public static boolean insertIntoSetting(Connection con, setting s) throws SQLException {
        String sql = "INSERT INTO setting "
                + "VALUES (?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, s.getKODE());
        statement.setString(2, s.getAKUN());
        statement.setString(3, s.getDESKRIPSI());
        boolean h = statement.execute();
        statement.close();
        return h;
    }

    public static boolean updateSetting(Connection con, String keyId, setting s) throws SQLException {
        String sql = "SELECT * FROM setting WHERE KODE = ?";
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
        if (s.getAKUN() != null) {
            entry.updateString("AKUN", s.getAKUN());
        }
        if (s.getDESKRIPSI() != null) {
            entry.updateString("DESKRIPSI", s.getDESKRIPSI());
        }

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromSetting(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM SETTING WHERE KODE = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    public static String getAkun(Connection con,String kode) throws SQLException{
        String hasil="";
        Statement stat=con.createStatement();
        ResultSet rs=stat.executeQuery("select AKUN from SETTING where KODE='"+kode+"'");
        if(rs.next()){
            hasil=rs.getString(1);
        }
        rs.close();
        stat.close();
        return hasil;
    }
     public static setting getDetails(Connection con,String id)throws SQLException, ClassNotFoundException
    {
       //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from SETTING where KODE=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        setting ubean = new setting();
        while(rs.next())
        {
            ubean.setKODE(rs.getString(1));
            ubean.setAKUN(rs.getString(2));
            ubean.setDESKRIPSI(rs.getString(3));
        }
        rs.close();
        pstmt.close();
        return ubean;
    }
}
