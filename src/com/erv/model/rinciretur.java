/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author erwadi
 */
public class rinciretur {

    private int ID;
    private int IDRETUR;
    private String KODEBARANG;
    private int JUMLAH;
    private float HARGA;
    private float TOTAL;
    private float DISKON;
    private float PPN;
    private int IDDO;
    private String SATUAN;
    private int JUMLAHKECIL;
    private double COGS;
    private String KODEBATCH;
    private String EXPIRE;
    private Barangstok barangstok;

    public rinciretur() {
        this.ID = 0;
        this.IDRETUR = 0;
        this.KODEBARANG = "";
        this.JUMLAH = 0;
        this.HARGA = 0;
        this.TOTAL = 0;
    }

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

    public int getIDRETUR() {
        return IDRETUR;
    }

    public void setIDRETUR(int IDRETUR) {
        this.IDRETUR = IDRETUR;
    }

    public float getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(float TOTAL) {
        this.TOTAL = TOTAL;
    }

    public float getDISKON() {
        return DISKON;
    }

    public void setDISKON(float DISKON) {
        this.DISKON = DISKON;
    }

    public float getPPN() {
        return PPN;
    }

    public void setPPN(float PPN) {
        this.PPN = PPN;
    }

    public int getIDDO() {
        return IDDO;
    }

    public void setIDDO(int IDDO) {
        this.IDDO = IDDO;
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

    public double getCOGS() {
        return COGS;
    }

    public void setCOGS(double COGS) {
        this.COGS = COGS;
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

    public Barangstok getBarangstok() {
        return barangstok;
    }

    public void setBarangstok(Barangstok barangstok) {
        this.barangstok = barangstok;
    }

}
