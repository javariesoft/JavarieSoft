/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.PoRinci;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class PoRinciDao {

    /**
     * Java method that inserts a row in the generated sql table and returns the
     * new generated id
     *
     * @param con (open java.sql.Connection)
     * @param idpo
     * @param iddo
     * @param status
     * @return id (database row id [id])
     * @throws SQLException
     */
    public static int insertIntoPORinci(Connection con, int idpo, int iddo, int status) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO PORINCI (idpo, iddo, status)"
                + "VALUES (?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, idpo);
        statement.setInt(2, iddo);
        statement.setInt(3, status);
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
     * @param idpo
     * @param iddo
     * @param status
     * @return boolean (true on success)
     * @throws SQLException
     */
    public static boolean updatePORinci(Connection con, int keyId, int idpo, int iddo, int status) throws SQLException {
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

        entry.updateInt("idpo", idpo);
        entry.updateInt("iddo", iddo);
        entry.updateInt("status", status);

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
    public static void deleteFromPORinci(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM PO WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static List<PoRinci> getAll(Connection con, int idpo) throws SQLException{
        String sql="select * from porinci where idpo=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idpo);
        ResultSet rs = ps.executeQuery();
        List<PoRinci> poRincis = new ArrayList<>();
        PoRinci poRinci;
        while(rs.next()){
            poRinci = new PoRinci();
            poRinci.setId(rs.getInt("ID"));
            poRinci.setIdpo(rs.getInt("IDPO"));
            poRinci.setIddo(rs.getInt("IDDO"));
            poRinci.setStatus(rs.getInt("STATUS"));
            poRincis.add(poRinci);
        }
        rs.close();
        ps.close();
        return poRincis;
    }

}
