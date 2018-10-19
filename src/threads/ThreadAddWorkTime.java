package threads;

import project.AllData;
import project.Project;

import java.time.LocalDate;
import java.util.concurrent.Callable;

public class ThreadAddWorkTime implements Callable<Boolean> {

    private int idProject;
    private LocalDate newDate;
    private int idUser;
    private double newTime;

    public ThreadAddWorkTime(int idProject, LocalDate newDate, int idUser, double newTime) {
        this.idProject = idProject;
        this.newDate = newDate;
        this.idUser = idUser;
        this.newTime = newTime;
    }

    @Override
    public Boolean call() throws Exception {
        boolean result = AllData.addWorkTime(idProject, newDate, idUser, newTime);
        return result;
    }
}
