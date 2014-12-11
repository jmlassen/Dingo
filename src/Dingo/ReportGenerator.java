/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mormon
 */
public class ReportGenerator {
    
    private int defaultChanges;
    private List<Change> changes;

    public ReportGenerator(int defaultChanges, List<Change> changes) {
        this.defaultChanges = defaultChanges;
        this.changes = changes;
    }
    
    // Main is only for testing purposes, I'll delete it once this is fully
    // implemented
    public static void main(String args[]) {  
        
        List<Change> changes = new ArrayList<>();    
        
        Change change = new Change();
        
        for (int i = 0; i < 5; i++) {
            change.setFilename("test");
            change.setType("deletion");
            change.setIsDirectory(false);
            change.setModified(new Date());
            change.setRevision(null);
            changes.add(change);
        }
        
        ReportGenerator report = new ReportGenerator(5, changes);
        report.getLastChanges();
        try {
            report.writeToFile("c:/Users/HEIDY2016/Desktop/Report_Backup.txt");
        } catch(FileNotFoundException ex) {
            System.out.println("Sorry, the file was not found");
        }
    }      
    
    /**
     * @param changes 
     */
    public void getLastChanges() {
        System.out.println("FILENAME\t\t\t CHANGE\t\t\t CHANGE DATE\n");
        
        // Check if there have been any changes recently
        if (!changes.isEmpty()) {
            // If the list of changes is smaller than the requested # of changes
            if (defaultChanges >= changes.size()) {
               for(Change ch:changes) {
                   System.out.println(ch.getFilename() + "\t\t\t\t" + ch.getType() + "\t\t" + 
                           ch.getDate().toString());
               } 
            } else {
                for (int i = 0; i < defaultChanges; i++) {
                    System.out.println(changes.get(i).getFilename() + "\t\t\t\t" +
                            changes.get(i).getType() + "\t\t" + changes.get(i).getDate().toString());
                }
            }
            
        } else {
            System.out.println("There are not recent changes to report");
        }  
    }
    
    /**
    * @param changesList
    * @param file
    * @throws java.io.FileNotFoundException
    **/    
    public void writeToFile(String file) throws FileNotFoundException {
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
