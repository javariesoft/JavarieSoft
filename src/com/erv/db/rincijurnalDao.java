/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.rincijurnal;
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
public class rincijurnalDao {

    public static boolean insertIntoRINCIJURNAL(Connection con, rincijurnal r) throws SQLException {
        String sql = "INSERT INTO RINCIJURNAL "
                + "VALUES (?, ?, ?, ?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, r.getKODEJURNAL());
        statement.setString(2, r.getKODEPERKIRAAN());
        statement.setDouble(3, r.getDEBET());
        statement.setDouble(4, r.getKREDIT());
        statement.setInt(5, r.getNO());
        statement.setString(6, r.getREF());
        boolean i = statement.execute();
        statement.close();
        return i;
    }

    public static void deleteFromRINCIJURNAL(Connection con, String kodejurnal,String kodeperkiraan) throws SQLException {
        String sql = "DELETE FROM RINCIJURNAL WHERE KODEJURNAL = ? and KODEPERKIRAAN=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, kodejurnal);
        statement.setString(2, kodeperkiraan);
        statement.executeUpdate();
        statement.close();
    }
    
    public static void deleteFromRINCIJURNAL(Connection con, String kodejurnal) throws SQLException {
        String sql = "DELETE FROM RINCIJURNAL WHERE KODEJURNAL = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, kodejurnal);
        statement.executeUpdate();
        statement.close();
    }
    
    public static List<rincijurnal> getRinciJurnal(Connection con, int kodejurnal) throws SQLException{
        String sql = "select * from rincijurnal where kodejurnal=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, kodejurnal);
        ResultSet rs = ps.executeQuery();
        List<rincijurnal> list=new ArrayList<>();
        rincijurnal r;
        while (rs.next()) {            
            r = new rincijurnal();
            r.setKODEJURNAL(rs.getString(1));
            r.setKODEPERKIRAAN(rs.getString(2));
            r.setDEBET(rs.getDouble(3));
            r.setKREDIT(rs.getDouble(4));
            r.setNO(rs.getInt(5));
            r.setREF(rs.getString(6));
            list.add(r);
        }
        rs.close();
        ps.close();
        return list;
    }
    
    public static boolean updateRINCIJURNAL(Connection con, rincijurnal r) throws SQLException {
        String sql = "update rincijurnal set debet=?, kredit=? "
                + "where kodejurnal=? and kodeperkiraan=? and no=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDouble(1, r.getDEBET());
        statement.setDouble(2, r.getKREDIT());
        statement.setString(3, r.getKODEJURNAL());
        statement.setString(4, r.getKODEPERKIRAAN());
        statement.setInt(5, r.getNO());
        boolean i = statement.execute();
        statement.close();
        return i;
    }
}
