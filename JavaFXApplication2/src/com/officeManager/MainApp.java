package com.officeManager;

import com.officeManager.model.Mandat;
import com.officeManager.view.RootLayoutController;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;


/**
 *
 * @author adrien.pillonel
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Mandat> mandats = FXCollections.observableArrayList();
    
    public MainApp(){
    }
    
    public static void main(String[] args) {
        launch(args);       
    }    

    @Override
    public void start(Stage primaryStage) { 
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Office Manager");
        
        initRootLayout();
        
        //showChoixMandat(); 
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            controller.showChoixMandat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//mandats.add(new Mandat(1,1,1,1,1,1,1,"num","nom","carton"));
//mandats.add(new Mandat(2,2,2,2,2,2,2,"num2","nom2","carton2"));