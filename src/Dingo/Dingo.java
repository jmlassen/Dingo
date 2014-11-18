package Dingo;

/**
 *
 * @author Joel Lassen
 * @author Heidy
 * @author Justin
 */
public class Dingo {
    DropboxService ds;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new Dingo().run();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }

    /**
     * 
     * @throws Exception 
     */
    private void run() throws Exception {
        // Init and start the db service.
        ds = new DropboxService();
        ds.start();
        
        Change change = new Change();
        XMLService xml = new XMLService();
        
        xml.appendLog(change);
    }
}
