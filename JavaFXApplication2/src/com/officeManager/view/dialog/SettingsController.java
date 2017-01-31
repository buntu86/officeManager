package com.officeManager.view.dialog;

import com.officeManager.data.ConfigVar;
import com.officeManager.utils.Log;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

    @FXML
    private TextField listMandat;
    
    private Stage settingsStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateValues();
    }    
    
    public void updateValues()
    {
        listMandat.setText(ConfigVar.getListMandat().toString());
    }
    
    public void saveValues(){
        if(ConfigVar.setListMandat(listMandat.getText()))
        {
            Log.msg(0, "SaveValues listMandat -> " + listMandat.getText());
            settingsStage.close();
        }
    }
    
    public void cancel(){
        settingsStage.close();
    }
    
    public void setSettingsStage(Stage stg){
        this.settingsStage = stg;
    }

    public void fileChooser()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("listMandat.db", "*.db"));
        fileChooser.setInitialDirectory(ConfigVar.getListMandat().getParent().toFile());
        
        File selectedFile = fileChooser.showOpenDialog(null);
        
        listMandat.setText(selectedFile.getPath());
    }    
}
