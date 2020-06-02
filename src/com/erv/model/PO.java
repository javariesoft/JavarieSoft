/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.util.List;

/**
 *
 * @author USER
 */
public class PO {

    private int id;
    private String kodepo;
    private String tanggal;
    private String kodepelanggan;
    private pelanggan pelanggan;
    private String nofaktur;
    private List<PoRinci> poRincis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodepo() {
        return kodepo;
    }

    public void setKodepo(String kodepo) {
        this.kodepo = kodepo;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public List<PoRinci> getPoRincis() {
        return poRincis;
    }

    public void setPoRincis(List<PoRinci> poRincis) {
        this.poRincis = poRincis;
    }

    public String getKodepelanggan() {
        return kodepelanggan;
    }

    public void setKodepelanggan(String kodepelanggan) {
        this.kodepelanggan = kodepelanggan;
    }

    public pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public String getNofaktur() {
        return nofaktur;
    }

    public void setNofaktur(String nofaktur) {
        this.nofaktur = nofaktur;
    }

    @Override
    public String toString() {
        return id+","+kodepo+","+tanggal+","+kodepelanggan+","+nofaktur;
    }
}
