package logging;

import java.io.File;

/**
 *
 * @author Joel Lassen
 */
public class Logger {
    private static Logger instance = new Logger();
    private File logFile;
    
    /**
     * Allows user to access the functions of the Logger throughout all their program.
     * @return the instance of the Logger 
     */
    public static Logger getInstance() {
        return instance;
    }
    
    /**
     * Called when the user wants to log a message to the log file.
     * @param logString text to be logged
     */
    public void Log(String logString) {
        
    }
    
    /**
     * Initialize where the log is getting saved to.
     */
    private Logger() {
        // Get the home for the user account
        String logFileStr = System.getProperty("user.home");
        // Get the directory seporator for the users system, and add our own dir
        logFileStr += File.separator + ".dingoLog" + File.separator;
        // Add the name of the log file
        logFileStr += "log.out";
        // Initialize where the log file is going to get saved.
        logFile = new File(logFileStr);
    }
}
