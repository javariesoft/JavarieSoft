/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;
import com.erv.model.Aktivatetap;
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
public class AktivatetapDao extends koneksi {

    public static boolean insertIntoAKTIVATETAP(Connection con, Aktivatetap a)
            throws SQLException {
        String sql = "INSERT INTO AKTIVATETAP "
                + "VALUES (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, a.getID());
        statement.setString(2, a.getNAMA());
        statement.setString(3, a.getTGLMASUK());
        statement.setDouble(4, a.getHARGA());
        statement.setDouble(5, a.getPERSENPENYUSUTAN());
        statement.setString(6, a.getACCAKUMULASIPENYUSUTAN());
        statement.setString(7, a.getACCBIAYAPENYUSUTAN());
        boolean hasil = statement.execute();
        statement.close();
        return !hasil;
    }

    public static boolean updateIntoAKTIVATETAP(Connection con, Aktivatetap a)
            throws SQLException {
        String sql = "update AKTIVATETAP set "
                + "NAMA=?, TGLMASUK=?, HARGA=?, PERSENPENYUSUTAN=?, ACCAKUMULASIPENYUSUTAN=?, ACCBIAYAPENYUSUTAN=? "
                + "WHERE ID=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(7, a.getID());
        statement.setString(1, a.getNAMA());
        statement.setString(2, a.getTGLMASUK());
        statement.setDouble(3, a.getHARGA());
        statement.setDouble(4, a.getPERSENPENYUSUTAN());
        statement.setString(5, a.getACCAKUMULASIPENYUSUTAN());
        statement.setString(6, a.getACCBIAYAPENYUSUTAN());
        boolean hasil = statement.execute();
        statement.close();
        return !hasil;
    }

    public static void deleteFromAKTIVATETAP(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM AKTIVATETAP WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static String setKodeAktivaTetap(Connection conn, String Akun) {
        
        String hasil="";
        int jum=0;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
        try {
            Statement stat=conn.createStatement();
            ResultSet rs=stat.executeQuery("select count(KODEPERKIRAAN) from PERKIRAAN where left(KODEPERKIRAAN,5)='"+Akun+"'");
            if(rs.next()){
            if(rs.getString(1)!=null){
                jum=rs.getInt(1);
            }
        }
            hasil=Akun+"."+ jum;
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }
     public static Aktivatetap getDetails(Connection con, String id) throws SQLException{
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from AKTIVATETAP where ID=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        Aktivatetap ubean = new Aktivatetap();
        while (rs.next()) {
            ubean.setID(rs.getString(1));
            ubean.setNAMA(rs.getString(2));
            ubean.setTGLMASUK(rs.getString(3));
            ubean.setHARGA(rs.getDouble(4));
            ubean.setPERSENPENYUSUTAN(rs.getDouble(5));
            ubean.setACCAKUMULASIPENYUSUTAN(rs.getString(6));
            ubean.setACCBIAYAPENYUSUTAN(rs.getString(7));
        }
        rs.close();
        pstmt.close();
        return ubean;
    }

}
