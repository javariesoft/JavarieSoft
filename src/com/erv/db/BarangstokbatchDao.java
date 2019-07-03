/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.Barangstokbatch;
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
public class BarangstokbatchDao {

    public static int insertIntoBARANGSTOKBATCH(Connection con, Barangstokbatch b) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO BARANGSTOKBATCH (idbarangstok, kodebatch, expire, stok)"
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, b.getIDBARANGSTOK());
        statement.setString(2, b.getKODEBATCH());
        statement.setString(3, b.getEXPIRE());
        statement.setInt(4, b.getSTOK());
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
     * @param idbarangstok
     * @param kodebatch
     * @param expire
     * @param stok
     * @return boolean (true on success)
     * @throws SQLException
     */
    
    public static Barangstokbatch getDetails(Connection con, int keyId) throws SQLException {
        Barangstokbatch bs = null;
        String sql = "SELECT * FROM BARANGSTOKBATCH WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId); 
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            bs = new Barangstokbatch();
            bs.setID(rs.getInt(1));
            bs.setIDBARANGSTOK(rs.getInt(2));
            bs.setKODEBATCH(rs.getString(3));
            bs.setSTOK(rs.getInt(4));
        }
        return bs;
    }
    
    public static Barangstokbatch getDetailKodeBatch(Connection con, String kodeBatch, int idbarangStok) throws SQLException {
        Barangstokbatch bs = null;
        String sql = "SELECT * FROM BARANGSTOKBATCH WHERE KODEBATCH = ? AND IDBARANGSTOK=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, kodeBatch); 
        statement.setInt(2, idbarangStok); 
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            bs = new Barangstokbatch();
            bs.setID(rs.getInt(1));
            bs.setIDBARANGSTOK(rs.getInt(2));
            bs.setKODEBATCH(rs.getString(3));
            bs.setEXPIRE(rs.getString(4));
            bs.setSTOK(rs.getInt(5));
        }
        return bs;
    }
    
    public static Barangstokbatch getDetailKodeBatchID(Connection con, int idbarangStok) throws SQLException {
        Barangstokbatch bs = null;
        String sql = "SELECT * FROM BARANGSTOKBATCH WHERE IDBARANGSTOK=?";
        PreparedStatement statement = con.prepareStatement(sql); 
        statement.setInt(1, idbarangStok); 
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            bs = new Barangstokbatch();
            bs.setID(rs.getInt(1));
            bs.setIDBARANGSTOK(rs.getInt(2));
            bs.setKODEBATCH(rs.getString(3));
            bs.setEXPIRE(rs.getString(4));
            bs.setSTOK(rs.getInt(5));
        }
        return bs;
    }
    
    
    public static List<Barangstokbatch> getAllDetailBarangStok(Connection con, int IdBarangStok) throws SQLException {
        List<Barangstokbatch> list = new ArrayList<Barangstokbatch>();
        Barangstokbatch bs;
        String sql = "SELECT * FROM BARANGSTOKBATCH WHERE IDBARANGSTOK = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, IdBarangStok); 
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            bs = new Barangstokbatch();
            bs.setID(rs.getInt(1));
            bs.setIDBARANGSTOK(rs.getInt(2));
            bs.setKODEBATCH(rs.getString(3));
            bs.setEXPIRE(rs.getString(4));
            bs.setSTOK(rs.getInt(5));
            list.add(bs);
        }
        return list;
    }
    
    public static boolean updateBARANGSTOKBATCH(Connection con, Barangstokbatch b) throws SQLException {
        String sql = "SELECT * FROM BARANGSTOKBATCH WHERE id = ?";
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

        entry.updateInt("idbarangstok", b.getIDBARANGSTOK());
        if (b.getKODEBATCH() != null) {
            entry.updateString("kodebatch", b.getKODEBATCH());
        }
        if (b.getEXPIRE() != null) {
            entry.updateString("expire", b.getEXPIRE());
        }
        entry.updateInt("stok", b.getSTOK());

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
    public static void deleteFromBARANGSTOKBATCH(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM BARANGSTOKBATCH WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static void deleteFromBARANGSTOK(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM BARANGSTOKBATCH WHERE IDBARANGSTOK = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    

}
