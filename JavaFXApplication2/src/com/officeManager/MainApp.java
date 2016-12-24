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
    private final RootLayoutController rootLayout = new RootLayoutController();
    
    public MainApp(){
    }
    
    public static void main(String[] args) {
        launch(args);       
    }    

    @Override
    public void start(Stage primaryStage) { 
        this.primaryStage = primaryStage;
        setTitlePrimaryStage("");
        
        rootLayout.setMainApp(this);
        rootLayout.showRootLayout();
    }
    
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }
    
    public RootLayoutController getRootLayout(){
        return this.rootLayout;
    }

    public void setTitlePrimaryStage(String str)
    {
        if(!str.isEmpty())
            str = " - " + str;
        this.primaryStage.setTitle("Office Manager" + str);
    }
}

//mandats.add(new Mandat(1,1,1,1,1,1,1,"num","nom","carton"));
//mandats.add(new Mandat(2,2,2,2,2,2,2,"num2","nom2","carton2"));