/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erwadi
 */
public class barang {

    private String KODEBARANG;
    private String NAMABARANG;
    private String SATUAN;
    private double HARGA;
    private String KODEAKUN;
    private String PENDAPATAN_ACC;
    private String COGS_ACC;
    private double COGS;
    private int STOK;
    private String IDKATEGORI;
    private String JENISBARANG;
    private int STATUS;
    private String SATUAN1;
    private int JUMLAH1;
    private String SATUAN2;
    private int JUMLAH2;

    public double getCOGS() {
        return COGS;
    }

    public void setCOGS(double COGS) {
        this.COGS = COGS;
    }

    public String getCOGS_ACC() {
        return COGS_ACC;
    }

    public void setCOGS_ACC(String COGS_ACC) {
        this.COGS_ACC = COGS_ACC;
    }

    public double getHARGA() {
        return HARGA;
    }

    public void setHARGA(double HARGA) {
        this.HARGA = HARGA;
    }

    public String getKODEAKUN() {
        return KODEAKUN;
    }

    public void setKODEAKUN(String KODEAKUN) {
        this.KODEAKUN = KODEAKUN;
    }

    public String getKODEBARANG() {
        return KODEBARANG;
    }

    public void setKODEBARANG(String KODEBARANG) {
        this.KODEBARANG = KODEBARANG;
    }

    public String getNAMABARANG() {
        return NAMABARANG;
    }

    public void setNAMABARANG(String NAMABARANG) {
        this.NAMABARANG = NAMABARANG;
    }

    public String getSATUAN() {
        return SATUAN;
    }

    public void setSATUAN(String SATUAN) {
        this.SATUAN = SATUAN;
    }

    public int getSTOK() {
        return STOK;
    }

    public void setSTOK(int STOK) {
        this.STOK = STOK;
    }

    public String getIDKATEGORI() {
        return IDKATEGORI;
    }

    public void setIDKATEGORI(String IDKATEGORI) {
        this.IDKATEGORI = IDKATEGORI;
    }

    public String getJENISBARANG() {
        return JENISBARANG;
    }

    public void setJENISBARANG(String JENISBARANG) {
        this.JENISBARANG = JENISBARANG;
    }

    public String getPENDAPATAN_ACC() {
        return PENDAPATAN_ACC;
    }

    public void setPENDAPATAN_ACC(String PENDAPATAN_ACC) {
        this.PENDAPATAN_ACC = PENDAPATAN_ACC;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getSATUAN1() {
        return SATUAN1;
    }

    public void setSATUAN1(String SATUAN1) {
        this.SATUAN1 = SATUAN1;
    }

    public int getJUMLAH1() {
        return JUMLAH1;
    }

    public void setJUMLAH1(int JUMLAH1) {
        this.JUMLAH1 = JUMLAH1;
    }

    public String getSATUAN2() {
        return SATUAN2;
    }

    public void setSATUAN2(String SATUAN2) {
        this.SATUAN2 = SATUAN2;
    }

    public int getJUMLAH2() {
        return JUMLAH2;
    }

    public void setJUMLAH2(int JUMLAH2) {
        this.JUMLAH2 = JUMLAH2;
    }

    public List<Object> getItemSatuan(){
        List<Object> list = new ArrayList<Object>();
        list.add(getSATUAN());
        list.add(getSATUAN1());
        list.add(getSATUAN2());
        return list;
    }
    
    public double getHarga(String pil){
        if(pil.equals(getSATUAN())){
            return getHARGA();
        }else if(pil.equals(getSATUAN1())){
            return getJUMLAH1() * getHARGA();
        }else if(pil.equals(getSATUAN2())){
            return getJUMLAH2() * getHARGA();
        }
        return 0;
    }
    
    public double getHargaSatuan(String pil, double harga){
        if(pil.equals(getSATUAN())){
            return harga;
        }else if(pil.equals(getSATUAN1())){
            return (harga / getJUMLAH1());
        }else if(pil.equals(getSATUAN2())){
            return (harga / getJUMLAH2());
        }
        return 0;
    }
    
    public int getJumlah(int jumbeli, String pil){
        if(pil.equals(getSATUAN())){
            return jumbeli;
        }else if(pil.equals(getSATUAN1())){
            return getJUMLAH1() * jumbeli;
        }else if(pil.equals(getSATUAN2())){
            return getJUMLAH2() * jumbeli;
        }
        return 0;
    }
    
    public int getJumlahSatuan(String pil){
        if(pil.equals(getSATUAN())){
            return 1;
        }else if(pil.equals(getSATUAN1())){
            return getJUMLAH1();
        }else if(pil.equals(getSATUAN2())){
            return getJUMLAH2();
        }
        return 0;
    }
    
    public int getJumlahKecil(int jumlahSatuan1, int jumlahSatuan2, int jumlahSatuan3){
        int hasil = jumlahSatuan1 + (JUMLAH1 * jumlahSatuan2) + (JUMLAH2 * jumlahSatuan3);
        return hasil;
    }
}
