/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.jurnal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.h2.api.Trigger;

/**
 *
 * @author erwadi
 */
public class jurnalDao {

    public static boolean insertIntoJURNAL(Connection con, jurnal j) throws SQLException {
        String sql = "INSERT INTO JURNAL (ID,KODEJURNAL, "
                + "TANGGAL, DESKRIPSI)"
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, j.getID());
        statement.setString(2, j.getKODEJURNAL());
        //statement.setDate(3, j.getTANGGAL());
        statement.setString(3, j.getTANGGAL());
        statement.setString(4, j.getDESKRIPSI());
        boolean i = statement.execute();
        statement.close();
        return i;
    }

    public static boolean updateJURNAL(Connection con, int keyId, jurnal j) throws SQLException {
        String sql = "SELECT * FROM JURNAL WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
        if (j.getKODEJURNAL() != null) {
            entry.updateString("KODEJURNAL", j.getKODEJURNAL());
        }
        if (j.getTANGGAL() != null) {
            //entry.updateDate("TANGGAL", j.getTANGGAL());
            entry.updateString("TANGGAL", j.getTANGGAL());
        }
        if (j.getDESKRIPSI() != null) {
            entry.updateString("DESKRIPSI", j.getDESKRIPSI());
        }
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromJURNAL(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM JURNAL WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public static void deleteJURNAL(Connection con, String keyId) throws SQLException {
        String sql = "DELETE FROM JURNAL WHERE KODEJURNAL = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, keyId);
        statement.executeUpdate();
        statement.close();
    }

    public boolean CekJurnal(Connection conn, String keyId) throws SQLException {
        boolean hasil = false;
        PreparedStatement pstmt = conn.prepareStatement("select KODEJURNAL from JURNAL WHERE KODEJURNAL = ?");
        pstmt.setString(1, keyId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = true;
            }
        }
        rs.close();
        pstmt.close();
        return hasil;
    }

    public boolean CekJurnalHutangBayar(Connection conn, String keyId) throws SQLException {
        boolean hasil = false;
        PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT J.KODEJURNAL  FROM JURNAL J, HUTANGBAYAR HB WHERE J.KODEJURNAL =HB.REF and J.KODEJURNAL =?");
        pstmt.setString(1, keyId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = true;
            }
        }
        rs.close();
        pstmt.close();
        return hasil;
    }

    public boolean CekJurnalPiutangBayar(Connection conn, String keyId) throws SQLException {
        boolean hasil = false;
        PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT J.KODEJURNAL  FROM JURNAL J, PIUTANGBAYAR PB WHERE J.KODEJURNAL =PB.REF and J.KODEJURNAL =?");
        pstmt.setString(1, keyId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = true;
            }
        }
        rs.close();
        pstmt.close();
        return hasil;
    }

    public jurnal getDataJurnal(Connection conn, String keyId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("select ID, KODEJURNAL, TANGGAL, DESKRIPSI from JURNAL WHERE KODEJURNAL = ?");
        statement.setString(1, keyId);
        ResultSet rs = statement.executeQuery();
        jurnal bean = new jurnal();
        while (rs.next()) {
            bean.setID(rs.getInt("ID"));
            bean.setKODEJURNAL(rs.getString("KODEJURNAL"));
            //bean.setTANGGAL(rs.getDate("TANGGAL"));
            bean.setTANGGAL(rs.getString("TANGGAL"));
            bean.setDESKRIPSI(rs.getString("DESKRIPSI"));
            // Process data here
        }
        rs.close();
        statement.close();
        return bean;
    }

    public jurnal getJurnal(Connection conn, String keyId) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("select ID, KODEJURNAL, TANGGAL, DESKRIPSI from JURNAL");
        ResultSet rs = statement.executeQuery();
        jurnal bean = new jurnal();
        while (rs.next()) {
            bean.setID(rs.getInt("ID"));
            bean.setKODEJURNAL(rs.getString("KODEJURNAL"));
            //bean.setTANGGAL(rs.getDate("TANGGAL"));
            bean.setTANGGAL(rs.getString("TANGGAL"));
            bean.setDESKRIPSI(rs.getString("DESKRIPSI"));
            // Process data here
        }
        rs.close();
        statement.close();
        return bean;
    }

    public static jurnal getJurnalKode(Connection conn, String KodeJurnal) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("Select * from JURNAL where kodejurnal=?");
        statement.setString(1, KodeJurnal);
        ResultSet rs = statement.executeQuery();
        jurnal bean = new jurnal();
        while (rs.next()) {
            bean.setID(rs.getInt("ID"));
            bean.setKODEJURNAL(rs.getString("KODEJURNAL"));
            //bean.setTANGGAL(rs.getDate("TANGGAL"));
            bean.setTANGGAL(rs.getString("TANGGAL"));
            bean.setDESKRIPSI(rs.getString("DESKRIPSI"));
            bean.setRincijurnals(rincijurnalDao.getRinciJurnal(conn, bean.getID()));
            // Process data here
        }
        rs.close();
        statement.close();
        return bean;
    }

    public static jurnal getJurnalKode(Connection conn, int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("Select * from JURNAL where id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        jurnal bean = new jurnal();
        while (rs.next()) {
            bean.setID(rs.getInt("ID"));
            bean.setKODEJURNAL(rs.getString("KODEJURNAL"));
            //bean.setTANGGAL(rs.getDate("TANGGAL"));
            bean.setTANGGAL(rs.getString("TANGGAL"));
            bean.setDESKRIPSI(rs.getString("DESKRIPSI"));
            bean.setRincijurnals(rincijurnalDao.getRinciJurnal(conn, bean.getID()));
            // Process data here
        }
        rs.close();
        statement.close();
        return bean;
    }

    
    public static int getIDJurnal(Connection con) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = con.prepareStatement("select max(ID) from JURNAL");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = (rs.getInt(1) + 1);
            }
        }
        rs.close();
        pstmt.close();
        return hasil;
    }

    public static String getGenKodeJurnal(Connection con, String kodeDepan) {
        String hasil = "";
        String tgl = com.erv.function.Util.toDateStringSql(new Date());
        int jum = 1;
//         String sql = "select max(right(kodejurnal,4)) from jurnal "
//                + "where substring(kodejurnal,4,2)='" + Util.getbln(tgl) + "' "
//                + "and substring(kodejurnal,6,2)='" + Util.getthn(tgl).substring(2, 4) + "' and left(kodejurnal,"+kodeDepan.length()+")='"+kodeDepan+"'";
         
        String sql = "select max(right(kodejurnal,4)) from jurnal "
                + "where substring(kodejurnal,4,2)='" + Util.getthn(tgl).substring(2, 4) + "' and left(kodejurnal,"+kodeDepan.length()+")='"+kodeDepan+"'";
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                if(rs.getString(1)!=null){
                    jum = rs.getInt(1)+1;
                }
            } 
            hasil = kodeDepan + "." + com.erv.function.Util.getthn(tgl).substring(2,4) + "" + new PrintfFormat("%04d").sprintf(jum);
            rs.close();
            stat.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }

    public static boolean getJurnalTanggal(Connection con, String akun, String tgl, String tgl1) throws SQLException {
        String sql = "select count(*) from jurnal inner join RINCIJURNAL on jurnal.ID = RINCIJURNAL.KODEJURNAL\n"
                + "where kodeperkiraan = '" + akun + "' and jurnal.tanggal >= '" + tgl + "' and jurnal.tanggal<= '"+ tgl1 +"'";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        boolean hasil = false;
        int jumlah = 0;
        if(rs.next()){
            jumlah = rs.getInt(1);
        }
        if(jumlah > 0){
            hasil = true;
        }
        stat.close();
        rs.close();
        return hasil;

    }
    
    public static jurnal getJurnal(Connection con, String tanggal, String kodebarang) throws SQLException {
        String sql = "select * from jurnal j "
                + "where tanggal=? and right(DESKRIPSI,5)=?  order by id";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tanggal);
        ps.setString(2, kodebarang);
        ResultSet rs = ps.executeQuery();
        jurnal j=null;
        if (rs.next()) {
            j = new jurnal();
            j.setID(rs.getInt("ID"));
            j.setDESKRIPSI(rs.getString("DESKRIPSI"));
            j.setKODEJURNAL(rs.getString("KODEJURNAL"));
            j.setTANGGAL(rs.getString("TANGGAL"));
            j.setRincijurnals(rincijurnalDao.getRinciJurnal(con, j.getID()));
        }
        return j;
    }
    
    public static List<jurnal> getJurnalList(Connection con, String tanggal, String kodebarang) throws SQLException {
        String sql = "select * from jurnal j "
                + "where tanggal=? and right(DESKRIPSI,5)=?  order by id";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tanggal);
        ps.setString(2, kodebarang);
        ResultSet rs = ps.executeQuery();
        jurnal j=null;
        List<jurnal> list = new ArrayList<>();
        while (rs.next()) {
            j = new jurnal();
            j.setID(rs.getInt("ID"));
            j.setDESKRIPSI(rs.getString("DESKRIPSI"));
            j.setKODEJURNAL(rs.getString("KODEJURNAL"));
            j.setTANGGAL(rs.getString("TANGGAL"));
            j.setRincijurnals(rincijurnalDao.getRinciJurnal(con, j.getID()));
            list.add(j);
        }
        return list;
    }


    public static class triggerJurnal implements Trigger {

        public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before, int type) {
            // initialize the trigger object is necessary
        }

        /**
         * This method is called for each triggered action.
         *
         * @param conn a connection to the database
         * @param oldRow the old row, or null if no old row is available (for
         * INSERT)
         * @param newRow the new row, or null if no new row is available (for
         * DELETE)
         * @throws SQLException if the operation must be undone
         */
        public void fire(Connection conn,
                Object[] oldRow, Object[] newRow)
                throws SQLException {
            if (newRow != null && oldRow == null) {
            } else if (newRow == null && oldRow != null) {
                Statement stat = conn.createStatement();
                stat.execute("delete from RINCIJURNAL where KODEJURNAL=" + oldRow[0] + "");
                System.out.println("Delete Rinci Jurnal");
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
