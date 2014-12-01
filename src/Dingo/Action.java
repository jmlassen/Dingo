package Dingo;

import java.io.File;

/**
 *
 * @author HEIDY2016
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class Action {
    private String action;
    private Flag flag;
    private File file;
    
    /**
     * @param action
     * @param flag
     * @param file
     */
    public Action(String action, Flag flag, File file) {
        this.action = action;
        this.flag = flag;
        this.file = file;
    }
    
    /**
     *
     * @param action
     */
    public void setAction (String action) {
        this.action = action;
    }
    
    /**
     *
     * @return action
     */
    public String getAction () {
        return action;
    }
    /**
     *
     * @return flag
     */
    
    public Flag getFlag() {
        return flag;
    }
    
    /**
     *
     * @param flag
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }
    
    /**
     *
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }
    
    /**
     * 
     */
    public void run() {
        if (action.equals("email")) {
            sendEmail();
        } else if (action.equals("")) {
            
        }
    }
    
    /**
     * 
     */
    public void sendEmail() {
        if (flag.getFlagType().equals("deletion")) {
            // Send sendEmail  
            System.out.println("Sending an email: " + file.getPath() + " has been deleted");
        } else if (flag.getFlagType().equals("alteration")) {
            // Send sendEmail
            System.out.println("Sending an email: " + file.getPath() + " has been altered");
        } else if (flag.getFlagType().equals("changeLoc")) {
            // Send sendEmail
            System.out.println("Sending an email: " + file.getPath() + " has been changed");
        } else {
            System.out.println("Error: Flag is not recognized");
        }
    }
}
