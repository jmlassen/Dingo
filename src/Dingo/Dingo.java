package Dingo;

/**
 *
 * @author Joel Lassen
 * @author Heidy
 * @author Justin
 */
public class Dingo {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new Dingo().run();
    }

    private void run() throws Exception {
        DropboxService.start();
        
        Change change = new Change();
        XMLService xml = new XMLService();
        
        xml.appendLog(change);
    }
}
