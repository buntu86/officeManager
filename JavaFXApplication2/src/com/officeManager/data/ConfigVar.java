package com.officeManager.data;

import com.officeManager.utils.Tools;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import javafx.scene.control.Alert;

public class ConfigVar {
     
    private static Path pathListMandat, pathProjets, pathArchive;
    private static Properties prop = new Properties();
    private static int userAuth;
    
    public static void iniConfig(){
        try {            
            prop.load(new FileInputStream("resources/config.properties"));
            setListMandat(prop.getProperty("listMandats"));
            setUserAuth(prop.getProperty("userAuth"));
            setPathProjets(prop.getProperty("pathProjets"));
            //setPathArchive(prop.getProperty("pathArchive"));
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
        
    public static Path getPathProjets(){
        return pathListMandat;
    }
    
    //resources\listMandats.db
    public static boolean setListMandat(String str){
        if(Files.exists(Paths.get(str)))
        {
            pathListMandat = Paths.get(str);
            if(!prop.getProperty("listMandats").equals(str))
            {
                prop.setProperty("listMandats", str);
                try{
                    prop.store(new FileOutputStream("resources/config.properties"), "edit listMandat");
                }catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur fatale - fichier de config");
                    alert.setHeaderText(null);
                    alert.setContentText("Le fichier de configuration n'a pas pu être sauvé.");
                    alert.showAndWait();
                    System.exit(1);
                }  
            }
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

    public static boolean setPathProjets(String str){
        if(Files.exists(Paths.get(str)))
        {
            pathProjets = Paths.get(str);
            if(!prop.getProperty("pathProjets").equals(str))
            {
                prop.setProperty("pathProjets", str);
                try{
                    prop.store(new FileOutputStream("resources/config.properties"), "edit pathProjet");
                }catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur fatale - fichier de config");
                    alert.setHeaderText(null);
                    alert.setContentText("Le fichier de configuration n'a pas pu être sauvé.");
                    alert.showAndWait();
                    System.exit(1);
                }  
            }
            return true;
        }
        else
        {
            pathProjets=Paths.get("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Emplacement dossier Projets");
            alert.setHeaderText(null);
            alert.setContentText("Le dossier des mandats n'est pas valide : " + str);
            alert.showAndWait();
            return false;
        }
    }    
    
    private static void setUserAuth(String property) {
        userAuth = Tools.convertStringToInt(property);
    }
    public static int getUserAuth(){
        return userAuth;
    }
}
