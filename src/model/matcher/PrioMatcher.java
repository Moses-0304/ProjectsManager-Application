package model.matcher;
import model.Task;
import model.TaskPrio;
public class PrioMatcher implements ITaskMatcher{
    private TaskPrio prio;

    public PrioMatcher(TaskPrio prio){
        this.prio = prio;
    }

    @Override
    public boolean match(Task task){
        return task.getPrio() == prio;
    }
}