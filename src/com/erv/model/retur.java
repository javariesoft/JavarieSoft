/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author erwadi
 */
public class retur {
    private int ID;
    private String KODERETUR;
    private String TANGGAL;
    private String KODEPELANGGAN;
    private String KETERANGAN;
    private int IDPENJUALAN;
    private double TAMBAHANTOTALRETUR;
    private int STATUS;
    private double TOTALRETUR;
    private double TOTALDISKON;
    private double TOTALPPN;
    private double TOTALHPP;
    private List<rinciretur> rincireturList;
    private jurnal jurnal;
    private penjualan penjualan;
    
    public retur() {
        this.STATUS = 0;
        this.TOTALRETUR=0;
        this.TOTALDISKON=0;
        this.TOTALPPN=0;
        this.TOTALHPP = 0;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    
    public String getKETERANGAN() {
        return KETERANGAN;
    }

    public void setKETERANGAN(String KETERANGAN) {
        this.KETERANGAN = KETERANGAN;
    }

    public String getKODEPELANGGAN() {
        return KODEPELANGGAN;
    }

    public void setKODEPELANGGAN(String KODEPELANGGAN) {
        this.KODEPELANGGAN = KODEPELANGGAN;
    }

    public String getKODERETUR() {
        return KODERETUR;
    }

    public void setKODERETUR(String KODERETUR) {
        this.KODERETUR = KODERETUR;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public int getIDPENJUALAN() {
        return IDPENJUALAN;
    }

    public void setIDPENJUALAN(int IDPENJUALAN) {
        this.IDPENJUALAN = IDPENJUALAN;
    }
    
    public void setTAMBAHANTOTALRETUR(double TAMBAHANTOTALRETUR) {
        this.TAMBAHANTOTALRETUR = TAMBAHANTOTALRETUR;
    }
    
    public double getTAMBAHANTOTALRETUR() {
        return TAMBAHANTOTALRETUR;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public double getTOTALRETUR() {
        return TOTALRETUR;
    }

    public void setTOTALRETUR(double TOTALRETUR) {
        this.TOTALRETUR = TOTALRETUR;
    }

    public double getTOTALDISKON() {
        return TOTALDISKON;
    }

    public void setTOTALDISKON(double TOTALDISKON) {
        this.TOTALDISKON = TOTALDISKON;
    }

    public double getTOTALPPN() {
        return TOTALPPN;
    }

    public void setTOTALPPN(double TOTALPPN) {
        this.TOTALPPN = TOTALPPN;
    }

    public double getTOTALHPP() {
        return TOTALHPP;
    }

    public void setTOTALHPP(double TOTALHPP) {
        this.TOTALHPP = TOTALHPP;
    }

    public List<rinciretur> getRincireturList() {
        return rincireturList;
    }

    public void setRincireturList(List<rinciretur> rincireturList) {
        this.rincireturList = rincireturList;
    }

    public jurnal getJurnal() {
        return jurnal;
    }

    public void setJurnal(jurnal jurnal) {
        this.jurnal = jurnal;
    }
    
    public Map<String, Double> getKalkulasi() {
        double totalretur = 0;
        double total = 0;
        double tdiskon = 0;
        double hpp = 0;
        double ppn = 0;
        Map<String, Double> hasil = new HashMap<>();
        for (rinciretur rp : rincireturList) {
                double ttot = rp.getJUMLAH() * rp.getHARGA();
                double tdisk = rp.getDISKON();
                double thpp = rp.getJUMLAHKECIL() * rp.getBarangstok().getCOGS();
                total += ttot;
                tdiskon += tdisk;
                hpp += thpp;
                ppn += rp.getPPN();
        }
        totalretur = total - tdiskon + ppn;
        hasil.put("total", total);
        hasil.put("diskon", tdiskon);
        hasil.put("ppn", ppn);
        hasil.put("hpp", hpp);
        hasil.put("totalretur", totalretur);
        return hasil;
    }

    public penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(penjualan penjualan) {
        this.penjualan = penjualan;
    }
    
}
