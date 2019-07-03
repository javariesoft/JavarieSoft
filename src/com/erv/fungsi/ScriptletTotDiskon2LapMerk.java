/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.fungsi;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

/**
 *
 * @author erwadi
 */
public class ScriptletTotDiskon2LapMerk extends JRDefaultScriptlet {
    public void beforeReportInit() throws JRScriptletException
	{
		super.beforeReportInit();
	}


	/**
	 *
	 */
	public void afterReportInit() throws JRScriptletException
	{
		super.afterReportInit();
	}


	/**
	 *
	 */
	public void beforePageInit() throws JRScriptletException
	{
		//System.out.println("call   beforePageInit : PAGE_NUMBER = " + this.getVariableValue("PAGE_NUMBER"));
            super.beforePageInit();
	}


	/**
	 *
	 */
	public void afterPageInit() throws JRScriptletException
	{
		//System.out.println("call   afterPageInit  : PAGE_NUMBER = " + this.getVariableValue("PAGE_NUMBER"));
            super.afterPageInit();
	}


	/**
	 *
	 */
	public void beforeColumnInit() throws JRScriptletException
	{
		System.out.println("call     beforeColumnInit");
	}


	/**
	 *
	 */
	public void afterColumnInit() throws JRScriptletException
	{
		System.out.println("call     afterColumnInit");
	}


	/**
	 *
	 */
	public void beforeGroupInit(String groupName) throws JRScriptletException
	{
		if (groupName.equals("CityGroup"))
		{
			System.out.println("call       beforeGroupInit : City = " + this.getFieldValue("City"));
		}
	}


	/**
	 *
	 */
//	public void afterGroupInit(String groupName) throws JRScriptletException
//	{
//		if (groupName.equals("CityGroup"))
//		{
//			System.out.println("call       afterGroupInit  : City = " + this.getFieldValue("City"));
//		
//			String allCities = (String)this.getVariableValue("AllCities");
//			String city = (String)this.getFieldValue("City");
//			StringBuffer sbuffer = new StringBuffer();
//		
//			if (allCities != null)
//			{
//				sbuffer.append(allCities);
//				sbuffer.append(", ");
//			}
//		
//			sbuffer.append(city);
//			this.setVariableValue("AllCities", sbuffer.toString());
//		}
//	}


	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException
	{
		System.out.println("        detail");
	}


	/**
	 *
	 */
	public void afterDetailEval() throws JRScriptletException
	{
	}


	/**
	 *
	 */
//        public String bilang(double uang) throws JRScriptletException {
//        String nama[]={"Nol","Satu","Dua","Tiga","Empat","Lima","Enam","Tujuh","Delapan","Sembilan"};
//        String besar[]={"Triliun","Milyar","Juta","Ribu",""};                
//        if(uang==0) return nama[0];
//        double p = 1000000000000l;
//        String hasil = "";
//        for(int i=0;i < besar.length;i++,p /= 1000)
//        {
//            if(uang < p) continue;
//            double temp = uang / p;                
//            boolean seribu = p==1000;
//            if(temp>=100) {
//                hasil += nama[(int)temp/100] + " Ratus ";
//                temp %= 100;
//                seribu = false;
//            }
//            if(temp>=11 && temp<=19) {
//                hasil += nama[(int)temp-10] + " Belas ";
//                temp = 0;
//                seribu = false;
//            }
//            if(temp>=10) {
//                hasil += nama[(int)temp/10] + " Puluh ";
//                temp %= 10;
//            }
//            if(temp > 0) {
//             if(seribu && temp==1)
//              hasil += "Se";
//             else
//              hasil += nama[(int)temp] + " ";  
//            }
//            uang %= p;
//            hasil += besar[i] + " ";
//        }
//        hasil=hasil.replaceAll("Satu Ratus", "Seratus");
//        hasil=hasil.replaceAll("Satu Belas", "Sebelas");
//        hasil=hasil.replaceAll("Satu Puluh", "Sepuluh");
//        return hasil.trim();
//    }
        
        public String bilang(double uangnya) throws JRScriptletException {
        long uang=0;
        uang = Math.round(uangnya);
        String nama[]={"Nol","Satu","Dua","Tiga","Empat","Lima","Enam","Tujuh","Delapan","Sembilan"};
        String besar[]={"Triliun","Milyar","Juta","Ribu",""};                
        if(uang==0) {
            return nama[0];
        }
        
        long p = 1000000000000l;
        String hasil = "";
        for(int i=0;i < besar.length;i++,p /= 1000)
        {
            if(uang < p) continue;
            long temp = uang / p;                
            boolean seribu = p==1000;
            if(temp>=100) {
                hasil += nama[(int)temp/100] + " Ratus ";
                temp %= 100;
                seribu = false;
            }
            if(temp>=11 && temp<=19) {
                hasil += nama[(int)temp-10] + " Belas ";
                temp = 0;
                seribu = false;
            }
            if(temp>=10) {
                hasil += nama[(int)temp/10] + " Puluh ";
                temp %= 10;
            }
            if(temp > 0) {
             if(seribu && temp==1)
              hasil += "Se";
             else
              hasil += nama[(int)temp] + " ";  
            }
            uang %= p;
            hasil += besar[i] + " ";
        }
        hasil=hasil.replaceAll("Satu Ratus", "Seratus");
        hasil=hasil.replaceAll("Satu Belas", "Sebelas");
        hasil=hasil.replaceAll("Satu Puluh", "Sepuluh");
        return hasil.trim();
    }
}
