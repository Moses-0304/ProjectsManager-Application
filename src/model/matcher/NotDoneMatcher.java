package model.matcher;
import model.Task;
import model.TaskState;

public class NotDoneMatcher implements ITaskMatcher {
    public boolean match (Task task) {
        return task.getState() != TaskState.DONE;
    }
}
