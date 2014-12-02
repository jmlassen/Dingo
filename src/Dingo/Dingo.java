package Dingo;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.List;

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
    private boolean listening = true;
    private int threadSleep = 1500;
    
    /**
     * 
     * @return 
     */
    public boolean isListening() {
        return listening;
    }

    /**
     * 
     * @param listening 
     */
    public void setListening(boolean listening) {
        this.listening = listening;
    }

    /**
     * 
     * @return 
     */
    public int getThreadSleep() {
        return threadSleep;
    }

    /**
     * 
     * @param threadSleep 
     */
    public void setThreadSleep(int threadSleep) {
        this.threadSleep = threadSleep;
    }
    
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
        // Check to make sure there is only one instance listening
        checkInstance();
        // TODO: Start the GUI.
        
        // Init and start the db service.
        dm = new DropboxMonitor();
        dm.start();
        cl = new ChangeJournal();
        // Get Towers from XmlService
        List<Tower> towers = cl.getTowers();
        // Init WatchTowerService
        wts = new WatchTowerService(towers);
        // Start listening.
        listen();
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
                while (listening) {       
                    List<Change> changes = dm.getChanges();
                    // Check to see if we got any new changes.
                    if (changes != null) {
                        wts.handleChanges(changes);
                        cl.handleChanges(changes);
                    }
                    try {
                        Thread.sleep(threadSleep);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        t.start();
    }

    /**
     * Checks to make sure this is the only instance of the program listening.
     */
    private void checkInstance() {
        ServerSocket socket = null;
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            socket = new ServerSocket(12345, 1, localAddress);
        } catch (Exception e) {
            System.out.println("Program already running, exiting");
            System.exit(1);
        }
    }
}
