/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dingo.Action;
import dingo.Flag;
import dingo.Task;
import java.io.File;
import javafx.fxml.FXML;
import org.controlsfx.dialog.Dialogs;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joel Lassen <jmlassen at gmail.com>
 */
public class TaskEditDialogController {
    @FXML
    private TabPane taskTabPane;
    @FXML
    private Tab taskTab;
    @FXML
    private Tab eventTab;
    @FXML
    private Tab actionTab;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label notesLabel;
    @FXML
    private Label fileLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private TextField fileTextField;
    @FXML
    private Button fileButton;
    @FXML
    private CheckBox fileCreatedCheckBox;
    @FXML
    private CheckBox fileModifiedCheckBox;
    @FXML
    private CheckBox fileMovedCheckBox;
    @FXML
    private CheckBox fileDeletedCheckBox;
    @FXML
    private Label sendEmailLabel;
    @FXML
    private Label toLabel;
    @FXML
    private TextField toTextField;
    @FXML
    private Label runProgramLabel;
    @FXML
    private Label selectProgramLabel;
    @FXML
    private TextField selectProgramTextField;
    @FXML
    private Button selectProgramButton;
    @FXML
    private Label runCommandLabel;
    @FXML
    private Label commandLabel;
    @FXML
    private TextField commandTextField;
    
    private Stage taskEditStage;
    private Task task;
    private boolean okClicked = false;
    
    @FXML
    public void initialize() {
    }
    
    public void setTaskEditStage(Stage taskEditStage) {
        this.taskEditStage = taskEditStage;
    }
    
    public void setTask(Task task) {
        this.task = task;
        showTaskDetails(task);
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            // Get Task details
            task.setName(nameTextField.getText());
            task.setNotes(notesTextArea.getText());
            task.setFile(fileTextField.getText());
            
            // Add Events
            if (fileCreatedCheckBox.isSelected()) {
                task.addFlag(new Flag("creation"));
            } else if (fileModifiedCheckBox.isSelected()) {
                task.addFlag(new Flag("alteration"));
            } else if (fileMovedCheckBox.isSelected()) {
                task.addFlag(new Flag("moveation"));
            } else if (fileDeletedCheckBox.isSelected()) {
                task.addFlag(new Flag("deletion"));
            }
            
            // Add Actions
            if (!toTextField.getText().equals("")) {
                task.addAction(new Action("email", toTextField.getText()));
            } else if (!selectProgramTextField.getText().equals("")) {
                task.addAction(new Action("program", toTextField.getText()));
            } else if (!commandTextField.getText().equals("")) {
                task.addAction(new Action("command", toTextField.getText()));
            }
            
            okClicked = true;
            taskEditStage.close();
        }
    }
    
    @FXML
    private void handleFileOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(taskEditStage);
        
        if (!file.getPath().contains("\\Dropbox\\")) {
            Dialogs.create()
                .title("Dropbox Folder")
                .masthead("")
                .message("Please select a folder in the Dropbox.")
                .showError();
            fileTextField.setText("");
        } else {
            String[] split = file.getPath().split("\\Dropbox");
            fileTextField.setText(split[1]);
        }
    }
    
    private void showTaskDetails(Task task) {
        // If we are editing a task...
        if (task != null) {
            // Fill in fields in Task tab
            nameTextField.setText(task.getName());
            notesTextArea.setText(task.getNotes());
            fileTextField.setText(task.getFile());
            
            // Loop through the flags, check the Event options if need be
            for (Flag flag : task.getFlags()) {
                if (flag.getType().equals("creation")) {
                    fileCreatedCheckBox.setSelected(true);
                } else if (flag.getType().equals("alteration")) {
                    fileModifiedCheckBox.setSelected(true);
                } else if (flag.getType().equals("moveation")) {
                    fileMovedCheckBox.setSelected(true);
                } else if (flag.getType().equals("deletion")) {
                    fileDeletedCheckBox.setSelected(true);
                }
            }
            
            // Loop through the actions
            for (Action action : task.getActions()) {
                if (action.getAction().equals("email")) {
                    toTextField.setText(action.getArgument());
                } else if (action.getAction().equals("program")) {
                    selectProgramTextField.setText(action.getArgument());
                } else if (action.getAction().equals("command")) {
                    commandTextField.setText(action.getArgument());
                }
            }
        } else {
            nameTextField.setText("");
            notesTextArea.setText("");
            fileTextField.setText("");
            fileCreatedCheckBox.setSelected(false);
            fileModifiedCheckBox.setSelected(false);
            fileMovedCheckBox.setSelected(false);
            fileDeletedCheckBox.setSelected(false);
            toTextField.setText("");
            selectProgramTextField.setText("");
            commandTextField.setText("");
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        
        if (nameTextField.getText() == null || nameTextField.getText().length() == 0) {
            errorMessage += "Missing Task Name.\n";
        }
        if (fileTextField.getText() == null || fileTextField.getText().length() == 0) {
            errorMessage += "Missing File.\n";
        }
        if (!(fileCreatedCheckBox.isSelected() ||
                fileModifiedCheckBox.isSelected() ||
                fileMovedCheckBox.isSelected() ||
                fileDeletedCheckBox.isSelected()
                )) {
            errorMessage += "No Event Selected.\n";
        }
        if ((toTextField.getText() == null || toTextField.getText().length() == 0) &&
                (selectProgramTextField.getText() == null || selectProgramTextField.getText().length() == 0) &&
                (commandTextField.getText() == null || commandTextField.getText().length() == 0)) {
            errorMessage += "No Action Selected.\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Dialogs.create()
                .title("Invalid Fields")
                .masthead("Please correct invalid fields")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
}
