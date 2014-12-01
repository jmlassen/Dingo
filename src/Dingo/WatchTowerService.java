package Dingo;

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
        this.towers = towers;
    }   
    
    /**
     * 
     * @param tower
     */
    public void addTower (Tower tower) {  
        towers.add(tower);
    }
        
    /**
     *    
     * @param changes
     */
    public void handleChanges(List<Change> changes) {
        for (Change change: changes) {
            String filename = change.getFilename();
            // Loop through each of our towers
            for (Tower tower:towers) {
                // Find the file in the list of files been watched
                if (tower.getWatching().getPath().equals(filename)) {
                    // Let the tower handle the change
                    tower.handleChange(change);
                    
                    if (tower.checkFlag(filename)) {
                        List <Flag> tmpFlags = tower.getFlags();
                        for (Flag f: tmpFlags) {
                            if (f.getFlagType().equals(filename)) {
                                f.handleFlag(change.getType());
                            }
                        }
                    }
                }            
            }
        }
    }
}              