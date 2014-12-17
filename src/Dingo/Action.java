package dingo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Valid actions are: email, program, command
 * @author HEIDY2016
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class Action {
    private StringProperty action;
    private StringProperty argument;
    
    /**
     * @param action
     * @param argument
     */
    public Action(String action, String argument) {
        this.action = new SimpleStringProperty(action);
        this.argument = new SimpleStringProperty(argument);
    }
    
    /**
     * 
     * @param argument 
     */
    public void setArgument(String argument) {
        this.argument = new SimpleStringProperty(argument);
    }
    
    /**
     * 
     * @return 
     */
    public String getArgument() {
        return argument.get();
    }
    
    public StringProperty getArgumentProperty() {
        return argument;
    }
    
    /**
     *
     * @param action
     */
    public void setAction (String action) {
        this.action = new SimpleStringProperty(action);
    }
    
    /**
     *
     * @return action
     */
    public String getAction () {
        return action.get();
    }
    
    public StringProperty getActionProperty() {
        return action;
    }
    
    /**
     *
     * @return flag
     */
    public void run() {
        if (action.equals("email")) {
            sendEmail();
        } else if (action.equals("")) {
            
        }
    }
    
    /**
     * The method sends an sendEmail notification to the indicated recipient (to)
     * according to the current flag.
     * @param to
     */
    public void sendEmail() {
        
        Email email;
//        
//        if (flag.getFlagType().equals("deletion")) {
//            // Create an sendEmail instance for a deletion notification
//            email = new Email(file.getPath() + " has been deleted in the Dropbox", flag.getTo());
//            email.run();
//            System.out.println("Sending an email: " + file.getPath() + " has been deleted");
//        } else if (flag.getFlagType().equals("alteration")) {
//            // Create an sendEmail instance for an alteration notification
//            email = new Email(file.getPath() + " has been altered in the Dropbox", flag.getTo());
//            email.run();
//            System.out.println("Sending an email: " + file.getPath() + " has been altered");
//        } else if (flag.getFlagType().equals("changeLoc")) {
//            // Create an sendEmail instance for a change of Location notification
//            email = new Email(file.getPath() + " has changed location in the Dropbox", flag.getTo());
//            email.run();
//            System.out.println("Sending an email: " + file.getPath() + " has been changed");
//        } else {
//            System.out.println("Error: Flag is not recognized");
//        }
    }
}
