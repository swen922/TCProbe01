package project;

import user.AllUsers;
import user.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name = "alldatawrapper")
public class AllDataWrapper {

    //поля класса AllData
    private int projectIdNumber;
    private Map<Integer, Project> allProjects = new HashMap<>();
    private int workSumProjects;

    //поля класса AllUsers
    private int IDCounterAllUsers;
    private Map<Integer, User> users = new HashMap<>();


    public AllDataWrapper() {
        this.projectIdNumber = AllData.getIdNumber();
        this.allProjects.putAll(AllData.getAllProjects());
        this.workSumProjects = AllData.getWorkSumProjects();

        this.IDCounterAllUsers = AllUsers.getIDCounterAllUsers();
        this.users = AllUsers.getUsers();
    }

    @XmlElement(name = "projectidnumber")
    public int getProjectIdNumber() {
        return projectIdNumber;
    }

    public void setProjectIdNumber(int newProjectIdNumber) {
        this.projectIdNumber = newProjectIdNumber;
    }

    @XmlElement(name = "allprojects")
    public Map<Integer, Project> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(Map<Integer, Project> newAllProjects) {
        this.allProjects = newAllProjects;
    }

    @XmlElement(name = "worksumprojects")
    public int getWorkSumProjects() {
        return workSumProjects;
    }

    public void setWorkSumProjects(int newWorkSumProjects) {
        this.workSumProjects = newWorkSumProjects;
    }

    @XmlElement(name = "idcounterallusers")
    public int getIDCounterAllUsers() {
        return IDCounterAllUsers;
    }

    public void setIDCounterAllUsers(int newIDCounterAllUsers) {
        this.IDCounterAllUsers = newIDCounterAllUsers;
    }

    @XmlElement(name = "allusers")
    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> newusers) {
        this.users = newusers;
    }

    @Override
    public String toString() {
        return "AllDataWrapper{" + "\n" +
                "projectIdNumber=" + projectIdNumber + "\n" +
                ", allProjects=" + allProjects + "\n" +
                ", workSumProjects=" + workSumProjects + "\n" +
                ", IDCounterAllUsers=" + IDCounterAllUsers + "\n" +
                ", users=" + users + "\n" +
                '}' + "\n";
    }
}
