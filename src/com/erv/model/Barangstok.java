/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author TI-PNP
 */
public class Barangstok {

    private int ID;
    private int IDGUDANG;
    private String KODEBARANG;
    private int STOK;
    private double COGS;
    private double HARGAJUAL;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDGUDANG() {
        return IDGUDANG;
    }

    public void setIDGUDANG(int IDGUDANG) {
        this.IDGUDANG = IDGUDANG;
    }

    public String getKODEBARANG() {
        return KODEBARANG;
    }

    public void setKODEBARANG(String KODEBARANG) {
        this.KODEBARANG = KODEBARANG;
    }

    public int getSTOK() {
        return STOK;
    }

    public void setSTOK(int STOK) {
        this.STOK = STOK;
    }

    public double getCOGS() {
        return COGS;
    }

    public void setCOGS(double COGS) {
        this.COGS = COGS;
    }

    public double getHARGAJUAL() {
        return HARGAJUAL;
    }

    public void setHARGAJUAL(double HARGAJUAL) {
        this.HARGAJUAL = HARGAJUAL;
    }

}
