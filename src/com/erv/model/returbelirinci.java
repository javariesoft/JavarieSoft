/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author erwadi
 */
public class returbelirinci {
    private int ID;
    private int IDRETURBELI;
    private String KODEBARANG;
    private int JUMLAH;
    private float HARGA;
    private float TOTAL;
    private String SATUAN;
    private int JUMLAHKECIL;
    private String KODEBATCH;
    private String EXPIRE;
    private double DISKON;
    private double PPN;
    private Barangstok barangstok;
    public float getHARGA() {
        return HARGA;
    }

    public void setHARGA(float HARGA) {
        this.HARGA = HARGA;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDRETURBELI() {
        return IDRETURBELI;
    }

    public void setIDRETURBELI(int IDRETURBELI) {
        this.IDRETURBELI = IDRETURBELI;
    }

    public int getJUMLAH() {
        return JUMLAH;
    }

    public void setJUMLAH(int JUMLAH) {
        this.JUMLAH = JUMLAH;
    }

    public String getKODEBARANG() {
        return KODEBARANG;
    }

    public void setKODEBARANG(String KODEBARANG) {
        this.KODEBARANG = KODEBARANG;
    }

    public float getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(float TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getSATUAN() {
        return SATUAN;
    }

    public void setSATUAN(String SATUAN) {
        this.SATUAN = SATUAN;
    }

    public int getJUMLAHKECIL() {
        return JUMLAHKECIL;
    }

    public void setJUMLAHKECIL(int JUMLAHKECIL) {
        this.JUMLAHKECIL = JUMLAHKECIL;
    }

    public String getKODEBATCH() {
        return KODEBATCH;
    }

    public void setKODEBATCH(String KODEBATCH) {
        this.KODEBATCH = KODEBATCH;
    }

    public String getEXPIRE() {
        return EXPIRE;
    }

    public void setEXPIRE(String EXPIRE) {
        this.EXPIRE = EXPIRE;
    }

    public double getDISKON() {
        return DISKON;
    }

    public void setDISKON(double DISKON) {
        this.DISKON = DISKON;
    }

    public double getPPN() {
        return PPN;
    }

    public void setPPN(double PPN) {
        this.PPN = PPN;
    }

    public Barangstok getBarangstok() {
        return barangstok;
    }

    public void setBarangstok(Barangstok barangstok) {
        this.barangstok = barangstok;
    }
}
