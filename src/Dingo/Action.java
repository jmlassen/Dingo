package dingo;

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
    private String argument;
    
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
    
    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
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
     * The method sends an sendEmail notification to the indicated recipient (to)
 according to the current flag
     * @param to
     */
    public void sendEmail() {
        
        Email email;
        
        if (flag.getFlagType().equals("deletion")) {
            // Create an sendEmail instance for a deletion notification
            email = new Email(file.getPath() + " has been deleted in the Dropbox", flag.getTo());
            email.run();
            System.out.println("Sending an email: " + file.getPath() + " has been deleted");
        } else if (flag.getFlagType().equals("alteration")) {
            // Create an sendEmail instance for an alteration notification
            email = new Email(file.getPath() + " has been altered in the Dropbox", flag.getTo());
            email.run();
            System.out.println("Sending an email: " + file.getPath() + " has been altered");
        } else if (flag.getFlagType().equals("changeLoc")) {
            // Create an sendEmail instance for a change of Location notification
            email = new Email(file.getPath() + " has changed location in the Dropbox", flag.getTo());
            email.run();
            System.out.println("Sending an email: " + file.getPath() + " has been changed");
        } else {
            System.out.println("Error: Flag is not recognized");
        }
    }
}
