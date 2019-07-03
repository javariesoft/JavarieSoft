/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.Returdo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.h2.api.Trigger;

/**
 *
 * @author TI-PNP
 */
public class ReturdoDao {

    public static int insertIntoRETURDO(Connection con, Returdo rd) throws SQLException {
        int generatedId = -1;
        String sql = "INSERT INTO RETURDO (kodereturdo, tanggal, kodepelanggan, keterangan, iddo, status"
                + ")"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, rd.getKodereturdo());
        statement.setString(2, rd.getTanggal());
        statement.setString(3, rd.getKodepelanggan());
        statement.setString(4, rd.getKeterangan());
        statement.setInt(5, rd.getIddo());
        statement.setInt(6, rd.getStatus());
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
     * @param kodereturdo
     * @param tanggal
     * @param kodepelanggan
     * @param keterangan
     * @param iddo
     * @param status
     * @return boolean (true on success)
     * @throws SQLException
     */
    public static boolean updateRETURDO(Connection con, Returdo rd) throws SQLException {
        String sql = "SELECT * FROM RETURDO WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, rd.getId());
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

        if (rd.getKodereturdo() != null) {
            entry.updateString("kodereturdo", rd.getKodereturdo());
        }
        if (rd.getTanggal() != null) {
            entry.updateString("tanggal", rd.getTanggal());
        }
        if (rd.getKodepelanggan() != null) {
            entry.updateString("kodepelanggan", rd.getKodepelanggan());
        }
        if (rd.getKeterangan() != null) {
            entry.updateString("keterangan", rd.getKeterangan());
        }
        entry.updateInt("iddo", rd.getIddo());
        entry.updateInt("status", rd.getStatus());

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
    public static void deleteFromRETURDO(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM RETURDO WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static Returdo getDetails(Connection c, int id) throws SQLException {
        String sql = "select * from returdo where id=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, id);
        Returdo r = null;
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            r = new Returdo();
            r.setId(rs.getInt(1));
            r.setKodereturdo(rs.getString(2));
            r.setTanggal(rs.getString(3));
            r.setKodepelanggan(rs.getString(4));
            r.setKeterangan(rs.getString(5));
            r.setIddo(rs.getInt(6));
            r.setStatus(rs.getInt(7));
            r.setReturdorincis(ReturdorinciDao.getReturdorinciList(c, id)); 
        }
        return r;
    }

    public static String getKodeReturDO(Connection c) throws SQLException {
        int jum = 1;
        String hasil = "";
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
        //String sql = "SELECT MAX(SUBSTR(KODERETURDO,-4)) FROM RETURDO where left(KODERETURDO,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        String sql = "select max(right(KODERETURDO,4)) from RETURDO "
                + "where substring(KODERETURDO,4,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        try {
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            //hasil = (com.erv.function.Util.getthn(tgl).substring(2, 4) + "." + new PrintfFormat("RD%04d").sprintf(jum));
            hasil = "RD." + com.erv.function.Util.getthn(tgl).substring(2, 4) + "" + new PrintfFormat("%04d").sprintf(jum);
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }

    public static class triggerReturDO implements Trigger {

        public void init(Connection conn, String string, String string1, String string2, boolean bln, int i) throws SQLException {

        }

        public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
            if (newRow != null && oldRow == null) {
            } else if (newRow == null && oldRow != null) {
                Statement stat = conn.createStatement();
                stat.execute("delete from returdorinci where idreturdo=" + oldRow[0] + "");
                stat.execute("delete from STOK where IDPENJUALAN=" + oldRow[0].toString() + " AND KODETRANS='E'");
                stat.close();
                System.out.println("Delete Retur DO, Retur DO Rinci dan Stok");
            }
        }

        public void close() throws SQLException {

        }

        public void remove() throws SQLException {

        }

    }

}
