/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.erv.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author TI-PNP
 */
@Entity
@Table(name = "RETURDO")
public class Returdo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotEmpty(message = "Tidak Boleh Kosong")
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "KODERETURDO")
    private String kodereturdo;
    @NotEmpty(message = "Tidak Boleh Kosong")
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "TANGGAL")    
    String tanggal;
    @NotEmpty(message = "Tidak Boleh Kosong")
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "KODEPELANGGAN")
    private String kodepelanggan;
    @NotEmpty(message = "Tidak Boleh Kosong")
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "KETERANGAN")
    private String keterangan;
    @NotNull(message = "Tidak Boleh Null")
    @Column(name = "IDDO")
    private int iddo;
    @Column(name = "STATUS")
    private int status;
    private List<Returdorinci> returdorincis;

    public Returdo() {
    }

    public Returdo(int id) {
        this.id = id;
    }

    public Returdo(int id, String kodereturdo) {
        this.id = id;
        this.kodereturdo = kodereturdo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodereturdo() {
        return kodereturdo;
    }

    public void setKodereturdo(String kodereturdo) {
        this.kodereturdo = kodereturdo;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodepelanggan() {
        return kodepelanggan;
    }

    public void setKodepelanggan(String kodepelanggan) {
        this.kodepelanggan = kodepelanggan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getIddo() {
        return iddo;
    }

    public void setIddo(int iddo) {
        this.iddo = iddo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Returdorinci> getReturdorincis() {
        return returdorincis;
    }

    public void setReturdorincis(List<Returdorinci> returdorincis) {
        this.returdorincis = returdorincis;
    }

   public static void main(String[] args){
       Returdo r = new Returdo();
       r.setId(1);
       r.setIddo(1);
       r.setKeterangan("Test");
       r.setKodereturdo(""); 
       ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
       Validator validator = factory.getValidator();
       Set<ConstraintViolation<Returdo>> violations = validator.validate(r);
        System.out.println(violations.size()); 
        for (Iterator<ConstraintViolation<Returdo>> it = violations.iterator(); it.hasNext();) {
           ConstraintViolation<Returdo> constraintViolation = it.next();
            System.out.println( constraintViolation.getPropertyPath()+"  "+ constraintViolation.getMessage());
       }
   }
    
}
