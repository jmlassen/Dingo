/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

/**
 *
 * @author HEIDY2016
 */
public class Flag {
    // flagType can be deletion, alteration, and changeDirectory
    private String flagType;
    // Recipient of the sendEmail notification for this flag
    private String to;  
    
    /**
     * Constructor
     * @param flagType
     */
    public Flag(String flagType) {
        this.flagType = flagType;
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
}
