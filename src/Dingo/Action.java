/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;

import java.io.File;

/**
 *
 * @author HEIDY2016
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class Action {
    private String action;
    private Flag flag;
    private File file;
    private String type;
    
    /**
     * @param action
     * @param flag
     * @param file
     */
    public Action(String action, Flag flag, File file) {
        this.action = action;
        this.flag = flag;
        this.file = file;
    }
    
    /**
     *
     * @param action
     */
    public void setAction (String action) {
        this.action = action;
    }
    
    /**
     *
     * @return action
     */
    public String getAction () {
        return action;
    }
    /**
     *
     * @return flag
     */
    
    public Flag getFlag() {
        return flag;
    }
    
    /**
     *
     * @param flag
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }
    
    /**
     *
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }
            
    /***********************************************************
     *
     */
    public void run() {
        if (type.equals("email")) {
            email();
        }
    }
    
    public void email() {
        if (flag.getFlagType().equals("deletion")) {
            // Send email  
            System.out.println("Sending an email: " + file.getPath() + " has been deleted");
        } else if (flag.getFlagType().equals("alteration")) {
            // Send email
            System.out.println("Sending an email: " + file.getPath() + " has been altered");
        } else if (flag.getFlagType().equals("changeLoc")) {
            // Send email
            System.out.println("Sending an email: " + file.getPath() + " has been changed");
        } else {
            System.out.println("Error: Flag is not recognized");
        }
    }
}
