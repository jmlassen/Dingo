package dingo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Handles all the properties business we are going to need for Dingo.
 * @author Joel Lassen
 */
public class PropertyManager {
    private static PropertyManager instance = new PropertyManager();
    
    /**
     * Returns the properties service instance.
     * @return 
     */
    public static PropertyManager getInstance(){
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
        getInstance().setDingoProperty("dropbox.accessToken", token);
    }
    
    /**
     * 
     * @param cursor 
     */
    public static void setDropboxAccountCursor(String cursor) {
        getInstance().setDingoProperty("dropbox.cursor", cursor);
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
        
        // Check to see if the properties file has been added yet
        try {
            FileInputStream in = new FileInputStream(
                    Dingo.class.getClassLoader().getResource("dingo.properties").getFile());
            prop.load(in);
            in.close();
            
            FileOutputStream out = new FileOutputStream(
                    Dingo.class.getClassLoader().getResource("dingo.properties").getFile());
            prop.setProperty(key, value);
            prop.store(out, null);
            out.close();
        } catch(Exception ex) {
            System.out.println("Error adding property \"" + key + "\"");
            System.exit(1);
        }
    }
    
    /**
     * Keep from new instances being created.
     */
    private PropertyManager() {
    }
}
