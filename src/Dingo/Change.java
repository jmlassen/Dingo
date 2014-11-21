/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;

/**
 * @author mormon
 */
public class Change {
    
    private String filename;
    private String type; // deletion, alteration, or changeLoc
    
    /**
    * @return filename
    */
    public String getFilename() {
        return filename;
    }
    
    /**
    * @param filename
    */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
    * @return type
    */
    public String getType() {
        return type;
    }
    
    /**
    * @param type
    */
    public void setType(String type) {
        this.type = type;
    }            
}
