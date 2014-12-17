package dingo;

/**
 * This class will run commands or programs in the command line
 * @author HEIDY2016
 */
import java.io.*;  

public class CommandPrompt { 
    private String execute;    // It contains the ommand to be typed in the cmd line
    private String task;       // Start a program or Run a command

    // Main is only for testing purposes
    public static void main(String args[]) {  
        new CommandPrompt("dir", "Run Command" ).run();
        new CommandPrompt("notepad", "Start Program" ).run();
    }
    
    // Constructor needs a command to be executed, and a task to specify
    // if a program is being started or a command run
    public CommandPrompt (String execute, String task) {
        this.execute = execute;
        this.task = task;
    }
    
    /**
     * Runs a command or a program depending on what the task is being 
     * requested.
     */    
    public void run() {
      try {
        String line;
        String command = null;           
        
        if (task.equals("Run Command")) {
            command = "cmd /c " + execute; // open the command line & run command
        } else if (task.equals("Start Program")) {            
            command = execute;  // run a program from the command line
        } else {
            System.out.println("Error: The task is not recognized");
        }
        
        Process p = Runtime.getRuntime().exec(command);
       
        p.waitFor();
      }
      catch (Exception err) {
        err.printStackTrace();
      }
    }
}