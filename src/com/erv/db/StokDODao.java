/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.StokDO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TI-PNP
 */
public class StokDODao {

    public static int insertIntoSTOKDO(Connection con, StokDO sd) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO STOKDO (kodebarang, kodebatch, expire, stok)"
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, sd.getKODEBARANG());
        statement.setString(2, sd.getKODEBATCH());
        statement.setString(3, sd.getEXPIRE());
        statement.setInt(4, sd.getSTOK());
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

    public static boolean updateSTOKDO(Connection con, StokDO sd) throws SQLException {
        String sql = "SELECT * FROM STOKDO WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, sd.getID());
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

        if (sd.getKODEBARANG() != null) {
            entry.updateString("kodebarang", sd.getKODEBARANG());
        }
        if (sd.getKODEBATCH() != null) {
            entry.updateString("kodebatch", sd.getKODEBATCH());
        }
        if (sd.getEXPIRE() != null) {
            entry.updateString("expire", sd.getEXPIRE());
        }
        entry.updateInt("stok", sd.getSTOK());

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromSTOKDO(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM STOKDO WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static StokDO getDetails(Connection c, String sql) throws SQLException {
        StokDO stokDO = null;
        Statement stat = c.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()){
            stokDO = new StokDO();
            stokDO.setID(rs.getInt(1));
            stokDO.setKODEBARANG(rs.getString(2));
            stokDO.setKODEBATCH(rs.getString(3));
            stokDO.setEXPIRE(rs.getString(4));
            stokDO.setSTOK(rs.getInt(5));
        }
        rs.close();
        stat.close();
        return stokDO;
    }
    
   
    

}
