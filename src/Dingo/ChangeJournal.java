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
      System.out.println("Database is open!!!!");
      
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
      System.out.println("Opened database successfully");

      statement = connect.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS change_journal " +
                   "(ID             INTEGER     PRIMARY KEY     AUTOINCREMENT," +
                   " FILENAME       TEXT                        NOT NULL," + 
                   " TYPE           TEXT                        NOT NULL," + 
                   " IS_DIRECTORY   BOOLEAN                     ," + 
                   " DATE_MODIFIED  DATETIME                    ," +    
                   " REVISION       TEXT                        ," + 
                   " TIMSTAMP       DATETIME    DEFAULT         CURRENT_TIMESTAMP)"; 
      statement.executeUpdate(sql);
      statement.close();
      connect.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
        System.out.println("Table created!!!!");
    }
    
    /**
     * 
     */
    public void insertIntoTable() {
        Change change = new Change();
        Connection connect = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:dingo.db");
            connect.setAutoCommit(false);
            
            statement = connect.createStatement();
            String insertStatement = "INSERT INTO change_journal (FILENAME, TYPE, IS_DIRECTORY)" + 
                                     "VALUES ('" + change.getFilename() + "', " + change.getType() + "', " + change.isDirectory() + ");";
            statement.executeUpdate(insertStatement);
            
            statement.close();
            connect.commit();
            connect.close();
        } catch (Exception e) {
        }
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
