/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.fungsi;

/**
 *
 * @author erwadi
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MakeCSVFile {

    public static void main(String[] args) throws IOException {

        //Note the "\\" used in the path of the file 
        //instead of "\", this is required to read 
        //the path in the String format.
        FileWriter fw = new FileWriter("C:\\h2\\WriteTest.csv");
        PrintWriter pw = new PrintWriter(fw);
        
        //Write to file for the first row
        pw.print("Hello guys");
        pw.print(",");
        pw.print("Java Code Online is maing");
        pw.print(",");
        pw.println("a csv file and now a new line is going to come");
        
        //Write to file for the second row
        pw.print("Hey");
        pw.print(",");
        pw.print("It's a");
        pw.print(",");
        pw.print("New Line");
        
        //Flush the output to the file
        pw.flush();
        
        //Close the Print Writer
        pw.close();
        
        //Close the File Writer
        fw.close();        
    }
}
