package com.officeManager.view;

import com.officeManager.MainApp;
import com.officeManager.view.dialog.OpenMandatController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RootLayoutController implements Initializable {

    private MainApp mainApp;
    private OpenMandatController openMandat = new OpenMandatController();    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void handleExit(){
        System.exit(0);
    }

    @FXML
    private void handleOpenMandat(){
        openMandat.show();
    }

    public RootLayoutController(){
        openMandat.setMainApp(mainApp);        
    }
    
    public void show() {
        try {
            Parent rootLayout = FXMLLoader.load(getClass().getResource("/com/officeManager/view/RootLayout.fxml"));
            
            Scene scene = new Scene(rootLayout);
            mainApp.getPrimaryStage().setScene(scene);
            mainApp.getPrimaryStage().show();

            openMandat.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }        
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}