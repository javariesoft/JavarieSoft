/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.PO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author USER
 */
public class PoDao {

    /**
     * Java method that inserts a row in the generated sql table and returns the
     * new generated id
     *
     * @param con (open java.sql.Connection)
     * @param kodepo
     * @param tanggal
     * @return id (database row id [id])
     * @throws SQLException
     */
    public static int insertIntoPO(Connection con, java.lang.String kodepo, java.lang.String tanggal, String kodepelanggan, String nofaktur) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO PO (kodepo, tanggal, kodepelanggan, nofaktur)"
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, kodepo);
        statement.setString(2, tanggal);
        statement.setString(3, kodepelanggan);
        statement.setString(4, nofaktur);
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
     * @param kodepo
     * @param tanggal
     * @return boolean (true on success)
     * @throws SQLException
     */
    public static boolean updatePO(Connection con, int keyId, java.lang.String kodepo, java.lang.String tanggal, String kodepelanggan, String nofaktur) throws SQLException {
        String sql = "SELECT * FROM PO WHERE id = ?";
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

        if (kodepo != null) {
            entry.updateString("kodepo", kodepo);
        }
        if (tanggal != null) {
            entry.updateString("tanggal", tanggal);
        }
        if (kodepelanggan != null) {
            entry.updateString("kodepelanggan", kodepelanggan);
        }
        if (nofaktur != null) {
            entry.updateString("nofaktur", nofaktur);
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
    public static void deleteFromPO(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM PO WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static PO getPO(Connection con, int id) throws SQLException {
        String sql = "select * from PO where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        PO po = null;
        if (rs.next()) {
            po = new PO();
            po.setId(rs.getInt("ID"));
            po.setKodepo(rs.getString("KODEPO"));
            po.setTanggal(rs.getString("TANGGAL"));
            po.setKodepelanggan(rs.getString("KODEPELANGGAN"));
            po.setNofaktur(rs.getString("NOFAKTUR")); 
            po.setPelanggan(new pelangganDao(con).getDetails(po.getKodepelanggan())); 
            po.setPoRincis(PoRinciDao.getAll(con, id));
        }
        rs.close();
        ps.close();
        return po;
    }

}
