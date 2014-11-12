package Dingo;

import java.io.InputStream;
import java.util.Properties;

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
    
    public static String getProperty(String key, String defaultProp) {
        return getInstance().getDingoProperty(key, defaultProp);
    }
    
    /**
     * Gets the requested property from the dingo.properties file. This properties
     * file IS NOT included in the git repo, so if you cloned this repo, you are
     * going to have to add it manually, lo siento.
     * @param key the property key
     * @param defaultProp the value if the property was not found
     * @return the property if found, else the passed default property. 
     */
    private String getDingoProperty(String key, String defaultProp){
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
        // Get the requested property
        
        return defaultProp;
    }
    
    /**
     * Keep from new instances being created.
     */
    private PropertiesService() {
    }
}
