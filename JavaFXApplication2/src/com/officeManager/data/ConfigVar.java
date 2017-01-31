package com.officeManager.data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.control.Alert;

public class ConfigVar {
    
    private static Path pathListMandat = Paths.get("resources/listMandats.db");
    
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Emplacement listMandats.db");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier contenant la liste des mandats n'est pas valide : " + str);
            alert.showAndWait();
            return false;
        }
    }
}
