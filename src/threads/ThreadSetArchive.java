package threads;

import project.AllData;

import java.util.concurrent.Callable;

public class ThreadSetArchive implements Callable<Boolean> {

    private int projectIDnumber;
    private boolean isArchive;

    public ThreadSetArchive(int projectIDnumber, boolean isArchive) {
        this.projectIDnumber = projectIDnumber;
        this.isArchive = isArchive;
    }

    @Override
    public Boolean call() throws Exception {
        return AllData.changeProjectArchiveStatus(projectIDnumber, isArchive);
    }
}
