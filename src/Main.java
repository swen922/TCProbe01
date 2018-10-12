import project.AllData;
import project.Project;
import threads.ParallelExecutor;
import threads.ThreadCreateProject;
import threads.ThreadCreateUser;
import user.AllUsers;
import user.Designer;
import user.Manager;
import user.User;
import threads.ParallelExecutor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        /*BigDecimal oldSum = new BigDecimal(5.0);
        BigDecimal addSum = new BigDecimal(3.0);
        BigDecimal newSum = oldSum.add(addSum);
        System.out.println(newSum);*/

        /*LocalDate a = LocalDate.of(2014, 6, 30);
        LocalDate b = LocalDate.of(2014, 6, 30);
        System.out.println(a.equals(b));*/


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


        ParallelExecutor.getService().shutdown();

    }
}
