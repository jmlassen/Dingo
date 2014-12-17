package dingo;

import java.util.List;

/**
 *
 * @author HEIDY2016
 */
public class TaskService {
    private List<Task> tasks;
    
    public TaskService(List<Task> tasks) {
        this.tasks = tasks;
    }   
    
    /**
     * 
     * @param task
     */
    public void addTask (Task task) {  
        tasks.add(task);
    }
    
    /**
     * Return back our tasks, for the GUI.
     * @return 
     */
    public List<Task> getTasks() {
        return tasks;
    }
    
    /**
     *    
     * @param changes
     */
    public void handleChanges(List<Change> changes) {
        for (Change change: changes) {
            // Loop through each of our tasks
            for (Task task:tasks) {
                // Find the file in the list of files been watched
                System.out.println(task.getFile());
                System.out.println(change.getFilename());
                if ((task.getFile().trim()).equals(change.getFilename().trim())) {
                    // Let the tower handle the change
                    task.handleChange(change);
                    System.out.println("Got in.");
                }            
            }
        }
    }
}              