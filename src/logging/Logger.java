package logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to allow the rest of the program to log files whilst only having one instance of the Logger
 * object. To log a file, call using Logger.getInstance().Log("Here is my message to log")
 * @author Joel Lassen
 */
public class Logger {
    // Initialized when program starts
    private static Logger instance = new Logger();
    // Where the log is getting saved to
    private File logFile;
    // The format of the timestamp
    private DateFormat formatTimeStamp;
    
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
        // Get timestamp
        String timeStamp = formatTimeStamp.format(Calendar.getInstance().getTime());
        try {
            // Create the buffered writer and declare that we are appending the file
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
            // Log the timestamped message
            bw.write(timeStamp + " - " + logString);
            // Add a new line
            bw.newLine();
            // Close the writer
            bw.close();
        } catch (Exception ex) {    // Do nothing if exception is thrown
        }
    }
    
    /**
     * Initialize where the log is getting saved to and timestamp format.
     */
    private Logger() {
        // Get the home for the user account
        String logFileStr = System.getProperty("user.home");
        // Get the directory seporator for the users system, and add our own dir
        logFileStr += File.separator + ".dingo" + File.separator;
        String logDirStr = logFileStr += File.separator + "Logs" + File.separator;
        // Add the name of the log file
        logFileStr += "log.out";
        // Initialize where the log file is going to get saved.
        logFile = new File(logFileStr);
        // Create save directory
        File logDir = new File(logDirStr);
        // Create file and director if not created yet
        if (!logFile.isFile()) {
            try {
                if (!logDir.isDirectory()) {    // Check to make sure directory exsists
                    logDir.mkdirs();
                }
                logFile.createNewFile();
            } catch (Exception ex) {    // Do nothing if exception is thrown
            }
        }
        // Set timestamp format, we should move string to a properties file
        formatTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    }
}
