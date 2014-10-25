package logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
/**
 * Class to allow the rest of the program to log files whilst only having one instance of the Logger
 * object. To log a file, call using Logger.getInstance().log("Here is my message to log")
 * @author Joel Lassen
 */
public final class Logger {
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
    private boolean logFileValidated;
    // File seperator for this system
    private String fs;
    
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
    public void log(String logString) {
        try {    
            // Check to see if file has been validated yet
            if (!logFileValidated) {
                validateFile();
            }
            // Get timestamp
            String timeStamp = formatTimeStamp.format(Calendar.getInstance().getTime());
            // Create the buffered writer and declare that we are appending the file
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
            // log the timestamped message
            bw.write(timeStamp + " - " + logString);
            // Add a new line
            bw.newLine();
            // Close the writer
            bw.close();
        } catch (Exception ex) {    // Do nothing if exception is thrown
        }
    }
    
    /**
     * Returns the name of the log file, this does not include the file directory.
     * @return log fileName (e.g. log.out)
     */
    public String getlogFile() {
        return logFileNameStr;
    }
    
    /**
     * Sets where the log file is going to be saved to.
     * @param logFileStr name of log file (e.g. log.out) 
     */
    public void setLogFile(String logFileStr) {
        this.logFileNameStr = logFileStr;
        logFile = new File(logDir, logFileStr);
        // Mark that file needs to be validated
        logFileValidated = false;
    }
    
    /**
     * Returns the directory the logs will be saved, this does not include the file itself.
     * @return log file directory (e.g. c:\\temp\\)
     */
    public File getLogDir() {
        return logDir;
    }
    
    /**
     * Sets the directory where the log file is going to get saved to.
     * @param logDir directory
     */
    public void setLogDir(File logDir) {
        this.logDir = logDir;
        // Mark that file needs to be validated
        logFileValidated = false;
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
        // Mark log file as validated
        logFileValidated = true;
    }
    
    /**
     * Initialize where the log is getting saved to and timestamp format.
     */
    private Logger() {
        // Set the file seperator
        fs = File.separator;
        // Get the home for the user account
        String logDirStr = System.getProperty("user.home");
        // Get the directory seporator for the users system, and add our directory
        logDirStr += fs + ".dingo" + fs;
        logDirStr += fs + "logs" + fs;
        // Create save directory
        setLogDir(new File(logDirStr));
        // Initialize where the log file is going to get saved.
        setLogFile("log.out");
        // Create save file
        logFile = new File(logDirStr, logFileNameStr);
        // Create holder for time stamp date format
        String timeStampProperty = "yyyy";
        try {
            // Get the SimpleDateFormat string for the logged timestamp
            Properties prop = new Properties();
            // Load the properties
            prop.load(new FileInputStream("src" + fs + "logging" + fs + "logger.properties"));
            // Reassign the timeStampProperty
            timeStampProperty = prop.getProperty("TimeStampFormat");
        } catch (Exception ex) {    // Do nothing if exception is thrown
        }
        // Set timestamp format
        formatTimeStamp = new SimpleDateFormat(timeStampProperty);
        // Make sure logFile gets validated before writing
        logFileValidated = false;
    }
}
