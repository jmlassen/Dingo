package Dingo;

import java.util.List;

/**
 *
 * @author Joel Lassen
 * @author Heidy
 * @author Justin
 */
public class Dingo implements Runnable {
    DropboxService ds;
    boolean running = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread(new Dingo()).start();
    }

    /**
     * Starts the DropboxService, starts our infinite loop for checking.
     * @throws Exception 
     */
    @Override
    public void run() {
        try {
            // Init and start the db service.
        ds = new DropboxService();
        ds.start();
        System.out.println("Starting an infinite loop...You can break it by"
        + " manually stoping the thread.");
        // Start the infinate loop
        while (running) {       
            // Sleep for 5 seconds
            Thread.sleep(500);
            List<Change> changes = ds.check();
            // Check to see if we got any new changes.
            if (changes != null) {
                // TODO send changes to WatchTowerService
                // TODO sent changes to XmlService
            }
        }
        
        Change change = new Change();
        XMLService xml = new XMLService();
        
        xml.appendLog(change);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
