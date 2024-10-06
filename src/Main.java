import model.ProjectsManager;
import ui.MainUI;
import model.Project;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Skapa ditt ProjectsManager-objekt och andra nödvändiga komponenter här
        ProjectsManager manager = new ProjectsManager();

        // Skapa ett MainUI-objekt och skicka ProjectsManager som parameter
        MainUI mainUI = new MainUI(manager);

        // Kör huvudloopen
        mainUI.mainLoop();
    }
}
