package com.officeManager.view.dialog;

import com.officeManager.MainApp;
import com.officeManager.data.ConfigVar;
import com.officeManager.data.Sql_listMandat;
import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import com.officeManager.utils.Tools;
import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
    private TableColumn<Mandat, String> colPath;
    
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
    private Button ouvrir;
    
    @FXML
    private Separator separatorEdit;
    
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
        colPath.setCellValueFactory(cellData -> cellData.getValue().pathProperty());        
        if(ConfigVar.getUserAuth()==0)
        {
            nouveau.setVisible(false);
            editer.setVisible(false);
            supprimer.setVisible(false);
            separatorEdit.setVisible(false);
        }
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
    public void ouvrir() {
        String tmpPath = "";
        Sql_listMandat mandatsSql = new Sql_listMandat();
        Mandat mandat = mandatsSql.getMandatById(idMandatSelected);
        if(mandat!=null){
            tmpPath = mandat.getPath();
        }
        
        Log.msg(0, "mandat select " + mandat.getStatut());
        
        try{
            if(mandat.getStatut()=="en cours")
            {
                if(Paths.get(ConfigVar.getPathProjets() + tmpPath).toFile().exists())
                    Desktop.getDesktop().open(Paths.get(ConfigVar.getPathProjets() + tmpPath).toFile());
                else
                    Log.msg(1, "Le fichier " + ConfigVar.getPathProjets() + tmpPath + " n'existe pas.");
            }
            else
                if(Paths.get(ConfigVar.getPathArchive() + tmpPath).toFile().exists())
                    Desktop.getDesktop().open(Paths.get(ConfigVar.getPathArchive() + tmpPath).toFile());
                else
                    Log.msg(1, "Le fichier " + ConfigVar.getPathArchive() + tmpPath + " n'existe pas.");            
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
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
            controller.iniListener();
            controller.setIdMandat(idMandatSelected);
            controller.setOpenMandatController(this);
            controller.setTris(this.tris);  
            controller.setFromEditOrAdd("edit");
            
            editMandatStage.showAndWait();
            
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    public void showAddMandatDialog(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/officeManager/view/dialog/AddMandat.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage addMandatStage = new Stage();
            addMandatStage.setTitle("Ajouter du mandat");
            addMandatStage.initModality(Modality.WINDOW_MODAL);
            addMandatStage.initOwner(openMandatStage);
            Scene scene = new Scene(page);
            addMandatStage.setScene(scene);
            
            EditMandatController controller = loader.getController();
            controller.setEditMandatStage(addMandatStage);
            controller.iniChoiceBox();
            controller.iniListener();
            controller.setOpenMandatController(this);
            controller.setFromEditOrAdd("add");
            controller.setAdd();
            
            addMandatStage.showAndWait();
            
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    public void showDeleteAlert(){
        Sql_listMandat mandatsSql = new Sql_listMandat();
        Mandat mandat = mandatsSql.getMandatById(idMandatSelected);
        if(mandat!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression mandat");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiement supprimer le mandat suivant? \n" + mandat.getNumMandat() + " - " + mandat.getNomMandat());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Log.msg(0, "ok");
                mandatsSql.del(mandat.getIdMandat());
                updateListMandat(tris, recherche);
            } else {
                Log.msg(0, "cancel");
            }                
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
