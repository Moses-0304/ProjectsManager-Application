package model.matcher;
import model.Task;

public class TakenByMatcher implements ITaskMatcher {

    private String takenBy;

    public TakenByMatcher(String takenBy) {
        this.takenBy = takenBy;
    }

    public boolean match(Task task) {
        return takenBy.equals(task.getTakenBy());
    }
}
