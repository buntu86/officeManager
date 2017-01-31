package com.officeManager.view;

import com.officeManager.MainApp;
import com.officeManager.view.dialog.OpenMandatController;
import com.officeManager.view.dialog.SettingsController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    @FXML
    private void handleOpenSettingsDialog(){
        showSettingsDialog();
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
            
            openMandatStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
                if(t.getCode()==KeyCode.ESCAPE)
                    openMandatStage.close();
            });            
            
            openMandatStage.showAndWait();
            
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    
    public void showSettingsDialog(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/officeManager/view/dialog/Settings.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage settingsStage = new Stage();
            settingsStage.setTitle("Paramètres");
            settingsStage.initModality(Modality.WINDOW_MODAL);
            settingsStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            settingsStage.setScene(scene);
            
            SettingsController controller = loader.getController();
            controller.setSettingsStage(settingsStage);
            
            settingsStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
                if(t.getCode()==KeyCode.ESCAPE)
                    settingsStage.close();
            });            
            
            settingsStage.showAndWait();
            
        } catch (IOException e) {
        e.printStackTrace();
        }        
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }    
}