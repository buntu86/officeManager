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
        
        
        return null;
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


/*

        
        //http://stackoverflow.com/questions/22463062/how-to-parse-format-dates-with-localdatetime-java-8
        //http://stackoverflow.com/questions/39628885/how-convert-localdatetime-to-date-in-java-8
        //http://stackoverflow.com/questions/22463062/how-to-parse-format-dates-with-localdatetime-java-8
        //1482958861
        
        /*long timePoint = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long timePoint1 = LocalDateTime.of(2016, 12, 28, 21, 18).toEpochSecond(ZoneOffset.UTC);
        
        
        Log.msg(0, "time : " + timePoint1 + " | " + timePoint);
        
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDate1 = LocalDate.of(2016, 12, 28);

        long mili = LocalDateTime.of(2016, 12, 28, 0, 0).toEpochSecond(ZoneOffset.UTC);
        Instant inst = Instant.ofEpochSecond(1482883200);
        LocalDateTime dateTimeFromInstant = LocalDateTime.ofInstant(inst, ZoneOffset.UTC);
        
        Log.msg(0, "mili1 : " + mili);
        Log.msg(0, "day : " + dateTimeFromInstant.getDayOfMonth() + dateTimeFromInstant.getMonthValue() + dateTimeFromInstant.getYear());
        */