/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class piutang {
    private int ID;
    private String KODEPIUTANG;
    private int IDPENJUALAN;
    private String TANGGAL;
    private double JUMLAH;
    private String IDPELANGGAN;
    private String JATUHTEMPO;
    private String KETERANGAN;
    private String STATUS;
    private List<piutangbayar> piutangBayarList;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKODEPIUTANG() {
        return KODEPIUTANG;
    }

    public void setKODEPIUTANG(String KODEPIUTANG) {
        this.KODEPIUTANG = KODEPIUTANG;
    }

    public int getIDPENJUALAN() {
        return IDPENJUALAN;
    }

    public void setIDPENJUALAN(int IDPENJUALAN) {
        this.IDPENJUALAN = IDPENJUALAN;
    }

    public double getJUMLAH() {
        return JUMLAH;
    }

    public void setJUMLAH(double JUMLAH) {
        this.JUMLAH = JUMLAH;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getIDPELANGGAN() {
        return IDPELANGGAN;
    }

    public void setIDPELANGGAN(String IDPELANGGAN) {
        this.IDPELANGGAN = IDPELANGGAN;
    }

    public String getJATUHTEMPO() {
        return JATUHTEMPO;
    }

    public void setJATUHTEMPO(String JATUHTEMPO) {
        this.JATUHTEMPO = JATUHTEMPO;
    }

    public String getKETERANGAN() {
        return KETERANGAN;
    }

    public void setKETERANGAN(String KETERANGAN) {
        this.KETERANGAN = KETERANGAN;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public List<piutangbayar> getPiutangBayarList() {
        return piutangBayarList;
    }

    public void setPiutangBayarList(List<piutangbayar> piutangBayarList) {
        this.piutangBayarList = piutangBayarList;
    }

    
    
}
