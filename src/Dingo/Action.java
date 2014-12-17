package dingo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Valid actions are: email, program, command
 * @author HEIDY2016
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class Action {
    private StringProperty type;
    private StringProperty argument;
    
    /**
     * @param action
     * @param argument
     */
    public Action(String action, String argument) {
        this.type = new SimpleStringProperty(action);
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
        this.type = new SimpleStringProperty(action);
    }
    
    /**
     *
     * @return action
     */
    public String getAction () {
        return type.get();
    }
    
    public StringProperty getActionProperty() {
        return type;
    }
    
    /**
     *
     * @return flag
     */
    public void run(String file, Flag flag) {
        if (getAction().equals("email")) {
            sendEmail(file, flag);
        } else if (getAction().equals("program")) {
            CommandPrompt c = new CommandPrompt(getArgument(), "Start Program");
            c.run();
        } else if (getAction().equals("command")) {
            CommandPrompt c = new CommandPrompt(getArgument(), "Run Command");
            c.run();
        }
    }
    
    /**
     * The method sends an sendEmail notification to the indicated recipient (to)
     * according to the current flag.
     * @param to
     */
    public void sendEmail(String file, Flag flag) {
        
        Email email;
        
        if (flag.getType().equals("deletion")) {
            // Create an sendEmail instance for a deletion notification
            email = new Email(file + " has been deleted in the Dropbox", argument.get());
            email.run();
            // System.out.println("Sending an email: " + file + " has been deleted");
        } else if (flag.getType().equals("alteration")) {
            // Create an sendEmail instance for an alteration notification
            email = new Email(file + " has been altered in the Dropbox", argument.get());
            email.run();
            // System.out.println("Sending an email: " + file + " has been altered");
        } else if (flag.getType().equals("changeation")) {
            // Create an sendEmail instance for a change of Location notification
            email = new Email(file + " has changed location in the Dropbox", argument.get());
            email.run();
            // System.out.println("Sending an email: " + file + " has been changed");
        } else {
            // System.out.println("Error: Flag is not recognized");
        }
    }
}
