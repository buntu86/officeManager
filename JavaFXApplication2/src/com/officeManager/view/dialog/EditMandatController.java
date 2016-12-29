package com.officeManager.view.dialog;

import com.officeManager.data.Sql_listMandat;
import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import com.officeManager.utils.Tools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    private Stage editMandatStage, openMandatStage;
    private int idMandat=0;
    private Mandat mandat=null;
    private OpenMandatController openMandatController;
    private String tris;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    }   
    
    public void setIdMandat(int i) {
        Log.msg(0, "idMandat " + i);
        Sql_listMandat mandatsSql = new Sql_listMandat();
        
        mandat = mandatsSql.getMandatById(i);
        if(mandat!=null){
            numMandat.setText(mandat.getNumMandat());
            nomMandat.setText(mandat.getNomMandat());
            dateDebut.setText(mandat.getDateDebut());
            dateArchive.setText(Tools.convertIntToString(mandat.getDateArchive()));
            numCarton.setText(mandat.getNumCarton());
            choiceBox.getSelectionModel().select(mandat.getIdStatut());
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
            mandat.setNumCarton(numCarton.getText());
            
            Sql_listMandat mandatsSql = new Sql_listMandat();

            //test if numMandat isn't empty after trim, if not don't close and don't update list
            if(mandatsSql.update(mandat))
            {
                editMandatStage.close();
                openMandatController.updateListMandat(this.tris, "");
            }
        }
    }

    void setOpenMandatController(OpenMandatController controller) {
        this.openMandatController = controller;
    }

    void setTris(String tris) {
        this.tris = tris;
    }
}
