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
    private static final Logger instance = new Logger();
    // Directory where log file is getting saved
    private File logDir;
    // Where the log is getting saved to
    private String logFileNameStr;
    private File logFile;
    // The format of the timestamp
    private final DateFormat formatTimeStamp;
    // Whether the file directory has been validated
    private boolean fileValidated = false;
    
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
        try {    
            // Check to see if file has been validated yet
            if (!fileValidated) {
                validateFile();
            }
            // Get timestamp
            String timeStamp = formatTimeStamp.format(Calendar.getInstance().getTime());
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
     * Get the log file.
     * @return where the log file gets saved to
     */
    public File getLogFile() {
        return logFile;
    }
    
    /**
     * Sets where the log file is going to be saved to.
     * @param logFileStr name of log file (e.g. log.out) 
     */
    public void setLogFile(String logFileStr) {
        this.logFileNameStr = logFileStr;
        logFile = new File(logDir, logFileStr);
        // Mark that file needs to be validated
        fileValidated = false;
    }
    
    /**
     * Sets the directory where the log file is going to get saved to.
     * @param logDir 
     */
    public void setLogDir(File logDir) {
        this.logDir = logDir;
        // Mark that file needs to be validated
        fileValidated = false;
    }
    
    /**
     * Validates the save directory and file. Creates them if they do not exist.
     * @throws Exception if file and directory cannot be created
     */
    private void validateFile() throws Exception {
        // Check to see if directory exists
        if (!logDir.isDirectory()) {
            // Create the directory and sub directories
            logDir.mkdirs();
        }
        // Check to see if file exists
        if (!logFile.isFile()) {
            // Create a new empty file
            logFile.createNewFile();
        }
    }
    
    /**
     * Initialize where the log is getting saved to and timestamp format.
     */
    private Logger() {
        // Get the home for the user account
        String logDirStr = System.getProperty("user.home");
        // Get the directory seporator for the users system, and add our directory
        logDirStr += File.separator + ".dingo" + File.separator;
        logDirStr += File.separator + "Logs" + File.separator;
        // Create save directory
        logDir = new File(logDirStr);
        // Initialize where the log file is going to get saved.
        logFileNameStr = "log.out";
        // Create save file
        logFile = new File(logDirStr, logFileNameStr);
        // Set timestamp format, we should move string to a properties file
        formatTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    }
}
