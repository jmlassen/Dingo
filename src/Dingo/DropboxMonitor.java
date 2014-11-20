package Dingo;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxDelta;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Joel Lassen <jmlassen at gmail.com>
 */
class DropboxMonitor {
    private DbxClient client;
    private boolean started = false;
    private String cursor = PropertyManager.getProperty("dropbox.cursor");
    
    /**
     * Starts the Dropbox service. Checks to see if the Dropbox account has been
     * set up, prompts the user if need be.
     */
    public void start() {
        try {
            final String APP_KEY = PropertyManager.getProperty("dropbox.appKey");
            final String APP_SECRET = PropertyManager.getProperty("dropbox.appSecret");

            DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

            DbxRequestConfig config = new DbxRequestConfig( "JavaTutorial/1.0",
                    Locale.getDefault().toString());
            
            // Check to see if the Dropbox account has been set up yet
            String token = PropertyManager.getProperty("dropbox.accessToken");
            if (token.isEmpty()) {
                System.out.println("Account not set up.");
                linkAccount(config, appInfo);
            }
            // Init the Dropbox client
            client = new DbxClient(config, token);
            started = true;
            // Let the user know we connected correctly.
            System.out.println("Welcome " + client.getAccountInfo().displayName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Prompts the user to allow Dingo to access their account. Updates the properties
     * file with the users access token so we don't have to prompt them every time
     * the program starts.
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
     * Runs a /delta check with the Dropbox API, returns back a list of changes.
     * @return list of changes, null if no changes have occurred.
     */
    public List<Change> getChanges() {
        if (!started) {
            start();
        }
        DbxDelta<DbxEntry> entries = null;
        System.out.println("Request started.");
        try {
            // Call a delta request.
            entries = client.getDelta(cursor);
        } catch (DbxException ex) {
            System.out.println("Error with delta request, exiting...");
            System.exit(1);
        }
        for (DbxDelta.Entry<DbxEntry> entry : entries.entries) {
            // If this is null, it means the file has been deleted.
            if (entry.metadata != null) {
                Change change = new Change();
                change.filename = entry.lcPath;
                System.out.println(entry.metadata.toString());
            }
            else {
                System.out.println(entry.lcPath + " deleted.");
            }
        }
        cursor = entries.cursor;
        PropertyManager.setDropboxAccountCursor(cursor);
        return null;
    }
}
