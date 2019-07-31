/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

/**
 *
 * @author USER
 */
public class NomorPajak {

    private int id;
    private String noawal;
    private String noakhir;
    private String noakhirpakai;
    private String tglrekam;
    private String tglupdate;

    public NomorPajak() {
    }

    public NomorPajak(int id, String noawal, String noakhir, String noakhirpakai, String tglrekam, String tglupdate) {
        this.id = id;
        this.noawal = noawal;
        this.noakhir = noakhir;
        this.noakhirpakai = noakhirpakai;
        this.tglrekam = tglrekam;
        this.tglupdate = tglupdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoawal() {
        return noawal;
    }

    public void setNoawal(String noawal) {
        this.noawal = noawal;
    }

    public String getNoakhir() {
        return noakhir;
    }

    public void setNoakhir(String noakhir) {
        this.noakhir = noakhir;
    }

    public String getNoakhirpakai() {
        return noakhirpakai;
    }

    public void setNoakhirpakai(String noakhirpakai) {
        this.noakhirpakai = noakhirpakai;
    }

    public String getTglrekam() {
        return tglrekam;
    }

    public void setTglrekam(String tglrekam) {
        this.tglrekam = tglrekam;
    }

    public String getTglupdate() {
        return tglupdate;
    }

    public void setTglupdate(String tglupdate) {
        this.tglupdate = tglupdate;
    }

}
