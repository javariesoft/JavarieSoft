/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.model.karyawan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author KARYAWAN
 */
public class karyawanDao {

    public static boolean insertIntoKARYAWAN(Connection con, karyawan k) throws SQLException {
        String sql = "INSERT INTO KARYAWAN "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, k.getIDKARYAWAN());
        statement.setString(2, k.getNAMA());
        statement.setString(3, k.getALAMAT());
        statement.setString(4, k.getPHONE());
        statement.setString(5, k.getTGLMASUK());
        boolean h = statement.execute();
        statement.close();
        return !h;
    }

    public static boolean updateKARYAWAN(Connection con, karyawan k) throws SQLException {
        String sql="update KARYAWAN set NAMA=?,ALAMAT=?,PHONE=?,TGLMASUK=? where IDKARYAWAN=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set values to prepared statement object by getting values from bean object
        pstmt.setString(1,k.getNAMA());
        pstmt.setString(2,k.getALAMAT());
        pstmt.setString(3,k.getPHONE());
        pstmt.setString(4,k.getTGLMASUK());
        pstmt.setString(5,k.getIDKARYAWAN());
        boolean i = pstmt.execute();
        pstmt.close();
        return i;
    }

    public static void deleteFromKARYAWAN(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM KARYAWAN WHERE IDKARYAWAN = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    public static String getID(Connection con) throws SQLException{
        int hasil=1;
        PreparedStatement pstmt = con.prepareStatement("select max(cast(IDKARYAWAN as int)) from KARYAWAN");
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            if(rs.getString(1)!=null){
                hasil=rs.getInt(1)+1;
            }
        }
        rs.close();
        pstmt.close();
        return new PrintfFormat("%02d").sprintf(hasil);
    }
    public static karyawan getDetails(Connection con, String id)throws SQLException, ClassNotFoundException
    {
       //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from KARYAWAN where IDKARYAWAN=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        karyawan ubean = new karyawan();
        while(rs.next())
        {
            ubean.setIDKARYAWAN(rs.getString(1));
            ubean.setNAMA(rs.getString(2));
            ubean.setALAMAT(rs.getString(3));
            ubean.setPHONE(rs.getString(4));
            ubean.setTGLMASUK(rs.getString(5));
        }
        rs.close();
        pstmt.close();
        return ubean;
    }
}
