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
    
    public TaskStorage() {
        try {
            // Initialize connections to the dingo database
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:dingo.db");
            Statement dbStatement = dbConnection.createStatement();
            
            // Generate and run CREATE TABLE statements
            createTasksTable();
            createActionsTable();
            
        } catch(Exception ex) {
        }
    }
    
    /**
     * Inserts a task into the tasks table
     * @param task task created by user
     * @throws SQLException 
     */
    public void addTask(Task task) throws SQLException {
        List<String> flags = new ArrayList<>();
        for (Flag flag : task.getFlags()) {
            flags.add(flag.getType());
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
        insertTask += (flags.contains("creation") ? 1 : 0) + ", ";
        insertTask += (flags.contains("alteration") ? 1 : 0) + ", ";
        insertTask += (flags.contains("moveation") ? 1 : 0) + ", ";
        insertTask += (flags.contains("deletion") ? 1 : 0) + ", ";
        insertTask += "'" + addActions(task) + "')";
        // System.out.println(insertTask);
        Statement dbStatement = dbConnection.createStatement();
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
            
            Statement dbStatement = dbConnection.createStatement();
            dbStatement.execute(insertAction);
            // Get the ID of the record we just added
            ResultSet result = dbStatement.executeQuery("SELECT MAX(ID) FROM Actions;");
            insertedActions += result.getString(1) + ",";
        }
        // Remove the last comma
        return insertedActions.substring(0, insertedActions.length() - 1);
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
            
        Statement dbStatement = dbConnection.createStatement();
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
        
        Statement dbStatement = dbConnection.createStatement();
        dbStatement.execute(createActionsTable);
    }

    /**
     * Selects tasks from Tasks table, returns a list to be used luego.
     * @return 
     */
    List<Task> getTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        Statement dbStatement = dbConnection.createStatement();
        ResultSet tasksSet = dbStatement.executeQuery("SELECT * FROM Tasks;");

        while (tasksSet.next()) {
            String name = tasksSet.getString("NAME");
            String notes = tasksSet.getString("NOTES");
            String file = tasksSet.getString("FILENAME");
            Task task = new Task(name, notes, file);

            //System.out.println(name + "\t" + notes + "\t" + file);

            // Get flags
            if (tasksSet.getString("EVENT_CREATED").equals("1")) {
                task.addFlag(new Flag("creation"));
            } if (tasksSet.getString("EVENT_MODIFIED").equals("1")) {
                task.addFlag(new Flag("alteration"));
            } if (tasksSet.getString("EVENT_MOVED").equals("1")) {
                task.addFlag(new Flag("moveation"));
            } if (tasksSet.getString("EVENT_DELETED").equals("1")) {
                task.addFlag(new Flag("deletion"));
            }

            // Get the actions string
            String actionsStr = tasksSet.getString("ACTIONS");
            String[] actionIds = actionsStr.split(",");

            // Loop through each associated action
            for (String actionId : actionIds) {
                Statement actionStatement = dbConnection.createStatement();
                ResultSet actionSet = actionStatement.executeQuery(
                        "SELECT * FROM Actions WHERE ID = '" + actionId + "';");
                while (actionSet.next()) {
                    String type = actionSet.getString("TYPE");
                    String argument = actionSet.getString("ARGUMENT");
                    task.addAction(new Action(type, argument));
                }
                actionSet.close();
            }

            tasks.add(task);
        }

        tasksSet.close();
        
        return tasks;
    }
}
