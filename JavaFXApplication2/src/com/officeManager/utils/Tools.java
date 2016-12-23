package com.officeManager.utils;

/**
 *
 * @author adrien.pillonel
 */
public class Tools {
    
    public static String ConvertDateToLisible(int date){
        return Integer.toString(date);
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
}
