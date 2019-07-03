/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.sql.Date;
import org.h2.util.StringUtils;

/**
 *
 * @author erwadi
 */
public class pelanggan {
    private String KODEPELANGGAN;
    private String NAMA;
    private String ALAMAT;
    private String HP;
    //private Date TGLREG;
    private String TGLREG;
    private float BATASKREDIT;
    private String KODEAKUN;
    private String NPWP;
    private String STATUSCABANG;
    private String NAMAPEMILIK;
    private int STATUSAKTIF;
    private String PROPINSI;
    private String KABUPATEN;
    private String KECAMATAN;
    private String KELURAHAN;
    private String KODEPOS;
    private String RT;
    private String RW;
    
    public pelanggan() {
        this.KODEPELANGGAN = "";
        this.NAMA = "";
        this.ALAMAT = "";
        this.HP = "";
        //this.TGLREG = java.sql.Date.valueOf(com.erv.function.Util.toDateStringSql(new java.util.Date()));
        this.TGLREG = "";
        this.BATASKREDIT = 0;
        this.KODEAKUN = "";
        this.NPWP="";
        this.STATUSCABANG="";
        this.NAMAPEMILIK="";
        this.STATUSAKTIF=0;
        PROPINSI = "-";
        KABUPATEN="-";
        KECAMATAN="-";
        KELURAHAN="-";
        KODEPOS="-";
        RT="";
        RW="";
    }

    public String getKODEPELANGGAN() {
        return KODEPELANGGAN;
    }

    public void setKODEPELANGGAN(String KODEPELANGGAN) {
        this.KODEPELANGGAN = KODEPELANGGAN;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

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

    public String getTGLREG() {
        return TGLREG;
    }

    public void setTGLREG(String TGLREG) {
        this.TGLREG = TGLREG;
    }

    public float getBATASKREDIT() {
        return BATASKREDIT;
    }

    public void setBATASKREDIT(float BATASKREDIT) {
        this.BATASKREDIT = BATASKREDIT;
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
        String hasil="";
        for(int i=0;i<NPWP.length();i++){
            if(StringUtils.isNumber(String.valueOf(NPWP.charAt(i)))){
                hasil+=NPWP.charAt(i);
            }
        }
        this.NPWP = hasil;
    }

    public String getSTATUSCABANG() {
        return STATUSCABANG;
    }

    public void setSTATUSCABANG(String STATUSCABANG) {
        this.STATUSCABANG = STATUSCABANG;
    }

    public String getNAMAPEMILIK() {
        return NAMAPEMILIK;
    }

    public void setNAMAPEMILIK(String NAMAPEMILIK) {
        this.NAMAPEMILIK = NAMAPEMILIK;
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

    public String getRT() {
        return RT;
    }

    public void setRT(String RT) {
        this.RT = RT;
    }

    public String getRW() {
        return RW;
    }

    public void setRW(String RW) {
        this.RW = RW;
    }

}
