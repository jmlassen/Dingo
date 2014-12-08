/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HEIDY2016
 */
public class Tower {
    private File watching;
    private List<Flag> flags;

    public Tower(File watching) {
        this.watching = watching;
        this.flags = new ArrayList<>();
    }
        
    /**
     *
     * @param file
     */
    public void setWatching (File file) {
        this.watching = file;
    }
    
    /**
     *
     * @return file
     */
    public File getWatching () {
        return watching;
    }
    
    /**
     *
     * @return flags
     */
    public List<Flag> getFlags() {
        return flags;
    }
    
    /**
     * @param flags
     */
    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }
    
    /**
     * @param flag
     */
    public void addFlags(Flag flag) {
        this.flags.add(flag);
    }
    
    /**
     *
     * @param event
     */
    public boolean checkFlag(String event) {
        for(Flag f:flags) {
            if (f.getFlagType().equals(event)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param change 
     */
    void handleChange(Change change) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
