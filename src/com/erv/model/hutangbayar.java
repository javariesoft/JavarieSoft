/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author erwadi
 */
public class hutangbayar {
    private int ID;
    private String KODEHUTANGBAYAR;
    private int IDHUTANG;
    private String TANGGAL;
    private double JUMLAH;
    private String REF;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDHUTANG() {
        return IDHUTANG;
    }

    public void setIDHUTANG(int IDHUTANG) {
        this.IDHUTANG = IDHUTANG;
    }

    public double getJUMLAH() {
        return JUMLAH;
    }

    public void setJUMLAH(double JUMLAH) {
        this.JUMLAH = JUMLAH;
    }

    public String getKODEHUTANGBAYAR() {
        return KODEHUTANGBAYAR;
    }

    public void setKODEHUTANGBAYAR(String KODEHUTANGBAYAR) {
        this.KODEHUTANGBAYAR = KODEHUTANGBAYAR;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getREF() {
        return REF;
    }

    public void setREF(String REF) {
        this.REF = REF;
    }
    
}
