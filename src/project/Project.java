package project;

import user.Designer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "project")
public class Project {

    /** TODO При восстановлении idNumber будет восстанавливаться, не надо ли убрать final?  */
    private final int idNumber;
    private String initiator;
    private String description;
    private volatile boolean isArchive = false;
    private String comment;
    private volatile int workSum = 0;
    private List<WorkTime> work = new ArrayList<>();

    public Project(String initiator, String description) {
        this.idNumber = AllData.incrementIdNumberAndGet();
        this.initiator = initiator;
        this.description = description;
    }

    public Project() {
        this.idNumber = 0;
        this.initiator = "";
        this.description = "";
    }

    @XmlElement(name = "projectidnumber")
    public int getIdNumber() {
        return idNumber;
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

    @XmlElement(name = "isarchive")
    public boolean isArchive() {
        return isArchive;
    }

    public synchronized void setArchive(boolean archive) {
        isArchive = archive;
    }

    @XmlElement(name = "cmmnt")
    public String getComment() {
        return comment;
    }

    public synchronized void setComment(String comment) {
        this.comment = comment;
    }

    @XmlElement(name = "worksumint")
    private int getWorkSumInt() {
        return workSum;
    }

    /*public double getWorkSum() {
        return AllData.intToDouble(workSum);
    }*/

    private void setWorkSum(int newWorkSum) {
        this.workSum = newWorkSum >= 0 ? newWorkSum : 0;
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
                int diff = AllData.doubleToInt(newTime) - wt.getTimeInt();
                int newWorkSumInt = getWorkSumInt() + diff;
                setWorkSum(newWorkSumInt);

                /** теперь вносим время в список рабочего времени **/
                wt.setTime(newTime);
                return;
            }
        }

        /** Создаем новый экземпляр WorkTime и кладем в список **/
        int newWorkSumInt = getWorkSumInt() + AllData.doubleToInt(newTime);
        setWorkSum(newWorkSumInt);
        work.add(new WorkTime(newDate, idUser, newTime));
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
