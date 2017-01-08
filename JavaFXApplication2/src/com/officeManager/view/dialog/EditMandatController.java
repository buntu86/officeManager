package com.officeManager.view.dialog;

import com.officeManager.data.Sql_listMandat;
import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import com.officeManager.utils.Tools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    @FXML
    private Label dateArchiveLabel;
    
    @FXML
    private Label numCartonLabel;
    
    private Stage editMandatStage, openMandatStage;
    private int idMandat=0;
    private Mandat mandat=null;
    private OpenMandatController openMandatController;
    private String tris;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateDebut.setPromptText("jj.mm.aaaa");
        dateArchive.setPromptText("aaaa");
    }    
    
    public void iniChoiceBox(){
        choiceBox.getItems().add("en cours");
        choiceBox.getItems().add("archive");
    }

    public void iniListener(){
        editMandatStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
        if(t.getCode()==KeyCode.ESCAPE)
            editMandatStage.close();
        });

        editMandatStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
        if(t.getCode()==KeyCode.ENTER)
            save();
        });
        
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue ov, Number value, Number new_value){
                if(new_value.intValue()==1)
                 {
                     dateArchive.setDisable(false);
                     numCarton.setDisable(false);
                     dateArchiveLabel.setDisable(false);
                     numCartonLabel.setDisable(false);
                 }    
                 else
                 {
                     dateArchive.setDisable(true);
                     numCarton.setDisable(true);
                     dateArchiveLabel.setDisable(true);
                     numCartonLabel.setDisable(true);
                 }                
            }
        });
    }   
    
    public void setIdMandat(int i) {
        Log.msg(0, "idMandat " + i);
        Sql_listMandat mandatsSql = new Sql_listMandat();
        mandat = mandatsSql.getMandatById(i);
        if(mandat!=null){
            numMandat.setText(mandat.getNumMandat());
            nomMandat.setText(mandat.getNomMandat());
            dateDebut.setText(mandat.getDateDebut());
            choiceBox.getSelectionModel().select(mandat.getIdStatut());
            
            if(choiceBox.getId().equals("1"))
            {
                dateArchive.setText(Tools.convertIntToString(mandat.getDateArchive()));
                numCarton.setText(mandat.getNumCarton());
            }    
            else
            {
                dateArchive.setDisable(true);
                numCarton.setDisable(true);
            }
        }
    }

    public void setEditMandatStage(Stage editMandatStage) {
        this.editMandatStage = editMandatStage;
    }
    
    public void setOpenMandatStage(Stage openMandatStage) {
        this.openMandatStage = openMandatStage;
    }    
    
    public void closeDialog(){
        this.editMandatStage.close();
    }
    
    public void save(){
        if(!this.numMandat.toString().trim().isEmpty())
        {
            mandat.setNumMandat(numMandat.getText());
            mandat.setNomMandat(nomMandat.getText());
            mandat.setStatut(choiceBox.getSelectionModel().getSelectedIndex());
            mandat.setDateArchive(dateArchive.getText());
            mandat.setDateDebut(Tools.ConvertDateToSecond(dateDebut.getText()));
            mandat.setNumCarton(numCarton.getText());
            
            //Log.msg(0, "EditMandatController | dateDebut " + Tools.ConvertDateToSecond(dateDebut.getText()));
            
            Sql_listMandat mandatsSql = new Sql_listMandat();

            //test if numMandat isn't empty after trim, if not don't close and don't update list
            if(mandatsSql.update(mandat))
            {
                editMandatStage.close();
                openMandatController.updateListMandat(this.tris, "");
            }
        }
    }

    public void updateAfterChoiceBox(){
           if(choiceBox.getId().equals("1"))
            {
                dateArchive.setDisable(false);
                numCarton.setDisable(false);
                dateArchiveLabel.setDisable(false);
                numCartonLabel.setDisable(false);                
            }    
            else
            {
                dateArchive.setDisable(true);
                numCarton.setDisable(true);
                dateArchiveLabel.setDisable(true);
                numCartonLabel.setDisable(true);                
            }
    }
    
    void setOpenMandatController(OpenMandatController controller) {
        this.openMandatController = controller;
    }

    void setTris(String tris) {
        this.tris = tris;
    }
}
