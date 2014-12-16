/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HEIDY2016
 */
public class Flag {
    private StringProperty type;
    
    /**
     * Constructor
     * @param flagType
     */
    public Flag(String flagType) {
        this.type = new SimpleStringProperty(flagType);
    }        
    
    /**
     * 
     * @param type 
     */
    public void setFlagType(String type) {
        this.type = new SimpleStringProperty(type);
    }
    
    /**
     * 
     * @return 
     */
    public String getType() {
        return type.get();
    }
    
    /**
     * 
     * @return 
     */
    public StringProperty getTypeProperty() {
        return type;
    }
}
