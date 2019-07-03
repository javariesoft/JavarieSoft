/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.Returdorinci;
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
public class ReturdorinciDao {

    public static int insertIntoRETURDORINCI(Connection con, Returdorinci r) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO RETURDORINCI (idreturdo, kodebarang, jumlah, satuan, kodebatch, expire, "
                + "jumlahkecil)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, r.getIdreturdo());
        statement.setString(2, r.getKodebarang());
        statement.setInt(3, r.getJumlah());
        statement.setString(4, r.getSatuan());
        statement.setString(5, r.getKodebatch());
        statement.setString(6, r.getExpire());
        statement.setInt(7, r.getJumlahkecil());
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
     * @param idreturdo
     * @param kodebarang
     * @param jumlah
     * @param satuan
     * @param kodebatch
     * @param expire
     * @param jumlahkecil
     * @return boolean (true on success)
     * @throws SQLException
     */
    public static boolean updateRETURDORINCI(Connection con, Returdorinci r) throws SQLException {
        String sql = "SELECT * FROM RETURDORINCI WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, r.getId());
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

        entry.updateInt("idreturdo", r.getIdreturdo());
        if (r.getKodebarang() != null) {
            entry.updateString("kodebarang", r.getKodebarang());
        }
        entry.updateInt("jumlah", r.getJumlah());
        if (r.getSatuan() != null) {
            entry.updateString("satuan", r.getSatuan());
        }
        if (r.getKodebatch() != null) {
            entry.updateString("kodebatch", r.getKodebatch());
        }
        if (r.getExpire() != null) {
            entry.updateString("expire", r.getExpire());
        }
        entry.updateInt("jumlahkecil", r.getJumlahkecil());

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
    public static void deleteFromRETURDORINCI(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM RETURDORINCI WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
    
    }

    public static Returdorinci getDetails(Connection c, int id) throws SQLException{
        String sql="select * from returdorinci where id=?";
        PreparedStatement ps = c.prepareStatement(sql);
        Returdorinci r = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            r=new Returdorinci();
            r.setId(rs.getInt(1));
            r.setIdreturdo(rs.getInt(2));
            r.setKodebarang(rs.getString(3));
            r.setJumlah(rs.getInt(4));
            r.setSatuan(rs.getString(5));
            r.setKodebatch(rs.getString(6));
            r.setExpire(rs.getString(7));
            r.setJumlahkecil(rs.getInt(8));
        }
        return r;
    }
    
    public static List<Returdorinci> getReturdorinciList(Connection c, int idreturdo) throws SQLException{
        
        String sql="select * from returdorinci where idreturdo=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, idreturdo);
        Returdorinci r = null;
        List<Returdorinci> returdoList = new ArrayList<Returdorinci>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            r=new Returdorinci();
            r.setId(rs.getInt(1));
            r.setIdreturdo(rs.getInt(2));
            r.setKodebarang(rs.getString(3));
            r.setJumlah(rs.getInt(4));
            r.setSatuan(rs.getString(5));
            r.setKodebatch(rs.getString(6));
            r.setExpire(rs.getString(7));
            r.setJumlahkecil(rs.getInt(8));
            returdoList.add(r);
        }
        return returdoList;
    }
}
