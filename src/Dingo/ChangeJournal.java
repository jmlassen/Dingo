/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;


import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;

/**
 * @author mormon
 */
public class ChangeJournal {
    
    ChangeJournal() {
        createDatabase();
    }
    
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
     * 
     */
    public void createDatabase() {
        Connection connect = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:dingo.db");
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
        }
      createTable();
    }
    
    /**
     * 
     */
    public void createTable() {
        Connection connect = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:dingo.db");
      
            statement = connect.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS change_journal " +
                         "(ID             INTEGER     PRIMARY KEY     AUTOINCREMENT," +
                         " FILENAME       TEXT                        NOT NULL," + 
                         " TYPE           TEXT                        NOT NULL," + 
                         " IS_DIRECTORY   BOOLEAN                     ," + 
                         " DATE_MODIFIED  DATETIME                    ," +    
                         " REVISION       TEXT                        ," + 
                         " TIMESTAMP      DATETIME    DEFAULT         CURRENT_TIMESTAMP)"; 
         statement.executeUpdate(sql);
         statement.close();
         connect.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    
    /**
     * 
     */
    public void insertIntoJournal(Change change) {
        Connection connect = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:dingo.db");
            connect.setAutoCommit(false);
            
            statement = connect.createStatement();
            String insertStatement = "INSERT INTO change_journal (FILENAME, TYPE, IS_DIRECTORY, DATE_MODIFIED, REVISION) " + 
                                     "VALUES ('" + change.getFilename() + "', '" + change.getType() + 
                                     "',"; 
            
            insertStatement += (change.isDirectory() ? 1 : 0) + ", " + change.getModified();
            
            // this will add the quotes depending on wheter it is null or not
            if (change.getRevision() != null) {
                insertStatement += ", '" + change.getRevision() + "');";
            } else {
                insertStatement += ", " + change.getRevision() + ");";
            }
            
            System.out.println(insertStatement);
            statement.executeUpdate(insertStatement);
            
            statement.close();
            connect.commit();
            connect.close();
        } catch (Exception e) {
        }
    }
    
    /**
     * @param size
     * @return 
     */
    public float getFileSize(String size) {
        return 0;
    }

    /**
     * 
     * @return 
     */
    List<Tower> getTowers() {
        return null;
    }

    /**
     * 
     * @param changes 
     */
    void handleChanges(List<Change> changes) {
        for (Change change : changes) {
            insertIntoJournal(change);
        }
    }
    
    /**
     * make an XML file of towers
     *
    public List<Tower> getSavedWatchTowers() {
       // add the read XML part here
    }*/    
}
