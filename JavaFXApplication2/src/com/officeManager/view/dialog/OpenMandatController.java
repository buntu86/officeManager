package com.officeManager.view.dialog;

import com.officeManager.MainApp;
import com.officeManager.data.Sql_listMandat;
import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import com.officeManager.utils.Tools;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
    private Button supprimer;
    
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
    
    @FXML
    private Label nbrResultats;
    
    private MainApp mainApp;
    private Stage openMandatStage;
    private String tris, recherche;
    private int idMandatSelected=0;

    
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
        Log.msg(0, "Size of mandat : " + mandats.size());
        nbrResultats.setText(Tools.convertIntToString(mandats.size()));
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
    public void iniButtons() {
        editer.setDisable(true);
        afficher.setDisable(true);
        supprimer.setDisable(true);
    }    
    
    public void listenerTextField(){
        rechercheTextField.textProperty().addListener((observable, oldValue, newValue) -> {updateListMandat(this.tris, newValue); Log.msg(0, "text field changed : " + newValue);});
        Platform.runLater(() -> {rechercheTextField.requestFocus();});
    }

    public void listenerTableView(){
        table.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> { 
                    if(newSelection!=null)
                    {
                        Log.msg(0, "mandat select : " + newSelection.getIdMandat());
                        idMandatSelected=newSelection.getIdMandat();
                        editer.setDisable(false);
                        afficher.setDisable(false);
                        supprimer.setDisable(false);
                    } 
                    else
                    {
                        editer.setDisable(true);
                        afficher.setDisable(true);
                        supprimer.setDisable(true);
                    }
                });        
    }
    
    public void showEditMandatDialog(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/officeManager/view/dialog/EditMandat.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage editMandatStage = new Stage();
            editMandatStage.setTitle("Edition du mandat");
            editMandatStage.initModality(Modality.WINDOW_MODAL);
            editMandatStage.initOwner(openMandatStage);
            Scene scene = new Scene(page);
            editMandatStage.setScene(scene);
            
            EditMandatController controller = loader.getController();
            controller.setEditMandatStage(editMandatStage);
            controller.iniChoiceBox();
            controller.setIdMandat(idMandatSelected);

            editMandatStage.showAndWait();
            
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }      
    
    
   public void afficherMandat(){
        openMandatStage.close();
    }

    public void fermerMandat(){
        openMandatStage.close();
    }

    public void setOpenMandatStage(Stage openMandatStage) {
        this.openMandatStage = openMandatStage;
    }
}
