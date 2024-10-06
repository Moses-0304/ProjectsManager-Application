package io;
import model.Project;
import java.io.*;
import java.util.List;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class ProjectsFileIO {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
    public static void serializeToFile(File file, List<Project> data) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream((file)));
            oos.writeObject(data);
        }finally { //ser till att oos stängs på rätt sätt (oavsett om undantag träffar eller inte)
            if (oos != null) {
                oos.close();
            }
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(file));
            List<Project> projects = (List<Project>) ois.readObject();
            return projects; //lagra eller bearbeta datan vidare
        }
        finally { //ser till ois stängs rätt
            if (ois != null) {
                ois.close();
            }
        }
    }

    private ProjectsFileIO() {}
}
