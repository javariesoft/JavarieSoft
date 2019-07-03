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

/**
 *
 * @author TI-PNP
 */
@Entity
@Table(name = "KONTROLTANGGAL")
@NamedQueries({
    @NamedQuery(name = "Kontroltanggal.findAll", query = "SELECT k FROM Kontroltanggal k")})
public class Kontroltanggal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TANGGAL")
    private String tanggal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;

    public Kontroltanggal() {
    }

    public Kontroltanggal(int id) {
        this.id = id;
    }

    public Kontroltanggal(int id, String tanggal, int status) {
        this.id = id;
        this.tanggal = tanggal;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
  
}
