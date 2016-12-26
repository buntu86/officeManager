package com.officeManager.view;

import com.officeManager.MainApp;
import com.officeManager.view.dialog.OpenMandatController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
    
    public void showRootLayout() {
        try {
            Parent rootLayout = FXMLLoader.load(getClass().getResource("/com/officeManager/view/RootLayout.fxml"));
            
            Scene scene = new Scene(rootLayout);
            mainApp.getPrimaryStage().setScene(scene);
            mainApp.getPrimaryStage().show();
           
            showOpenMandatDialog();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            controller.updateListMandat("en cours", "");
            controller.listenerTextField();

            openMandatStage.showAndWait();
            
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }    
}