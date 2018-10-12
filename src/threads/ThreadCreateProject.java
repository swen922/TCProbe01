package threads;

import project.Project;

import java.util.concurrent.Callable;

public class ThreadCreateProject implements Callable<Project> {

    private String initiator;
    private String description;

    public ThreadCreateProject(String initiator, String description) {
        this.initiator = initiator;
        this.description = description;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Project call() throws Exception {
        return new Project(initiator, description);
    }
}
