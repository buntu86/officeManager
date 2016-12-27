package com.officeManager.view;

import com.officeManager.MainApp;
import com.officeManager.view.dialog.OpenMandatController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootLayoutController {

    private MainApp mainApp;
    
    @FXML
    private void handleExit(){
        System.exit(0);
    }

    @FXML
    private void handleOpenMandat(){
        showOpenMandatDialog();
    }

    public RootLayoutController(){
    }      
    
    public void showOpenMandatDialog(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/officeManager/view/dialog/OpenMandat.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage openMandatStage = new Stage();
            openMandatStage.setTitle("Choix du mandat");
            openMandatStage.initModality(Modality.WINDOW_MODAL);
            openMandatStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            openMandatStage.setScene(scene);
            
            OpenMandatController controller = loader.getController();
            controller.setOpenMandatStage(openMandatStage);
            controller.iniChoiceBox();
            controller.iniButtons();
            controller.updateListMandat("en cours", "");
            controller.listenerTextField();
            controller.listenerTableView();

            openMandatStage.showAndWait();
            
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }    

    public void setRootLayout(BorderPane rootLayout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}