/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.StokPeriode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class StokPeriodeDao {
    public static void insert(Connection con, StokPeriode stokPeriode) throws SQLException{
        String sql="insert into stokperiode values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, stokPeriode.getPeriode());
        ps.setString(2, stokPeriode.getKodebarang());
        ps.setInt(3, stokPeriode.getJumlah());
        ps.setDouble(4, stokPeriode.getCogs());
        ps.executeUpdate();
    }
    
    public static void update(Connection con, StokPeriode stokPeriode) throws SQLException{
        String sql="update stokperiode set jumlah=?, cogs=? where periode=? and kodebarang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, stokPeriode.getJumlah());
        ps.setDouble(2, stokPeriode.getCogs());
        ps.setString(3, stokPeriode.getPeriode());
        ps.setString(4, stokPeriode.getKodebarang());
        ps.executeUpdate();
    }
    
    public static void delete(Connection con, StokPeriode stokPeriode) throws SQLException{
        String sql="delete from stokperiode where periode=? and kodebarang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, stokPeriode.getPeriode());
        ps.setString(2, stokPeriode.getKodebarang());
        ps.executeUpdate();
    }
    
    public static StokPeriode getStokPeriode(Connection con, String kodebarang, String periode) throws SQLException {
        String sql = "select * from stokperiode where periode=? and kodebarang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, periode);
        ps.setString(2, kodebarang);
        ResultSet rs = ps.executeQuery();
        StokPeriode stokPeriode=null;
        if (rs.next()) {
            stokPeriode = new StokPeriode();
            stokPeriode.setPeriode(rs.getString(1));
            stokPeriode.setKodebarang(rs.getString(2));
            stokPeriode.setJumlah(rs.getInt(3));
            stokPeriode.setCogs(rs.getDouble(4));
        }
        return stokPeriode;
    }
    
    public static List<StokPeriode> getStokPeriode(Connection con, String periode) throws SQLException {
        String sql = "select * from stokperiode where periode=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, periode);
        ResultSet rs = ps.executeQuery();
        StokPeriode stokPeriode=null;
        List<StokPeriode> stokPeriodes=new ArrayList<>();
        while (rs.next()) {
            stokPeriode = new StokPeriode();
            stokPeriode.setPeriode(rs.getString(1));
            stokPeriode.setKodebarang(rs.getString(2));
            stokPeriode.setJumlah(rs.getInt(3));
            stokPeriode.setCogs(rs.getDouble(4));
            stokPeriodes.add(stokPeriode);
        }
        return stokPeriodes;
    }
    
    
}
