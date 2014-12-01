/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;

import java.util.Date;

/**
 * @author mormon
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class Change {
    
    private String filename;
    private String type;    // deletion, alteration
    private boolean isDirectory;
    private Date modified;
    private String revision;   // This will probably be useful later...

    /**
     * 
     * @return 
     */
    public String getRevision() {
        return revision;
    }

    /**
     * 
     * @param revision 
     */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
     * 
     * @return 
     */
    public Date getModified() {
        return modified;
    }

    /**
     * 
     * @param modified 
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    /**
     * 
     * @return 
     */
    public String getFilename() {
        return filename;
    }
    
    /**
     * 
     * @param filename 
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 
     * @return 
     */
    public String getType() {
        return type;
    }
    
    /**
     * 
     * @param type 
     */
    public void setType(String type) {
        this.type = type;
    }            

    /**
     * 
     * @return 
     */
    public boolean isDirectory() {
        return isDirectory;
    }

    /**
     * 
     * @param isDirectory 
     */
    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }   
    
    
}
