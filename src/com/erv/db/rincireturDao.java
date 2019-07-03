/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.model.rinciretur;
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
public class rincireturDao {

    public static boolean insertIntoRINCIRETUR(Connection con, rinciretur r)
            throws SQLException {
        String sql = "INSERT INTO RETURRINCI "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, r.getID());
        statement.setInt(2, r.getIDRETUR());
        statement.setString(3, r.getKODEBARANG());
        statement.setInt(4, r.getJUMLAH());
        statement.setFloat(5, r.getHARGA());
        statement.setFloat(6, r.getTOTAL());
        statement.setFloat(7, r.getDISKON());
        statement.setFloat(8, r.getPPN());
        statement.setInt(9, r.getIDDO());
        statement.setString(10, r.getSATUAN());
        statement.setInt(11, r.getJUMLAHKECIL());
        statement.setDouble(12, r.getCOGS());
        statement.setString(13, r.getKODEBATCH());
        statement.setString(14, r.getEXPIRE());
        boolean h = statement.execute();
        statement.close();
        return !h;
    }

    public static boolean updateRINCIRETUR(Connection con, rinciretur r)
            throws SQLException {
        String sql = "SELECT * FROM RETURRINCI WHERE ID = ?";
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
        entry.updateInt("IDRETUR", r.getIDRETUR());

        if (r.getKODEBARANG() != null) {
            entry.updateString("KODEBARANG", r.getKODEBARANG());
        }
        entry.updateInt("JUMLAH", r.getJUMLAH());
        entry.updateFloat("HARGA", r.getHARGA());
        entry.updateFloat("TOTAL", r.getTOTAL());
        entry.updateFloat("DISKON", r.getDISKON());
        entry.updateFloat("PPN", r.getPPN());
        entry.updateInt("IDDO",r.getIDDO());
        entry.updateString("SATUAN",r.getSATUAN());
        entry.updateInt("JUMLAHKECIL",r.getJUMLAHKECIL());
        entry.updateDouble("COGS",r.getCOGS());
        entry.updateString("KODEBATCH",r.getKODEBATCH());
        entry.updateString("EXPIRE",r.getEXPIRE());
        entry.updateRow();
        entry.close();
        statement.close();
        return true;
    }

    public static void deleteFromRINCIRETUR(Connection con, int keyId) throws SQLException {
        String sql = "DELETE FROM RETURRINCI WHERE ID = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, keyId);
        statement.executeUpdate();
        statement.close();
    }
    
    public static List<rinciretur> getRinciRetur(Connection con, int idretur) throws SQLException{
        String sql="select * from returrinci where idretur=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idretur);
        ResultSet rs = ps.executeQuery();
        List<rinciretur> list = new ArrayList<>();
        rinciretur r;
        while(rs.next()){
            r = new rinciretur();
            r.setID(rs.getInt("ID"));
            r.setIDRETUR(rs.getInt("IDRETUR"));
            r.setKODEBARANG(rs.getString("KODEBARANG"));
            r.setJUMLAH(rs.getInt("JUMLAH"));
            r.setHARGA(rs.getFloat("HARGA"));
            r.setTOTAL(rs.getFloat("TOTAL"));
            r.setDISKON(rs.getFloat("DISKON"));
            r.setPPN(rs.getFloat("PPN"));
            r.setIDDO(rs.getInt("IDDO"));
            r.setSATUAN(rs.getString("SATUAN"));
            r.setJUMLAHKECIL(rs.getInt("JUMLAHKECIL"));
            r.setCOGS(rs.getFloat("COGS"));
            r.setKODEBATCH(rs.getString("KODEBATCH"));
            r.setEXPIRE(rs.getString("EXPIRE"));
            r.setBarangstok(BarangstokDao.getDetailKodeBarang(con, r.getKODEBARANG()));
            list.add(r);
        }
        rs.close();
        ps.close();
        return list;
    }
    
    
    public static int getID(Connection c) throws SQLException{
        int hasil=1;
        PreparedStatement pstmt = c.prepareStatement("select max(id) from RETURRINCI");
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            if(rs.getString(1)!=null){
                hasil=rs.getInt(1)+1;
            }
        }
        return hasil;
    }
}
