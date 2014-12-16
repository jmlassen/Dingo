/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class rootController {
    @FXML
    private MenuBar mainMenuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
}
