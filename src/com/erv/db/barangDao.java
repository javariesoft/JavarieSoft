/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.barang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class barangDao {

    public boolean insert(Connection con, barang bb) throws SQLException {
        String sql = "insert into BARANG values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set values to prepared statement object by getting values from bean object
        pstmt.setString(1, bb.getKODEBARANG());
        pstmt.setString(2, bb.getNAMABARANG());
        pstmt.setString(3, bb.getSATUAN());
        pstmt.setDouble(4, bb.getHARGA());
        pstmt.setString(5, bb.getKODEAKUN());
        pstmt.setString(6, bb.getPENDAPATAN_ACC());
        pstmt.setString(7, bb.getCOGS_ACC());
        pstmt.setDouble(8, bb.getCOGS());
        pstmt.setInt(9, bb.getSTOK());
        pstmt.setString(10, bb.getIDKATEGORI());
        pstmt.setString(11, bb.getJENISBARANG());
        pstmt.setInt(12, bb.getSTATUS());
        pstmt.setString(13, bb.getSATUAN1());
        pstmt.setInt(14, bb.getJUMLAH1());
        pstmt.setString(15, bb.getSATUAN2());
        pstmt.setInt(16, bb.getJUMLAH2());
        boolean i = pstmt.execute();
        pstmt.close();
        return i;
    }

    public List getAlldetails(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("select * from BARANG");
        ResultSet rs = pstmt.executeQuery();
        List list = new ArrayList();
        while (rs.next()) {
            barang ubean = new barang();
            ubean.setKODEBARANG(rs.getString(1));
            ubean.setNAMABARANG(rs.getString(2));
            ubean.setSATUAN(rs.getString(3));
            ubean.setHARGA(rs.getDouble(4));
            ubean.setKODEAKUN(rs.getString(5));
            ubean.setPENDAPATAN_ACC(rs.getString(6));
            ubean.setCOGS_ACC(rs.getString(7));
            ubean.setCOGS(rs.getDouble(8));
            ubean.setSTOK(rs.getInt(9));
            ubean.setIDKATEGORI(rs.getString(10));
            ubean.setJENISBARANG(rs.getString(11));
            ubean.setSTATUS(rs.getInt("STATUS"));
            ubean.setSATUAN1(rs.getString("SATUAN1"));
            ubean.setJUMLAH1(rs.getInt("JUMLAH1"));
            ubean.setSATUAN2(rs.getString("SATUAN2"));
            ubean.setJUMLAH2(rs.getInt("JUMLAH2"));
            list.add(ubean);
        }
        rs.close();
        pstmt.close();
        return list;
    }

    public barang getDetails(Connection con, String id) throws SQLException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from BARANG where KODEBARANG=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        barang ubean = null;
        while (rs.next()) {
            ubean = new barang();
            ubean.setKODEBARANG(rs.getString(1));
            ubean.setNAMABARANG(rs.getString(2));
            ubean.setSATUAN(rs.getString(3));
            ubean.setHARGA(rs.getDouble(4));
            ubean.setKODEAKUN(rs.getString(5));
            ubean.setPENDAPATAN_ACC(rs.getString(6));
            ubean.setCOGS_ACC(rs.getString(7));
            ubean.setCOGS(rs.getDouble(8));
            ubean.setSTOK(rs.getInt(9));
            ubean.setIDKATEGORI(rs.getString(10));
            ubean.setJENISBARANG(rs.getString(11));
            ubean.setSTATUS(rs.getInt(12));
            ubean.setSATUAN1(rs.getString(13));
            ubean.setJUMLAH1(rs.getInt(14));
            ubean.setSATUAN2(rs.getString(15));
            ubean.setJUMLAH2(rs.getInt(16));
        }
        rs.close();
        pstmt.close();
        return ubean;
    }

    public boolean update(Connection con,barang bb) throws SQLException {
        String sql = "update BARANG set NAMABARANG=?,SATUAN=?,HARGA=?,KODEAKUN=?,"
                + "PENDAPATAN_ACC=?,COGS_ACC=?, COGS=?,STOK=?, IDKATEGORI=?, "
                + "IDJENIS=?, STATUS=?, "
                + "SATUAN1=?, JUMLAH1=?, SATUAN2=?, JUMLAH2=? "
                + "where KODEBARANG=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set values to prepared statement object by getting values from bean object
        pstmt.setString(16, bb.getKODEBARANG());
        pstmt.setString(1, bb.getNAMABARANG());
        pstmt.setString(2, bb.getSATUAN());
        pstmt.setDouble(3, bb.getHARGA());
        pstmt.setString(4, bb.getKODEAKUN());
        pstmt.setString(5, bb.getPENDAPATAN_ACC());
        pstmt.setString(6, bb.getCOGS_ACC());
        pstmt.setDouble(7, bb.getCOGS());
        pstmt.setInt(8, bb.getSTOK());
        pstmt.setString(9, bb.getIDKATEGORI());
        pstmt.setString(10, bb.getJENISBARANG());
        pstmt.setInt(11, bb.getSTATUS());
        pstmt.setString(12, bb.getSATUAN1());
        pstmt.setInt(13, bb.getJUMLAH1());
        pstmt.setString(14, bb.getSATUAN2());
        pstmt.setInt(15, bb.getJUMLAH2());
        boolean i = pstmt.execute();
        pstmt.close();
        return i;
    }
    
    public boolean updateBarang(Connection con,barang bb) throws SQLException {
        String sql = "update BARANG set NAMABARANG=?,SATUAN=?,HARGA=?,KODEAKUN=?,"
                + "PENDAPATAN_ACC=?,COGS_ACC=?, COGS=?,STOK=?, IDKATEGORI=?, "
                + "IDJENIS=?, STATUS=? where KODEBARANG=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set values to prepared statement object by getting values from bean object
        pstmt.setString(12, bb.getKODEBARANG());
        pstmt.setString(1, bb.getNAMABARANG());
        pstmt.setString(2, bb.getSATUAN());
        pstmt.setDouble(3, bb.getHARGA());
        pstmt.setString(4, bb.getKODEAKUN());
        pstmt.setString(5, bb.getPENDAPATAN_ACC());
        pstmt.setString(6, bb.getCOGS_ACC());
        pstmt.setDouble(7, bb.getCOGS());
        pstmt.setInt(8, bb.getSTOK());
        pstmt.setString(9, bb.getIDKATEGORI());
        pstmt.setString(10, bb.getJENISBARANG());
        pstmt.setInt(11, bb.getSTATUS());
//        pstmt.setString(12, bb.getSATUAN1());
//        pstmt.setInt(13, bb.getJUMLAH1());
//        pstmt.setString(14, bb.getSATUAN2());
//        pstmt.setInt(15, bb.getJUMLAH2());
        boolean i = pstmt.execute();
        pstmt.close();
        return i;
    }

//    public boolean updateCOGS(barang bb) throws SQLException
//     {
//        String sql="update BARANG set COGS=? where KODEBARANG=?";
//        PreparedStatement pstmt = con.prepareStatement(sql);
//        //set values to prepared statement object by getting values from bean object
//        pstmt.setDouble(1,bb.getCOGS());
//        pstmt.setString(2,bb.getKODEBARANG());
//        boolean i = pstmt.execute();
//        return i;
//     }
    public void deleteDetails(Connection con, String id) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("delete from BARANG where KODEBARANG=?");
        pstmt.setString(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public static String getID(Connection c) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = c.prepareStatement("select max(cast(KODEBARANG as int)) from BARANG");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        rs.close();
        pstmt.close();
        return new PrintfFormat("%05d").sprintf(hasil);
    }

    public static boolean cekKodeBarang(Connection con, String kode) throws SQLException {
        boolean hasil = false;
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select KODEBARANG from BARANG where KODEBARANG='" + kode + "'");
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    hasil = true;
                }
            }
            rs.close();
            stat.close();
        return hasil;
    }
    
    public static boolean cekBarangBatch(Connection con, String kode,String kodeb) throws SQLException {
        boolean hasil = false;
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT BSB.KODEBATCH FROM BARANGSTOKBATCH BSB, BARANGSTOK BS,BARANG B "
                    + "WHERE BSB.IDBARANGSTOK=BS.ID AND BS.KODEBARANG=B.KODEBARANG "
                    + "AND B.KODEBARANG='" + kode + "' AND BSB.KODEBATCH='" + kodeb + "'");
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    hasil = true;
                }
            }
            rs.close();
            stat.close();
        return hasil;
    }
    
    public static void importDataBarang(Connection con, List<barang> barangList){
        
    }
    
}
