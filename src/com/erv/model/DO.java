/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class DO {
    private int ID;
    private String KODEDO;
    private String TANGGAL;
    private String KODEPELANGGAN;
    private String STATUS;
    private String TGLTUTUP;
    private String STATUSAKTIF;
    private List<DORinci> dORincis;
    
    public DO() {
        this.ID = 0;
        this.KODEDO = "";
        this.KODEPELANGGAN = "0";
        this.STATUS = "A";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKODEDO() {
        return KODEDO;
    }

    public void setKODEDO(String KODEDO) {
        this.KODEDO = KODEDO;
    }

    public String getKODEPELANGGAN() {
        return KODEPELANGGAN;
    }

    public void setKODEPELANGGAN(String KODEPELANGGAN) {
        this.KODEPELANGGAN = KODEPELANGGAN;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getTGLTUTUP() {
        return TGLTUTUP;
    }

    public void setTGLTUTUP(String TGLTUTUP) {
        this.TGLTUTUP = TGLTUTUP;
    }

    public String getSTATUSAKTIF() {
        return STATUSAKTIF;
    }

    public void setSTATUSAKTIF(String STATUSAKTIF) {
        this.STATUSAKTIF = STATUSAKTIF;
    }

    public List<DORinci> getdORincis() {
        return dORincis;
    }

    public void setdORincis(List<DORinci> dORincis) {
        this.dORincis = dORincis;
    }
    
}
