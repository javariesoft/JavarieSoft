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
public class karyawan {
    private String IDKARYAWAN;
    private String NAMA;
    private String ALAMAT;
    private String PHONE;
    private String TGLMASUK;

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getIDKARYAWAN() {
        return IDKARYAWAN;
    }

    public void setIDKARYAWAN(String IDKARYAWAN) {
        this.IDKARYAWAN = IDKARYAWAN;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getTGLMASUK() {
        return TGLMASUK;
    }

    public void setTGLMASUK(String TGLMASUK) {
        this.TGLMASUK = TGLMASUK;
    }
   
}
