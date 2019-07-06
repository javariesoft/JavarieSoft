/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class pelangganDao {

    Connection con;
    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

    public pelangganDao(Connection con) {
        this.con = con;
    }

    public boolean insert(pelanggan bb) throws SQLException {
        String sql = "insert into PELANGGAN values ("
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set values to prepared statement object by getting values from bean object
        pstmt.setString(1, bb.getKODEPELANGGAN());
        pstmt.setString(2, bb.getNAMA());
        pstmt.setString(3, bb.getALAMAT());
        pstmt.setString(4, bb.getHP());
        pstmt.setString(5, bb.getTGLREG());
        pstmt.setFloat(6, bb.getBATASKREDIT());
        pstmt.setString(7, bb.getKODEAKUN());
        pstmt.setString(8, bb.getNPWP());
        pstmt.setString(9, bb.getSTATUSCABANG());
        pstmt.setString(10, bb.getNAMAPEMILIK());
        pstmt.setInt(11, bb.getSTATUSAKTIF());
        pstmt.setString(12, bb.getPROPINSI());
        pstmt.setString(13, bb.getKABUPATEN());
        pstmt.setString(14, bb.getKECAMATAN());
        pstmt.setString(15, bb.getKELURAHAN());
        pstmt.setString(16, bb.getKODEPOS());
        pstmt.setString(17, bb.getRT());
        pstmt.setString(18, bb.getRW());
        pstmt.setString(19, bb.getNOMOR());
        pstmt.setString(20, bb.getBLOK());
        boolean i = pstmt.execute();
        return i;
    }

    public List getAlldetails() throws SQLException {

        PreparedStatement pstmt = con.prepareStatement("select * from PELANGGAN");
        ResultSet rs = pstmt.executeQuery();
        List list = new ArrayList();
        while (rs.next()) {
            pelanggan ubean = new pelanggan();
            ubean.setKODEPELANGGAN(rs.getString(1));
            ubean.setNAMA(rs.getString(2));
            ubean.setALAMAT(rs.getString(3));
            ubean.setHP(rs.getString(4));
            ubean.setTGLREG(rs.getString(5));
            ubean.setBATASKREDIT(rs.getFloat(6));
            ubean.setKODEAKUN(rs.getString(7));
            ubean.setNPWP(rs.getString(8));
            ubean.setSTATUSCABANG(rs.getString(9));
            ubean.setNAMAPEMILIK(rs.getString(10));
            ubean.setSTATUSAKTIF(rs.getInt(11));
            ubean.setPROPINSI(rs.getString("PROPINSI"));
            ubean.setKABUPATEN(rs.getString("KABUPATEN"));
            ubean.setKECAMATAN(rs.getString("KECAMATAN"));
            ubean.setKELURAHAN(rs.getString("KELURAHAN"));
            ubean.setKODEPOS(rs.getString("KODEPOS"));
            ubean.setRT(rs.getString("RT"));
            ubean.setRW(rs.getString("RW"));
            ubean.setNOMOR(rs.getString("NOMOR"));
            ubean.setBLOK(rs.getString("BLOK"));
            list.add(ubean);
        }
        rs.close();
        pstmt.close();

        return list;
    }

    public pelanggan getDetails(String id) throws SQLException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("select * from PELANGGAN where KODEPELANGGAN=?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        pelanggan ubean = new pelanggan();
        while (rs.next()) {
            ubean.setKODEPELANGGAN(rs.getString(1));
            ubean.setNAMA(rs.getString(2));
            ubean.setALAMAT(rs.getString(3));
            ubean.setHP(rs.getString(4));
            ubean.setTGLREG(rs.getString(5));
            ubean.setBATASKREDIT(rs.getFloat(6));
            ubean.setKODEAKUN(rs.getString(7));
            ubean.setNPWP(rs.getString(8));
            ubean.setSTATUSCABANG(rs.getString(9));
            ubean.setNAMAPEMILIK(rs.getString(10));
            ubean.setSTATUSAKTIF(rs.getInt(11));
            ubean.setPROPINSI(rs.getString("PROPINSI"));
            ubean.setKABUPATEN(rs.getString("KABUPATEN"));
            ubean.setKECAMATAN(rs.getString("KECAMATAN"));
            ubean.setKELURAHAN(rs.getString("KELURAHAN"));
            ubean.setKODEPOS(rs.getString("KODEPOS")); 
            ubean.setRT(rs.getString("RT"));
            ubean.setRW(rs.getString("RW"));
            ubean.setNOMOR(rs.getString("NOMOR"));
            ubean.setBLOK(rs.getString("BLOK"));
        }

        return ubean;
    }

    public boolean update(pelanggan bb) throws SQLException {
        String sql = "update PELANGGAN set NAMA=?,ALAMAT=?,HP=?,TGLREG=?,"
                + "BATASKREDIT=?,KODEAKUN=?,"
                + "NPWP=?,STATUSCABANG=?,NAMAPEMILIK=?,"
                + "STATUSAKTIF=?, PROPINSI=?, KABUPATEN=?, KECAMATAN=?,"
                + "KELURAHAN=?,KODEPOS=?, RT=?, RW=?, NOMOR=?, BLOK=? "
                + "where KODEPELANGGAN=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //set values to prepared statement object by getting values from bean object
        pstmt.setString(20, bb.getKODEPELANGGAN());
        pstmt.setString(1, bb.getNAMA());
        pstmt.setString(2, bb.getALAMAT());
        pstmt.setString(3, bb.getHP());
        pstmt.setString(4, bb.getTGLREG());
        pstmt.setFloat(5, bb.getBATASKREDIT());
        pstmt.setString(6, bb.getKODEAKUN());
        pstmt.setString(7, bb.getNPWP());
        pstmt.setString(8, bb.getSTATUSCABANG());
        pstmt.setString(9, bb.getNAMAPEMILIK());
        pstmt.setInt(10, bb.getSTATUSAKTIF());
        pstmt.setString(11, bb.getPROPINSI());
        pstmt.setString(12, bb.getKABUPATEN());
        pstmt.setString(13, bb.getKECAMATAN());
        pstmt.setString(14, bb.getKELURAHAN());
        pstmt.setString(15, bb.getKODEPOS());
        pstmt.setString(16, bb.getRT());
        pstmt.setString(17, bb.getRW());
        pstmt.setString(18, bb.getNOMOR());
        pstmt.setString(19, bb.getBLOK());
        boolean i = pstmt.execute();
        return i;
    }

    public void deleteDetails(String id) throws SQLException, ClassNotFoundException {
        //here we will write code to get a single record from database
        PreparedStatement pstmt = con.prepareStatement("delete from PELANGGAN where KODEPELANGGAN=?");
        pstmt.setString(1, id);
        pstmt.executeUpdate();
    }

    public static int getID(Connection con) throws SQLException {
        int hasil = 1;
        PreparedStatement pstmt = con.prepareStatement("select max(cast(KODEPELANGGAN as int)) from PELANGGAN");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getString(1) != null) {
                hasil = rs.getInt(1) + 1;
            }
        }
        return hasil;
    }
//     public static String getKode(Connection c,String kode){
//        String hasil="";
//        String[]h;
//           h=com.erv.function.Util.split(java.sql.Date.valueOf(com.erv.function.Util.toDateStringSql(new Date())).toString(), "-");
//            hasil=kode+h[1]+h[2];
//        return hasil;
//    }

    public List<pelanggan> getAllFilter(String where, int limit) throws SQLException {
        String sql = "select * from pelanggan where 1=1 ";
        if (where.length() > 0) {
            sql += "AND " + where;
        }
        sql += " limit " + limit;
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List list = new ArrayList();
        while (rs.next()) {
            pelanggan ubean = new pelanggan();
            ubean.setKODEPELANGGAN(rs.getString(1));
            ubean.setNAMA(rs.getString(2));
            list.add(ubean);
        }
        rs.close();
        pstmt.close();

        return list;
    }

    public void close() throws SQLException {
        con.close();
    }
}
