/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author erwadi
 */
public class rincijurnal {
    private String KODEJURNAL;
    private String KODEPERKIRAAN;
    private double DEBET;
    private double KREDIT;
    private int NO;
    private String REF;

    public double getDEBET() {
        return DEBET;
    }

    public void setDEBET(double DEBET) {
        this.DEBET = DEBET;
    }

    public String getKODEJURNAL() {
        return KODEJURNAL;
    }

    public void setKODEJURNAL(String KODEJURNAL) {
        this.KODEJURNAL = KODEJURNAL;
    }

    public String getKODEPERKIRAAN() {
        return KODEPERKIRAAN;
    }

    public void setKODEPERKIRAAN(String KODEPERKIRAAN) {
        this.KODEPERKIRAAN = KODEPERKIRAAN;
    }

    public double getKREDIT() {
        return KREDIT;
    }

    public void setKREDIT(double KREDIT) {
        this.KREDIT = KREDIT;
    }

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    public String getREF() {
        return REF;
    }

    public void setREF(String REF) {
        this.REF = REF;
    }
    
}
