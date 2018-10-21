package threads;

import project.AllData;

import java.util.concurrent.Callable;

public class ThreadDeleteProject implements Callable<Boolean> {
    private int idNumber;

    public ThreadDeleteProject(int idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public Boolean call() throws Exception {
        return AllData.deleteProject(idNumber);
    }
}
