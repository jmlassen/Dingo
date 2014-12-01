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
    private DropboxMonitor dm;
    private WatchTowerService wts;
    private ChangeJournal cl;
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
        dm = new DropboxMonitor();
        dm.start();
        cl = new ChangeJournal();
        // Get Towers from XmlService
        List<Tower> towers = cl.getTowers();
        // Init WatchTowerService
        // wts = new WatchTowerService(towers);
        // Start listening.
        listen();
        System.out.println("Moving on.");
    }
    /**
     * Start calling the Dropbox API. We should probably consider finding a smarter
     * way to determine how often we should check.
     */
    private void listen() {
        Thread t = new Thread() {
            /**
             * Thread to call the getChanges method in the DropboxMonitor.
             */
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
                    List<Change> changes = dm.getChanges();
                    // Check to see if we got any new changes.
                    if (changes != null) {
                        //wts.handleChanges(changes);
                        cl.handleChanges(changes);
                    }
                }
            }
        };
        t.start();
    }
}
