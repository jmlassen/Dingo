/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mormon
 */
public class ReportGenerator {
    
    private int defaultChanges;
    private List<Change> changes;
    private String file;

    public ReportGenerator(int defaultChanges, List<Change> changes) {
        this.defaultChanges = defaultChanges;
        this.changes = changes;
        this.file = "c:/Users/HEIDY2016/Desktop/Report_Backup.txt";
    }
    
    // Main is only for testing purposes, I'll delete it once this is fully
    // implemented
    public static void main(String args[]) {  
        
        List<Change> changes = new ArrayList<>();    
                        
        for (int i = 0; i < 5; i++) {  
            Change change = new Change();
            if ((i % 2) == 0) {                
                change.setType("alteration");                 
            } else {                
                change.setType("deletion  ");                 
            }
            change.setFilename("test");
            change.setIsDirectory(false);
            change.setModified(new Date());
            change.setRevision(null);
            changes.add(change);
        }
        
        ReportGenerator report = new ReportGenerator(5, changes);
        report.getLastChanges();        
    }      
    
    /**
     * @param changes 
     */
    public void getLastChanges() {
        try {
            writeToFile();
            // Open the file to leave it ready to be read
            BufferedReader reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /**
    * @param changesList
    * @param file
    * @throws java.io.FileNotFoundException
    **/    
    public void writeToFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        String content = "";
        System.out.println("Saving document " + file);
        
        writer.println("FILENAME\t\t\t CHANGE\t\t\t CHANGE DATE\n");
        
        // Check if there have been any changes recently
        if (!changes.isEmpty()) {
            // If the list of changes is smaller than the requested # of changes
            if (defaultChanges >= changes.size()) {
               for(Change ch:changes) {
                   writer.println(ch.getFilename() + "\t\t\t\t" + ch.getType() + "\t\t" + 
                           ch.getDate().toString());
               } 
            } else {
                // Display only the number of requested changes, not the whole list
                for (int i = 0; i < defaultChanges; i++) {
                    writer.println(changes.get(i).getFilename() + "\t\t\t\t" +
                            changes.get(i).getType() + "\t\t" + changes.get(i).getDate().toString());
                }
            }            
        } else {
            System.out.println("There are not recent changes to report");
        }  
        writer.close(); 
    }
        
    /**
     * 
     */
    void getFilesSize(File file) {
        
    }
}
