/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.erv.model;

import java.sql.Timestamp;

/**
 *
 * @author ervan
 */
public class Giro {
    private int ID;
    private String NOMORGIRO;
    private String TGLGIRO;
    private String TGLJTEMPO;
    private double JUMLAH;
    private String NAMAPENERIMA;
    private int STATUS;
    private String KODEPELANGGAN;
    private pelanggan plg;
    private String BANKASAL;
    private int IDBANK;
    private bank bk;
    private Timestamp CREATE_AT;
    private Timestamp UPDATE_AT;

    public Giro() {
        this.NOMORGIRO = "";
        this.TGLGIRO = "";
        this.TGLJTEMPO = "";
        this.JUMLAH = 0.0;
        this.NAMAPENERIMA = "";
        this.STATUS = 0;
        this.KODEPELANGGAN = "";
        this.BANKASAL = "";
        this.IDBANK = 0;
        this.plg = null;
        this.bk = null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNOMORGIRO() {
        return NOMORGIRO;
    }

    public void setNOMORGIRO(String NOMORGIRO) {
        this.NOMORGIRO = NOMORGIRO;
    }

    public String getTGLGIRO() {
        return TGLGIRO;
    }

    public void setTGLGIRO(String TGLGIRO) {
        this.TGLGIRO = TGLGIRO;
    }

    public String getTGLJTEMPO() {
        return TGLJTEMPO;
    }

    public void setTGLJTEMPO(String TGLJTEMPO) {
        this.TGLJTEMPO = TGLJTEMPO;
    }

    public double getJUMLAH() {
        return JUMLAH;
    }

    public void setJUMLAH(double JUMLAH) {
        this.JUMLAH = JUMLAH;
    }

    public String getNAMAPENERIMA() {
        return NAMAPENERIMA;
    }

    public void setNAMAPENERIMA(String NAMAPENERIMA) {
        this.NAMAPENERIMA = NAMAPENERIMA;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getKODEPELANGGAN() {
        return KODEPELANGGAN;
    }

    public void setKODEPELANGGAN(String KODEPELANGGAN) {
        this.KODEPELANGGAN = KODEPELANGGAN;
    }

    public String getBANKASAL() {
        return BANKASAL;
    }

    public void setBANKASAL(String BANKASAL) {
        this.BANKASAL = BANKASAL;
    }

    public int getIDBANK() {
        return IDBANK;
    }

    public void setIDBANK(int IDBANK) {
        this.IDBANK = IDBANK;
    }   

    public pelanggan getPlg() {
        return plg;
    }

    public void setPlg(pelanggan plg) {
        this.plg = plg;
    }

    public bank getBk() {
        return bk;
    }

    public void setBk(bank bk) {
        this.bk = bk;
    }

    public Timestamp getCREATE_AT() {
        return CREATE_AT;
    }

    public void setCREATE_AT(Timestamp CREATE_AT) {
        this.CREATE_AT = CREATE_AT;
    }

    public Timestamp getUPDATE_AT() {
        return UPDATE_AT;
    }

    public void setUPDATE_AT(Timestamp UPDATE_AT) {
        this.UPDATE_AT = UPDATE_AT;
    }
    
    
    
}