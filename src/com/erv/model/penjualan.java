/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author erwadi
 */
public class penjualan implements Serializable {

    private int ID;
    private String FAKTUR;
    private String TANGGAL;
    private String KODEPELANGGAN;
    private String CASH;
    private String TGLLUNAS;
    private double PPN;
    private double DP;
    private double DISKON;
    private String STATUS;
    private String IDSALES;
    private double TAMBAHANTOTAL;
    private int IDBANK;
    private String PELANGGAN;
    private String JENISTRANS;
    private String STATUSDO;
    private double ONGKOSKIRIM;
    private double DISKONPERSEN;
    private List<rincipenjualan> rincipenjualans;
    private jurnal jurnal;
    private String KODEPAJAK;

    public penjualan() {
        this.ID = 0;
        this.FAKTUR = "";
        this.KODEPELANGGAN = "";
        this.CASH = "0";
        this.PPN = 0f;
        this.DP = 0f;
        this.DISKON = 0f;
        this.STATUS = "0";
        this.IDSALES = "0";
        this.TAMBAHANTOTAL = 0f;
        this.PELANGGAN = "";
        this.JENISTRANS = "";
        this.ONGKOSKIRIM = 0;
        this.DISKONPERSEN = 0;
        rincipenjualans = new ArrayList<>();

    }

    public String getCASH() {
        return CASH;
    }

    public void setCASH(String CASH) {
        this.CASH = CASH;
    }

    public String getFAKTUR() {
        return FAKTUR;
    }

    public void setFAKTUR(String FAKTUR) {
        this.FAKTUR = FAKTUR;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKODEPELANGGAN() {
        return KODEPELANGGAN;
    }

    public void setKODEPELANGGAN(String KODEPELANGGAN) {
        this.KODEPELANGGAN = KODEPELANGGAN;
    }

    public double getPPN() {
        return PPN;
    }

    public void setTAMBAHANTOTAL(double TAMBAHANTOTAL) {
        this.TAMBAHANTOTAL = TAMBAHANTOTAL;
    }

    public double getTAMBAHANTOTAL() {
        return TAMBAHANTOTAL;
    }

    public void setPPN(double PPN) {
        this.PPN = PPN;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getTGLLUNAS() {
        return TGLLUNAS;
    }

    public void setTGLLUNAS(String TGLLUNAS) {
        this.TGLLUNAS = TGLLUNAS;
    }

    public double getDISKON() {
        return DISKON;
    }

    public void setDISKON(double DISKON) {
        this.DISKON = DISKON;
    }

    public double getDP() {
        return DP;
    }

    public void setDP(double DP) {
        this.DP = DP;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getIDSALES() {
        return IDSALES;
    }

    public void setIDSALES(String IDSALES) {
        this.IDSALES = IDSALES;
    }

    public int getIDBANK() {
        return IDBANK;
    }

    public void setIDBANK(int IDBANK) {
        this.IDBANK = IDBANK;
    }

    public String getPELANGGAN() {
        return PELANGGAN;
    }

    public void setPELANGGAN(String PELANGGAN) {
        this.PELANGGAN = PELANGGAN;
    }

    public String getJENISTRANS() {
        return JENISTRANS;
    }

    public void setJENISTRANS(String JENISTRANS) {
        this.JENISTRANS = JENISTRANS;
    }

    public String getSTATUSDO() {
        return STATUSDO;
    }

    public void setSTATUSDO(String STATUSDO) {
        this.STATUSDO = STATUSDO;
    }

    public double getONGKOSKIRIM() {
        return ONGKOSKIRIM;
    }

    public void setONGKOSKIRIM(double ONGKOSKIRIM) {
        this.ONGKOSKIRIM = ONGKOSKIRIM;
    }

    public double getDISKONPERSEN() {
        return DISKONPERSEN;
    }

    public void setDISKONPERSEN(double DISKONPERSEN) {
        this.DISKONPERSEN = DISKONPERSEN;
    }

    public List<rincipenjualan> getRincipenjualans() {
        return rincipenjualans;
    }

    public void setRincipenjualans(List<rincipenjualan> rincipenjualans) {
        this.rincipenjualans = rincipenjualans;
    }

    public jurnal getJurnal() {
        return jurnal;
    }

    public void setJurnal(jurnal jurnal) {
        this.jurnal = jurnal;
    }

    public Map<String, Double> getKalkulasi() {
        double total = 0;
        double diskon = 0;
        double tdiskon = 0;
        double ppn = 0;
        double totalJual = 0;
        double hpp = 0;
        double bonus = 0;

        Map<String, Double> hasil = new HashMap<>();
        for (rincipenjualan rp : rincipenjualans) {
            if (rp.getBONUS().trim().equals("Bonus")) {
                bonus += rp.getJUMLAH() * rp.getCOGS();
            } else {
                double ttot = rp.getJUMLAH() * rp.getHARGA();
                double tdisk = rp.getDISKON();
                double thpp = rp.getJUMLAHKECIL() * rp.getCOGS();
                total += ttot;
                tdiskon += tdisk;
                hpp += thpp;
            }
        }
        diskon = tdiskon + DISKON;
        if (PPN > 0) {
            ppn = 0.1 * (total - diskon);
        }
        totalJual = total - diskon + ppn;
        hasil.put("total", total);
        hasil.put("bonus", bonus);
        hasil.put("diskon", diskon);
        hasil.put("ppn", ppn);
        hasil.put("totaljual", totalJual);
        hasil.put("hpp", hpp);
        return hasil;
    }

    public double getHpp(double cogs) {
        double hpp = 0;
        for (rincipenjualan rp : rincipenjualans) {
            if (!rp.getBONUS().trim().equals("Bonus")) {
                double thpp = rp.getJUMLAHKECIL() * cogs;
                hpp += thpp;
            }
        }
        return hpp;
    }

    public String getKODEPAJAK() {
        return KODEPAJAK;
    }

    public void setKODEPAJAK(String KODEPAJAK) {
        this.KODEPAJAK = KODEPAJAK;
    }
    
    
}
