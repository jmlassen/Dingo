package ui;

import dingo.Dingo;
import dingo.Task;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DingoLoader extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Dingo dingo;
    
    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Task> taskData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public DingoLoader() {
        dingo = new Dingo();
        
        for (Task task : dingo.getTasks()) {
            taskData.add(task);
        }
        
    }
    
    /**
     * 
     * @return 
     */
    public Dingo getDingo() {
        return dingo;
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Task> getTaskData() {
        return taskData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Task Overview");

        initRootLayout();

        showTaskOverview();
    }
    
    /**
     * Quick fix, my good chum.
     */
    @Override
    public void stop() {
        dingo.setListening(false);
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DingoLoader.class.getResource("/ui/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.   setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showTaskOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DingoLoader.class.getResource("/ui/TaskOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            // Give the controller access to the main app.
            TaskOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param task
     * @return 
     */
    public boolean showTaskEditDialog(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DingoLoader.class.getResource("/ui/TaskEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage taskEditStage = new Stage();
            taskEditStage.setTitle("Edit Task");
            taskEditStage.initModality(Modality.WINDOW_MODAL);
            taskEditStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            taskEditStage.setScene(scene);
            
            TaskEditDialogController controller = loader.getController();
            controller.setTaskEditStage(taskEditStage);
            controller.setTask(task);
            
            taskEditStage.showAndWait();
            
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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