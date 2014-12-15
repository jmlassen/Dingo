/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class DingoGuiController implements Initializable {
    @FXML
    private MenuItem closeGui;
    @FXML
    private TableView<?> taskTableView;
    @FXML
    private MenuItem startListen;
    @FXML
    private MenuItem stopListening;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonEvent(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleButtonEvent2(ActionEvent event) {
        System.exit(0);
    }

    
    @FXML
    void handleButtonEvent4(ActionEvent event) {
        System.exit(0);
    }
}
