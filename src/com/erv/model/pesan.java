/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.io.Serializable;

/**
 *
 * @author Ervan Asri
 */
public class pesan implements Serializable {
    private String header;
    private String deskripsi;
    private String ref;
    public pesan() {
        header="S";
        deskripsi="";
    }

    public pesan(String header, String deskripsi, String ref) {
        this.header = header;
        this.deskripsi = deskripsi;
        this.ref = ref;
    }
    
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
    
}
