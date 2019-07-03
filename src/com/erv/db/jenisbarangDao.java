/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.jenisbarang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class jenisbarangDao {

    public static boolean insertIntoJENISBARANG(Connection con, jenisbarang j) throws SQLException {
        String sql = "INSERT INTO JENISBARANG (ID, "
                + "JENIS)"
                + "VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, j.getID());
        statement.setString(2, j.getJENIS());
        boolean i = statement.execute();
        statement.close();
        return !i;
    }

    public static boolean updateJENISBARANG(Connection con, String keyId, jenisbarang j) throws SQLException {
        String sql = "SELECT * FROM JENISBARANG WHERE ID = ?";
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
            return false;
        }
        entry.next();

        if (j.getJENIS() != null) {
            entry.updateString("JENIS", j.getJENIS());
        }
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromJENISBARANG(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM JENISBARANG WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static jenisbarang getDetails(Connection con, String id) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from JENISBARANG where ID=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        jenisbarang ubean = new jenisbarang();
        while (rs.next()) {
            ubean.setID(rs.getString(1));
            ubean.setJENIS(rs.getString(2));
        }
        rs.close();
        pstmt.close();
        return ubean;
    }

    public static List<jenisbarang> getAllDetails(Connection con) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from JENISBARANG");
        ResultSet rs = pstmt.executeQuery();
        jenisbarang ubean = null;
        List<jenisbarang> list = new ArrayList<>();
        while (rs.next()) {
            ubean = new jenisbarang();
            ubean.setID(rs.getString(1));
            ubean.setJENIS(rs.getString(2));
            list.add(ubean);
        }
        rs.close();
        pstmt.close();
        return list;
    }

    public static int getID(Connection con) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = con.prepareStatement("select max(cast(ID as int)) from JENISBARANG");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        rs.close();
        pstmt.close();
        return hasil;
    }
}
