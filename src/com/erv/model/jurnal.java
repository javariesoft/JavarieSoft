/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class jurnal {
    private int ID;
    private String KODEJURNAL;
    //private Date TANGGAL;
    private String TANGGAL;
    private String DESKRIPSI;
    private List<rincijurnal> rincijurnals;

    public jurnal() {
        rincijurnals = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDESKRIPSI() {
        return DESKRIPSI;
    }

    public void setDESKRIPSI(String DESKRIPSI) {
        this.DESKRIPSI = DESKRIPSI;
    }

    public String getKODEJURNAL() {
        return KODEJURNAL;
    }

    public void setKODEJURNAL(String KODEJURNAL) {
        this.KODEJURNAL = KODEJURNAL;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public List<rincijurnal> getRincijurnals() {
        return rincijurnals;
    }

    public void setRincijurnals(List<rincijurnal> rincijurnals) {
        this.rincijurnals = rincijurnals;
    }
    
    public double getDebet(){
        double hasil = 0;
        for (rincijurnal r : rincijurnals) {
            hasil += r.getDEBET();
        }
        return hasil;
    }
    
    public double getKredit(){
        double hasil = 0;
        for (rincijurnal r : rincijurnals) {
            hasil += r.getKREDIT();
        }
        return hasil;
    }
    
}
