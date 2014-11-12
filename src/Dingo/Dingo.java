package Dingo;

import com.dropbox.core.*;
import java.io.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String temp = PropertiesService.getProperty("madness","madness");
    }
}
