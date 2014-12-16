package ui;

import dingo.Tower;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Joel Lassen <jmlassen at gmail.com>
 */
class DingoView extends View {
    /**
     * 
     * @return 
     */
    @Override
    Parent getContent() {
        String file = "/ui/DingoGui.fxml";
        
        Parent parent = null;
        
        try {
            parent = FXMLLoader.load(getClass().getResource(file));
        } catch (Exception ex) {
            System.out.println("Error loading fxml");
            ex.printStackTrace();
            System.exit(1);
        }
        
        return parent;
    }
    
    public void updateTableView(List<Tower> tasks) {
        
    }
}
