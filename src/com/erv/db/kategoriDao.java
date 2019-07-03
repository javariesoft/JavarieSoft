/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.kategori;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author erwadi
 */
public class kategoriDao {

    public static boolean insertIntoKATEGORI(Connection con, kategori k) throws SQLException {
        String sql = "INSERT INTO KATEGORI (IDKATEGORI, "
                + "KATEGORI)"
                + "VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, k.getIDKATEGORI());
        statement.setString(2, k.getKATEGORI());
        boolean hasil = statement.execute();
        statement.close();
        return !hasil;
    }

    public static boolean updateKATEGORI(Connection con, kategori k) throws SQLException {
        String sql = "SELECT * FROM KATEGORI WHERE IDKATEGORI = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setString(1, k.getIDKATEGORI());
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

        if (k.getIDKATEGORI() != null) {
            entry.updateString("IDKATEGORI", k.getIDKATEGORI());
        }
        if (k.getKATEGORI() != null) {
            entry.updateString("KATEGORI", k.getKATEGORI());
        }

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromKATEGORI(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM KATEGORI WHERE IDKATEGORI = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
     public static kategori getDetails(Connection con,String id)throws SQLException, ClassNotFoundException
    {
       //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from KATEGORI where IDKATEGORI=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        kategori ubean = new kategori();
        while(rs.next())
        {
            ubean.setIDKATEGORI(rs.getString(1));
            ubean.setKATEGORI(rs.getString(2));
        }
        rs.close();
        pstmt.close();
        return ubean;
    }
     public static int getID(Connection con) throws SQLException{
        int hasil=1;
        PreparedStatement pstmt = con.prepareStatement("select max(cast(IDKATEGORI as int)) from KATEGORI");
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            if(rs.getString(1)!=null){
                hasil=rs.getInt(1)+1;
            }
        }
        rs.close();
        pstmt.close();
        return hasil;
    }
     
}
