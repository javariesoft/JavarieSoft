/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author erwadi
 */
public class returbeli {
    private int ID;
    private String KODERETURBELI;
    private String TANGGAL;
    private String IDSUPPLIER;
    private String KETERANGAN;
    private int IDPEMBELIAN;
    private int STATUS;
    private double TOTALRETUR;
    private double TOTALDISKON;
    private double TOTALPPN;
    private List<returbelirinci> rincireturList;
    private jurnal jurnal;
    
    public returbeli() {
        this.STATUS=0;
        this.TOTALRETUR=0;
        this.TOTALDISKON=0;
        this.TOTALPPN=0;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIDSUPPLIER() {
        return IDSUPPLIER;
    }

    public void setIDSUPPLIER(String IDSUPPLIER) {
        this.IDSUPPLIER = IDSUPPLIER;
    }

    public String getKETERANGAN() {
        return KETERANGAN;
    }

    public void setKETERANGAN(String KETERANGAN) {
        this.KETERANGAN = KETERANGAN;
    }

    public String getKODERETURBELI() {
        return KODERETURBELI;
    }

    public void setKODERETURBELI(String KODERETURBELI) {
        this.KODERETURBELI = KODERETURBELI;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public int getIDPEMBELIAN() {
        return IDPEMBELIAN;
    }

    public void setIDPEMBELIAN(int IDPEMBELIAN) {
        this.IDPEMBELIAN = IDPEMBELIAN;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public double getTOTALRETUR() {
        return TOTALRETUR;
    }

    public void setTOTALRETUR(double TOTALRETUR) {
        this.TOTALRETUR = TOTALRETUR;
    }

    public double getTOTALDISKON() {
        return TOTALDISKON;
    }

    public void setTOTALDISKON(double TOTALDISKON) {
        this.TOTALDISKON = TOTALDISKON;
    }

    public double getTOTALPPN() {
        return TOTALPPN;
    }

    public void setTOTALPPN(double TOTALPPN) {
        this.TOTALPPN = TOTALPPN;
    }

    public List<returbelirinci> getRincireturList() {
        return rincireturList;
    }

    public void setRincireturList(List<returbelirinci> rincireturList) {
        this.rincireturList = rincireturList;
    }

    public jurnal getJurnal() {
        return jurnal;
    }

    public void setJurnal(jurnal jurnal) {
        this.jurnal = jurnal;
    }
    
    public Map<String, Double> getKalkulasi() {
        double totalretur = 0;
        double total = 0;
        double tdiskon = 0;
        double hpp = 0;
        double ppn = 0;
        Map<String, Double> hasil = new HashMap<>();
        for (returbelirinci rp : rincireturList) {
                double ttot = rp.getJUMLAH() * rp.getHARGA();
                double tdisk = rp.getDISKON();
                double thpp = rp.getJUMLAHKECIL() * rp.getBarangstok().getCOGS();
                total += ttot;
                tdiskon += tdisk;
                hpp += thpp;
                ppn += rp.getPPN();
        }
        totalretur = total - tdiskon + ppn;
        hasil.put("total", total);
        hasil.put("diskon", tdiskon);
        hasil.put("ppn", ppn);
        hasil.put("hpp", hpp);
        hasil.put("totalretur", totalretur);
        return hasil;
    }
    
}
