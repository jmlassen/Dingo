package ui;

import dingo.Dingo;

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
     * Adds a task to Dingo.
     */
    public void addTask() {
        // TODO: Get data from GUI and use it to create a task.
        
        // Update the list of tasks in GUI with Dingos tasks
        viewer.updateTaskTable(dingo.getTasks());
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
