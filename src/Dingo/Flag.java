/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HEIDY2016
 */
public class Flag {
    // flagType can be deletion, alteration, and changeDirectory
    private String flagType;
    // Recipient of the sendEmail notification for this flag
    private String to;  
    // There is a different action(reaction) for each flagType
    private List<Action> actions;
    
    /**
     * Constructor
     * @param flagType
     */
    public Flag(String flagType, String to) {
        this.flagType = flagType;
        this.to = to;
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
     * getter for the recipient of the sendEmail notification
     * @return 
     */
    public String getTo() {
        return to;
    }

    /*
    * set the recipient of the sendEmail notification
    * @param to
    */
    public void setTo(String to) {
        this.to = to;
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
