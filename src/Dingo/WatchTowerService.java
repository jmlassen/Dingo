package Dingo;

import java.io.File;
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
        for (Change ch: changes) {
            String filename = ch.getFilename();
           for (Tower t:towers) {
                // Find the file in the list of files been watched
                if (t.getWatching().getPath().equals(filename)) {
                    if (t.checkFlag(filename)) {
                        //logEvent(file, flag);
                        List <Flag> tmpFlags = t.getFlags();
                        for (Flag f: tmpFlags) {
                            if (f.getFlagType().equals(filename)) {
                                f.handleFlag(ch.getType());
                            }
                        }
                    }
                }            
           }
       }
    }
}              