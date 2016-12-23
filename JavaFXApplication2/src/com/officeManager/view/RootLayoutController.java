package com.officeManager.view;

import com.officeManager.MainApp;
import com.officeManager.view.dialog.OpenMandatController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootLayoutController implements Initializable {

    private MainApp mainApp;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void handleExit(){
        System.exit(0);
    }

    @FXML
    private void handleOpenMandat(){
        showChoixMandat();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void showChoixMandat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/dialog/OpenMandat.fxml"));
            AnchorPane openMandat = (AnchorPane) loader.load();
            OpenMandatController controller = loader.getController();
            controller.updateListMandat("all");
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ouvrir mandat");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            dialogStage.setScene(new Scene(openMandat));              
            dialogStage.showAndWait();            
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }    
}