/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.pajak;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author erwadi
 */
public class pajakDao {

    public static boolean insertIntoPAJAK(Connection con, pajak p)
            throws SQLException {
        String sql = "INSERT INTO PAJAK "
                + "VALUES (?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, p.getNOPAJAK());
        statement.setInt(2, p.getIDJUAL());
        boolean h = statement.execute();
        statement.close();
        return !h;
    }
    public static void deleteFromPAJAK(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM PAJAK WHERE NOPAJAK = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    public static String setFaktur(Connection conn) {
        String hasil="";
        int jum=0;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
        try {
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select count(NOPAJAK) from PAJAK where SUBSTR(NOPAJAK,9,2)='"+Util.getthn(tgl).substring(2,4)+"'");
            if(rs.next()){
            if(rs.getString(1)!=null){
                jum=rs.getInt(1)+1;
            }
        }
            hasil="010.000-"+(com.erv.function.Util.getthn(tgl).substring(2,4)+"."+ new PrintfFormat("%08d").sprintf(jum));
            rs.close();
            stat.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }
    public static boolean cekFaktur(Connection con, String nofak) throws SQLException{
        boolean hasil=false;
        String sql="select * from pajak where NOPAJAK='"+nofak+"'";
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery(sql);
            if(rs.next()){
                if(rs.getString(1)!=null){
                    hasil=true;
                }
            }
            rs.close();
            stat.close();
        return hasil;
    }
}
