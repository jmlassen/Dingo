package ui;

import dingo.Dingo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class DingoController {
    /**
     * TODO: Implement main function for Dingo program.
     * @param args 
     */
    public static void main(String[] args) {
        new DingoController();
    }
    
    /**
     * Controller Class Members.
     */
    Dingo dingo;
    DingoViewer viewer;
    
    public DingoController() {
        dingo = new Dingo();
    }
    
    /**
     * Dingo starts automatically, but we can start it if it has been stopped.
     */
    public void startDingo() {
        
    }
    
    /**
     * Stops Dingo from running.
     */
    public void stopDingo() {
        
    }
}
