/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.erv.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TI-PNP
 */
public class KontrolperiodeDao {
    public static boolean cekperiode(Connection c, String periode) {
        boolean hasil1 = false;
        try {
            //String periode = thn + "." + bln;
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from KONTROLPERIODE where PERIODE='" + periode + "' and STATUSSTOK='1'");
            
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    hasil1 = true;
                }
            }
            rs.close();
            s.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(KontrolperiodeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil1;
    }

    public static boolean cekperiodeAda(Connection c, String bul) {
        boolean ada = false;
        try {
            //String periode = thn + "." + bln;
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from KONTROLPERIODE where PERIODE='" + bul + "'");
            
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    ada = true;
                }
            }
            rs.close();
            s.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(KontrolperiodeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ada;
    }
}
