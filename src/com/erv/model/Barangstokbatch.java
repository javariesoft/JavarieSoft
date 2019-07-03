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
public class Barangstokbatch {
    private int ID;
    private int IDBARANGSTOK;
    private String KODEBATCH;
    private String EXPIRE;
    private int STOK;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDBARANGSTOK() {
        return IDBARANGSTOK;
    }

    public void setIDBARANGSTOK(int IDBARANGSTOK) {
        this.IDBARANGSTOK = IDBARANGSTOK;
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

    public int getSTOK() {
        return STOK;
    }

    public void setSTOK(int STOK) {
        this.STOK = STOK;
    }
    
    
}
