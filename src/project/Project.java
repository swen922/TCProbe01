package project;

import user.Designer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "project")
public class Project {

    private int idNumber;
    private String initiator;
    private String description;
    private String dateCreationString;
    private volatile boolean isArchive = false;
    private String comment;
    private List<Integer> linkedProject;
    private int PONumber = 0;
    private volatile int workSum = 0;
    private List<WorkTime> work = new ArrayList<>();

    public Project(String initiator, String description) {
        this.idNumber = AllData.incrementIdNumberAndGet();
        this.initiator = initiator;
        this.description = description;
        this.dateCreationString = AllData.formatDate(LocalDate.now());
    }

    public Project(String initiator, String description, LocalDate newDate) {
        this.idNumber = AllData.incrementIdNumberAndGet();
        this.initiator = initiator;
        this.description = description;
        this.dateCreationString = AllData.formatDate(newDate);
    }

    /*public Project() {
        this.idNumber = 0;
        this.initiator = "";
        this.description = "";
        this.dateCreationString = AllData.formatDate(LocalDate.now());
    }*/

    @XmlElement(name = "projectidnumber")
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
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

    public void setDateCreationString(String newDateCreationString) {
        this.dateCreationString = newDateCreationString;
    }

    @XmlElement(name = "linkedprojects")
    public List<Integer> getLinkedProject() {
        if (this.linkedProject != null) {
            return linkedProject;
        }
        return null;
    }

    public void setLinkedProject(List<Integer> newLinkedProject) {
        this.linkedProject = newLinkedProject;
    }

    public void addLinkedProjects(int... args) {
        if (this.linkedProject == null) {
            List<Integer> result = new ArrayList<>();
            result.addAll(Arrays.asList(args));
            setLinkedProject(result);
        }
        else {
            getLinkedProject().addAll(Arrays.asList(args));
        }
    }

    @XmlElement(name = "ponumber")
    public int getPONumber() {
        return PONumber;
    }

    public void setPONumber(int newPONumber) {
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

    public double getWorkSumDouble() {
        return AllData.intToDouble(workSum);
    }

    private void setWorkSum(int newWorkSum) {
        this.workSum = newWorkSum >= 0 ? newWorkSum : 0;
    }

    public void setWorkSumDouble(double newWorkSumDouble) {
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

    public void setWork(List<WorkTime> work) {
        this.work = work;
    }

    public synchronized void addWorkTime(LocalDate newDate, int idUser, double newTime) {

        for (WorkTime wt : work) {
            /** Проверяем наличие такого дня + дизайнера **/
            if ((AllData.parseDate(wt.getDateSting()).equals(newDate)) && (wt.getDesignerID() == idUser)) {
                // сначала правим суммарное рабочее время всего проекта
                int diff = AllData.doubleToInt(newTime) - wt.getTime();
                int newWorkSumInt = getWorkSum() + diff;
                setWorkSum(newWorkSumInt);

                /** теперь вносим время в список рабочего времени **/
                wt.setTime(AllData.doubleToInt(newTime));
                return;
            }
        }

        /** Создаем новый экземпляр WorkTime и кладем в список **/
        int newWorkSumInt = getWorkSum() + AllData.doubleToInt(newTime);
        setWorkSum(newWorkSumInt);
        work.add(new WorkTime(newDate, idUser, newTime));
    }


    public void computeWorkSum() {
        int result = 0;
        for (WorkTime wt : this.work) {
            result += wt.getTime();
        }
        workSum = result;
    }


    /** Используем только ID-номер проекта для equals,
     *  потому что остальные поля могут измениться **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return getIdNumber() == project.getIdNumber();
    }

    /** Используем только ID-номер проекта для hashCode,
     * потому что остальные поля могут измениться **/
    @Override
    public int hashCode() {
        return Objects.hash(getIdNumber());
    }

    @Override
    public String toString() {
        return "Project{" +
                "idNumber=" + idNumber +
                ", initiator='" + initiator + '\'' +
                ", description='" + description + '\'' +
                ", workSum=" + AllData.intToDouble(workSum) +
                '}';
    }
}
