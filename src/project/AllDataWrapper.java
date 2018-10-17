package project;

import user.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name = "alldatawrapper")
public class AllDataWrapper {

    //поля класса AllData
    private int allProjectsIdNumber;
    private Map<Integer, Project> allProjects = new HashMap<>();
    private int workSumProjects;

    //поля класса AllUsers
    private int IDCounterAllUsers;
    private Map<Integer, Designer> designers = new HashMap<>();
    private Map<Integer, Manager> managers = new HashMap<>();
    //private Map<Integer, User> users = new HashMap<>();



    public AllDataWrapper() {
        this.allProjectsIdNumber = AllData.getIdNumber();
        this.allProjects.putAll(AllData.getAllProjects());
        this.workSumProjects = AllData.getWorkSumProjects();

        this.IDCounterAllUsers = AllUsers.getIDCounterAllUsers();

        Collection<? extends User> collUsers = AllUsers.getUsers().values();
        for (User u : collUsers) {
            if (u.getRole().equals(Role.DESIGNER)) {
                Designer des = (Designer) u;
                this.designers.put(des.getIDNumber(), des);
            }
            else if (u.getRole().equals(Role.MANAGER)) {
                Manager man = (Manager) u;
                this.managers.put(man.getIDNumber(), man);
            }
        }
    }

    @XmlElement(name = "allprojectsidnumber")
    public int getAllProjectsIdNumber() {
        return allProjectsIdNumber;
    }

    public void setAllProjectsIdNumber(int newAllProjectIdNumber) {
        this.allProjectsIdNumber = newAllProjectIdNumber;
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

    @XmlElement(name = "usersdesigners")
    public Map<Integer, Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(Map<Integer, Designer> newdesigners) {
        this.designers = newdesigners;
    }

    @XmlElement(name = "usersmanagers")
    public Map<Integer, Manager> getManagers() {
        return managers;
    }

    public void setManagers(Map<Integer, Manager> newmanagers) {
        this.managers = newmanagers;
    }

    /*@XmlElement(name = "allusers")
    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> newusers) {
        this.users = newusers;
    }*/

    @Override
    public String toString() {
        return "AllDataWrapper{" + "\n" +
                "projectIdNumber=" + allProjectsIdNumber + "\n" +
                ", allProjects=" + allProjects + "\n" +
                ", workSumProjects=" + workSumProjects + "\n" +
                ", IDCounterAllUsers=" + IDCounterAllUsers + "\n" +
                ", designers=" + designers + "\n" +
                ", managers=" + managers + "\n" +
                '}' + "\n";
    }
}
