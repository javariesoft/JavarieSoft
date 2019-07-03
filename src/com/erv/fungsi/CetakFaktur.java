/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.fungsi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erwadi
 */
public class CetakFaktur {

    public void cetak() throws IOException {
        Writer output = null;
        File file = new File("write.txt");
        output = new BufferedWriter(new FileWriter(file));
        output.write("\r\n");
        output.write("\r\n");
        String jdl1="                                                                                 Kepada:";
        String jdl2="                                                                          RSI. IBNUSINA PADANG";
        String jdl3="                                                                          Jl. Gajah MadaPadang";
        String jdl4="                                           FAKTUR";
        String jdl5="                                  No Faktur   : 12.000002";
        String jdl6="                                  Tanggal     : 12-12-2012";
        String jdl7="Sales = 6                         Jatuh Tempo : 12-12-2012";
        
        output.write(jdl1+"\r\n");
        output.write(jdl2+"\r\n");
        output.write(jdl3+"\r\n");
        output.write(jdl4+"\r\n");
        output.write(jdl5+"\r\n");
        output.write(jdl6+"\r\n");
        output.write(jdl7+"\r\n");
        output.write("=============================================================================" + "\r\n");
        output.write("| No | Kd Brg | Nama Barang              | Volume | Harga    |" + "\r\n");
        output.write("=============================================================================" + "\r\n");
        output.close();
        System.out.println("Your file has been written");
    }
    public static void main(String[] args){
        CetakFaktur c=new CetakFaktur();
        try {
            c.cetak();
        } catch (IOException ex) {
            Logger.getLogger(CetakFaktur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
