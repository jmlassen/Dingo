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
    public static void main(String[] args) {
        new Dingo().run();
    }

    private void run() {
        DropboxService.start();
    }
}
