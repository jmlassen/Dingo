/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dingo.Tower;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    private TableView<Tower> personTable;
    @FXML
    private TableColumn<Tower, String> firstNameColumn;
    @FXML
    private TableColumn<Tower, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private DingoLauncher launcher;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public Controller() {
    }

    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNotes());
    }

    public void setMainApp(DingoLauncher launcher) {
        this.launcher = launcher;

        // Add observable list data to the table
        personTable.setItems(launcher.getTasks());
    }
}