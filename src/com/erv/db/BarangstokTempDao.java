/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.Barangstok;
import com.erv.model.BarangstokTemp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TI-PNP
 */
public class BarangstokTempDao {

    public static int insertIntoBARANGSTOK(Connection con, BarangstokTemp b)
            throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO BARANGSTOKTEMP (id, idgudang, kodebarang, stok, cogs, hargajual, stokdo)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, b.getID());
        statement.setInt(2, b.getIDGUDANG());
        statement.setString(3, b.getKODEBARANG());
        statement.setInt(4, b.getSTOK());
        statement.setDouble(5, b.getCOGS());
        statement.setDouble(6, b.getHARGAJUAL());
        statement.setDouble(7, b.getSTOKDO());
        statement.execute();
        ResultSet auto = statement.getGeneratedKeys();

        if (auto.next()) {
            generatedId = auto.getInt(1);
        } else {
            generatedId = -1;
        }

        statement.close();
        return generatedId;
    }

    public static boolean updateBARANGSTOK(Connection con, BarangstokTemp b)
            throws SQLException {
        String sql = "SELECT * FROM BARANGSTOKTEMP WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql,
        ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, b.getID());
        ResultSet entry = statement.executeQuery();

        entry.last();
        int rows = entry.getRow();
        entry.beforeFirst();
        if (rows == 0) {
            entry.close();
            statement.close();
            con.close();
            return false;
        }
        entry.next();

        entry.updateInt("idgudang", b.getIDGUDANG());
        if (b.getKODEBARANG() != null) {
            entry.updateString("kodebarang", b.getKODEBARANG());
        }
        entry.updateInt("stok", b.getSTOK());
        entry.updateDouble("cogs", b.getCOGS());
        entry.updateDouble("hargajual", b.getHARGAJUAL());
        entry.updateDouble("stokdo", b.getSTOKDO());

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromBARANGSTOK(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM BARANGSTOKTEMP WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static void deleteFromBARANGSTOK(Connection con, String kodebarang) throws SQLException {
        String sql = "DELETE FROM BARANGSTOKTEMP WHERE kodebarang = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, kodebarang);
        statement.executeUpdate();
        statement.close();
    }
    
    public static void deleteAllBARANGSTOK(Connection con) throws SQLException {
        String sql = "DELETE FROM BARANGSTOKTEMP";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }
    
    public static BarangstokTemp getDetails(Connection con, int keyId) throws SQLException {
        BarangstokTemp bs = null;
        String sql = "SELECT * FROM BARANGSTOKTEMP WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId); 
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            bs = new BarangstokTemp();
            bs.setID(rs.getInt(1));
            bs.setIDGUDANG(rs.getInt(2));
            bs.setKODEBARANG(rs.getString(3));
            bs.setSTOK(rs.getInt(4));
            bs.setCOGS(rs.getDouble(5));
            bs.setHARGAJUAL(rs.getDouble(6));
            bs.setSTOKDO(rs.getInt(7));
        }
        return bs;
    }
    
    public static List<BarangstokTemp> getAllDetails(Connection con) throws SQLException {
        BarangstokTemp bs = null;
        String sql = "SELECT * FROM BARANGSTOKTEMP";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<BarangstokTemp> list = new ArrayList<>();
        while(rs.next()){
            bs = new BarangstokTemp();
            bs.setID(rs.getInt(1));
            bs.setIDGUDANG(rs.getInt(2));
            bs.setKODEBARANG(rs.getString(3));
            bs.setSTOK(rs.getInt(4));
            bs.setCOGS(rs.getDouble(5));
            bs.setHARGAJUAL(rs.getDouble(6));
            bs.setSTOKDO(rs.getInt(7));
            list.add(bs);
        }
        return list;
    }
    
    
    public static BarangstokTemp getDetailKodeBarang(Connection con, String KodeBarang) throws SQLException {
        BarangstokTemp bs = null;
        String sql = "SELECT * FROM BARANGSTOKTEMP WHERE KODEBARANG = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, KodeBarang); 
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            bs = new BarangstokTemp();
            bs.setID(rs.getInt(1));
            bs.setIDGUDANG(rs.getInt(2));
            bs.setKODEBARANG(rs.getString(3));
            bs.setSTOK(rs.getInt(4));
            bs.setCOGS(rs.getDouble(5));
            bs.setHARGAJUAL(rs.getDouble(6));
            bs.setSTOKDO(rs.getInt(7));
        }
        return bs;
    }

}
