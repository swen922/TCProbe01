package threads;

import project.AllData;
import project.Project;

import java.util.concurrent.Callable;

public class ThreadCreateProject implements Callable<Boolean> {

    private String companyClient;
    private String initiator;
    private String description;

    public ThreadCreateProject(String companyClient, String initiator, String description) {
        this.companyClient = companyClient;
        this.initiator = initiator;
        this.description = description;
    }

    public String getCompanyClient() {
        return companyClient;
    }

    public void setCompanyClient(String companyClient) {
        this.companyClient = companyClient;
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
    public Boolean call() throws Exception {
        Project project = new Project(companyClient, initiator, description);
        return AllData.addNewProject(project);
    }
}
