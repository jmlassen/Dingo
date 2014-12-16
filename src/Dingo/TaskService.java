package dingo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HEIDY2016
 */
public class TaskService {
    private List<Task> towers;
    
    public TaskService(List<Task> towers) {
        this.towers = new ArrayList<>();
        //this.towers = towers;
    }   
    
    /**
     * 
     * @param tower
     */
    public void addTower (Task tower) {  
        towers.add(tower);
    }
    
    /**
     * Return back our towers, for the GUI.
     * @return 
     */
    public List<Task> getTowers() {
        return towers;
    }
    
    /**
     *    
     * @param changes
     */
    public void handleChanges(List<Change> changes) {
        for (Change change: changes) {
            // Loop through each of our towers
            for (Task tower:towers) {
                // Find the file in the list of files been watched
                if (tower.getFile().equals(change.getFilename())) {
                    // Let the tower handle the change
                    tower.handleChange(change);
                }            
            }
        }
    }
}              