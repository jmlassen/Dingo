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
    private List<Action> actions;

    /**
     * Create Task with only the file, probably need to remove.
     * @param file 
     */
    public Task(String file) {
        this.file = new SimpleStringProperty(file);
        this.flags = new ArrayList<>();
        this.actions = new ArrayList<>();
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
        //System.out.println(this.file.get());
        this.flags = new ArrayList<>();
        this.actions = new ArrayList<>();
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
     * 
     * @param flags
     */
    public void setFlags(List<Flag> flags) {
        this.flags = flags;
    }
    
    /**
     * 
     * @param flag
     */
    public void addFlag(Flag flag) {
        flags.add(flag);
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
     * @param action 
     */
    public void addAction(Action action) {
        actions.add(action);
    }
    
    /**
     * 
     * @return 
     */
    public List<Action> getActions() {
        return actions;
    }
    
    
    /**
     *
     * @param event
     */
    public boolean checkFlag(String event) {
        for(Flag f:flags) {
            if (f.getType().equals(event)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param change 
     */
    public void handleChange(Change change) {
        for (Action action : actions) {
            action.run(change.getFilename(), new Flag(change.getType()));
        }
    }
}
