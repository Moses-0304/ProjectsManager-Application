package model;

import java.time.LocalDate;
import java.io.Serializable;


/**
 * A class representing a task with a description, ID, priority, and other attributes.
 */
public class Task implements Comparable<Task>, Serializable {
    private String description;
    private int id;
    private String takenBy;

    private TaskState state;

    private LocalDate lastUpdate;

    private TaskPrio prio;

    /**
     * Constructor for creating a new Task.
     *
     * @param description The description of the task.
     * @param id          The unique ID of the task.
     * @param prio        The priority of the task.
     */
    Task(String description, int id, TaskPrio prio) {
        this.description = description;
        this.id = id;
        this.prio = prio;
    }

    /**
     * Get the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the unique ID of the task.
     *
     * @return The ID of the task.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the user who has taken the task.
     *
     * @return The user who has taken the task, or null if not taken.
     */
    public String getTakenBy() {
        return takenBy;
    }

    /**
     * Get the state of the task.
     *
     * @return The state of the task.
     */
    public TaskState getState() {
        return state;
    }

    /**
     * Get the date of the last update of the task.
     *
     * @return The date of the last update.
     */
    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Get the priority of the task.
     *
     * @return The priority of the task.
     */
    public TaskPrio getPrio() {
        return prio;
    }

    /**
     * Set the description of the task.
     *
     * @param description The new description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the unique ID of the task.
     *
     * @param id The new ID for the task.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the user who has taken the task.
     *
     * @param takenBy The user who has taken the task.
     * @throws IllegalStateException if the task is already taken.
     */
    public void setTakenBy(String takenBy) {
        if (this.takenBy != null) {
            throw new IllegalStateException("Aktiviteten upptagen");
        }
        this.takenBy = takenBy;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * Set the state of the task.
     *
     * @param state The new state for the task.
     */
    public void setState(TaskState state) {
        this.state = state;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * Set the date of the last update of the task.
     *
     * @param lastUpdate The new date of the last update.
     */
    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Set the priority of the task.
     *
     * @param prio The new priority for the task.
     */
    public void setPrio(TaskPrio prio) {
        this.prio = prio;
        this.lastUpdate = LocalDate.now();
    }

    /**
     * Compares this task to another task based on priority and description.
     *
     * @param other The task to compare to.
     * @return A negative integer, zero, or a positive integer if this task is less than, equal to, or greater than the other task.
     */
    @Override
    public int compareTo(Task other) {
        int comparePrio = this.prio.compareTo(other.prio);
        if (comparePrio == 0) {
            return this.description.compareTo(other.description);
        } else {
            return comparePrio;
        }
    }

    /**
     * Checks if this task is equal to another object.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
        @Override
        public boolean equals(Object obj){
            if (this == obj){
                return true;
            }

            if (obj instanceof Task){
                Task otherTask = (Task) obj;

                return this.prio == otherTask.prio &&
                     this.description.equals(otherTask.description);
            }
            return false;
        }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", takenBy='" + takenBy + '\'' +
                ", state=" + state +
                ", lastUpdate=" + lastUpdate +
                ", prio=" + prio +
                '}';
    }
}
