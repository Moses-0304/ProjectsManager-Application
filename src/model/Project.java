package model;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import model.matcher.ITaskMatcher;

/**
 * Represents a project that contains tasks.
 */
public class Project implements Comparable<Project>, Serializable {
    private String title;
    private int id;
    private int nextTaskId;
    private String descr;
    private LocalDate created;
    private List<Task> tasks;

    /**
     * Creates a new project with the given title, description, and ID.
     *
     * @param title The project's title.
     * @param descr The project's description.
     * @param id    The unique ID for the project.
     */
    Project(String title, String descr, int id) {
        this.title = title;
        this.descr = descr;
        this.id = id;
        this.created = LocalDate.now();
        tasks = new ArrayList<>();
    }

    /**
     * Gets the next available ID for a task in the project.
     *
     * @return The next task ID.
     */
    public int getNextTaskId() {
        return nextTaskId;
    }

    /**
     * Gets the project's ID.
     *
     * @return The project's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the project's title.
     *
     * @return The project's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the project's description.
     *
     * @return The project's description.
     */
    public String getDescr() {
        return descr;
    }

    /**
     * Gets the date when the project was created.
     *
     * @return The creation date of the project.
     */
    public LocalDate getCreated() {
        return created;
    }

    /**
     * Gets a list of tasks in the project.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
        if (tasks == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(tasks);
    }

    /**
     * Sets the project's title.
     *
     * @param title The new project title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the ID of the project.
     *
     * @param id The new ID for the project.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the next task ID for the project.
     *
     * @param nextTaskId The new next task ID for the project.
     */
    public void setNextTaskId(int nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    /**
     * Sets the description of the project.
     *
     * @param descr The new description for the project.
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Sets the creation date of the project.
     *
     * @param created The new creation date for the project.
     */
    public void setCreated(LocalDate created) {
        this.created = created;
    }

    /**
     * Sets the list of tasks for the project.
     *
     * @param tasks The new list of tasks for the project.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets a task from the project by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return The task with the specified ID, or null if not found.
     */
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    /**
     * Finds and returns a list of tasks in the project that match a specified task matcher.
     *
     * @param matcher The task matcher to use for matching tasks.
     * @return A sorted list of tasks that match the specified criteria.
     */
    public List<Task> findTasks(ITaskMatcher matcher) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (matcher.match(task)) {
                matchedTasks.add(task);
            }
        }
        Collections.sort(matchedTasks);
        return matchedTasks;

    }

    /**
     * Adds a new task to the project with the given description and priority.
     *
     * @param descr The description of the new task.
     * @param prio  The priority of the new task.
     * @return The newly created task.
     */
    public Task addTask(String descr, TaskPrio prio) {
        int nextId = nextTaskId;
        Task newTask = new Task(descr, nextId, prio);
        tasks.add(newTask);
        nextTaskId++;
        return newTask;
    }

    /**
     * Removes a task from the project.
     *
     * @param task The task to be removed.
     * @return true if the task was successfully removed, false otherwise.
     */
    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    /**
     * Gets the state of the project, which can be EMPTY, ONGOING, or COMPLETED.
     *
     * @return The state of the project.
     */
    public ProjectState getState() {
        if (tasks.isEmpty()) {
            return ProjectState.EMPTY;
        }
        else {
            boolean allTasksDone = true;
            for (Task task : tasks) {
                if (task.getState() != TaskState.DONE) {
                    allTasksDone = false;
                    break;
                }
            }
            if (allTasksDone) {
                return ProjectState.COMPLETED;
            }
            else {
                return ProjectState.ONGOING;
            }
        }
    }

    /**
     * Gets the date when the project was last updated.
     *
     * @return The last updated date of the project.
     */
    public LocalDate getLastUpdated() {
        LocalDate lastUpdatedDate = created;

        for (Task task : tasks) {
            LocalDate taskLastUpdated = task.getLastUpdate();
            if (taskLastUpdated.isAfter(lastUpdatedDate)) {
                lastUpdatedDate = taskLastUpdated;
            }
        }
        return lastUpdatedDate;
    }

    /**
     * Compares this project to another project for ordering based on their titles.
     *
     * @param other The project to compare to.
     * @return A negative integer, zero, or a positive integer as this project's
     *         title is less than, equal to, or greater than the title of the
     *         specified project.
     */
    @Override
    public int compareTo(Project other)  {
        return this.title.compareTo(other.title);
    }

    /**
     * Indicates whether some other object is "equal to" this project.
     *
     * @param obj The reference object with which to compare.
     * @return true if this project is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj instanceof Project){
            Project otherProject =(Project) obj;

            return this.title.equals(otherProject.title);
        }
        return false;
    }

    /**
     * Returns a string representation of this project.
     *
     * @return A string representation of the project, including its title, ID,
     *         next task ID, description, creation date, and tasks.
     */
    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", nextTaskId=" + nextTaskId +
                ", descr='" + descr + '\'' +
                ", created=" + created +
                ", tasks=" + tasks +
                '}';
    }
}
