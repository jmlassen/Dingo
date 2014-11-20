package Dingo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joel Lassen
 * @author Heidy
 * @author Justin
 */
public class Dingo {
    private DropboxMonitor ds;
    private WatchTowerService wts;
    private ChangeLogger xs;
    private boolean running = true;
    private int threadSleep = 1500;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Dingo().run();
    }

    /**
     * Starts the DropboxMonitor, starts our infinite loop for checking.
     */
    public void run() {
        // Init and start the db service.
        ds = new DropboxMonitor();
        ds.start();
        xs = new ChangeLogger();
        // Get Towers from XmlService
        List<Tower> towers = xs.getTowers();
        // Init WatchTowerService
        // wts = new WatchTowerService(towers);
        // Start listening.
        listen();
        System.out.println("Moving on.");
        Change change = new Change();
        ChangeLogger xml = new ChangeLogger();
        try {
            xml.appendLog(change);
        } catch (Exception ex) {
            Logger.getLogger(Dingo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 
     */
    private void listen() {
        Thread t = new Thread() {
          @Override  
            public void run() {
                System.out.println("Starting an infinite loop...You can break it by"
                + " manually stoping the thread.");
                // Start the infinate loop
                while (running) {       
                    try {
                        // Sleep for .5 seconds
                        Thread.sleep(threadSleep);
                    } catch (InterruptedException ex) {
                    }
                    List<Change> changes = ds.getChanges();
                    // Check to see if we got any new changes.
                    if (changes != null) {
                        // wts.handleChanges(changes);
                        xs.handleChanges(changes);
                    }
                }
            }
        };
        t.start();
    }
}
