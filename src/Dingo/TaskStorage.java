/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle storing our tasks.
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class TaskStorage {
    Connection dbConnection;
    Statement dbStatement;
    
    public TaskStorage() {
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
    
    /**
     * Inserts a task into the tasks table
     * @param task task created by user
     * @param name string name the user gave for the task
     * @param notes string notes the user gave for the task
     * @throws SQLException 
     */
    public void addTask(Task task) throws SQLException {
        List<String> flags = new ArrayList<>();
        for (Flag flag : task.getFlags()) {
            flags.add(flag.getFlagType());
        }
        
        // Holy string concatenation, Batman.
        String insertTask = "INSERT INTO Tasks " +
                "(NAME, NOTES, START_DATE, END_DATE, FILENAME, " +
                "EVENT_CREATED, EVENT_MODIFIED, EVENT_MOVED, EVENT_DELETED, " +
                "ACTIONS) VALUES (";
        insertTask += "'" + task.getName() + "', ";  // Add task name
        insertTask += "'" + task.getNotes() + "', "; // Add task notes
        insertTask += "'" + "', ";  // TODO add start date to task class
        insertTask += "'" + "', ";  // TODO add end date to task class
        insertTask += "'" + task.getFile() + "', ";
        insertTask += (flags.contains("creation")) ? 1 : 0 + ", ";
        insertTask += (flags.contains("alteration")) ? 1 : 0 + ", ";
        insertTask += (flags.contains("moveation")) ? 1 : 0 + ", ";
        insertTask += (flags.contains("deletion")) ? 1 : 0 + ", ";
        insertTask += "'" + addActions(task) + "')";
        System.out.println(insertTask);
        dbStatement.executeUpdate(insertTask);
    }
    
    /**
     * Takes all the actions from the tower and adds them to the actions table.
     * @param task
     * @return list of the action IDs created in a string
     */
    private String addActions(Task task) throws SQLException {
        String insertedActions = "";
        for (Action action : task.getActions()) {
            String insertAction = "INSERT INTO Actions " +
                    "(TYPE, ARGUMENT) VALUES (" +
                    "'" + action.getAction() + "'," + 
                    "'" + action.getArgument() + "')";

            dbStatement.execute(insertAction);
            // Get the ID of the record we just added
            ResultSet result = dbStatement.executeQuery("SELECT MAX(ID) FROM Tasks");
            insertedActions += result.getString(1) + ",";
        }
        // Remove the last comma
        return "0"; //insertedActions.substring(0, insertedActions.length() - 2);
    }

    /**
     * Generates and runs the CREATE TABLE statement for the tasks table.
     * @throws SQLException 
     */
    private void createTasksTable() throws SQLException {
        String createTasksTable = "CREATE TABLE IF NOT EXISTS Tasks " +
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
        String createActionsTable = "CREATE TABLE IF NOT EXISTS Actions " +
                "(ID                INTEGER         PRIMARY KEY     AUTOINCREMENT" +
                ",TYPE              TEXT            NOT NULL" + 
                ",ARGUMENT          TEXT            NOT NULL" +
                ",DATE_ADDED        DATETIME        DEFAULT     CURRENT_TIMESTAMP)";
        
        dbStatement.execute(createActionsTable);
    }

    List<Task> getTasks() {
        return new ArrayList<Task>();
    }
}
