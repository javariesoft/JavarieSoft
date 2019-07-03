/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.Barangstok;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author TI-PNP
 */
public class BarangstokDao {

    public static int insertIntoBARANGSTOK(Connection con, Barangstok b)
            throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO BARANGSTOK (idgudang, kodebarang, stok, cogs, hargajual)"
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, b.getIDGUDANG());
        statement.setString(2, b.getKODEBARANG());
        statement.setInt(3, b.getSTOK());
        statement.setDouble(4, b.getCOGS());
        statement.setDouble(5, b.getHARGAJUAL());
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

    public static boolean updateBARANGSTOK(Connection con, Barangstok b)
            throws SQLException {
        String sql = "SELECT * FROM BARANGSTOK WHERE id = ?";
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

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }
    
    public static boolean updateFRMBRG_BARANGSTOK(Connection con, Barangstok b)
            throws SQLException {
        String sql = "SELECT * FROM BARANGSTOK WHERE id = ?";
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
////        entry.updateInt("stok", b.getSTOK());
////        entry.updateDouble("cogs", b.getCOGS());
        entry.updateDouble("hargajual", b.getHARGAJUAL());

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromBARANGSTOK(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM BARANGSTOK WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static Barangstok getDetails(Connection con, int keyId) throws SQLException {
        Barangstok bs = null;
        String sql = "SELECT * FROM BARANGSTOK WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId); 
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            bs = new Barangstok();
            bs.setID(rs.getInt(1));
            bs.setIDGUDANG(rs.getInt(2));
            bs.setKODEBARANG(rs.getString(3));
            bs.setSTOK(rs.getInt(4));
            bs.setCOGS(rs.getDouble(5));
            bs.setHARGAJUAL(rs.getDouble(6));
        }
        return bs;
    }
    
    public static Barangstok getDetailKodeBarang(Connection con, String KodeBarang) throws SQLException {
        Barangstok bs = null;
        String sql = "SELECT * FROM BARANGSTOK WHERE KODEBARANG = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, KodeBarang); 
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            bs = new Barangstok();
            bs.setID(rs.getInt(1));
            bs.setIDGUDANG(rs.getInt(2));
            bs.setKODEBARANG(rs.getString(3));
            bs.setSTOK(rs.getInt(4));
            bs.setCOGS(rs.getDouble(5));
            bs.setHARGAJUAL(rs.getDouble(6));
        }
        return bs;
    }

}
