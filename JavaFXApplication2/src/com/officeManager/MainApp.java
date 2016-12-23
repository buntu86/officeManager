package com.officeManager;

import com.officeManager.view.RootLayoutController;
import javafx.application.Application;
import javafx.stage.Stage;
import static javafx.application.Application.launch;


/**
 *
 * @author adrien.pillonel
 */
public class MainApp extends Application {
    private Stage primaryStage;
    
    public MainApp(){
    }
    
    public static void main(String[] args) {
        launch(args);       
    }    

    @Override
    public void start(Stage primaryStage) { 
        this.primaryStage = primaryStage;
        setTitlePrimaryStage("");
        
        RootLayoutController rootLayout = new RootLayoutController();
        rootLayout.setMainApp(this);
        rootLayout.show();
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setTitlePrimaryStage(String str)
    {
        if(!str.isEmpty())
            str = " - " + str;
        this.primaryStage.setTitle("Office Manager" + str);
    }
}

    /*public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/officeManager/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            controller.dialogChoixMandat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    




//mandats.add(new Mandat(1,1,1,1,1,1,1,"num","nom","carton"));
//mandats.add(new Mandat(2,2,2,2,2,2,2,"num2","nom2","carton2"));