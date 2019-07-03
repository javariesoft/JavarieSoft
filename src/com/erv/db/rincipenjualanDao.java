/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.rincipenjualan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class rincipenjualanDao {

    public static int insert(Connection con, rincipenjualan bb) throws SQLException {
        String sql = "insert into RINCIPENJUALAN values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set values to prepared statement object by getting values from bean object
        pstmt.setInt(1, bb.getID());
        pstmt.setInt(2, bb.getIDPENJUALAN());
        pstmt.setString(3, bb.getKODEBARANG());
        pstmt.setInt(4, bb.getJUMLAH());
        pstmt.setDouble(5, bb.getHARGA());
        pstmt.setDouble(6, bb.getDISKON());
        pstmt.setDouble(7, bb.getTOTAL());
        pstmt.setDouble(8, bb.getPPN());
        pstmt.setInt(9, bb.getIDDO());
        pstmt.setString(10, bb.getSATUAN());
        pstmt.setInt(11, bb.getJUMLAHKECIL());
        pstmt.setDouble(12, bb.getCOGS());
        pstmt.setString(13, bb.getKODEBATCH());
        pstmt.setString(14, bb.getEXPIRE());
        pstmt.setDouble(15, bb.getDISKONP());
        pstmt.setString(16, bb.getBONUS());
        
        int i = pstmt.executeUpdate();
        return i;
    }

    public static rincipenjualan getDetails(Connection con, int id) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from RINCIPENJUALAN where ID=?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        rincipenjualan ubean = new rincipenjualan();
        while (rs.next()) {
            ubean.setID(rs.getInt("ID"));
            ubean.setIDPENJUALAN(rs.getInt(2));
            ubean.setKODEBARANG(rs.getString(3));
            ubean.setJUMLAH(rs.getInt(4));
            ubean.setHARGA(rs.getDouble(5));
            ubean.setDISKON(rs.getDouble(6));
            ubean.setTOTAL(rs.getDouble(7));
            ubean.setPPN(rs.getDouble(8));
            ubean.setIDDO(rs.getInt(9));
            ubean.setSATUAN(rs.getString(10));
            ubean.setJUMLAHKECIL(rs.getInt(11));
            ubean.setCOGS(rs.getDouble(12));
            ubean.setKODEBATCH(rs.getString(13));
            ubean.setEXPIRE(rs.getString(14));
            ubean.setDISKONP(rs.getDouble(15));
            ubean.setBONUS(rs.getString(16));
            
        }

        return ubean;
    }

    public static List<rincipenjualan> getAllDetails(Connection con, int idpenjualan) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from RINCIPENJUALAN where IDPENJUALAN=?");
        pstmt.setInt(1, idpenjualan);
        List<rincipenjualan> list=new ArrayList<rincipenjualan>();
        ResultSet rs = pstmt.executeQuery();
        rincipenjualan ubean;
        while (rs.next()) {
            ubean = new rincipenjualan();
            ubean.setID(rs.getInt("ID"));
            ubean.setIDPENJUALAN(rs.getInt(2));
            ubean.setKODEBARANG(rs.getString(3));
            ubean.setJUMLAH(rs.getInt(4));
            ubean.setHARGA(rs.getDouble(5));
            ubean.setDISKON(rs.getDouble(6));
            ubean.setTOTAL(rs.getDouble(7));
            ubean.setPPN(rs.getDouble(8));
            ubean.setIDDO(rs.getInt(9));
            ubean.setSATUAN(rs.getString(10));
            ubean.setJUMLAHKECIL(rs.getInt(11));
            ubean.setCOGS(rs.getDouble(12));
            ubean.setKODEBATCH(rs.getString(13));
            ubean.setEXPIRE(rs.getString(14));
            ubean.setDISKONP(rs.getDouble(15));
            ubean.setBONUS(rs.getString(16));
            list.add(ubean);
        }
        return list;
    }

    
    public static boolean update(Connection con, rincipenjualan bb) throws SQLException {
        String sql = "update RINCIPENJUALAN set IDPENJUALAN=?,KODEBARANG=?,JUMLAH=?,HARGA=?,"
                + "DISKON=?,TOTAL=?,PPN=?,IDDO=?, SATUAN=?, JUMLAHKECIL=?, "
                + "COGS=?, KODEBATCH=?, EXPIRE=?, DISKONP=?, BONUS=? where ID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set values to prepared statement object by getting values from bean object
        pstmt.setInt(16, bb.getID());
        pstmt.setInt(1, bb.getIDPENJUALAN());
        pstmt.setString(2, bb.getKODEBARANG());
        pstmt.setInt(3, bb.getJUMLAH());
        pstmt.setDouble(4, bb.getHARGA());
        pstmt.setDouble(5, bb.getDISKON());
        pstmt.setDouble(6, bb.getTOTAL());
        pstmt.setDouble(7, bb.getPPN());
        pstmt.setInt(8, bb.getIDDO());
        pstmt.setString(9, bb.getSATUAN());
        pstmt.setInt(10, bb.getJUMLAHKECIL());
        pstmt.setDouble(11, bb.getCOGS());
        pstmt.setString(12, bb.getKODEBATCH());
        pstmt.setString(13, bb.getEXPIRE());
        pstmt.setDouble(14, bb.getDISKONP());
        pstmt.setString(15, bb.getBONUS());
        boolean i = pstmt.execute();
        return i;
    }

    public static void deleteDetails(Connection con, int id) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("delete from RINCIPENJUALAN where ID=?");
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public static int getJumlahItem(Connection con, int nid) throws SQLException {
        int hasil = 0;
        PreparedStatement pstmt = con.prepareStatement("select count(*) from RINCIPENJUALAN where idpenjualan=?");
        pstmt.setInt(1, nid);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) != 0) {
                hasil = rs.getInt(1);
            }
        }
        rs.close();
        pstmt.close();
        return hasil;
    }

    public static int getID(Connection con) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = con.prepareStatement("select max(id) from RINCIPENJUALAN");
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
}