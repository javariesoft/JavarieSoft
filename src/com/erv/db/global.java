/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

/**
 *
 * @author Ervan Asri
 */
public class global {
    public static String IPADDRESS="";
    public static String URL="";
    public static String REPORT="";

    public global(String ipaddress,String url,String report) {
            IPADDRESS = ipaddress;
            REPORT = report;
            URL = url; 
    }
}
