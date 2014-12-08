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
            
            // Create the tasks and actions tables if we are running program for first time
            createTasksTable();
            createActionsTable();
        } catch(Exception ex) {
        }
    }

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

    private void createActionsTable() throws SQLException {
        String createActionsTable = "CREATE TABLE IF NOT EXISTS actions" +
                "(ID                INTEGER         PRIMARY KEY     AUTOINCREMENT" +
                ",TYPE              TEXT            NOT NULL" + 
                ",ARGUMENT          TEXT            NOT NULL" +
                ",DATE_ADDED        DATETIME        DEFAULT     CURRENT_TIMESTAMP)";
        
        dbStatement.execute(createActionsTable);
    }
}
