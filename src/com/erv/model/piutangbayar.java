/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.sql.Date;

/**
 *
 * @author erwadi
 */
public class piutangbayar {
    private int ID;
    private String KODEPIUTANGBAYAR;
    private int IDPIUTANG;
    //private Date TANGGAL;
    private String TANGGAL;
    private double JUMLAH;
    private String REF;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDPIUTANG() {
        return IDPIUTANG;
    }

    public void setIDPIUTANG(int IDPIUTANG) {
        this.IDPIUTANG = IDPIUTANG;
    }

    public double getJUMLAH() {
        return JUMLAH;
    }

    public void setJUMLAH(double JUMLAH) {
        this.JUMLAH = JUMLAH;
    }

    public String getKODEPIUTANGBAYAR() {
        return KODEPIUTANGBAYAR;
    }

    public void setKODEPIUTANGBAYAR(String KODEPIUTANGBAYAR) {
        this.KODEPIUTANGBAYAR = KODEPIUTANGBAYAR;
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
