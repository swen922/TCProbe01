package threads;

import project.AllData;
import project.Project;
import project.WorkTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public class ThreadModifyProject implements Callable<Boolean> {

    private int idNumber;

    // Новые значения
    private String newCompany;
    private String newInitiator;
    private String newDescription;
    private String newDateCreationString;
    private String newComment;
    private Set<Integer> newLinkedProjects;
    private Integer newPONumber;

    public ThreadModifyProject(int idNumber, String newCompany, String newInitiator, String newDescription, String newDateCreationString, Boolean newisArchive, String newComment, Set<Integer> newLinkedProjects, Integer newPONumber) {
        this.idNumber = idNumber;
        this.newCompany = newCompany;
        this.newInitiator = newInitiator;
        this.newDescription = newDescription;
        this.newDateCreationString = newDateCreationString;
        this.newComment = newComment;
        this.newLinkedProjects = newLinkedProjects;
        this.newPONumber = newPONumber;
    }

    @Override
    public Boolean call() throws Exception {
        Project project = AllData.getAnyProject(idNumber);
        boolean result = false;
        if (this.newCompany != null) {
            project.setCompany(newCompany);
            result = true;
        }
        if (this.newInitiator != null) {
            project.setInitiator(newInitiator);
            result = true;
        }
        if (this.newDescription != null) {
            project.setDescription(newDescription);
            result = true;
        }
        if (this.newDateCreationString != null) {
            project.setDateCreationString(newDateCreationString);
            result = true;
        }
        if (this.newComment != null) {
            project.setComment(newComment);
            result = true;
        }
        if (this.newComment != null) {
            project.setComment(newComment);
            result = true;
        }
        if (this.newLinkedProjects != null) {
            project.setLinkedProject(newLinkedProjects);
            result = true;
        }
        if (this.newPONumber != null) {
            project.setPONumber(newPONumber);
            result = true;
        }
        return result;
    }
}
