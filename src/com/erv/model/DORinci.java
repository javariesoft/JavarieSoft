/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author erwadi
 */
public class DORinci {
    private int IDDO;
    private String KODEBARANG;
    private int JUMLAH;
    private String SATUAN;
    private String KODEBATCH;
    private String EXPIRE;
    private int JUMLAHKECIL;
    private Barangstok barangstok;

    public int getIDDO() {
        return IDDO;
    }

    public void setIDDO(int IDDO) {
        this.IDDO = IDDO;
    }

    public int getJUMLAH() {
        return JUMLAH;
    }

    public void setJUMLAH(int JUMLAH) {
        this.JUMLAH = JUMLAH;
    }

    public String getKODEBARANG() {
        return KODEBARANG;
    }

    public void setKODEBARANG(String KODEBARANG) {
        this.KODEBARANG = KODEBARANG;
    }

    public String getSATUAN() {
        return SATUAN;
    }

    public void setSATUAN(String SATUAN) {
        this.SATUAN = SATUAN;
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

    public int getJUMLAHKECIL() {
        return JUMLAHKECIL;
    }

    public void setJUMLAHKECIL(int JUMLAHKECIL) {
        this.JUMLAHKECIL = JUMLAHKECIL;
    }

    public Barangstok getBarangstok() {
        return barangstok;
    }

    public void setBarangstok(Barangstok barangstok) {
        this.barangstok = barangstok;
    }
    
}
