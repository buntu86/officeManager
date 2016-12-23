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
    private Stage dialogOpenMandat = new Stage();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void handleExit(){
        System.exit(0);
    }

    @FXML
    private void handleOpenMandat(){
        dialogChoixMandat();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void dialogChoixMandat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/dialog/OpenMandat.fxml"));
            AnchorPane openMandat = (AnchorPane) loader.load();
            OpenMandatController controller = loader.getController();
            controller.updateListMandat("all");
            controller.setRootLayout(this);
            
            dialogOpenMandat.setTitle("Ouvrir mandat");
            dialogOpenMandat.initModality(Modality.WINDOW_MODAL);
            dialogOpenMandat.initOwner(mainApp.getPrimaryStage());
            dialogOpenMandat.setScene(new Scene(openMandat));              
            dialogOpenMandat.showAndWait();           
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }    
    
    public Stage getDialogOpenMandat(){
        return this.dialogOpenMandat;
    }
    
    public MainApp getMainApp(){
        return this.mainApp;
    }
}