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
public class supplier {

    private String IDSUPPLIER;
    private String NAMA;
    private String ALAMAT;
    private String NOHP;
    private float BATASKREDIT;
    private String TGLREG;
    private String KODEAKUN;
    private String NPWP;
    private int STATUSAKTIF;
    private String PROPINSI;
    private String KABUPATEN;
    private String KECAMATAN;
    private String KELURAHAN;
    private String KODEPOS;

    public supplier() {
        this.IDSUPPLIER = "";
        this.NAMA = "";
        this.ALAMAT = "";
        this.NOHP = "";
        this.BATASKREDIT = 0;
        //this.TGLREG = java.sql.Date.valueOf(com.erv.function.Util.toDateStringSql(new java.util.Date()));
        this.KODEAKUN = "";
        this.NPWP = "";
        this.STATUSAKTIF = 0;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getIDSUPPLIER() {
        return IDSUPPLIER;
    }

    public void setIDSUPPLIER(String IDSUPPLIER) {
        this.IDSUPPLIER = IDSUPPLIER;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getNOHP() {
        return NOHP;
    }

    public void setNOHP(String NOHP) {
        this.NOHP = NOHP;
    }

    public float getBATASKREDIT() {
        return BATASKREDIT;
    }

    public void setBATASKREDIT(float BATASKREDIT) {
        this.BATASKREDIT = BATASKREDIT;
    }

    public String getTGLREG() {
        return TGLREG;
    }

    public void setTGLREG(String TGLREG) {
        this.TGLREG = TGLREG;
    }

    public String getKODEAKUN() {
        return KODEAKUN;
    }

    public void setKODEAKUN(String KODEAKUN) {
        this.KODEAKUN = KODEAKUN;
    }

    public String getNPWP() {
        return NPWP;
    }

    public void setNPWP(String NPWP) {
        this.NPWP = NPWP;
    }

    public int getSTATUSAKTIF() {
        return STATUSAKTIF;
    }

    public void setSTATUSAKTIF(int STATUSAKTIF) {
        this.STATUSAKTIF = STATUSAKTIF;
    }

    public String getPROPINSI() {
        return PROPINSI;
    }

    public void setPROPINSI(String PROPINSI) {
        this.PROPINSI = PROPINSI;
    }

    public String getKABUPATEN() {
        return KABUPATEN;
    }

    public void setKABUPATEN(String KABUPATEN) {
        this.KABUPATEN = KABUPATEN;
    }

    public String getKECAMATAN() {
        return KECAMATAN;
    }

    public void setKECAMATAN(String KECAMATAN) {
        this.KECAMATAN = KECAMATAN;
    }

    public String getKELURAHAN() {
        return KELURAHAN;
    }

    public void setKELURAHAN(String KELURAHAN) {
        this.KELURAHAN = KELURAHAN;
    }

    public String getKODEPOS() {
        return KODEPOS;
    }

    public void setKODEPOS(String KODEPOS) {
        this.KODEPOS = KODEPOS;
    }

}
