/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author temp
 */
class DropboxMonitor {
    private DbxClient client;
    /**
     * Starts the Dropbox service. Checks to see if the Dropbox account has been
     * set up yet and starts listening for changes.
     */
    void start() {
        try {
            final String APP_KEY = PropertyManager.getProperty("DropboxAppKey");
            final String APP_SECRET = PropertyManager.getProperty("DropboxAppSecret");

            DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

            DbxRequestConfig config = new DbxRequestConfig( "JavaTutorial/1.0",
                    Locale.getDefault().toString());
            
            // Check to see if the Dropbox account has been set up yet
            String token = PropertyManager.getProperty("DropboxAccessToken");
            if (token.isEmpty()) {
                System.out.println("Account not set up.");
                linkAccount(config, appInfo);
            }
            // Connect to the Dropbox API
            client = new DbxClient(config, token);
            // Let the user know we connected correctly.
            System.out.println("Welcome " + client.getAccountInfo().displayName);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 
     * @param config
     * @param appInfo 
     */
    private static void linkAccount(DbxRequestConfig config, DbxAppInfo appInfo) {
        try {
            DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
            String uriStr = webAuth.start();
            URI uri = new URI(uriStr);
            java.awt.Desktop.getDesktop().browse(uri);
            
            System.out.println("Copy the authorization code.");
            String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
            
            DbxAuthFinish authFinish = webAuth.finish(code);
            String accessToken = authFinish.accessToken;
            
            PropertyManager.setDropboxAccessToken(accessToken);
        } catch (Exception ex) {
            System.out.println("Error setting up account. Please try again.");
            System.out.println(ex.toString());
            System.exit(1);
        }
    }

    /**
     * Runs a /delta check with the Dropbox api.
     * @return 
     */
    List<Change> getChanges() {
        return null;
    }
}
