package Dingo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HEIDY2016
 */
public class WatchTowerService {
    private List<Tower> towers = new ArrayList<>();
   
    
    /**
     * 
     * @param tower
     */
    public void addTower (Tower tower) {  
        towers.add(tower);
    }
        
    /**
     *
     * @param file
     * @param flag
     */
    public void handleEvent(File file, Flag flag) {
        
        for (Tower t:towers) {
            if (t.getWatching() == file) {
                // if the flag matches one of the flags we want to watch for that file
                // then we want to send a logEvent and handle the event
                if (t.checkFlag(flag.getFlagType())) {
                    logEvent(file, flag);
                    List <Flag> tmpFlags = t.getFlags();
                    for (Flag f: tmpFlags) {
                        if (f.getFlagType().equals(flag.getFlagType())) {
                            flag.handleFlag(flag.getFlagType());
                        }
                    }
                }
            }
        }
    }
    
    /**
     *
     * @param file
     * @param flag
     */
    public void logEvent(File file, Flag flag) {
        // What exactly is the log? -> xmlService
    }
}              