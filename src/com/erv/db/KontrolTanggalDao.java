/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.Kontroltanggal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ervan
 */
public class KontrolTanggalDao {

    public static int insertIntoKONTROLTANGGAL(Connection con, Kontroltanggal k) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO KONTROLTANGGAL (tanggal, status)"
                + "VALUES (?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, k.getTanggal());
        statement.setInt(2, k.getStatus());
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

    public static boolean updateKONTROLTANGGAL(Connection con, Kontroltanggal k) throws SQLException {
        String sql = "SELECT * FROM KONTROLTANGGAL WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, k.getId());
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

        if (k.getTanggal() != null) {
            entry.updateString("tanggal", k.getTanggal());
        }
        entry.updateInt("status", k.getStatus());

        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromKONTROLTANGGAL(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM KONTROLTANGGAL WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static boolean cekHarian(Connection con, String tgl) throws SQLException {
        String sql = "select * from kontroltanggal where tanggal='"+tgl+"' and status=0";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        boolean hasil = false;
        hasil = rs.next();
        rs.close();
        stat.close();
        return hasil;
    }
    
    public static List<Kontroltanggal> getKontroltanggals(Connection c, int bulan, int tahun) throws SQLException{
        String sql = "Select * from KONTROLTANGGAL where Month(tanggal)="+bulan+" AND Year(tanggal)="+tahun+"";
        List<Kontroltanggal> list = new ArrayList<Kontroltanggal>();
        Kontroltanggal kontroltanggal=null;
        Statement stat = c.createStatement();
        ResultSet rs= stat.executeQuery(sql);
        while(rs.next()){
            kontroltanggal = new Kontroltanggal();
            kontroltanggal.setId(rs.getInt(1));
            kontroltanggal.setTanggal(rs.getString(2));
            kontroltanggal.setStatus(rs.getInt(3));
            list.add(kontroltanggal);
        }
        return list;
    }
    
    public static Kontroltanggal getKontroltanggals(Connection c, String tanggal) throws SQLException{
        String sql = "Select * from KONTROLTANGGAL where tanggal='"+tanggal+"' and status=0";
        Kontroltanggal kontroltanggal=null;
        Statement stat = c.createStatement();
        ResultSet rs= stat.executeQuery(sql);
        while(rs.next()){
            kontroltanggal = new Kontroltanggal();
            kontroltanggal.setId(rs.getInt(1));
            kontroltanggal.setTanggal(rs.getString(2));
            kontroltanggal.setStatus(rs.getInt(3));
        }
        return kontroltanggal;
    }
    
    public static Kontroltanggal getKontroltanggalstatus(Connection c) throws SQLException{
        String sql = "Select * from KONTROLTANGGAL where status=0";
        Kontroltanggal kontroltanggal=null;
        Statement stat = c.createStatement();
        ResultSet rs= stat.executeQuery(sql);
        while(rs.next()){
            kontroltanggal = new Kontroltanggal();
            kontroltanggal.setId(rs.getInt(1));
            kontroltanggal.setTanggal(rs.getString(2));
            kontroltanggal.setStatus(rs.getInt(3));
        }
        return kontroltanggal;
    }
}
