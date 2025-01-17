package ui;

import model.matcher.ITaskMatcher;
import model.matcher.NotDoneMatcher;
import model.matcher.PrioMatcher;
import model.TaskPrio;
import model.Task;
import model.TaskState;
import model.Project;
import model.matcher.TakenByMatcher;

import java.util.List;
import java.util.Scanner;

/**
 * User interactions for a specific project, current project.
 * The user selects actions on current project in the projectLoop method.
 */
class CurrentProjectUI {
    private Project currentProject;
    private final Scanner scan;

    // package private visibility - only visible to other classes in
    // package ui - intended for MainUI.
    CurrentProjectUI(Scanner scan) {
        this.scan = scan;
        this.currentProject = null; // TODO: Ugly!
    }

    void setCurrentProject(Project project) {
        this.currentProject = project;
        projectLoop();
    }

    Project getCurrentProject() {
        return currentProject;
    }

    void projectLoop() {
        char choice;
        do {
            printCurrentProjectMenu();
            choice = InputUtils.scanAndReturnFirstChar(scan);

            switch (choice) {
                case 'T':
                    System.out.print("Name? ");
                    String takenBy = scan.nextLine();
                    viewTasks(new TakenByMatcher(takenBy));
                    break;
                case 'N':
                    viewTasks(new NotDoneMatcher());
                    break;
                case 'H':
                    viewTasks(new PrioMatcher(TaskPrio.High));
                    break;
                case 'A':
                    addTask();
                    break;
                case 'R':
                    removeTask();
                    break;
                case 'U':
                    updateTask();
                    break;
                case 'X':
                    break;
                default:
                    System.out.println("Unknown command");
            }

        } while (choice != 'X');
    }

    private void viewTasks(ITaskMatcher matcher) {
        System.out.println(currentProject.toString());
        List<Task> tasks = currentProject.findTasks(matcher);
        printTasks(tasks);
    }

    private void addTask() {
        System.out.print("Description? ");
        String descr = scan.nextLine();
        System.out.print("Priority (L)ow, (M)edium, (H)igh? ");
        char prioChar = InputUtils.scanAndReturnFirstChar(scan);
        TaskPrio prio = prioChar == 'H' ? TaskPrio.High : prioChar == 'L' ? TaskPrio.Low : TaskPrio.Medium;
        currentProject.addTask(descr, prio);
    }

    private void removeTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Task ID: ");
        int taskId = scanner.nextInt();

        for (Task task : currentProject.getTasks()) {
            if (task.getId() == taskId) {
                if (currentProject.removeTask(task)) {
                    System.out.println("Task with ID " + taskId + " removed.");
                } else {
                    System.out.println("Failed to remove task with ID " + taskId);
                }
                return; // Uppgiften har tagits bort, avsluta loopen
            }
        }
    }



    private void updateTask() {
        System.out.print("Task id? ");
        int id = scan.nextInt();
        scan.nextLine(); //remove "new line" from scanner buffer
        Task task = currentProject.getTaskById(id);
        if (task != null) {
            System.out.println(task);
            System.out.print("New state (T)odo (I)n progress (D)one? ");
            char stateChar = InputUtils.scanAndReturnFirstChar(scan);
            if (stateChar == 'T') {
                System.out.print("Taken by (name or email address)? ");
                String emailStr = scan.nextLine();
                task.setState(TaskState.TO_DO);
                task.setTakenBy(emailStr);
            }
            else if(stateChar == ('D')) {
                task.setState(TaskState.DONE);
            }
            else if (stateChar == ('I')) {
                task.setState(TaskState.IN_PROGRESS);
            }
        } else {
            System.out.println("Id not found.");
        }
    }

    private void printCurrentProjectMenu() {
        System.out.println("--- Manage " + currentProject.getTitle() + " ---");
        System.out.println("T - list tasks taken by ...");
        System.out.println("N - View tasks");
        System.out.println("H - list high priority tasks");
        System.out.println("A - add task");
        System.out.println("R - remove task");
        System.out.println("U - update task");
        System.out.println("X - exit project menu");
        System.out.println("----------");
    }

    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added");
        } else {
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        }
    }
}
