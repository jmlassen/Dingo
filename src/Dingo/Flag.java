/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HEIDY2016
 */
public class Flag {
    // flagType can be deletion, alteration, and changeDirectory
    private String flagType;
    // There is a different action(reaction) for each flagType
    private List<Action> actions;
    
    /**
     * Constructor
     * @param flagType
     */
    public Flag(String flagType) {
        this.flagType = flagType;
        actions = new ArrayList<>();
    }        
    
     /**
     *
     */
    public String getFlagType() {
        return flagType;
    }

    /**
     *
     * @param flagType
     */
    public void setFlagType(String flagType) {
        this.flagType = flagType;
    }

    /**
     * @return actions
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     *
     * @param actions
     */
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }    
    
    /**
     *
     * @param actions
     */
    public void addActions(Action action) {
        actions.add(action);
    }   
    
    /**
     *
     * @param flag
     */
    public void handleFlag (String flag) {
        
        if (flag.equals(flagType)) {
            runAction();
        }
    }
    
    /**
     *
     */
    public void runAction() {
        for (Action a: actions) {
            a.run();
        }
    }   
}
