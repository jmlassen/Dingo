package ui;

import dingo.Dingo;
import dingo.Tower;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class DingoLauncher extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Dingo dingo;
    
    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Tower> tasks = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public DingoLauncher() {
        dingo = new Dingo();
        tasks.add(new Tower(new File("foo.txt")));
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Tower> getTasks() {
        return tasks;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Dingo");

        initRootLayout();

        showTaskOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DingoLauncher.class.getResource("ui/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTaskOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DingoLauncher.class.getResource("ui/TaskOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(personOverview);
            
            Controller controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}