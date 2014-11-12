package Dingo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles all the properties business we are going to need for Dingo.
 * @author Joel Lassen
 */
public class PropertiesService {
    private static PropertiesService instance = new PropertiesService();
    
    /**
     * Returns the properties service instance.
     * @return 
     */
    public static PropertiesService getInstance(){
        return instance;
    }
    
    
    /**
     * Shortcut for getting properties.
     * @param key
     * @return 
     */
    public static String getProperty(String key) {
        return getInstance().getDingoProperty(key);
    }
    
    /**
     * Sets the dropboxAccountName property.
     * @param token 
     */
    public static void setDropboxAccessToken(String token) {
        getInstance().setDingoProperty("DropboxAccessToken", token);
    }
    
    /**
     * Gets the requested property from the dingo.properties file. This properties
     * file IS NOT included in the git repo, so if you cloned this repo, you are
     * going to have to add it manually, lo siento.
     * @param key the property key
     * @return the found property value
     */
    private String getDingoProperty(String key) {
        Properties prop = new Properties();
        InputStream in = null;
        // Check to see if the properties file has been added yet
        try {
            in = Dingo.class.getClassLoader().getResourceAsStream("dingo.properties");
            prop.load(in);
        } catch(Exception ex) {
            System.out.println("Dingo properties file not found. Please add it" +
                    " and try running the program again.");
            System.exit(1);
        }
        // Get the requested property stored 
        String storedProperty = prop.getProperty(key);
        if (storedProperty == null) {
            System.out.println("Could not find the \"" + key + "\" property."
            + " Please add it to your properties file and try again.");
            System.exit(1);
        }
        return storedProperty;
    }
    
    /**
     * Sets a property in our properites file.
     * @param key
     * @param value 
     */
    private void setDingoProperty(String key, String value) {
        Properties prop = new Properties();
        
        try {
            prop.load(Dingo.class.getClassLoader().getResourceAsStream("dingo.properties"));
            prop.setProperty(key, value);
        } catch (IOException ex) {
            System.out.println("Error setting \"" + key + "\" property.");
            System.exit(1);
        }
    }
    
    /**
     * Keep from new instances being created.
     */
    private PropertiesService() {
    }
}
