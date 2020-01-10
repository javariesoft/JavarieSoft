/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.returbeli;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.h2.api.Trigger;

/**
 *
 * @author erwadi
 */
public class returbeliDao {

    public static boolean insertIntoRETURBELI(Connection con, returbeli r) throws SQLException {
        String sql = "INSERT INTO RETURBELI "
                + "VALUES (?, ?, ?, ?, ?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, r.getID());
        statement.setString(2, r.getKODERETURBELI());
        statement.setString(3, r.getTANGGAL());
        statement.setString(4, r.getIDSUPPLIER());
        statement.setString(5, r.getKETERANGAN());
        statement.setInt(6, r.getIDPEMBELIAN());
        statement.setDouble(7, r.getTOTALRETUR());
        statement.setDouble(8, r.getTOTALDISKON());
        statement.setDouble(9, r.getTOTALPPN());        
        statement.setInt(10, r.getSTATUS());
        
        boolean h = statement.execute();
        statement.close();
        return !h;
    }
    
    public static returbeli getReturBeli(Connection c, int id) throws SQLException{
        String sql= "select * from returbeli where ID=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, id);
        returbeli rb = null;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            rb = new returbeli();
            rb.setID(rs.getInt("ID"));
            rb.setKODERETURBELI(rs.getString("KODERETURBELI"));
            rb.setTANGGAL(rs.getString("TANGGAL"));
            rb.setIDSUPPLIER(rs.getString("IDSUPPLIER"));
            rb.setKETERANGAN(rs.getString("KETERANGAN"));
            rb.setIDPEMBELIAN(rs.getInt("IDPEMBELIAN"));
            rb.setTOTALRETUR(rs.getDouble("TOTALRETUR"));
            rb.setTOTALDISKON(rs.getDouble("TOTALDISKON"));
            rb.setTOTALPPN(rs.getDouble("TOTALPPN"));
            rb.setSTATUS(rs.getInt("STATUS")); 
            rb.setRincireturList(returbelirinciDao.getreturbelirinci(c, rb.getID()));
            rb.setJurnal(jurnalDao.getJurnalKode(c, rb.getKODERETURBELI()));
        }
        return rb;
    }

    public static boolean updateRETURBELI(Connection con, returbeli r) throws SQLException {
        String sql = "SELECT * FROM RETURBELI WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.setInt(1, r.getID());
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

        if (r.getKODERETURBELI() != null) {
            entry.updateString("KODERETURBELI", r.getKODERETURBELI());
        }
        if (r.getTANGGAL() != null) {
            entry.updateString("TANGGAL", r.getTANGGAL());
        }
        if (r.getIDSUPPLIER() != null) {
            entry.updateString("IDSUPPLIER", r.getIDSUPPLIER());
        }
        if (r.getKETERANGAN() != null) {
            entry.updateString("KETERANGAN", r.getKETERANGAN());
        }
        entry.updateDouble("TOTALRETUR", r.getTOTALRETUR());
        entry.updateDouble("TOTALDISKON", r.getTOTALDISKON());
        entry.updateDouble("TOTALPPN", r.getTOTALPPN());
        entry.updateInt("STATUS", r.getSTATUS());
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromRETURBELI(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM RETURBELI WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
     public static int getID(Connection c) throws SQLException{
        int hasil=1;
        PreparedStatement pstmt = c.prepareStatement("select max(id) from RETURBELI");
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            if(rs.getString(1)!=null){
                hasil=rs.getInt(1)+1;
            }
        }
        return hasil;
    }
     
     public static String setReturBeli(Connection conn) {
        String hasil = "";
        long jum = 1;
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
//        String sql = "select max(right(kodereturbeli,4)) from returbeli "
//                + "where substring(kodereturbeli,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(kodereturbeli,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'";
        
        String sql = "select max(right(kodereturbeli,4)) from returbeli "
                + "where substring(kodereturbeli,3,3)='." + Util.getthn(tgl).substring(2, 4) + "'";
        try {
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            hasil ="RB."+ com.erv.function.Util.getthn(tgl).substring(2, 4) + "" + new PrintfFormat("%04d").sprintf(jum);
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }
     
     public static class triggerReturBeli implements Trigger {

        public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) {
            // initialize the trigger object is necessary
        }

        /**
         * This method is called for each triggered action.
         *
         * @param conn a connection to the database
         * @param oldRow the old row, or null if no old row is available (for INSERT)
         * @param newRow the new row, or null if no new row is available (for DELETE)
         * @throws SQLException if the operation must be undone
         */
        public void fire(Connection conn,
                Object[] oldRow, Object[] newRow)
                throws SQLException {
            if (newRow != null && oldRow == null) {
            }else if (newRow == null && oldRow != null) {
                 Statement stat=conn.createStatement();
                 stat.execute("delete from RETURBELIRINCI where IDRETURBELI="+oldRow[0].toString()+"");
                 stat.execute("delete from STOK where IDPENJUALAN="+oldRow[0].toString()+" AND KODETRANS='K'");
                 stat.execute("delete from jurnal where kodejurnal='"+oldRow[1].toString()+"'"); 
                 stat.execute("delete from hutangbayar where REF='"+oldRow[1].toString()+"'"); 
                 stat.close();
                 System.out.println("Delete Retur Beli Rinci,Stok,Jurnal,Hutang");
            }
        }

        public void close() {
            // ignore
        }

        public void remove() {
            // ignore
        }
    }
}
