/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.util.List;

/**
 *
 * @author erwadi
 */
public class hutang {
    private int ID;
    private String KODEHUTANG;
    private int IDPEMBELIAN;
    //private Date TANGGAL;
    private String TANGGAL;
    private double JUMLAH;
    private String IDSUPPLIER;
    //private Date JATUHTEMPO;
    private String JATUHTEMPO;
    private String KETERANGAN;
    private String STATUS;
    private List<hutangbayar> listHutangBayar;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDPEMBELIAN() {
        return IDPEMBELIAN;
    }

    public void setIDPEMBELIAN(int IDPEMBELIAN) {
        this.IDPEMBELIAN = IDPEMBELIAN;
    }

    public double getJUMLAH() {
        return JUMLAH;
    }

    public void setJUMLAH(double JUMLAH) {
        this.JUMLAH = JUMLAH;
    }

    public String getKODEHUTANG() {
        return KODEHUTANG;
    }

    public void setKODEHUTANG(String KODEHUTANG) {
        this.KODEHUTANG = KODEHUTANG;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getIDSUPPLIER() {
        return IDSUPPLIER;
    }

    public void setIDSUPPLIER(String IDSUPPLIER) {
        this.IDSUPPLIER = IDSUPPLIER;
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

    public List<hutangbayar> getListHutangBayar() {
        return listHutangBayar;
    }

    public void setListHutangBayar(List<hutangbayar> listHutangBayar) {
        this.listHutangBayar = listHutangBayar;
    }
    
    
}
