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
public class Aktivatetap {
    private String ID;
    private String NAMA;
    private String TGLMASUK;
    private double HARGA;
    private double PERSENPENYUSUTAN;
    private String ACCAKUMULASIPENYUSUTAN;
    private String ACCBIAYAPENYUSUTAN;

    public String getACCAKUMULASIPENYUSUTAN() {
        return ACCAKUMULASIPENYUSUTAN;
    }

    public void setACCAKUMULASIPENYUSUTAN(String ACCAKUMULASIPENYUSUTAN) {
        this.ACCAKUMULASIPENYUSUTAN = ACCAKUMULASIPENYUSUTAN;
    }

    public double getHARGA() {
        return HARGA;
    }

    public void setHARGA(double HARGA) {
        this.HARGA = HARGA;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public double getPERSENPENYUSUTAN() {
        return PERSENPENYUSUTAN;
    }

    public void setPERSENPENYUSUTAN(double PERSENPENYUSUTAN) {
        this.PERSENPENYUSUTAN = PERSENPENYUSUTAN;
    }

    public String getTGLMASUK() {
        return TGLMASUK;
    }

    public void setTGLMASUK(String TGLMASUK) {
        this.TGLMASUK = TGLMASUK;
    }

    public String getACCBIAYAPENYUSUTAN() {
        return ACCBIAYAPENYUSUTAN;
    }

    public void setACCBIAYAPENYUSUTAN(String ACCBIAYAPENYUSUTAN) {
        this.ACCBIAYAPENYUSUTAN = ACCBIAYAPENYUSUTAN;
    }
    
}
