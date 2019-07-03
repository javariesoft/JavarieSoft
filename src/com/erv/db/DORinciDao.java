/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.DORinci;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class DORinciDao {

    public static boolean insertIntoDORinci(Connection con, DORinci d) throws SQLException {
        PreparedStatement statement = null;
        String sql = "INSERT INTO DORinci "
                + "VALUES (?, ?, ?,?,?,?,?)";
        statement = con.prepareStatement(sql);
        statement.setInt(1, d.getIDDO());
        statement.setString(2, d.getKODEBARANG());
        statement.setInt(3, d.getJUMLAH());
        statement.setString(4, d.getSATUAN());
        statement.setString(5, d.getKODEBATCH());
        statement.setString(6, d.getEXPIRE());
        statement.setInt(7, d.getJUMLAHKECIL());
        boolean h = statement.execute();
        statement.close();
        return !h;
    }

    public static boolean updateDORinci(Connection conn, DORinci d) throws SQLException {
        PreparedStatement statement = null;
        statement = conn.prepareStatement("update DORINCI set JUMLAH = ?,SATUAN=?,EXPIRE=?,JUMLAHKECIL=? "
                + "where IDDO = ? AND KODEBARANG = ? AND KODEBATCH=? ");
        statement.setInt(1, d.getJUMLAH());
        statement.setString(2, d.getSATUAN());
        statement.setString(3, d.getEXPIRE());
        statement.setInt(4, d.getJUMLAHKECIL());        
        statement.setInt(5, d.getIDDO());
        statement.setString(6, d.getKODEBARANG());  
        statement.setString(7, d.getKODEBATCH());  
        boolean h = statement.execute();
        statement.close();
        return !h;
    }

    public static void deleteFromDORinci(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM DORinci WHERE IDDO = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static void deleteFromDORinciDetil(Connection con, int IDDO, String KODEBARANG,String KODEBATCH) throws SQLException {
        String sql = "DELETE FROM DORinci WHERE IDDO = ? AND KODEBARANG=? AND KODEBATCH=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, IDDO);
        statement.setString(2, KODEBARANG);
        statement.setString(3, KODEBATCH);
        statement.executeUpdate();
        statement.close();
    }

    public static DORinci getDetails(Connection con, int IDDO, String KODEBARANG,String KODEBATCH) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from DORinci where IDDO=? AND KODEBARANG=?  AND KODEBATCH=?");
        pstmt.setInt(1, IDDO);
        pstmt.setString(2, KODEBARANG);
        pstmt.setString(3, KODEBATCH);
        ResultSet rs = pstmt.executeQuery();
        DORinci ubean = new DORinci();
        while (rs.next()) {
            ubean.setIDDO(rs.getInt(1));
            ubean.setKODEBARANG(rs.getString(2));
            ubean.setJUMLAH(rs.getInt(3));
            ubean.setSATUAN(rs.getString(4));
            ubean.setKODEBATCH(rs.getString(5));
            ubean.setEXPIRE(rs.getString(6));
            ubean.setJUMLAHKECIL(rs.getInt(7));
        }
        rs.close();
        pstmt.close();
        return ubean;
    }
    
    public static List<DORinci> getDetailDORinci(Connection con, int IDDO) throws SQLException {
        //here we will write code to get a single record from database
        List<DORinci> list = new ArrayList<DORinci>();
        PreparedStatement pstmt = con.prepareStatement("select * from DORinci where IDDO=?");
        pstmt.setInt(1, IDDO);
        ResultSet rs = pstmt.executeQuery();
        DORinci ubean = null;
        while (rs.next()) {     
            ubean = new DORinci();
            ubean.setIDDO(rs.getInt(1));
            ubean.setKODEBARANG(rs.getString(2));
            ubean.setJUMLAH(rs.getInt(3));
            ubean.setSATUAN(rs.getString(4));
            ubean.setKODEBATCH(rs.getString(5));
            ubean.setEXPIRE(rs.getString(6));
            ubean.setJUMLAHKECIL(rs.getInt(7));
            ubean.setBarangstok(BarangstokDao.getDetailKodeBarang(con, ubean.getKODEBARANG()));
            list.add(ubean);
        }
        rs.close();
        pstmt.close();
        return list;
    }
    
    public static boolean cekKodeBarangBatch(Connection con, String viddo,String vkdpel,String vkdbrg,String vkdbatch) throws SQLException {
        boolean hasil = false;
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select DR.KODEBARANG,BR.NAMABARANG, DR.KODEBATCH\n" +
                         " FROM DO inner join DORINCI DR on DO.ID = DR.IDDO\n" +
                         " inner join BARANG BR on BR.KODEBARANG=DR.KODEBARANG\n" +
                         " inner join JENISBARANG JB on BR.IDJENIS = JB.ID\n" +
                         " where DO.ID = '" + viddo + "' and DO.KODEPELANGGAN='" + vkdpel + "' and DR.KODEBARANG='" + vkdbrg + "' and DR.KODEBATCH='" + vkdbatch + "'");
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    hasil = true;
                }
            }
            rs.close();
            stat.close();
        return hasil;
    }
    
    public static boolean cekKodeBarangDO(Connection con, String vkddo,String vkdpel,String vkdbrg,String vkdbatch) throws SQLException {
        //String periode = thn + "." + bln;    
        boolean hasil1 = false;
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("Select DR.KODEBARANG,BR.NAMABARANG, DR.KODEBATCH\n" +
                         " FROM DO inner join DORINCI DR on DO.ID = DR.IDDO\n" +
                         " inner join BARANG BR on BR.KODEBARANG=DR.KODEBARANG\n" +
                         " inner join JENISBARANG JB on BR.IDJENIS = JB.ID\n" +
                         " where DO.KODEDO = '" + vkddo + "' and DO.KODEPELANGGAN='" + vkdpel + "' and DR.KODEBARANG='" + vkdbrg + "' and DR.KODEBATCH='" + vkdbatch + "'");

        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil1 = true;
            }
        }
        rs.close();
        stat.close();
        return hasil1;
    }
    
    public static int getStokDO(Connection con, String vkddo,String vkdpel,String vkdbrg,String vkdbatch) {
        int hasil = 0;
        String sql = "Select DR.KODEBARANG,BR.NAMABARANG, DR.KODEBATCH, DR.EXPIRE, "
                + "DR.JUMLAHKECIL - IFNULL((SELECT RETURDORINCI.JUMLAHKECIL AS RETURDORINCI_JUMLAHKECIL "
                + "FROM PUBLIC.RETURDO RETURDO INNER JOIN PUBLIC.RETURDORINCI RETURDORINCI ON RETURDO.ID = RETURDORINCI.IDRETURDO "
                + "where RETURDO.IDDO=DO.ID and DR.KODEBARANG = RETURDORINCI.KODEBARANG and RETURDORINCI.KODEBATCH= DR.KODEBATCH),0)"
                + " - IFNULL((select SUM(RP.JUMLAHKECIL) from RINCIPENJUALAN RP where RP.KODEBARANG = DR.KODEBARANG AND RP.KODEBATCH=DR.KODEBATCH AND RP.IDDO=DO.ID),0) as STOK "
                + "FROM DO inner join DORINCI DR on DO.ID = DR.IDDO "
                + "inner join BARANG BR on BR.KODEBARANG=DR.KODEBARANG "
                + "inner join JENISBARANG JB on BR.IDJENIS = JB.ID "
                + "where DO.KODEDO = '" + vkddo + "' and DO.KODEPELANGGAN='" + vkdpel + "' and DR.KODEBARANG='" + vkdbrg + "' and DR.KODEBATCH='" + vkdbatch + "'";
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if(rs.getString(1)!=null){
                    hasil = rs.getInt(5);
                }
            } 
//            hasil = 0;
            rs.close();
            stat.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }
}
