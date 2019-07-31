/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.NomorPajak;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author USER
 */
public class NomorpajakDao {

    public static int insertIntoNomorpajak(Connection con, String noawal, String noakhir, String noakhirpakai, String tglrekam, String tglupdate)
            throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO nomorpajak (noawal, noakhir, noakhirpakai, tglrekam, tglupdate)"
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, noawal);
        statement.setString(2, noakhir);
        statement.setString(3, noakhirpakai);
        statement.setString(4, tglrekam);
        statement.setString(5, tglupdate);
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
     * @param noawal
     * @param noakhir
     * @param noakhirpakai
     * @param tglrekam
     * @param tglupdate
     * @return boolean (true on success)
     * @throws SQLException
     */
    public static boolean updateNomorpajak(Connection con, int keyId, String noawal, String noakhir, String noakhirpakai, String tglrekam, String tglupdate)
            throws SQLException {
        String sql = "SELECT * FROM nomorpajak WHERE id = ?";
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
            con.close();
            return false;
        }
        entry.next();

        if (noawal != null) {
            entry.updateString("noawal", noawal);
        }
        if (noakhir != null) {
            entry.updateString("noakhir", noakhir);
        }
        if (noakhirpakai != null) {
            entry.updateString("noakhirpakai", noakhirpakai);
        }
        if (tglrekam != null) {
            entry.updateString("tglrekam", tglrekam);
        }
        if (tglupdate != null) {
            entry.updateString("tglupdate", tglupdate);
        }

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    /**
     * Java method that deletes a row from the generated sql table
     *
     * @param con (open java.sql.Connection)
     * @param keyId (the primary key to the row)
     * @throws SQLException
     */
    public static void deleteFromNomorpajak(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM nomorpajak WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static NomorPajak getNomorpajak(Connection con, int keyId) throws SQLException {
        String sql = "select * from nomorpajak where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, keyId);
        ResultSet rs = ps.executeQuery();
        NomorPajak nomorPajak = null;
        if (rs.next()) {
            nomorPajak = new NomorPajak();
            nomorPajak.setId(rs.getInt(1));
            nomorPajak.setNoawal(rs.getString(2));
            nomorPajak.setNoakhir(rs.getString(3));
            nomorPajak.setNoakhirpakai(rs.getString(4));
            nomorPajak.setTglrekam(rs.getString(5));
            nomorPajak.setTglupdate(rs.getString(6));
        }
        return nomorPajak;
    }

    public static NomorPajak getNomorpajakAkhir(Connection con) throws SQLException {
        int idmax = 0;
        String sql1 = "select max(id) from nomorpajak";
        String sql = "select * from nomorpajak where id=?";
        Statement stat = con.createStatement();
        ResultSet rs1 = stat.executeQuery(sql1);
        if (rs1.next()) {
            idmax = rs1.getInt(1);
        }
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idmax);
        ResultSet rs = ps.executeQuery();
        NomorPajak nomorPajak = null;
        if (rs.next()) {
            nomorPajak = new NomorPajak();
            nomorPajak.setId(rs.getInt(1));
            nomorPajak.setNoawal(rs.getString(2));
            nomorPajak.setNoakhir(rs.getString(3));
            nomorPajak.setNoakhirpakai(rs.getString(4));
            nomorPajak.setTglrekam(rs.getString(5));
            nomorPajak.setTglupdate(rs.getString(6));
        }
        rs.close();
        rs1.close();
        stat.close();
        ps.close();
        return nomorPajak;
    }
}
