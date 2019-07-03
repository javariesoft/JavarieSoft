/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.Satuan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TI-PNP
 */
public class SatuanDao {

    public static void insertIntoSATUAN(Connection con, Satuan s) throws SQLException {
        String sql = "INSERT INTO SATUAN (KODE, SATUAN)"
                + "VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, s.getKODE());
        statement.setString(2, s.getSATUAN());
        statement.execute();
        statement.close();
    }

    /**
     * Java method that updates a row in the generated sql table
     *
     * @param con (open java.sql.Connection)
     * @param KODE
     * @param SATUAN
     * @return boolean (true on success)
     * @throws SQLException
     */
    public static void updateSATUAN(Connection con, String keyId, Satuan s) throws SQLException {
        String sql = "SELECT * FROM SATUAN WHERE KODE = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setString(1, keyId);
        ResultSet entry = statement.executeQuery();

        entry.last();
        int rows = entry.getRow();
        entry.beforeFirst();
        if (rows == 0) {
            entry.close();
            statement.close();
        }
        entry.next();

        if (s.getSATUAN() != null) {
            entry.updateString("SATUAN", s.getSATUAN());
        }

        entry.updateRow();
        entry.close();
        statement.close();
    }

    /**
     * Java method that deletes a row from the generated sql table
     *
     * @param con (open java.sql.Connection)
     * @param keyId (the primary key to the row)
     * @throws SQLException
     */
    public static void deleteFromSATUAN(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM SATUAN WHERE KODE = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static Satuan getDetails(Connection con, String id)throws SQLException
    {
       //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from SATUAN where KODE=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        Satuan ubean = new Satuan();
        while(rs.next())
        {
            ubean.setKODE(rs.getString(1));
            ubean.setSATUAN(rs.getString(2));
        }
        rs.close();
        pstmt.close();
        return ubean;
    }
    
    public static List<Satuan> getAllDetails(Connection con)throws SQLException
    {
       //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from SATUAN");
        ResultSet rs = pstmt.executeQuery();
        Satuan ubean = null;
        List<Satuan> list = new ArrayList<Satuan>();
        while(rs.next())
        {
            ubean = new Satuan();
            ubean.setKODE(rs.getString(1));
            ubean.setSATUAN(rs.getString(2));
            list.add(ubean);
        }
        rs.close();
        pstmt.close();
        return list;
    }
}
