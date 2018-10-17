package load;

import project.AllData;
import project.AllDataWrapper;
import user.AllUsers;
import user.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Loader {
    private final String meUser = System.getProperty("user.name");
    private final String pathString = "/Users/" + meUser + "/Library/Application Support/TimeCountProbe/tcprobe.xml";
    private File file = new File(pathString);

    public File getFile() {
        return file;
    }

    public boolean save() {
        try {
            AllDataWrapper allDataWrapper = new AllDataWrapper();

            if (!this.file.exists()) {
                Path pathToFile = Paths.get(pathString);
                Files.createDirectories(pathToFile.getParent());
                Files.createFile(pathToFile);
            }

            JAXBContext context = JAXBContext.newInstance(AllDataWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(allDataWrapper, this.file);
        } catch (IOException e) {
            System.out.println("Can't save data to file:\n" + file.getPath());
            e.printStackTrace();
            return false;
        } catch (JAXBException e) {
            System.out.println("Can't marshal data");
            e.printStackTrace();
            return false;

        }
        return true;
    }

    public boolean load() {
        if (!this.file.exists()) {
            System.out.println("No data to load");
            return false;
        }

        try {
            JAXBContext context = JAXBContext.newInstance(AllDataWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            AllDataWrapper allDataWrapper = (AllDataWrapper) unmarshaller.unmarshal(this.file);

            if (allDataWrapper != null) {
                AllUsers.setIDCounterAllUsers(allDataWrapper.getIDCounterAllUsers());
                Map<Integer, User> users = new HashMap<>();
                users.putAll(allDataWrapper.getDesigners());
                users.putAll(allDataWrapper.getManagers());
                AllUsers.setUsers(users);

                AllData.setIdNumber(allDataWrapper.getProjectIdNumber());
                AllData.setAllProjects(allDataWrapper.getAllProjects());
                AllData.setWorkSumProjects(allDataWrapper.getWorkSumProjects());

                AllData.syncProjects();

                return true;
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return false;
    }
}
