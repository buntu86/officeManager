package com.officeManager.view.dialog;

import com.officeManager.MainApp;
import com.officeManager.data.Sql_listMandat;
import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adrien.pillonel
 */
public class OpenMandatController {
    @FXML
    private TableView<Mandat> table;
            
    @FXML
    private TableColumn<Mandat, String> colStatut;

    @FXML
    private TableColumn<Mandat, String> colNum;

    @FXML
    private TableColumn<Mandat, String> colNom;

    @FXML
    private TableColumn<Mandat, String> colDebut;

    @FXML
    private TableColumn<Mandat, String> colArchive;
    
    @FXML
    private TableColumn<Mandat, String> colCarton;
    
    @FXML
    private Button afficher;
    
    @FXML
    private Button editer;
    
    @FXML
    private Button nouveau;

    @FXML
    private Button fermer;   
    
    @FXML
    private Button enCours;
    
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    
    @FXML
    private TextField rechercheTextField = new TextField();
    
    private MainApp mainApp;
    //private Stage dialogOpenMandat = new Stage();
    private Stage openMandatStage;
    private String tris, recherche;

    
    public OpenMandatController(){
    }

    @FXML
    private void initialize() {
        colStatut.setCellValueFactory(cellData -> cellData.getValue().statutProperty());
        colNum.setCellValueFactory(cellData -> cellData.getValue().numMandatProperty());
        colNom.setCellValueFactory(cellData -> cellData.getValue().nomMandatProperty());
        colDebut.setCellValueFactory(cellData -> cellData.getValue().dateDebutProperty());
        colArchive.setCellValueFactory(cellData -> cellData.getValue().dateArchiveProperty());
        colCarton.setCellValueFactory(cellData -> cellData.getValue().numCartonProperty());

    }    
    
    public void updateListMandat(String tris, String recherche){
        Sql_listMandat mandatsSql = new Sql_listMandat();
        this.tris = tris;
        this.recherche = recherche;
        ObservableList<Mandat> mandats = mandatsSql.updateListMandats(this.tris, this.recherche);
        if(mandats!=null && !mandats.isEmpty())
        {
            table.setItems(mandats);
        }
        else
            table.setItems(null);   
    }
    
    public void iniChoiceBox(){
        choiceBox.getItems().add("en cours");
        choiceBox.getItems().add("tous");
        choiceBox.getItems().add("archive");
        choiceBox.getSelectionModel().selectFirst();
        
        choiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> updateListMandat(newValue, this.recherche) );
    }
    
    public void listenerTextField(){
        rechercheTextField.textProperty().addListener((observable, oldValue, newValue) -> {updateListMandat(this.tris, newValue); Log.msg(0, "text field changed : " + newValue);});
        Platform.runLater(() -> {rechercheTextField.requestFocus();});
    }
    
   public void afficherMandat(){
        openMandatStage.close();
    }

    public void fermerMandat(){
        openMandatStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setOpenMandatStage(Stage openMandatStage) {
        this.openMandatStage = openMandatStage;
    }
}
