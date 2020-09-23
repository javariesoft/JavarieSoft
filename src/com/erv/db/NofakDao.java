/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.Nofak;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author USER
 */
public class NofakDao {

    public static int insertIntoNOFAK(Connection con, int nofak) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO NOFAK (nofak)"
                + "VALUES (?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, nofak);
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

    /**
     * Java method that updates a row in the generated sql table
     *
     * @param con (open java.sql.Connection)
     * @param nofak
     * @return boolean (true on success)
     * @throws SQLException
     */
    public static boolean updateNOFAK(Connection con, int keyId, int nofak) throws SQLException {
        String sql = "SELECT * FROM NOFAK WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, keyId);
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

        entry.updateInt("nofak", nofak);

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void setAktif(Connection con, int id) throws SQLException{
        String sql="update nofak set status=0";
        Statement stat = con.createStatement();
        stat.executeUpdate(sql);
        sql = "update nofak set status=1 where id="+id+"";
        stat.executeUpdate(sql);
        stat.close();
    }
    
    /**
     * Java method that deletes a row from the generated sql table
     *
     * @param con (open java.sql.Connection)
     * @param keyId (the primary key to the row)
     * @throws SQLException
     */
    public static void deleteFromNOFAK(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM NOFAK WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static Nofak getNofak(Connection con, int status) throws SQLException {
        String sql = "select * from nofak where status=1";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        Nofak nofak = null;
        if (rs.next()) {
            nofak = new Nofak();
            nofak.setId(rs.getInt(1));
            nofak.setNofak(rs.getInt(2));
        }
        return nofak;
    }
    
    public static boolean cekNoFakturAda(Connection con, int nfak) throws SQLException {
        boolean hasil = false;
        try {
        String sql = "select * from nofak where nofak="+nfak+"";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
//        Nofak nofak = null;
            if (rs.next()) {
                if (rs.getString(2) != null) {
                    hasil = true;
                }
            }
        } catch (Exception e) {
        }
        return hasil;
    }
    
    public static boolean cekAktivasiNoFaktur(Connection con, int nfak) throws SQLException {
        boolean hasil = false;
        try {
        String sql = "select * from nofak where nofak="+nfak+"";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
//        Nofak nofak = null;
            if (rs.next()) {
                if (rs.getString(2) != null) {
                    hasil = true;
                }
            }
        } catch (Exception e) {
        }
        return hasil;
    }
    
    public static Nofak getNofakId(Connection con, int id) throws SQLException {
        String sql = "select * from nofak where id="+id+"";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        Nofak nofak = null;
        if (rs.next()) {
            nofak = new Nofak();
            nofak.setId(rs.getInt(1));
            nofak.setNofak(rs.getInt(2));
        }
        rs.close();
        stat.close();
        return nofak;
    }
}
