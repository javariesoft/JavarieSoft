/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.exception.GiroException;
import com.erv.function.PrintfFormat;
import com.erv.function.Util;
import com.erv.model.Giro;
import com.erv.model.bank;
import com.erv.model.jurnal;
import com.erv.model.pelanggan;
import com.erv.model.piutang;
import com.erv.model.piutangbayar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ervan
 */
public class GiroDao {

    private final Connection connection;
    private final String Insert = "INSERT INTO GIRO (NOMORGIRO,\n"
            + "                  TGLGIRO,\n"
            + "                  TGLJTEMPO,\n"
            + "                  JUMLAH,\n"
            + "                  NAMAPENERIMA,\n"
            + "                  STATUS,\n"
            + "                  KODEPELANGGAN,\n"
            + "                  BANKASAL,\n"
            + "                  IDBANK,"
            + "                  CREATE_AT,"
            + "                  UPDATE_AT,"
            + "                  TGLCAIR) values("
            + "?,?,?,?,?,"
            + "?,?,?,?,?,"
            + "?,?)";
    private final String delete = "delete from giro where ID=?";
    private final String getByID = "select * from giro where ID=?";
    private final String selectAll = "select * from giro where id<>0";

    public GiroDao() {
        this.connection = null;
    }

    public GiroDao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Giro c) throws GiroException {
        PreparedStatement statement = null;
        try {
            //connection.createStatement().execute("set autocommit false");
            statement = connection.prepareStatement(Insert);
            statement.setString(1, c.getNOMORGIRO());
            statement.setString(2, c.getTGLGIRO());
            statement.setString(3, c.getTGLJTEMPO());
            statement.setDouble(4, c.getJUMLAH());
            statement.setString(5, c.getNAMAPENERIMA());
            statement.setInt(6, c.getSTATUS());
            statement.setString(7, c.getKODEPELANGGAN());
            statement.setString(8, c.getBANKASAL());
            statement.setInt(9, c.getIDBANK());
            statement.setTimestamp(10, new Timestamp(new Date().getTime()));
            statement.setTimestamp(11, new Timestamp(new Date().getTime()));
            statement.setString(12, c.getTGLCAIR());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                c.setID(result.getInt(1));
            }
            //connection.commit();
        } catch (SQLException ex) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex1) {
//            }
            throw new GiroException(ex.getMessage());
        } finally {
//            try {
//                connection.createStatement().execute("set autocommit true");
//            } catch (SQLException ex) {
//            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public void update(Giro c) throws GiroException {
        String Update = "UPDATE GIRO SET \n"
            + "    NOMORGIRO = ?,\n"
            + "    TGLGIRO = ?,\n"
            + "    TGLJTEMPO = ?,\n"
            + "    JUMLAH = ?,\n"
            + "    NAMAPENERIMA = ?,\n"
            + "    STATUS = ?,\n"
            + "    KODEPELANGGAN = ?,\n"
            + "    BANKASAL = ?,\n"
            + "    IDBANK = ?,"
            + "    UPDATE_AT = ?, "
            + "    TGLCAIR = ? "
            + "where ID=?;";
        PreparedStatement statement = null;
        try {
            //connection.createStatement().execute("set autocommit false");
            statement = connection.prepareStatement(Update);
            statement.setString(1, c.getNOMORGIRO());
            statement.setString(2, c.getTGLGIRO());
            statement.setString(3, c.getTGLJTEMPO());
            statement.setDouble(4, c.getJUMLAH());
            statement.setString(5, c.getNAMAPENERIMA());
            statement.setInt(6, c.getSTATUS());
            statement.setString(7, c.getKODEPELANGGAN());
            statement.setString(8, c.getBANKASAL());
            statement.setInt(9, c.getIDBANK());
            statement.setTimestamp(10, new Timestamp(new Date().getTime()));
            statement.setString(11, c.getTGLCAIR());
            statement.setInt(12, c.getID());
            statement.executeUpdate();
            //connection.commit();
        } catch (SQLException ex) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex1) {
//            }
            throw new GiroException(ex.getMessage());
        } finally {
//            try {
//                connection.createStatement().execute("set autocommit true");
//            } catch (SQLException ex) {
//            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public void delete(int id) throws GiroException {
        PreparedStatement statement = null;
        try {
            connection.createStatement().execute("set autocommit false");
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
            throw new GiroException(ex.getMessage());
        } finally {
            try {
                connection.createStatement().execute("set autocommit true");
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public Giro getGiro(int id) throws GiroException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getByID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Giro giro = null;
            pelanggan plg = null;
            bank bk = null;
            if (rs.next()) {
                giro = new Giro();
                giro.setID(rs.getInt(1));
                giro.setNOMORGIRO(rs.getString(2));
                giro.setTGLGIRO(rs.getString(3));
                giro.setTGLJTEMPO(rs.getString(4));
                giro.setJUMLAH(rs.getDouble(5));
                giro.setNAMAPENERIMA(rs.getString(6));
                giro.setSTATUS(rs.getInt(7));
                giro.setKODEPELANGGAN(rs.getString(8));
                giro.setBANKASAL(rs.getString(9));
                giro.setIDBANK(rs.getInt(10));
                plg = new pelangganDao(connection).getDetails(rs.getString(8));
                bk = bankDao.getDetails(connection, rs.getInt(10));
                giro.setPlg(plg);
                giro.setBk(bk);
                giro.setCREATE_AT(rs.getTimestamp("CREATE_AT"));
                giro.setUPDATE_AT(rs.getTimestamp("UPDATE_AT"));
                giro.setTGLCAIR(rs.getString("TGLCAIR"));
                
            } else {
                throw new GiroException("Giro dengan Kode " + id + " tidak ditemukan");
            }
            return giro;
        } catch (SQLException e) {
            throw new GiroException(e.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new GiroException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public List<Giro> selectAll(String kriteria) throws GiroException {
        PreparedStatement statement = null;
        List<Giro> list = new ArrayList<Giro>();
        String sql = "select * from giro, pelanggan where giro.kodepelanggan=pelanggan.kodepelanggan ";
        if (!kriteria.trim().equals("")) {
            sql += kriteria;
        }
        try {
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            Giro giro = null;
            pelanggan plg = null;
            bank bk = null;
            while (rs.next()) {
                giro = new Giro();
                giro.setID(rs.getInt(1));
                giro.setNOMORGIRO(rs.getString(2));
                giro.setTGLGIRO(rs.getString(3));
                giro.setTGLJTEMPO(rs.getString(4));
                giro.setJUMLAH(rs.getDouble(5));
                giro.setNAMAPENERIMA(rs.getString(6));
                giro.setSTATUS(rs.getInt(7));
                giro.setKODEPELANGGAN(rs.getString(8));
                giro.setBANKASAL(rs.getString(9));
                giro.setIDBANK(rs.getInt(10));
                plg = new pelangganDao(connection).getDetails(rs.getString(8));
                bk = bankDao.getDetails(connection, rs.getInt(10));
                giro.setPlg(plg);
                giro.setBk(bk);
                list.add(giro);
                giro.setCREATE_AT(rs.getTimestamp("CREATE_AT"));
                giro.setUPDATE_AT(rs.getTimestamp("UPDATE_AT"));
                giro.setTGLCAIR(rs.getString("TGLCAIR"));
            }
            return list;
        } catch (SQLException e) {
            throw new GiroException(e.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new GiroException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    public String getKodeGiro() {
        String hasil = "";
        int jum = 1;
        String tgl = com.erv.function.Util.toDateStringSql(new java.util.Date());
        try {
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery("select max(right(NOMORGIRO,4)) from GIRO "
                    + "where substring(NOMORGIRO,4,2)='" + Util.getbln(tgl) + "' "
                    + "and substring(NOMORGIRO,6,2)='" + Util.getthn(tgl).substring(2, 4) + "'");
            if (rs.next()) {
                if (rs.getString(1) != null) {
                    jum = rs.getInt(1) + 1;
                }
            }
            //GR.04160001
            hasil = "GM." + Util.getbln(tgl) + "" + com.erv.function.Util.getthn(tgl).substring(2, 4) + new PrintfFormat("%04d").sprintf(jum);
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return hasil;
    }

    public void angsuranPiutang(Giro giro, String tanggal) throws GiroException {
        Statement stat = null;
        Statement s = null;
        ResultSet rsPiutang = null;
        try {
            String sql = "SELECT ID,KETERANGAN,NOFAKTUR,JUMLAH,JUMLAH - JUMLAHBAYAR as SISA,JATUHTEMPO,STATUS from VIEW_PIUTANG where IDPELANGGAN='" + giro.getKODEPELANGGAN() + "' AND STATUS='BELUM LUNAS' ORDER BY NOFAKTUR";
            connection.createStatement().execute("set autocommit false");
            stat = connection.createStatement();
            s = connection.createStatement();
            rsPiutang = stat.executeQuery(sql);
            double totalGiro = giro.getJUMLAH();
            piutangbayar pb;
            jurnal jn;
            boolean st = false;
            while (rsPiutang.next()) {
                st = true;
                if (totalGiro != 0) {
                    if (totalGiro >= rsPiutang.getDouble(5)) {
                        pb = new piutangbayar();
                        pb.setID(piutangbayarDao.getID(connection));
                        pb.setIDPIUTANG(rsPiutang.getInt(1));
                        pb.setJUMLAH(rsPiutang.getDouble(5));
                        pb.setKODEPIUTANGBAYAR(piutangbayarDao.getKodePiutangBayar(connection));
                        pb.setREF(giro.getNOMORGIRO());
                        pb.setTANGGAL(tanggal);
                        piutangbayarDao.insertIntoPIUTANGBAYAR(connection, pb);
                        totalGiro -= rsPiutang.getDouble(5);
                        piutang p = piutangDao.getDetails(connection, rsPiutang.getInt(1));
                        p.setSTATUS("0");
                        piutangDao.updatePIUTANG(connection, p);
                    } else {
                        pb = new piutangbayar();
                        pb.setID(piutangbayarDao.getID(connection));
                        pb.setIDPIUTANG(rsPiutang.getInt(1));
                        pb.setJUMLAH(totalGiro);
                        pb.setKODEPIUTANGBAYAR(piutangbayarDao.getKodePiutangBayar(connection));
                        pb.setREF(giro.getNOMORGIRO());
                        pb.setTANGGAL(tanggal);
                        piutangbayarDao.insertIntoPIUTANGBAYAR(connection, pb);
                        totalGiro = 0;
                        break;
//                    jn = new jurnal();
//                    jn.setID(jurnalDao.getIDJurnal(connection));
//                    jn.setKODEJURNAL(pb.getKODEPIUTANGBAYAR());
//                    jn.setTANGGAL(Util.toDateStringSql(new Date()));
//                    jn.setDESKRIPSI("Terima Piutang Dari " + new pelangganDao(connection).getDetails(giro.getKODEPELANGGAN()).getNAMA() + " Via Giro No " + giro.getNOMORGIRO());
//                    jurnalDao.insertIntoJURNAL(connection, jn);
//                    s.execute("insert into RINCIJURNAL values(" + jn.getID() + ",'" + bankDao.getDetails(connection, giro.getIDBANK()).getKODEAKUN() + "'," + totalGiro + ",0,1)");
//                    s.execute("insert into RINCIJURNAL values(" + jn.getID() + ",'" + new pelangganDao(connection).getDetails(giro.getKODEPELANGGAN()).getKODEAKUN() + "',0," + totalGiro + ",2)");
                    }
                }
            }
            if (st) {
                jn = new jurnal();
                jn.setID(jurnalDao.getIDJurnal(connection));
                jn.setKODEJURNAL(giro.getNOMORGIRO());
                jn.setTANGGAL(tanggal);
                jn.setDESKRIPSI("Terima Piutang Dari " + new pelangganDao(connection).getDetails(giro.getKODEPELANGGAN()).getNAMA() + " Via Giro No " + giro.getNOMORGIRO());
                jurnalDao.insertIntoJURNAL(connection, jn);
                s.execute("insert into RINCIJURNAL values(" + jn.getID() + ",'" + settingDao.getAkun(connection, "PIUTANGGIRO") + "'," + giro.getJUMLAH() + ",0,1,'')");
                s.execute("insert into RINCIJURNAL values(" + jn.getID() + ",'" + new pelangganDao(connection).getDetails(giro.getKODEPELANGGAN()).getKODEAKUN() + "',0," + giro.getJUMLAH() + ",2,'')");
                giro.setSTATUS(1);
                update(giro);
                connection.commit();
            } else {
                throw new GiroException("Piutang Tidak Ada");
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
                throw new GiroException("Rollback " + ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(GiroDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            throw new GiroException(ex.getMessage());
        } finally {
            if (rsPiutang != null) {
                try {
                    rsPiutang.close();
                } catch (SQLException ex) {
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                }
            }

        }
    }

    public void angsuranPiutang1(Giro giro, String tanggal) throws GiroException {
        Statement stat = null;
        Statement s = null;
        ResultSet rsPiutang = null;
        try {
            String sql = "SELECT ID,KETERANGAN,NOFAKTUR,JUMLAH,JUMLAH - JUMLAHBAYAR as SISA,JATUHTEMPO,STATUS from VIEW_PIUTANG where IDPELANGGAN='" + giro.getKODEPELANGGAN() + "' AND STATUS='BELUM LUNAS' ORDER BY NOFAKTUR";
            connection.createStatement().execute("set autocommit false");
            stat = connection.createStatement();
            s = connection.createStatement();
            rsPiutang = stat.executeQuery(sql);
            double totalGiro = giro.getJUMLAH();
            piutangbayar pb;
            jurnal jn;
            boolean st = false;
            while (rsPiutang.next()) {
                st = true;
                if (totalGiro != 0) {
                    if (totalGiro >= rsPiutang.getDouble(5)) {
                        pb = new piutangbayar();
                        pb.setID(piutangbayarDao.getID(connection));
                        pb.setIDPIUTANG(rsPiutang.getInt(1));
                        pb.setJUMLAH(rsPiutang.getDouble(5));
                        pb.setKODEPIUTANGBAYAR(piutangbayarDao.getKodePiutangBayar(connection));
                        pb.setREF(giro.getNOMORGIRO());
                        pb.setTANGGAL(tanggal);
                        piutangbayarDao.insertIntoPIUTANGBAYAR(connection, pb);
                        totalGiro -= rsPiutang.getDouble(5);
                        piutang p = piutangDao.getDetails(connection, rsPiutang.getInt(1));
                        p.setSTATUS("0");
                        piutangDao.updatePIUTANG(connection, p);
                    } else {
                        pb = new piutangbayar();
                        pb.setID(piutangbayarDao.getID(connection));
                        pb.setIDPIUTANG(rsPiutang.getInt(1));
                        pb.setJUMLAH(totalGiro);
                        pb.setKODEPIUTANGBAYAR(piutangbayarDao.getKodePiutangBayar(connection));
                        pb.setREF(giro.getNOMORGIRO());
                        pb.setTANGGAL(tanggal);
                        piutangbayarDao.insertIntoPIUTANGBAYAR(connection, pb);
                        totalGiro = 0;
                        break;
//                    jn = new jurnal();
//                    jn.setID(jurnalDao.getIDJurnal(connection));
//                    jn.setKODEJURNAL(pb.getKODEPIUTANGBAYAR());
//                    jn.setTANGGAL(Util.toDateStringSql(new Date()));
//                    jn.setDESKRIPSI("Terima Piutang Dari " + new pelangganDao(connection).getDetails(giro.getKODEPELANGGAN()).getNAMA() + " Via Giro No " + giro.getNOMORGIRO());
//                    jurnalDao.insertIntoJURNAL(connection, jn);
//                    s.execute("insert into RINCIJURNAL values(" + jn.getID() + ",'" + bankDao.getDetails(connection, giro.getIDBANK()).getKODEAKUN() + "'," + totalGiro + ",0,1)");
//                    s.execute("insert into RINCIJURNAL values(" + jn.getID() + ",'" + new pelangganDao(connection).getDetails(giro.getKODEPELANGGAN()).getKODEAKUN() + "',0," + totalGiro + ",2)");
                    }
                }
            }
            if (st) {
                jn = new jurnal();
                jn.setID(jurnalDao.getIDJurnal(connection));
                jn.setKODEJURNAL(giro.getNOMORGIRO());
                jn.setTANGGAL(tanggal);
                jn.setDESKRIPSI("Terima Piutang Dari " + new pelangganDao(connection).getDetails(giro.getKODEPELANGGAN()).getNAMA() + " Via Giro No " + giro.getNOMORGIRO());
                jurnalDao.insertIntoJURNAL(connection, jn);
                s.execute("insert into RINCIJURNAL values(" + jn.getID() + ",'" + settingDao.getAkun(connection, "PIUTANGGIRO") + "'," + giro.getJUMLAH() + ",0,1,'')");
                s.execute("insert into RINCIJURNAL values(" + jn.getID() + ",'" + new pelangganDao(connection).getDetails(giro.getKODEPELANGGAN()).getKODEAKUN() + "',0," + giro.getJUMLAH() + ",2,'')");
                giro.setSTATUS(1);
                update(giro);
                connection.commit();
            } 
        } catch (SQLException ex) {
            try {
                connection.rollback();
                throw new GiroException("Rollback " + ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(GiroDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            throw new GiroException(ex.getMessage());
        } finally {
            if (rsPiutang != null) {
                try {
                    rsPiutang.close();
                } catch (SQLException ex) {
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                }
            }

        }
    }

    
    public void close() throws SQLException {
        connection.close();
    }
}
