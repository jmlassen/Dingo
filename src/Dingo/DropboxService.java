/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import java.io.IOException;
import java.net.URI;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author temp
 */
class DropboxService {
    /**
     * Starts the Dropbox service. Checks to see if the Dropbox account has been
     * set up yet and starts listening for changes.
     */
    static void start() {
        try {
            final String APP_KEY = PropertiesService.getProperty("DropboxAppKey");
            final String APP_SECRET = PropertiesService.getProperty("DropboxAppSecret");

            DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_KEY);

            DbxRequestConfig config = new DbxRequestConfig( "JavaTutorial/1.0",
                    Locale.getDefault().toString());
            

            // Check to see if the Dropbox account has been set up yet
            String token = PropertiesService.getProperty("DropboxAccessToken");
            if (token.isEmpty()) {
                System.out.println("Account not set up.");
                linkAccount(config, appInfo);
            }
        } catch (Exception ex) {
        }
    }

    private static void linkAccount(DbxRequestConfig config, DbxAppInfo appInfo) {
        try {
            DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
            URI uri = new URI(webAuth.start());
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (Exception ex) {
        }
    }
}
