package dingo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HEIDY2016
 */
public class WatchTowerService {
    private List<Tower> towers;
    
    public WatchTowerService(List<Tower> towers) {
        this.towers = new ArrayList<>();
        //this.towers = towers;
    }   
    
    /**
     * 
     * @param tower
     */
    public void addTower (Tower tower) {  
        towers.add(tower);
    }
    
    /**
     * Return back our towers, for the GUI.
     * @return 
     */
    public List<Tower> getTowers() {
        return towers;
    }
    
    /**
     *    
     * @param changes
     */
    public void handleChanges(List<Change> changes) {
        for (Change change: changes) {
            // Loop through each of our towers
            for (Tower tower:towers) {
                // Find the file in the list of files been watched
                if (tower.getWatching().getPath().equals(change.getFilename())) {
                    // Let the tower handle the change
                    tower.handleChange(change);
                }            
            }
        }
    }
}              