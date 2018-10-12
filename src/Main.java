import project.AllData;
import project.Project;
import project.ProjectListWrapper;
import threads.ParallelExecutor;
import threads.ThreadCreateProject;
import threads.ThreadCreateUser;
import user.AllUsers;
import user.Designer;
import user.Manager;
import user.User;
import threads.ParallelExecutor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {

        /*Designer gosha = new Designer("Gosha");
        Designer evva = new Designer("Evvlampia");
        Designer roma = new Designer("ROMA");
        AllUsers.addUser(gosha);
        AllUsers.addUser(evva);
        AllUsers.addUser(roma);

        Project p = new Project("Manager", "Very important project");
        p.addWorkTime(LocalDate.now(), gosha.getIDNumber(), 5.2);
        p.addWorkTime(LocalDate.of(2017, 5, 6), evva.getIDNumber(), 2.6);
        p.addWorkTime(LocalDate.of(2017, 5, 6), evva.getIDNumber(), 1.4);

        Project p2 = new Project("Junior", "Not important project");
        p2.addWorkTime(LocalDate.of(2018, 7, 22), roma.getIDNumber(), 3.3);

        System.out.println(p.getWorkSum());
        System.out.println(p2.getWorkSum());
        System.out.println(gosha);
        System.out.println(evva);
        System.out.println(roma);
        System.out.println(p);
        System.out.println(p2);


        ThreadCreateProject tcp = new ThreadCreateProject("Leha", "Some Project");
        Future<Project> newProject = new ParallelExecutor().getService().submit(tcp);

        ThreadCreateUser crd = new ThreadCreateUser("designer", Designer.class);
        Future<User> des = new ParallelExecutor().getService().submit(crd);

        ThreadCreateUser crm = new ThreadCreateUser("manager", Manager.class);
        Future<User> manager = new ParallelExecutor().getService().submit(crm);

        try {
            AllUsers.addUser(des.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        AllData.addNewProject(p);
        AllData.addNewProject(p2);

        Project np = null;
        try {
            np = newProject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        AllData.addNewProject(np);

        System.out.println(AllUsers.getOneUser("designer"));
        System.out.println(AllData.getAnyProject(np.getIdNumber()));

        ParallelExecutor.getService().shutdown();*/


        Designer gosha = new Designer("Gosha");
        Designer evva = new Designer("Evvlampia");
        Designer roma = new Designer("ROMA");
        AllUsers.addUser(gosha);
        AllUsers.addUser(evva);
        AllUsers.addUser(roma);

        Project p = new Project("Manager", "Very important project");
        p.addWorkTime(LocalDate.now(), gosha.getIDNumber(), 5.2);
        p.addWorkTime(LocalDate.of(2017, 5, 6), evva.getIDNumber(), 2.6);
        p.addWorkTime(LocalDate.of(2017, 5, 6), evva.getIDNumber(), 1.4);

        Project p2 = new Project("Junior", "Not important project");
        p2.addWorkTime(LocalDate.of(2018, 7, 22), roma.getIDNumber(), 3.3);

        AllData.addNewProject(p);
        AllData.addNewProject(p2);


        File file = new File("/_jToys/TCProbe01.xml");

        ProjectListWrapper plw = new ProjectListWrapper();

        System.out.println(plw.getAllProjects());
        System.out.println("");

        JAXBContext context = JAXBContext.newInstance(ProjectListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(plw, file);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        ProjectListWrapper plw2 = (ProjectListWrapper) unmarshaller.unmarshal(file);

        System.out.println(plw2.getAllProjects());




    }
}
