/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to handle storing our tasks.
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class TaskManager {
    Connection dbConnection;
    Statement dbStatement;
    
    public TaskManager() {
        try {
            // Initialize connections to the dingo database
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:dingo.db");
            dbStatement = dbConnection.createStatement();
            
            // Generate and run CREATE TABLE statements
            createTasksTable();
            createActionsTable();
            
        } catch(Exception ex) {
        }
    }
    
    public void addTask(Tower tower, String name, String notes) {
        String insertTower = "INSERT INTO tasks " +
                "(NAME, NOTES, START_DATE, END_DATE, FILENAME, " +
                "EVENT_CREATED, EVENT_MODIFIED, EVENT_MOVED, EVENT_DELETED, " +
                "ACTIONS) VALUES (";
        insertTower += "'" + name + "', ";  // Add task name
        insertTower += "'" + notes + "', "; // Add task notes
        insertTower += "";  // TODO add start date to tower class
        insertTower += "";  // TODO add end date to tower class
        insertTower += "'" + tower;
        try {
            dbStatement.executeUpdate(insertTower);
        } catch (SQLException ex) {
            Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Generates and runs the CREATE TABLE statement for the tasks table.
     * @throws SQLException 
     */
    private void createTasksTable() throws SQLException {
        String createTasksTable = "CREATE TABLE IF NOT EXISTS tasks " +
                "(ID                INTEGER         PRIMARY KEY     AUTOINCREMENT" +
                ",NAME              TEXT            NOT NULL" +
                ",NOTES             TEXT" +
                ",START_DATE        DATETIME" +
                ",END_DATE          DATETIME" +
                ",FILENAME          TEXT            NOT NULL" +
                ",EVENT_CREATED     BOOLEAN" +
                ",EVENT_MODIFIED    BOOLEAN" +
                ",EVENT_MOVED       BOOLEAN" +
                ",EVENT_DELETED     BOOLEAN" +
                ",ACTIONS           TEXT" +
                ",DATE_ADDED        DATETIME        DEFAULT     CURRENT_TIMESTAMP)";
            
            dbStatement.execute(createTasksTable);
    }

    /**
     * Generates and runs the CREATE TABLE statement for the actions table.
     * @throws SQLException 
     */
    private void createActionsTable() throws SQLException {
        String createActionsTable = "CREATE TABLE IF NOT EXISTS actions " +
                "(ID                INTEGER         PRIMARY KEY     AUTOINCREMENT" +
                ",TYPE              TEXT            NOT NULL" + 
                ",ARGUMENT          TEXT            NOT NULL" +
                ",DATE_ADDED        DATETIME        DEFAULT     CURRENT_TIMESTAMP)";
        
        dbStatement.execute(createActionsTable);
    }
}
