/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author erwadi
 */
public class perkiraan {

    private String KODEPERKIRAAN;
    private String NAMAPERKIRAAN;
    private int GRUP;
    private String TIPE;
    private int STATUSAKTIF;

    public perkiraan() {
        STATUSAKTIF = 0;
    }

    public int getGRUP() {
        return GRUP;
    }

    public void setGRUP(int GRUP) {
        this.GRUP = GRUP;
    }

    public String getKODEPERKIRAAN() {
        return KODEPERKIRAAN;
    }

    public void setKODEPERKIRAAN(String KODEPERKIRAAN) {
        this.KODEPERKIRAAN = KODEPERKIRAAN;
    }

    public String getNAMAPERKIRAAN() {
        return NAMAPERKIRAAN;
    }

    public void setNAMAPERKIRAAN(String NAMAPERKIRAAN) {
        this.NAMAPERKIRAAN = NAMAPERKIRAAN;
    }

    public String getTIPE() {
        return TIPE;
    }

    public void setTIPE(String TIPE) {
        this.TIPE = TIPE;
    }

    public int getSTATUSAKTIF() {
        return STATUSAKTIF;
    }

    public void setSTATUSAKTIF(int STATUSAKTIF) {
        this.STATUSAKTIF = STATUSAKTIF;
    }

}
