/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author erwadi
 */
public class sales {
    private String IDSALES;
    private String NAMA;
    private String ALAMAT;
    private String HP;
    private String INISIAL;
    private int STATUSAKTIF;
    
    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getHP() {
        return HP;
    }

    public void setHP(String HP) {
        this.HP = HP;
    }

    public String getIDSALES() {
        return IDSALES;
    }

    public void setIDSALES(String IDSALES) {
        this.IDSALES = IDSALES;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getINISIAL() {
        return INISIAL;
    }

    public void setINISIAL(String INISIAL) {
        this.INISIAL = INISIAL;
    }

    public int getSTATUSAKTIF() {
        return STATUSAKTIF;
    }

    public void setSTATUSAKTIF(int STATUSAKTIF) {
        this.STATUSAKTIF = STATUSAKTIF;
    }
    
    
}
