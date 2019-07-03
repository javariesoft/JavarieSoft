/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.erv.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author TI-PNP
 */
@Entity
@Table(name = "RETURDORINCI")
public class Returdorinci implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "IDRETURDO")
    private int idreturdo;
    @Basic(optional = false)
    @NotEmpty(message = "Tidak Boleh Kosong")
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "KODEBARANG")
    private String kodebarang;
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "JUMLAH")
    private int jumlah;
    @NotEmpty(message = "Tidak Boleh Kosong")
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "SATUAN")
    private String satuan;
    @Basic(optional = false)
    @NotEmpty(message = "Tidak Boleh Kosong")
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "KODEBATCH")
    private String kodebatch;
    @NotEmpty(message = "Tidak Boleh Kosong")
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "EXPIRE")
    private String expire;
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "JUMLAHKECIL")
    private int jumlahkecil;
    private Barangstok barangstok;

    public Returdorinci() {
    }

    public Returdorinci(int id) {
        this.id = id;
    }

    public Returdorinci(int id, int idreturdo, String kodebarang, String kodebatch) {
        this.id = id;
        this.idreturdo = idreturdo;
        this.kodebarang = kodebarang;
        this.kodebatch = kodebatch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdreturdo() {
        return idreturdo;
    }

    public void setIdreturdo(int idreturdo) {
        this.idreturdo = idreturdo;
    }

    public String getKodebarang() {
        return kodebarang;
    }

    public void setKodebarang(String kodebarang) {
        this.kodebarang = kodebarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getKodebatch() {
        return kodebatch;
    }

    public void setKodebatch(String kodebatch) {
        this.kodebatch = kodebatch;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public int getJumlahkecil() {
        return jumlahkecil;
    }

    public void setJumlahkecil(int jumlahkecil) {
        this.jumlahkecil = jumlahkecil;
    }

    public Barangstok getBarangstok() {
        return barangstok;
    }

    public void setBarangstok(Barangstok barangstok) {
        this.barangstok = barangstok;
    }
    
}
