package ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TaskOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> taskNameColumn;
    @FXML
    private TableColumn<Person, String> taskFileColumn;
    @FXML
    private TableColumn<Person, String> taskNotesColumn;

    // Reference to the main application.
    private DingoLoader mainApp;

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
        taskNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        taskFileColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        taskNotesColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(DingoLoader mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
}