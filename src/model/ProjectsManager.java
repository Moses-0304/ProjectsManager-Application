package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.exception.TitleNotUniqueException;

/**
 * The `ProjectsManager` class is responsible for managing a collection of projects and offers a range of operations.
 */
public class ProjectsManager{
    private int nextProjectId;
    private List<Project> projects = new ArrayList<>();

    /**
     * Constructs a `ProjectsManager` with the default settings.
     *
     * This constructor initializes a `ProjectsManager` instance with default settings,
     * which includes an empty list of projects and an unspecified initial value for the
     * next project's ID.
     */
    public ProjectsManager() {

    }

    /**
     * Gets a copy of the list of projects.
     *
     * @return A list of projects.
     */
    public List<Project> getProjects() {
        if (projects == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(projects);
    }

    /**
     * Sets the list of projects with a new list.
     *
     * @param incomingProjects The new list of projects to set.
     */
    public void setProjects(List<Project> incomingProjects){
        if(projects != null){
            projects.clear();
        }
        else
        {
            projects = new ArrayList<>();
        }

        if (incomingProjects != null){
            projects.addAll(incomingProjects);
        }

        if (projects.isEmpty()){
            nextProjectId = 1;
        }
        else {
            nextProjectId = getHighestId() + 1;
        }
    }

    /**
     * Checks if a project title is unique within the list of projects.
     *
     * @param title The title to check for uniqueness.
     * @return `true` if the title is unique; otherwise, `false`.
     */
    public boolean isTitleUnique(String title){
        if(projects == null){
            return true;
        }

        for (Project p : projects){
            if (p.getTitle().equals(title)){
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a new project to the list of projects.
     *
     * @param title The title of the new project.
     * @param description The description of the new project.
     * @return The newly added `Project` object.
     * @throws TitleNotUniqueException if the title is not unique.
     */
    public Project addProject(String title, String description) throws TitleNotUniqueException{
        if(!isTitleUnique(title)){
            throw new TitleNotUniqueException("Titel ej unik!");
        }

        Project newP = new   Project(title, description, nextProjectId);
        projects.add(newP);
        nextProjectId++;

        return newP;
    }

    /**
     * Removes a project from the list of projects.
     *
     * @param project The `Project` object to be removed.
     */
    public void removeProject(Project project){
        if (projects != null && project != null){
            projects.remove(project);
        }
    }

    /**
     * Retrieves a project by its ID.
     *
     * @param id The ID of the project to retrieve.
     * @return The `Project` object with the specified ID, or `null` if not found.
     */
    public Project getProjectById(int id){
        if(projects != null){
            for (Project p : projects){
                if (p.getId() == id){
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Finds projects that contain a specific substring in their title.
     *
     * @param tString The substring to search for in project titles.
     * @return A list of projects that match the search criteria.
     */
    public List<Project> findProjects(String tString){
        List<Project> foundProjects = new ArrayList<>();
        if (projects != null){
            for (Project p : projects){
                if (p.getTitle().contains(tString)){
                    foundProjects.add(p);
                }
            }
        }
        return foundProjects;
    }

    /**
     * Retrieves the highest project ID among all projects.
     *
     * @return The highest project ID, or 0 if no projects exist.
     */
    private int getHighestId(){
        int highest = 0;

        if (projects != null){
            for (Project p : projects){
                if (p.getId() > highest){
                    highest = p.getId();
                }
            }
        }
        return highest;
    }

    /**
     * Returns a string representation of the `ProjectsManager` object, including its `nextProjectId` and the list of projects.
     *
     * @return A string representation of the `ProjectsManager` object, showing its `nextProjectId` and the list of projects.
     */

    @Override
    public String toString() {
        return "ProjectsManager{" +
                "nextProjectId=" + nextProjectId +
                ", projects=" + projects +
                '}';
    }
}
