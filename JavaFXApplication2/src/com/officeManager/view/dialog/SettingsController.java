package com.officeManager.view.dialog;

import com.officeManager.data.ConfigVar;
import com.officeManager.utils.Log;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

    @FXML
    private TextField listMandat;
    @FXML
    private TextField pathProjets;
    @FXML
    private TextField pathArchive;
    
    private Stage settingsStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateValues();
    }    
    
    public void updateValues()
    {
        listMandat.setText(ConfigVar.getListMandat().toString());
        pathProjets.setText(ConfigVar.getPathProjets().toString());
        pathArchive.setText(ConfigVar.getPathArchive().toString());
    }
    
    public void saveValues(){
        if(ConfigVar.setListMandat(listMandat.getText()))
            Log.msg(0, "SaveValues listMandat -> " + listMandat.getText());
        else
            Log.msg(1, "SaveValues listMandat -> " + listMandat.getText());
        
        if(ConfigVar.setPathProjets(pathProjets.getText()))
            Log.msg(0, "SaveValues pathProjets -> " + pathProjets.getText());
        else
            Log.msg(1, "SaveValues pathProjets -> " + pathProjets.getText());

        if(ConfigVar.setPathArchive(pathArchive.getText()))
            Log.msg(0, "SaveValues pathArchive -> " + pathArchive.getText());
        else
            Log.msg(1, "SaveValues pathArchive -> " + pathArchive.getText());
        
        settingsStage.close();        
    }
    
    public void cancel(){
        settingsStage.close();
    }
    
    public void setSettingsStage(Stage stg){
        this.settingsStage = stg;
    }

    public void fileChooserListMandat()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choix du fichier listMandat.db");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("listMandat.db", "*.db"));
        fileChooser.setInitialDirectory(ConfigVar.getListMandat().getParent().toFile());
        
        File selectedFile = fileChooser.showOpenDialog(null);
        
        listMandat.setText(selectedFile.getPath());
    }    
    
    public void directoryChooserPathProjets()
    {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choix du dossier Projets");
        if(ConfigVar.getPathProjets().toFile().exists())
            chooser.setInitialDirectory(ConfigVar.getPathProjets().toFile());
        
        File selectedFile = chooser.showDialog(null);
        
        pathProjets.setText(selectedFile.getPath());
    }        

    public void directoryChooserPathArchive()
    {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choix du dossier Archive");
        if(ConfigVar.getPathArchive().toFile().exists())
            chooser.setInitialDirectory(ConfigVar.getPathArchive().toFile());
        
        File selectedFile = chooser.showDialog(null);
        
        pathArchive.setText(selectedFile.getPath());
    }        
}
