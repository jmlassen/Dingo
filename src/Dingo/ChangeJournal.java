/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;


import java.util.List;
import java.io.File;
import java.io.FileWriter;

/**
 * @author mormon
 */
public class ChangeJournal {
    
    /**
     * Make an XML document of the changes that were received
     * from the watchTowerServices.
     * @param change
     * @return 
     */
    public List<Change> getLastChanges(List<Change> change) throws Exception {
        return null;   
    }
   
    /**
     * Once again, this is an example as to what we are going to do. It will
     * be changed when needed.
     * @param change
     * @throws Exception 
     */
    public void appendLog(Change change) throws Exception {
        // use the write/append section under the bookmark(chrome)
        String outputFile = "c:/output.csv";
        
        // check to see of the file has been created
        boolean alreadyExists = new File(outputFile).exists();
    }
    
    /**
     * As of right now this is an example as to what we are going to do, but 
     * it will not take much to change it.
     * @param changes
     * @throws Exception 
     */
    public void readLog(Change changes) throws Exception {
        
    }
    
    /**
     * @param size
     * @return 
     */
    public float getFileSize(String size) {
        return 0;
    }

    List<Tower> getTowers() {
        return null;
    }

    void handleChanges(List<Change> changes) {
    }
    
    /**
     * make an XML file of towers
     *
    public List<Tower> getSavedWatchTowers() {
       // add the read XML part here
    }*/    
}
