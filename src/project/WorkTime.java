package project;

import user.Designer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class WorkTime {

    private String date;
    private int designerID;
    /**
     Время храним умноженное на 10, чтобы обойтись без double или BigDecimal,
     а при выводе на экран делим на 10 и выдаем double
     */
    private int time = 0;

    public WorkTime(LocalDate date, int ID, double time) {
        this.date = date;
        this.designerID = ID;
        setTime(time);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDesignerID() {
        return designerID;
    }

    public void setDesignerID(int designerID) {
        this.designerID = designerID;
    }

    public int getTimeInt() {
        return this.time;
    }

    public double getTime() {
        return AllData.intToDouble(time);
    }

    private void setTimeInt(int newTime) {
        this.time = newTime >= 0 ? newTime : 0;
    }

    public void setTime(double newTime) {
        if (newTime == 0) {
            setTimeInt(0);
        }
        else {
            setTimeInt(AllData.doubleToInt(newTime));
        }
    }

    /** Используем для equals 2 поля из 3. Время не используем. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkTime workTime = (WorkTime) o;
        return getDesignerID() == workTime.getDesignerID() &&
                Objects.equals(getDate(), workTime.getDate());
    }

    /** Используем для hashCode 2 поля из 3. Время не используем. */
    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getDesignerID());
    }

    @Override
    public String toString() {
        return "WorkTime{" +
                "date=" + date +
                ", designerID=" + designerID +
                ", time=" + time +
                '}';
    }
}