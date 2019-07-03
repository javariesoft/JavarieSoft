/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author erwadi
 */
public class pembelian {

    private int ID;
    private String NOFAKTUR;
    private String IDSUPPLIER;
    private String CASH;
    private String TGLBAYAR;
    private double DP;
    private double PAJAK;
    private double DISKON;
    private String TANGGAL;
    private String STATUS;
    private String NOFAKTURSUPPLIER;
    private String TGLMASUK;
    private List<rincipembelian> rincipembelians;
    private jurnal jurnal;

    public pembelian() {
        rincipembelians = new ArrayList<>();
    }

    public String getCASH() {
        return CASH;
    }

    public void setCASH(String CASH) {
        this.CASH = CASH;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIDSUPPLIER() {
        return IDSUPPLIER;
    }

    public void setIDSUPPLIER(String IDSUPPLIER) {
        this.IDSUPPLIER = IDSUPPLIER;
    }

    public String getNOFAKTUR() {
        return NOFAKTUR;
    }

    public void setNOFAKTUR(String NOFAKTUR) {
        this.NOFAKTUR = NOFAKTUR;
    }

    public double getPAJAK() {
        return PAJAK;
    }

    public void setPAJAK(double PAJAK) {
        this.PAJAK = PAJAK;
    }

    public String getTGLBAYAR() {
        return TGLBAYAR;
    }

    public void setTGLBAYAR(String TGLBAYAR) {
        this.TGLBAYAR = TGLBAYAR;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getNOFAKTURSUPPLIER() {
        return NOFAKTURSUPPLIER;
    }

    public void setNOFAKTURSUPPLIER(String NOFAKTURSUPPLIER) {
        this.NOFAKTURSUPPLIER = NOFAKTURSUPPLIER;
    }

    public String getTGLMASUK() {
        return TGLMASUK;
    }

    public void setTGLMASUK(String TGLMASUK) {
        this.TGLMASUK = TGLMASUK;
    }

    public List<rincipembelian> getRincipembelians() {
        return rincipembelians;
    }

    public void setRincipembelians(List<rincipembelian> rincipembelians) {
        this.rincipembelians = rincipembelians;
    }

    public jurnal getJurnal() {
        return jurnal;
    }

    public void setJurnal(jurnal jurnal) {
        this.jurnal = jurnal;
    }

    public Map<String, Double> getKalkulasi() {
        double totalbeli = 0;
        double total = 0;
        double bonus = 0;
        double diskon = 0;
        double tdiskon = 0;
        double ppn = 0;
        Map<String, Double> hasil = new HashMap<>();
        for (rincipembelian rp : rincipembelians) {
            if (rp.getBONUS().trim().equals("Bonus")) {
                bonus += rp.getJUMLAH() * rp.getHARGA();
            } else {
                double ttot = rp.getJUMLAH() * rp.getHARGA();
                double tdisk = rp.getDISKON();
                total += ttot;
                tdiskon += tdisk;
            }
        }
        diskon = tdiskon + DISKON;
        if (PAJAK > 0) {
            ppn = 0.1 * (total - diskon);
        }
        totalbeli = total - diskon + ppn;
        hasil.put("total", total);
        hasil.put("bonus", bonus);
        hasil.put("diskon", diskon);
        hasil.put("ppn", ppn);
        hasil.put("totalbeli", totalbeli);
        return hasil;
    }

}
