import javafx.scene.control.Alert;
import project.AllData;
import project.AllDataWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

public class Loader {

    private File file;
    private String meUser = System.getProperty("user.name");
    private volatile String standartFilePath = "/Users/" + meUser + "/Library/Application Support/TimeCountProbe/tcprobe.xml";
    private volatile String filePath;



    public Loader() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        this.filePath = prefs.get("filePath", null);
        if (this.filePath == null) {
            this.file = new File(this.standartFilePath);
        }
        else {
            this.file = new File(filePath);
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String newFilePath) {
        this.filePath = newFilePath;
    }

    public void setAllDataFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        prefs.put("filePath", getFilePath());
    }

    public void setAllDataFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            setFilePath(file.getPath());
        }
        else {
            prefs.put("filePath", getFilePath());
        }
        /*System.out.println(prefs.toString() + "\n" + prefs.absolutePath() + "\n");
        System.out.println(prefs.get("filePath", "no path"));*/

    }

    public File getAllDataFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        }
        else {
            return null;
        }
    }


    public void saveAllDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(AllDataWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            AllDataWrapper wrapper = new AllDataWrapper();

            // Маршаллируем и сохраняем XML в файл
            marshaller.marshal(wrapper,file);

        } catch (JAXBException e) {
            System.out.println("Can't save data to file:\n" + file.getPath());
        }
    }

    public boolean save() {
        AllDataWrapper = new AllDataWrapper();

    }

    public static void load() {

    }
}
