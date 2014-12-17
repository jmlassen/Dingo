package ui;

import dingo.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;

public class TaskOverviewController {
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, String> taskFileColumn;
    @FXML
    private TableColumn<Task, String> taskNotesColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    
    // Reference to the main application.
    private DingoLoader dingoLoader;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TaskOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        taskFileColumn.setCellValueFactory(cellData -> cellData.getValue().getFileProperty());
        taskNotesColumn.setCellValueFactory(cellData -> cellData.getValue().getNotesProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param dingoApp
     */
    public void setMainApp(DingoLoader dingoApp) {
        this.dingoLoader = dingoApp;

        // Add observable list data to the table
        taskTable.setItems(dingoApp.getTaskData());
    }
    
    @FXML
    private void handleNewTask() {
        Task tempTask = new Task("", "", "");
        boolean okClicked = dingoLoader.showTaskEditDialog(tempTask);
        if (okClicked) {
            dingoLoader.getTaskData().add(tempTask);
        }
    }
    
    @FXML
    private void handleEditTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            boolean okClicked = dingoLoader.showTaskEditDialog(selectedTask);
        } else {
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Task Selected")
                    .message("Please select a task from the table")
                    .showWarning();
        }
    }
}