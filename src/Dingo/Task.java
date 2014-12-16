/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HEIDY2016
 */
public class Task {
    private StringProperty name;
    private StringProperty notes;
    private StringProperty file;
    private List<Flag> flags;

    /**
     * Create Task with only the file, probably need to remove.
     * @param file 
     */
    public Task(String file) {
        this.file = new SimpleStringProperty(file);
        this.flags = new ArrayList<>();
    }
    
    /**
     * Create Task with the name, notes, and file.
     * @param name
     * @param notes
     * @param file 
     */
    public Task(String name, String notes, String file) {
        this.name = new SimpleStringProperty(name);
        this.notes = new SimpleStringProperty(notes);
        this.file = new SimpleStringProperty(file);
    }
    
    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
    
    /**
     * 
     * @return 
     */
    public String getName() {
        return name.get();
    }
    
    /**
     * 
     * @return 
     */
    public StringProperty getNameProperty() {
        return name;
    }
    
    /**
     * 
     * @param notes 
     */
    public void setNotes(String notes) {
        this.notes = new SimpleStringProperty(notes);
    }
    
    /**
     * 
     * @return 
     */
    public String getNotes() {
        return notes.get();
    }
    
    /**
     * 
     * @return 
     */
    public StringProperty getNotesProperty() {
        return notes;
    }
    
    /**
     *
     * @param file
     */
    public void setFile (String file) {
        this.file = new SimpleStringProperty(file);
    }
    
    /**
     *
     * @return file
     */
    public String getFile() {
        return file.get();
    }
    
    /**
     * 
     * @return 
     */
    public StringProperty getFileProperty() {
        return file;
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
