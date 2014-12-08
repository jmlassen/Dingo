package Dingo;

/**
 * This class will run commands or programs in the command line
 * @author HEIDY2016
 */
import java.io.*;  

public class CommandPrompt { 
    private String execute;    // It contains the ommand to be typed in the cmd line
    private String task;       // Start a program or Run a command

    public static void main(String args[]) {  // This is for testing purposes
        new CommandPrompt("dir", "Run Command" ).run();
    }
    
    // Constructor needs a command to be executed
    public CommandPrompt (String execute, String task) {
        this.execute = execute;
        this.task = task;
    }
    
    public void run() {
      try {
        String line;
        Process p = Runtime.getRuntime().exec("cmd /c ");
        
        
        if (task.equals("Run Command")) {
            p = Runtime.getRuntime().exec("cmd /c " + execute); // opens the command linr & run command
        } else if (task.equals("Start Program")) {            
            p = Runtime.getRuntime().exec(execute); //run a program from the command line
        }
                
        BufferedReader readInfo = new BufferedReader
          (new InputStreamReader(p.getInputStream()));
        BufferedReader readError = new BufferedReader
          (new InputStreamReader(p.getErrorStream()));
        
        // Display any data to the user
        while ((line = readInfo.readLine()) != null) {
          System.out.println(line);
        }
        readInfo.close();
        
        // Inform if there are errors after running a command
        while ((line = readError.readLine()) != null) {
          System.out.println(line);
        }
        readError.close();
       
        p.waitFor();
        System.out.println("Done working on command line.");
      }
      catch (Exception err) {
        err.printStackTrace();
      }
    }
}

// batch file - runSomething.bat (in windows)
// in linux/mac - .sh "shell script"
