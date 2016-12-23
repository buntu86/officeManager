package com.officeManager.view.dialog;

import com.officeManager.MainApp;
import com.officeManager.data.Sql_listMandat;
import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import com.officeManager.view.RootLayoutController;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Button nouveau;

    @FXML
    private Button fermer;    
    
    private MainApp mainApp;
    private Stage dialogEditMandat = new Stage();
    private RootLayoutController rootLayout;
    
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
    
    public void updateListMandat(String str){
        Sql_listMandat mandatsSql = new Sql_listMandat();
        ObservableList<Mandat> mandats = mandatsSql.updateListMandats(str);
        if(mandats!=null && !mandats.isEmpty())
        {
            table.setItems(mandats);
        }
        else
            table.setItems(null);    
    }
    
    public void afficherMandat(){
        Log.msg(0, "OpenMandatController | CLICK afficher");
        rootLayout.getDialogOpenMandat().close();
    }

    public void fermerMandat(){
        Log.msg(0, "OpenMandatController | CLICK fermer");
        rootLayout.getDialogOpenMandat().close();
    }
    
    public void editerMandat(){
        dialogEditNewMandat("edit");
    }
    
    public void nouveauMandat(){
        dialogEditNewMandat("new");
    }

    public void setRootLayout(RootLayoutController rootLayout) {
        this.rootLayout = rootLayout;
    }
    
    private void dialogEditNewMandat(String str){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/dialog/EditMandat.fxml"));
            AnchorPane editMandat = (AnchorPane) loader.load();
            EditMandatController controller = loader.getController();
            
            if(str.equals("edit"))
                dialogEditMandat.setTitle("Edition mandat");
            else if(str.equals("new"))
                dialogEditMandat.setTitle("Nouveau mandat");
            dialogEditMandat.initModality(Modality.WINDOW_MODAL);
            dialogEditMandat.initOwner(rootLayout.getDialogOpenMandat());
            dialogEditMandat.setScene(new Scene(editMandat));              
            dialogEditMandat.showAndWait();           
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}
