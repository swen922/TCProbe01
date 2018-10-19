package project;

import user.Designer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.util.*;

@XmlRootElement(name = "project")
public class Project {

    private int idNumber;
    private String company;
    private String initiator;
    private String description;
    private String dateCreationString;
    private volatile boolean isArchive = false;
    private String comment;
    private Set<Integer> linkedProjects = new TreeSet<>();
    private int PONumber;
    private volatile int workSum = 0;
    private List<WorkTime> work = new ArrayList<>();

    public Project(String comp, String initiator, String description) {
        this.idNumber = AllData.incrementIdNumberAndGet();
        this.company = comp;
        this.initiator = initiator;
        this.description = description;
        this.dateCreationString = AllData.formatDate(LocalDate.now());
    }

    public Project(String comp, String initiator, String description, LocalDate newDate) {
        this.idNumber = AllData.incrementIdNumberAndGet();
        this.company = comp;
        this.initiator = initiator;
        this.description = description;
        this.dateCreationString = AllData.formatDate(newDate);
    }

    public Project() {
        this.idNumber = 0;
        this.company = "";
        this.initiator = "";
        this.description = "";
        this.dateCreationString = AllData.formatDate(LocalDate.now());
    }

    @XmlElement(name = "projectidnumber")
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @XmlElement(name = "clientcompany")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @XmlElement(name = "initby")
    public String getInitiator() {
        return initiator;
    }

    public synchronized void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    @XmlElement(name = "descr")
    public String getDescription() {
        return description;
    }

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "datecreationstring")
    public String getDateCreationString() {
        return dateCreationString;
    }

    public synchronized void setDateCreationString(String newDateCreationString) {
        this.dateCreationString = newDateCreationString;
    }

    @XmlElement(name = "linkedprojects")
    public Set<Integer> getLinkedProjects() {
        return this.linkedProjects;
    }

    public synchronized void setLinkedProject(Set<Integer> newLinkedProjects) {
        this.linkedProjects = newLinkedProjects;
    }

    public synchronized void addLinkedProjects(Integer... args) {
        this.linkedProjects.addAll(Arrays.asList(args));
    }

    @XmlElement(name = "ponumber")
    public int getPONumber() {
        return PONumber;
    }

    public synchronized void setPONumber(int newPONumber) {
        this.PONumber = newPONumber;
    }

    @XmlElement(name = "isarchive")
    public boolean isArchive() {
        return isArchive;
    }

    public synchronized void setArchive(boolean archive) {
        isArchive = archive;
    }

    @XmlElement(name = "comment")
    public String getComment() {
        return comment;
    }

    public synchronized void setComment(String comment) {
        this.comment = comment;
    }

    @XmlElement(name = "worksumint")
    public int getWorkSum() {
        return workSum;
    }

    private void setWorkSum(int newWorkSum) {
        this.workSum = newWorkSum >= 0 ? newWorkSum : 0;
    }

    @XmlTransient
    public double getWorkSumDouble() {
        return AllData.intToDouble(workSum);
    }

    protected void setWorkSumDouble(double newWorkSumDouble) {
        if (newWorkSumDouble <= 0) {
            setWorkSum(0);
        }
        else {
            this.workSum = AllData.doubleToInt(newWorkSumDouble);
        }
    }

    @XmlElement(name = "listworks")
    public List<WorkTime> getWork() {
        return work;
    }

    public synchronized void setWork(List<WorkTime> work) {
        this.work = work;
    }


    /** Метод рассчитан на вызов из класса AllData,
     * в котором содержится одноименный метод для обращения извне.
     * Возвращаемое значение int используется в AllData для добавления
     * к суммарному общему рабочему времени workSumProjects
     * */
    protected int addWorkTime(LocalDate newDate, int idUser, double newTimeDouble) {

        int newTimeInt = AllData.doubleToInt(newTimeDouble);

        for (WorkTime wt : work) {
            // Проверяем наличие такого дня + дизайнера
            if ((AllData.parseDate(wt.getDateString()).equals(newDate)) && (wt.getDesignerID() == idUser)) {
                // сначала правим суммарное рабочее время всего проекта
                int diff = newTimeInt - wt.getTime();
                int newWorkSumInt = getWorkSum() + diff;
                setWorkSum(newWorkSumInt);

                // теперь вносим время в список рабочего времени
                wt.setTime(newTimeInt);
                return diff;
            }
        }

        // Создаем новый экземпляр WorkTime и кладем в список
        int newWorkSumInt = getWorkSum() + newTimeInt;
        setWorkSum(newWorkSumInt);
        work.add(new WorkTime(newDate, idUser, newTimeDouble));
        return newTimeInt;
    }


    public void computeWorkSum() {
        int result = 0;
        for (WorkTime wt : this.work) {
            result += wt.getTime();
        }
        this.workSum = result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return getIdNumber() == project.getIdNumber() &&
                isArchive() == project.isArchive() &&
                getPONumber() == project.getPONumber() &&
                getWorkSum() == project.getWorkSum() &&
                Objects.equals(getInitiator(), project.getInitiator()) &&
                Objects.equals(getDescription(), project.getDescription()) &&
                Objects.equals(getDateCreationString(), project.getDateCreationString()) &&
                Objects.equals(getComment(), project.getComment()) &&
                Objects.equals(getLinkedProjects(), project.getLinkedProjects()) &&
                Objects.equals(getWork(), project.getWork());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdNumber(), getInitiator(), getDescription(), getDateCreationString(), isArchive(), getComment(), getLinkedProjects(), getPONumber(), getWorkSum(), getWork());
    }

    @Override
    public String toString() {
        return "Project{" +
                "idNumber=" + idNumber +
                ", initiator='" + initiator + '\'' +
                ", description='" + description + '\'' +
                ", workSum=" + AllData.intToDouble(workSum) +
                '}' + "\n";
    }
}
