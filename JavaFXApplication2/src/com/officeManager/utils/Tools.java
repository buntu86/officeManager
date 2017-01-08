package com.officeManager.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 *
 * @author adrien.pillonel
 */
public class Tools {
    
    public static String ConvertDateToLisible(String str){
        
        int date = convertStringToInt(str);
        
        Instant inst = Instant.ofEpochSecond(date);
        LocalDateTime dateLisible = LocalDateTime.ofInstant(inst, ZoneOffset.UTC);
        
        str = new String(String.format("%02d", dateLisible.getDayOfMonth()) + "." + String.format("%02d",dateLisible.getMonthValue()) + "." + dateLisible.getYear());
        return  str;
    }
    
    public static String ConvertDateToSecond(String date){
        if(date!=null)
        {
            String[] parts = date.split("\\.");
        
            if(parts.length==3)
            {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                if(day<32 & day>0 & month>0 & month<13 & year>1900 & year<2100)
                {
                    return Long.toString(LocalDateTime.of(year, month, day, 0, 0).toEpochSecond(ZoneOffset.UTC));
                }
                else
                    return "0";
            }
            else
                return "0";
        }
        else
            return "0";
    }

    public static int convertStringToInt(String test){
        try{
            return Integer.parseInt(test.trim());
        }catch(Exception e){
            return 0;
        }
    }   

    public static String convertIntToString(int i) {
        return Integer.toString(i);
    }

    public static String getStatutById(int idStatut){
        String str="-";
        
        if(idStatut==0)
            str="en cours";
        
        if(idStatut==1)
            str="archivé";
        
        return str;
    }    
}
