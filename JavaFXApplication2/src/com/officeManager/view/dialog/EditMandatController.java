package com.officeManager.view.dialog;

import com.officeManager.MainApp;
import com.officeManager.utils.Log;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class EditMandatController implements Initializable {

    private Stage dialogOpenMandat = new Stage();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    /*public void showChoixMandat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/dialog/EditMandat.fxml"));
            AnchorPane openMandat = (AnchorPane) loader.load();
            OpenMandatController controller = loader.getController();
            controller.updateListMandat("all");


            dialogOpenMandat.setTitle("Ouvrir mandat");
            dialogOpenMandat.initModality(Modality.WINDOW_MODAL);
            dialogOpenMandat.initOwner(mainApp.getPrimaryStage());
            dialogOpenMandat.setScene(new Scene(openMandat));              
            dialogOpenMandat.showAndWait();           
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }  */

    void setIdMandat(int i) {
        Log.msg(0, "idMandat " + i);
    }
}
