package com.officeManager.view.dialog;

import com.officeManager.data.Sql_listMandat;
import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adrien
 */
public class EditMandatController implements Initializable {

    private Stage dialogOpenMandat = new Stage();
    
    @FXML
    private TextField numMandat;

    @FXML
    private TextField nomMandat;

    @FXML
    private TextField dateDebut;

    @FXML
    private TextField dateArchive;    
    
    @FXML
    private TextField numCarton;
    
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();    
    private Stage editMandatStage;
    private int idMandat=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void iniChoiceBox(){
        choiceBox.getItems().add("en cours");
        choiceBox.getItems().add("tous");
        choiceBox.getItems().add("archive");
        //choiceBox.getSelectionModel().selectFirst();
    }

    void setIdMandat(int i) {
        Log.msg(0, "idMandat " + i);
        Sql_listMandat mandatsSql = new Sql_listMandat();
        
        Mandat mandat = mandatsSql.getMandatById(i);
        numMandat.setText(mandat.getNumMandat());
        nomMandat.setText(mandat.getNomMandat());
        dateDebut.setText(mandat.getDateDebutLisible());
        dateArchive.setText(mandat.getDateArchiveLisible());
        numCarton.setText(mandat.getNumCarton());
        
    }

    void setEditMandatStage(Stage editMandatStage) {
        this.editMandatStage = editMandatStage;
    }
    
    public void closeDialog(){
        this.editMandatStage.close();
    }
}
