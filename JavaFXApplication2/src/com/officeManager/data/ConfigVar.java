package com.officeManager.data;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import javafx.scene.control.Alert;

public class ConfigVar {
     
    private static Path pathListMandat;

    public static void iniConfig(){
        Properties prop = new Properties();

        try {            
            prop.load(new FileInputStream("resources/config.properties"));
            setListMandat(prop.getProperty("listMandats"));
            
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - fichier de config");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier de configuration n'a pas pu être chargé.");
            alert.showAndWait();
            System.exit(1);
        }   
    }
    
    public static Path getListMandat(){
        return pathListMandat;
    }
    
    public static boolean setListMandat(String str){
        if(Files.exists(Paths.get(str)))
        {
            pathListMandat = Paths.get(str);
            return true;
        }
        else
        {
            pathListMandat=Paths.get("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Emplacement listMandats.db");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier contenant la liste des mandats n'est pas valide : " + str);
            alert.showAndWait();
            return false;
        }
    }
}
