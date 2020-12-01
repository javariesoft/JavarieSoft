/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.fungsi;

import com.erv.db.koneksi;
import com.erv.function.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erwadi
 */
public class Fungsi {

    public static double getTotalSaldo(Connection conn, int bulan, int tahun, int grup) {
        double hasil = 0;
        try {
            Statement stat = conn.createStatement();
            String sql = "SELECT "
                    + "NERACABANTU.KODEPERKIRAAN AS NERACABANTU_KODEPERKIRAAN,"
                    + "NERACABANTU.NAMAPERKIRAAN AS NERACABANTU_NAMAPERKIRAAN, "
                    + "NERACABANTU.GRUP AS NERACABANTU_GRUP, "
                    + "NERACABANTU.TIPE AS NERACABANTU_TIPE, "
                    + "GRUP.grup AS NAMAGRUP, "
                    + "IFNULL( SELECT sum(SALDOPERIODE.SALDO) FROM \"PUBLIC\".\"SALDOPERIODE\" "
                    + "SALDOPERIODE WHERE LEFT(SALDOPERIODE.PERIODE,4) = (CASEWHEN(" + bulan + " - 1=0," + tahun + " -1," + tahun + ")) "
                    + "AND SUBSTR(SALDOPERIODE.PERIODE,6,2) = (CASEWHEN(" + bulan + " - 1=0,12," + (bulan - 1) + ")) "
                    + "AND SALDOPERIODE.KODEAKUN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 ) AS SALDOAWAL, "
                    + "IFNULL( SELECT sum(RINCIJURNAL.\"DEBET\") FROM \"PUBLIC\".\"RINCIJURNAL\" RINCIJURNAL "
                    + "INNER JOIN \"PUBLIC\".\"JURNAL\" JURNAL ON RINCIJURNAL.\"KODEJURNAL\" = JURNAL.\"ID\" "
                    + "WHERE MONTH(JURNAL.TANGGAL) = " + bulan + " AND YEAR(JURNAL.TANGGAL) = " + tahun + " "
                    + "AND RINCIJURNAL.KODEPERKIRAAN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 ) AS DEBET, "
                    + "IFNULL( SELECT sum(RINCIJURNAL.KREDIT) FROM JURNAL INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                    + "WHERE MONTH(JURNAL.TANGGAL) = " + bulan + " AND YEAR(JURNAL.TANGGAL) = " + tahun + " "
                    + "AND RINCIJURNAL.KODEPERKIRAAN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 ) AS KREDIT, "
                    + "ifnull( "
                    + "CASE NERACABANTU.GRUP ";
            if (grup == 1 || grup == 6 || grup == 5) {
                sql
                        += "WHEN " + grup + " THEN "
                        + "IFNULL( SELECT sum(SALDOPERIODE.SALDO) FROM SALDOPERIODE "
                        + "WHERE LEFT(SALDOPERIODE.PERIODE,4) = (CASEWHEN(" + bulan + " - 1=0," + tahun + "-1," + tahun + ")) "
                        + "AND SUBSTR(SALDOPERIODE.PERIODE,6,2) = (CASEWHEN(" + bulan + " - 1=0,12," + (bulan - 1) + ")) "
                        + "AND SALDOPERIODE.KODEAKUN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 )+ "
                        + "IFNULL( SELECT sum(RINCIJURNAL.DEBET) "
                        + "FROM RINCIJURNAL INNER JOIN JURNAL ON RINCIJURNAL.KODEJURNAL = JURNAL.ID "
                        + "WHERE MONTH(JURNAL.TANGGAL) = " + bulan + " "
                        + "AND YEAR(JURNAL.TANGGAL) = " + tahun + " "
                        + "AND RINCIJURNAL.KODEPERKIRAAN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 )- "
                        + "IFNULL( SELECT sum(RINCIJURNAL.KREDIT) FROM JURNAL "
                        + "INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                        + "WHERE MONTH(JURNAL.TANGGAL) = " + bulan + " "
                        + "AND YEAR(JURNAL.TANGGAL) = " + tahun + " "
                        + "AND RINCIJURNAL.KODEPERKIRAAN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 ) ";
            } else {
                sql
                        += "WHEN " + grup + " THEN "
                        + "IFNULL( SELECT sum(SALDOPERIODE.SALDO) FROM SALDOPERIODE "
                        + "WHERE LEFT(SALDOPERIODE.PERIODE,4) = (CASEWHEN(" + bulan + " - 1=0," + tahun + "-1," + tahun + ")) "
                        + "AND SUBSTR(SALDOPERIODE.PERIODE,6,2) = (CASEWHEN(" + bulan + " - 1=0,12," + (bulan - 1) + ")) "
                        + "AND SALDOPERIODE.KODEAKUN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 )- "
                        + "IFNULL( SELECT sum(RINCIJURNAL.DEBET) "
                        + "FROM RINCIJURNAL INNER JOIN JURNAL ON RINCIJURNAL.KODEJURNAL = JURNAL.ID "
                        + "WHERE MONTH(JURNAL.TANGGAL) = " + bulan + " "
                        + "AND YEAR(JURNAL.TANGGAL) = " + tahun + " "
                        + "AND RINCIJURNAL.KODEPERKIRAAN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 )+ "
                        + "IFNULL( SELECT sum(RINCIJURNAL.KREDIT) FROM JURNAL "
                        + "INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                        + "WHERE MONTH(JURNAL.TANGGAL) = " + bulan + " "
                        + "AND YEAR(JURNAL.TANGGAL) = " + tahun + " "
                        + "AND RINCIJURNAL.KODEPERKIRAAN LIKE CONCAT(NERACABANTU.KODEPERKIRAAN,'%'),0 ) ";
            }
            sql
                    += "END,0) as SALDO "
                    + "FROM "
                    + "NERACABANTU INNER JOIN GRUP ON (NERACABANTU.GRUP=GRUP.KODE) "
                    + "WHERE NERACABANTU.GRUP=" + grup + ""
                    + "ORDER BY NERACABANTU.KODEPERKIRAAN";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                hasil += rs.getDouble(9);
            }
            rs.close();
            stat.close();
        } catch (Exception ex) {
            Logger.getLogger(Fungsi.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }

        return hasil;
    }

    public static java.util.Date dateAdd(Connection conn, String jenis, int jumlah, Date tglmulai) {
        java.util.Date hasil = null;
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select  extract (YEAR FROM dateadd('" + jenis + "'," + jumlah + ",'" + tglmulai + "'))  as tahun,  extract (MONTH FROM dateadd('" + jenis + "'," + jumlah + ",'" + tglmulai + "'))  as bulan, extract (DAY FROM dateadd('" + jenis + "'," + jumlah + ",'" + tglmulai + "'))  as tanggal");
            while (rs.next()) {
                hasil = com.erv.function.Util.getDate(rs.getInt(3), rs.getInt(2), rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fungsi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hasil;
    }

    public static String bilang(long uang) {
        String nama[] = {"Nol", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan"};
        String besar[] = {"Triliun", "Milyar", "Juta", "Ribu", ""};
        if (uang == 0) {
            return nama[0];
        }
        long p = 1000000000000l;
        String hasil = "";
        for (int i = 0; i < besar.length; i++, p /= 1000) {
            if (uang < p) {
                continue;
            }
            long temp = uang / p;
            boolean seribu = p == 1000;
            if (temp >= 100) {
                hasil += nama[(int) temp / 100] + " Ratus ";
                temp %= 100;
                seribu = false;
            }
            if (temp >= 11 && temp <= 19) {
                hasil += nama[(int) temp - 10] + " Belas ";
                temp = 0;
                seribu = false;
            }
            if (temp >= 10) {
                hasil += nama[(int) temp / 10] + " Puluh ";
                temp %= 10;
            }
            if (temp > 0) {
                if (seribu && temp == 1) {
                    hasil += "Se";
                } else {
                    hasil += nama[(int) temp] + " ";
                }
            }
            uang %= p;
            hasil += besar[i] + " ";
        }
        hasil = hasil.replaceAll("Satu Ratus", "Seratus");
        hasil = hasil.replaceAll("Satu Belas", "Sebelas");
        hasil = hasil.replaceAll("Satu Puluh", "Sepuluh");
        return hasil.trim();
    }

    public static String bilangDouble(double uangnya) {
        long uang = 0;
        uang = Math.round(uangnya);
        String nama[] = {"Nol", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan"};
        String besar[] = {"Triliun", "Milyar", "Juta", "Ribu", ""};
        if (uang == 0) {
            return nama[0];
        }
        long p = 1000000000000l;
        String hasil = "";
        for (int i = 0; i < besar.length; i++, p /= 1000) {
            if (uang < p) {
                continue;
            }
            long temp = uang / p;
            boolean seribu = p == 1000;
            if (temp >= 100) {
                hasil += nama[(int) temp / 100] + " Ratus ";
                temp %= 100;
                seribu = false;
            }
            if (temp >= 11 && temp <= 19) {
                hasil += nama[(int) temp - 10] + " Belas ";
                temp = 0;
                seribu = false;
            }
            if (temp >= 10) {
                hasil += nama[(int) temp / 10] + " Puluh ";
                temp %= 10;
            }
            if (temp > 0) {
                if (seribu && temp == 1) {
                    hasil += "Se";
                } else {
                    hasil += nama[(int) temp] + " ";
                }
            }
            uang %= p;
            hasil += besar[i] + " ";
        }
        hasil = hasil.replaceAll("Satu Ratus", "Seratus");
        hasil = hasil.replaceAll("Satu Belas", "Sebelas");
        hasil = hasil.replaceAll("Satu Puluh", "Sepuluh");
        return hasil.trim();
    }

    public static double getDebet(Connection conn, String kodeakun, int bulan, int tahun) {
        double hasil = 0;
        String sql = "SELECT sum(RINCIJURNAL.DEBET) AS DEBET "
                + "FROM JURNAL INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                + "WHERE RINCIJURNAL.KODEPERKIRAAN LIKE '" + kodeakun + "%' AND MONTH(JURNAL.TANGGAL) = " + bulan + " AND YEAR(JURNAL.TANGGAL) = " + tahun + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return hasil;
    }

    public static double getDebet(Connection conn, String kodeakun, int tahun) {
        double hasil = 0;
        String sql = "SELECT sum(RINCIJURNAL.DEBET) AS DEBET "
                + "FROM JURNAL INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                + "WHERE RINCIJURNAL.KODEPERKIRAAN LIKE '" + kodeakun + "%' AND  YEAR(JURNAL.TANGGAL) = " + tahun + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return hasil;
    }

    
    public static double getDebetTanggal(Connection conn, String kodeakun, String tanggal) {
        double hasil = 0;
        String t[] = Util.split(tanggal, "-");
        String tglawal = t[0] + "-" + t[1] + "-1";

        String sql = "SELECT sum(RINCIJURNAL.DEBET) AS DEBET "
                + "FROM JURNAL INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                + "WHERE RINCIJURNAL.KODEPERKIRAAN LIKE '" + kodeakun + "%' AND (JURNAL.TANGGAL) >= '" + tglawal + "' AND (JURNAL.TANGGAL) <=  DATEADD('DAY',-1,DATE '" + tanggal + "')";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return hasil;
    }

    public static double getKredit(Connection conn, String kodeakun, int bulan, int tahun) {
        double hasil = 0;
        String sql = "SELECT sum(RINCIJURNAL.KREDIT) AS KREDIT "
                + "FROM JURNAL INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                + "WHERE RINCIJURNAL.KODEPERKIRAAN LIKE '" + kodeakun + "%' AND MONTH(JURNAL.TANGGAL) = " + bulan + " AND YEAR(JURNAL.TANGGAL) = " + tahun + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }

    public static double getKredit(Connection conn, String kodeakun, int tahun) {
        double hasil = 0;
        String sql = "SELECT sum(RINCIJURNAL.KREDIT) AS KREDIT "
                + "FROM JURNAL INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                + "WHERE RINCIJURNAL.KODEPERKIRAAN LIKE '" + kodeakun + "%' AND YEAR(JURNAL.TANGGAL) = " + tahun + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }

    
    public static double getKreditTanggal(Connection conn, String kodeakun, String tanggal) {
        double hasil = 0;
        String t[] = Util.split(tanggal, "-");
        String tglawal = t[0] + "-" + t[1] + "-1";
        String sql = "SELECT sum(RINCIJURNAL.KREDIT) AS KREDIT "
                + "FROM JURNAL INNER JOIN RINCIJURNAL ON JURNAL.ID = RINCIJURNAL.KODEJURNAL "
                + "WHERE RINCIJURNAL.KODEPERKIRAAN LIKE '" + kodeakun + "%' AND (JURNAL.TANGGAL) >= '" + tglawal + "' AND (JURNAL.TANGGAL) <= DATEADD('DAY',-1,DATE '" + tanggal + "')";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return hasil;
    }

    public static double getSaldo(Connection conn, String kodeakun, int bulan, int tahun) {
        double hasil = 0;
        String sql = "SELECT sum(SALDO) FROM SALDOPERIODE WHERE KODEAKUN like '" + kodeakun + "%' AND PERIODE = '" + (tahun + "." + bulan) + "'";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }
    
    public static double getSaldoAwalKasPenjualan(Connection conn, String kodeakun, String tanggal, boolean pajak) {
        double hasil = 0;
        String sql="";
        if(pajak){
            sql = "select ifnull((sum(debet)-sum(kredit)),0) as saldo from JURNAL j inner join RINCIJURNAL rj on j.id = rj.KODEJURNAL "
                + "where j.KODEJURNAL in (select faktur from penjualan where ppn<>0) and rj.KODEPERKIRAAN like '" + kodeakun + "%'  "
                + "and tanggal < '"+tanggal+"'"
                + "";
        }else{
            sql = "select ifnull((sum(debet)-sum(kredit)),0) as saldo from JURNAL j inner join RINCIJURNAL rj on j.id = rj.KODEJURNAL "
                + "where j.KODEJURNAL in (select faktur from penjualan where ppn=0) and rj.KODEPERKIRAAN like '" + kodeakun + "%'  "
                + "and tanggal < '"+tanggal+"'"
                + "";
        }
        
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }

    public static double getSaldoAwalKasPiutang(Connection conn, String kodeakun, String tanggal, boolean pajak) {
        double hasil = 0;
        String sql="";
        if(pajak){
            sql = "select "
                    + "    sum(debet) - sum (kredit) as saldo "
                    + "from "
                    + "    JURNAL j  "
                    + "inner join "
                    + "    RINCIJURNAL rj  "
                    + "        on j.id = rj.KODEJURNAL  "
                    + "where "
                    + "    ( "
                    + "        j.KODEJURNAL in ( "
                    + "            select "
                    + "                KODEPIUTANGBAYAR  "
                    + "            from "
                    + "                PIUTANGBAYAR pb, "
                    + "                PIUTANG pt, "
                    + "                penjualan jual  "
                    + "            where "
                    + "                pt.id = pb.idpiutang  "
                    + "                and pt.idpenjualan = jual.id  "
                    + "                and ppn <> 0  "
                    + "        ) "
                    + "        OR ( "
                    + "            j.KODEJURNAL in ( "
                    + "                select "
                    + "                    REF  "
                    + "                from "
                    + "                    PIUTANGBAYAR pb, "
                    + "                    PIUTANG pt, "
                    + "                    penjualan jual  "
                    + "                where "
                    + "                    pt.id = pb.idpiutang  "
                    + "                    and pt.idpenjualan = jual.id  "
                    + "                    and ppn <> 0  "
                    + "            ) "
                    + "        ) "
                    + "    ) "
                    + "    and rj.KODEPERKIRAAN like '11110' "
                    + "    and tanggal < '"+tanggal+"' "
                    + "";
        } else {
            sql = "select "
                    + "    sum(debet) - sum (kredit) as saldo "
                    + "from "
                    + "    JURNAL j  "
                    + "inner join "
                    + "    RINCIJURNAL rj  "
                    + "        on j.id = rj.KODEJURNAL  "
                    + "where "
                    + "    ( "
                    + "        j.KODEJURNAL in ( "
                    + "            select "
                    + "                KODEPIUTANGBAYAR  "
                    + "            from "
                    + "                PIUTANGBAYAR pb, "
                    + "                PIUTANG pt, "
                    + "                penjualan jual  "
                    + "            where "
                    + "                pt.id = pb.idpiutang  "
                    + "                and pt.idpenjualan = jual.id  "
                    + "                and ppn = 0  "
                    + "        ) "
                    + "        OR ( "
                    + "            j.KODEJURNAL in ( "
                    + "                select "
                    + "                    REF  "
                    + "                from "
                    + "                    PIUTANGBAYAR pb, "
                    + "                    PIUTANG pt, "
                    + "                    penjualan jual  "
                    + "                where "
                    + "                    pt.id = pb.idpiutang  "
                    + "                    and pt.idpenjualan = jual.id  "
                    + "                    and ppn = 0  "
                    + "            ) "
                    + "        ) "
                    + "        OR ( "
                    + "            j.KODEJURNAL in ( "
                    + "                select "
                    + "                    KODEPIUTANGBAYAR  "
                    + "                from "
                    + "                    PIUTANGBAYAR pb, "
                    + "                    PIUTANG pt, "
                    + "                    penjualan jual  "
                    + "                where "
                    + "                    pt.id = pb.idpiutang  "
                    + "                    and pt.idpenjualan = 0 "
                    + "            ) "
                    + "        ) "
                    + "        OR ( "
                    + "            j.KODEJURNAL in ( "
                    + "                select "
                    + "                    REF  "
                    + "                from "
                    + "                    PIUTANGBAYAR pb, "
                    + "                    PIUTANG pt, "
                    + "                    penjualan jual  "
                    + "                where "
                    + "                    pt.id = pb.idpiutang  "
                    + "                    and pt.idpenjualan = 0 "
                    + "            ) "
                    + "        ) "
                    + "    ) "
                    + "    and rj.KODEPERKIRAAN like '11110' "
                    + "    and tanggal < '"+tanggal+"' "
                    + "";
        }

        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }

    public static double getSaldoAwalKasPembelian(Connection conn, String kodeakun, String tanggal, boolean pajak) {
        double hasil = 0;
        String sql="";
        if(pajak){
            sql = "select ifnull(sum(debet) - sum(kredit),0) as saldo from JURNAL j inner join RINCIJURNAL rj on j.id = rj.KODEJURNAL "
                    + "where j.KODEJURNAL in ( "
                    + "select NOFAKTUR from PEMBELIAN beli where pajak<>0 "
                    + ")  "
                    + "and rj.KODEPERKIRAAN like '"+kodeakun+"%' "
                    + "and tanggal < '"+ tanggal +"'"
                    + "";
        }else{
            sql = "select ifnull(sum(debet) - sum(kredit),0) as saldo from JURNAL j inner join RINCIJURNAL rj on j.id = rj.KODEJURNAL "
                    + "where j.KODEJURNAL in ( "
                    + "select NOFAKTUR from PEMBELIAN beli where pajak=0 "
                    + ")  "
                    + "and rj.KODEPERKIRAAN like '"+kodeakun+"%' "
                    + "and tanggal < '"+ tanggal +"'"
                    + "";
        }
        
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }

    public static double getSaldoAwalKasHutang(Connection conn, String kodeakun, String tanggal, boolean pajak) {
        double hasil = 0;
        String sql="";
        if(pajak){
            sql = "select "
                    + "   sum (debet) - sum (kredit) as saldo "
                    + "from "
                    + "    JURNAL j "
                    + "inner join "
                    + "    RINCIJURNAL rj "
                    + "        on j.id = rj.KODEJURNAL "
                    + "where "
                    + "    ( "
                    + "        j.KODEJURNAL in ( "
                    + "            select "
                    + "                KODEHUTANGBAYAR "
                    + "            from "
                    + "                HUTANGBAYAR hb, "
                    + "                HUTANG h, "
                    + "                PEMBELIAN beli "
                    + "            where "
                    + "                h.id = hb.IDHUTANG "
                    + "                and h.IDPEMBELIAN = beli.id "
                    + "                and pajak <> 0 "
                    + "        ) "
                    + "        OR ( "
                    + "            j.KODEJURNAL in ( "
                    + "                select "
                    + "                    REF "
                    + "                from "
                    + "                    HUTANGBAYAR hb, "
                    + "                    HUTANG h, "
                    + "                    PEMBELIAN beli "
                    + "                where "
                    + "                    h.id = hb.IDHUTANG "
                    + "                    and h.IDPEMBELIAN = beli.id "
                    + "                    and pajak <> 0 "
                    + "            ) "
                    + "        ) "
                    + "    ) "
                    + "    and rj.KODEPERKIRAAN like '11110' "
                    + "   and tanggal < '"+tanggal+"' "
                    + "";
        } else {
            sql = "select "
                    + "   sum (debet) - sum (kredit) as saldo "
                    + "from "
                    + "    JURNAL j "
                    + "inner join "
                    + "    RINCIJURNAL rj "
                    + "        on j.id = rj.KODEJURNAL "
                    + "where "
                    + "    ( "
                    + "        j.KODEJURNAL in ( "
                    + "            select "
                    + "                KODEHUTANGBAYAR "
                    + "            from "
                    + "                HUTANGBAYAR hb, "
                    + "                HUTANG h, "
                    + "                PEMBELIAN beli "
                    + "            where "
                    + "                h.id = hb.IDHUTANG "
                    + "                and h.IDPEMBELIAN = beli.id "
                    + "                and pajak = 0 "
                    + "        ) "
                    + "        OR ( "
                    + "            j.KODEJURNAL in ( "
                    + "                select "
                    + "                    REF "
                    + "                from "
                    + "                    HUTANGBAYAR hb, "
                    + "                    HUTANG h, "
                    + "                    PEMBELIAN beli "
                    + "                where "
                    + "                    h.id = hb.IDHUTANG "
                    + "                    and h.IDPEMBELIAN = beli.id "
                    + "                    and pajak = 0 "
                    + "            ) "
                    + "        ) "
                    + "        OR ( "
                    + "            j.KODEJURNAL in ( "
                    + "                select "
                    + "                    KODEHUTANGBAYAR "
                    + "                from "
                    + "                    HUTANGBAYAR hb, "
                    + "                    HUTANG h, "
                    + "                    PEMBELIAN beli "
                    + "                where "
                    + "                    h.id = hb.IDHUTANG "
                    + "                    and h.IDPEMBELIAN = 0 "
                    + "            ) "
                    + "        ) "
                    + "        OR ( "
                    + "            j.KODEJURNAL in ( "
                    + "                select "
                    + "                    REF "
                    + "                from "
                    + "                    HUTANGBAYAR hb, "
                    + "                    HUTANG h, "
                    + "                    PEMBELIAN beli "
                    + "                where "
                    + "                    h.id = hb.IDHUTANG "
                    + "                    and h.IDPEMBELIAN = 0 "
                    + "            ) "
                    + "        ) "
                    + "    ) "
                    + "    and rj.KODEPERKIRAAN like '11110' "
                    + "   and tanggal < '"+tanggal+"' "
                    + "";
        }

        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }

    public static double getLaba(Connection conn, int bulan, int tahun) {
        double hasil = 0.0;
        try {
            Statement stat = conn.createStatement();
            String sql = "select (getKredit('4'," + bulan + "," + tahun + ") - getDebet('4'," + bulan + "," + tahun + ") ) \n"
                    + "- ( getDebet('5'," + bulan + "," + tahun + ") - getKredit('5'," + bulan + "," + tahun + ") )\n"
                    + "- ( getDebet('6'," + bulan + "," + tahun + ") - getKredit('6'," + bulan + "," + tahun + ") )";
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fungsi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public static double getLaba(Connection conn, int tahun) {
        double hasil = 0.0;
        try {
            Statement stat = conn.createStatement();
            String sql = "select (getKredit('4'," + tahun + ") - getDebet('4'," + tahun + ") ) \n"
                    + "- ( getDebet('5'," + tahun + ") - getKredit('5'," + tahun + ") )\n"
                    + "- ( getDebet('6'," + tahun + ") - getKredit('6'," + tahun + ") )";
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fungsi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    
    public static double getPembelian(Connection conn, int bulan, int tahun) {
        double hasil = 0;
        String sql = "SELECT\n"
                + "     sum(RINCIPEMBELIAN.\"TOTAL\")\n"
                + "FROM\n"
                + "     \"PUBLIC\".\"PEMBELIAN\" PEMBELIAN INNER JOIN \"PUBLIC\".\"RINCIPEMBELIAN\" RINCIPEMBELIAN ON PEMBELIAN.\"ID\" = RINCIPEMBELIAN.\"IDPEMBELIAN\"\n"
                + "WHERE\n"
                + "     month(pembelian.tanggal) = " + bulan + "\n"
                + " AND year(pembelian.tanggal) = " + tahun + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }

    public static double getPembelianNoPPN(Connection conn, int bulan, int tahun) {
        double hasil = 0;
        String sql = "SELECT"
                + "     sum(RINCIPEMBELIAN.TOTAL - RINCIPEMBELIAN.PPN) "
                + "FROM"
                + "     PEMBELIAN INNER JOIN RINCIPEMBELIAN ON PEMBELIAN.ID = RINCIPEMBELIAN.IDPEMBELIAN "
                + "WHERE"
                + "     month(PEMBELIAN.tanggal) = " + bulan + ""
                + " AND year(PEMBELIAN.tanggal) = " + tahun + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                hasil = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return hasil;
    }
 
    public static double getPenjualan(Connection conn, int bulan, int tahun) {
        double totjual = 0, totongkir = 0;
        String sql = "select sum(jumlah * harga - rp.diskon) from penjualan p inner join RINCIPENJUALAN rp "
                + "on rp.IDPENJUALAN = p.id "
                + "where month(tanggal) = " + bulan + " and year(tanggal)=" + tahun + " "
                + "";
        String sql2 = "select sum(ongkoskirim) from penjualan p "
                + "where month(tanggal) = " + bulan + " and year(tanggal)=" + tahun + " "
                + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                totjual = rs.getDouble(1);
            }
            rs.close();
            rs = stat.executeQuery(sql2);
            if (rs.next()) {
                totongkir = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return totjual + totongkir;
    }

    public static double getReturJual(Connection conn, int bulan, int tahun) {
        double tot = 0;
        String sql = "select sum(jumlah * harga - diskon) from RETUR r inner join RETURRINCI rr on r.id = rr.IDRETUR "
                + "where month(TANGGAL) = " + bulan + " and year(TANGGAL)=" + tahun + ""
                + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                tot = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return tot;
    }

    public static double getReturBeli(Connection conn, int bulan, int tahun) {
        double tot = 0;
        String sql = "select sum(jumlah * harga - diskon) from RETURBELI r inner join RETURBELIRINCI rr on r.id = rr.IDRETURBELI "
                + "where month(TANGGAL) = " + bulan + " and year(TANGGAL)=" + tahun + ""
                + "";
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                tot = rs.getDouble(1);
            }
            rs.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        return tot;
    }

    
    public static double getPersediaanAkhir(Connection conn, int bulan, int tahun) {
        double hasil = 0;
//        String sql = "select sum(cogs * jumlah) from stokperiode where PERIODE='"+tahun+"."+bulan+"'";
        try {
            hasil = getSaldo(conn, "113", (bulan - 1 == 0) ? 12 : (bulan - 1), (bulan - 1 == 0) ? (tahun - 1) : tahun) + getDebet(conn, "113", bulan, tahun) - getKredit(conn, "113", bulan, tahun);
//            Statement stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(sql);
//            if (rs.next()) {
//                hasil = rs.getDouble(1);
//            }
//            rs.close();
//            stat.close();
        } catch (Exception ex) {
            System.out.print(ex.toString());
        }
        return hasil;
    }

    public static double getSaldoLR(Connection conn, String akun, int bulan, int tahun) {
        double hasil = 0.0;
        if (akun.substring(0, 1).equals("5")) {
            hasil = getDebet(conn, akun, bulan, tahun) - getKredit(conn, akun, bulan, tahun);
        }
        if (akun.substring(0, 1).equals("6")) {
            hasil = getDebet(conn, akun, bulan, tahun) - getKredit(conn, akun, bulan, tahun);
        }
        return hasil;
    }
    
    public static double getSaldoLR(Connection conn, String akun, int tahun) {
        double hasil = 0.0;
        if (akun.substring(0, 1).equals("5")) {
            hasil = getDebet(conn, akun, tahun) - getKredit(conn, akun, tahun);
        }
        if (akun.substring(0, 1).equals("6")) {
            hasil = getDebet(conn, akun, tahun) - getKredit(conn, akun, tahun);
        }
        return hasil;
    }

    public static int[] getKonversiSatuan(int jumlah, int jum1, int jum2) {
        int[] a = new int[3];
        int sisa = 0;
        if (jum2 != 0) {
            a[0] = (int) jumlah / jum2;
            sisa = jumlah % jum2;
        }
        if (jum1 != 0) {
            a[1] = sisa / jum1;
            sisa = sisa % jum1;
        }
        if (jum1 == 0 && jum2 == 0) {
            a[2] = jumlah;
        } else {
            a[2] = sisa;
        }
        return a;
    }

    public static int getJumlahSatuan(int jumlah, int jum1, int jum2, int satuan) {
        int[] a = new int[3];
        int sisa = 0;
        if (jum2 != 0) {
            a[0] = (int) jumlah / jum2;
            sisa = jumlah % jum2;
        }
        if (jum1 != 0) {
            a[1] = sisa / jum1;
            sisa = sisa % jum1;
        }
        if (jum1 == 0 && jum2 == 0) {
            a[2] = jumlah;
        } else {
            a[2] = sisa;
        }
        return a[satuan];
    }

    public static int getStokPeriode(Connection c, String kdbarang, int bulan, int tahun) throws SQLException {
        String sql = "select * from stokperiode where periode='" + tahun + "." + bulan + "' and kodebarang='" + kdbarang + "'";
        Statement stat = c.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        int jumlah = 0;
        while (rs.next()) {
            jumlah += rs.getInt("jumlah");
        }
        rs.close();
        stat.close();
        return jumlah;
    }

    public static int getStok(Connection c, String kdbarang, String tgl1, String tgl2, int in_out) throws SQLException {
        String sql = "";
        if (in_out == 0) {
            sql = "select sum(IN) from stok where kodebarang='" + kdbarang + "' and tanggal>='" + tgl1 + "' and tanggal <= '" + tgl2 + "'";
        } else {
            sql = "select sum(OUT) from stok where kodebarang='" + kdbarang + "' and tanggal>='" + tgl1 + "' and tanggal <= '" + tgl2 + "'";
        }
        Statement stat = c.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        int jumlah = 0;
        if (rs.next()) {
            jumlah = rs.getInt(1);
        }
        rs.close();
        stat.close();
        return jumlah;
    }

    public static int getStokAwal(Connection c, String kdbarang, String tanggal) throws SQLException {
        String sql = "select FORMATDATETIME(date '" + tanggal + "' - 1,'yyyy-MM-dd')";
        String tgl[] = Util.split(tanggal, "-");
        Statement stat = c.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        String tglakhir = tanggal;
        if (rs.next()) {
            tglakhir = rs.getString(1);
        }
        String tglawal = tgl[0] + "-" + tgl[1] + "-1";
        int bulan = ((Integer.parseInt(tgl[1]) - 1) == 0) ? 12 : (Integer.parseInt(tgl[1]) - 1);
        int tahun = ((Integer.parseInt(tgl[1]) - 1) == 0) ? (Integer.parseInt(tgl[0]) - 1) : (Integer.parseInt(tgl[0]));
        int stokawal = getStokPeriode(c, kdbarang, bulan, tahun);
        int stok_in = getStok(c, kdbarang, tglawal, tglakhir, 0);
        int stok_out = getStok(c, kdbarang, tglawal, tglakhir, 1);
        int hasil = stokawal + stok_in - stok_out;
        return hasil;

    }

    public static boolean cekTransaksiBarangAda(Connection c, String kodebarang, String tgl1, String tgl2) throws SQLException {
        boolean hasil = false;
        String sql = "select * from stok where KODEBARANG=? and TANGGAL >= ? and TANGGAL <= ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, kodebarang);
        ps.setString(2, tgl1);
        ps.setString(3, tgl2);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            hasil = true;
        }
        rs.close();
        ps.close();
        return hasil;
    }

    public static void main(String[] args) {
        try {
            koneksi.createPoolKoneksi();
            Connection con = koneksi.getKoneksiJ();
            System.out.println("Hutang" + getSaldoAwalKasHutang(con,"11110","2020-01-01", true));
            System.out.println("Piutang" + getSaldoAwalKasPiutang(con,"11110","2020-01-01", true));
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(Fungsi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean cekVersi(String versi) {
        boolean hasil = false;
        if (versi.equals("VERSI1.0MRTAALKES2020")) {
            hasil = true;
        }
        return hasil;
    }

    public static boolean cekVersiServer(String verserver) throws SQLException {
        String ver = "";
        String sql = "SELECT VERSION FROM VERSIAPP WHERE STATAKTIF = '1'";
        try {

            Connection conn = koneksi.getKoneksiJ();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ver = rs.getString(1);
            }
            rs.close();
            ps.close();
            conn.close();
//            System.out.print(ver);
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }

        boolean hasil = false;
        if (ver.equals(verserver)) {
            hasil = true;
        }
        return hasil;
    }
    
}
