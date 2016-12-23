package old;

import com.officeManager.MainApp;
import com.officeManager.data.Sql_listMandat;
import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class OldOpenMandatController implements Initializable {
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
    
    private ObservableList<Mandat> mandats = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        colStatut.setCellValueFactory(cellData -> cellData.getValue().statutProperty());
        colNum.setCellValueFactory(cellData -> cellData.getValue().numMandatProperty());
        colNom.setCellValueFactory(cellData -> cellData.getValue().nomMandatProperty());
        colDebut.setCellValueFactory(cellData -> cellData.getValue().dateDebutProperty());
        colArchive.setCellValueFactory(cellData -> cellData.getValue().dateArchiveProperty());
        colCarton.setCellValueFactory(cellData -> cellData.getValue().numCartonProperty());
    }
    
    public void show(){
        MainApp mainApp = new MainApp();
        Sql_listMandat mandatsSql = new Sql_listMandat();
        mandats = mandatsSql.updateListMandats("all");
        if(mandats!=null && !mandats.isEmpty())
        {
            Log.msg(0, "OpenMandatController | Test mandat"); 
            table.setItems(mandats);
        }
        else
            table.setItems(null);

        
        Log.msg(0, "OpenMandatController | getNomMandat:" + mandats.get(0).getNomMandat());

        try{
            AnchorPane openMandat = (AnchorPane) FXMLLoader.load(MainApp.class.getResource("view/dialog/OpenMandat.fxml"));

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ouvrir mandat");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            dialogStage.setScene(new Scene(openMandat));              
            dialogStage.showAndWait(); 
            
          
        }
        catch(IOException e)
        {
            Log.msg(1, "OpenMandatController | " + e.getMessage());
        }
    }
}

           /*FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/dialog/OpenMandat.fxml"));
            AnchorPane openMandat = loader.load();*/