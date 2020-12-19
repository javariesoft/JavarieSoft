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
public class stok {
    private int IDPENJUALAN;
    private String KODEBARANG;
    private String TANGGAL;
    private int IN;
    private int OUT;
    private String KODETRANS;
    private String KODEBATCH;
    private Date CREATEAT;
    private Date UPDATEAT;
    
    public int getIDPENJUALAN() {
        return IDPENJUALAN;
    }

    public void setIDPENJUALAN(int IDPENJUALAN) {
        this.IDPENJUALAN = IDPENJUALAN;
    }

    public int getIN() {
        return IN;
    }

    public void setIN(int IN) {
        this.IN = IN;
    }

    public String getKODEBARANG() {
        return KODEBARANG;
    }

    public void setKODEBARANG(String KODEBARANG) {
        this.KODEBARANG = KODEBARANG;
    }

    public int getOUT() {
        return OUT;
    }

    public void setOUT(int OUT) {
        this.OUT = OUT;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getKODETRANS() {
        return KODETRANS;
    }

    public void setKODETRANS(String KODETRANS) {
        this.KODETRANS = KODETRANS;
    }

    public Date getCREATEAT() {
        return CREATEAT;
    }

    public void setCREATEAT(Date CREATEAT) {
        this.CREATEAT = CREATEAT;
    }

    public Date getUPDATEAT() {
        return UPDATEAT;
    }

    public void setUPDATEAT(Date UPDATEAT) {
        this.UPDATEAT = UPDATEAT;
    }

    public String getKODEBATCH() {
        return KODEBATCH;
    }

    public void setKODEBATCH(String KODEBATCH) {
        this.KODEBATCH = KODEBATCH;
    }
    
}
