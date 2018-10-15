package project;

import user.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AllData {

    private static volatile AtomicInteger idNumber = new AtomicInteger(0);

    private static Map<Integer, Project> activeProjects = new ConcurrentHashMap<>();

    private static Map<Integer, Project> allProjects = new ConcurrentHashMap<>();

    private static volatile AtomicInteger workSumProjects = new AtomicInteger(0);



    /** Стандартные геттеры и сеттеры */

    public static int incrementIdNumberAndGet() {
        return idNumber.incrementAndGet();

    }

    public static int getIdNumber() {
        return idNumber.get();
    }

    public static void setIdNumber(int newIdNumber) {
        idNumber.set(newIdNumber);
    }

    public static Map<Integer, Project> getActiveProjects() {
        return activeProjects;
    }

    public static synchronized void setActiveProjects(Map<Integer, Project> newActiveProjects) {
        AllData.activeProjects = newActiveProjects;
    }

    public static Map<Integer, Project> getAllProjects() {
        return allProjects;
    }

    public static synchronized void setAllProjects(Map<Integer, Project> newAllProjects) {
        AllData.allProjects = newAllProjects;
    }

    public static int getWorkSumProjects() {
        return workSumProjects.get();
    }

    public static void setWorkSumProjects(int newWorkSumProjects) {
        AllData.workSumProjects.set(newWorkSumProjects);
    }



    /** Геттеры активного, неактивного и любого проекта из мапы
     * @return null
     * */

    public static Project getOneActiveProject(int idActiveProject) {
        if (isProjectExist(idActiveProject)) {
            return activeProjects.get(idActiveProject);
        }
        return null;
    }

    public static Project getAnyProject(int idProject) {
        if (isProjectExist(idProject)) {
            return allProjects.get(idProject);
        }
        return null;
    }

    public static Project getOneArchiveProject(int idArchiveProject) {
        if (isProjectArchive(idArchiveProject)) {
            return allProjects.get(idArchiveProject);
        }
        return null;
    }



    /** Методы добавления, удаления проектов */

    public static boolean addNewProject(Project newProject) {
        if (!isProjectExist(newProject.getIdNumber())) {
            allProjects.put(newProject.getIdNumber(), newProject);
            activeProjects.put(newProject.getIdNumber(), newProject);

            // Добавляем время в общее суммарное
            int tmp = workSumProjects.get();
            tmp += newProject.getWorkSum();
            workSumProjects.set(tmp);

            return true;
        }
        return false;
    }

    public static boolean deleteProject(int deadProject) {
        if (isProjectExist(deadProject)) {
            allProjects.remove(deadProject);
            activeProjects.remove(deadProject);

            // Удаляем время из общего суммарного
            int tmp = workSumProjects.get();
            tmp -= allProjects.get(deadProject).getWorkSum();
            if (tmp < 0) {
                tmp = 0;
            }
            workSumProjects.set(tmp);

            return true;
        }
        return false;
    }

    public static boolean setProjectArchive(int moveToArchiveProject) {
        if (isProjectExist(moveToArchiveProject)) {
            Project project = allProjects.get(moveToArchiveProject);
            project.setArchive(true);
            activeProjects.remove(moveToArchiveProject);
            return true;
        }
        return false;
    }

    public static boolean setProjectActive(int moveToActiveProject) {
        if (isProjectExist(moveToActiveProject)) {
            Project project = allProjects.get(moveToActiveProject);
            project.setArchive(false);
            activeProjects.put(project.getIdNumber(), project);
            return true;
        }
        return false;
    }



    /** Методы проверки существования проекта в списке
     * и его проверки на архивное состояние */

    public static boolean isProjectExist(int idProject) {
        if (idProject <= 0 || idProject > getIdNumber()) {
            return false;
        }
        if (allProjects.containsKey(idProject)) {
            return true;
        }
        return false;
    }

    public static boolean isProjectArchive(int idProject) {
        if (isProjectExist(idProject)) {
            Project project = allProjects.get(idProject);
            if (project.isArchive()) {
                return true;
            }
        }
        return false;
    }



    /** Метод сверки и синхронизации списков и поля суммарного времени */
    private static synchronized void syncProjects() {
        Map<Integer, Project> newActiveProjects = new HashMap<>();
        allProjects.forEach((k,v)-> {
            if (!v.isArchive()) {
                newActiveProjects.put(k, v);
            }
        });
        activeProjects.clear();
        activeProjects.putAll(newActiveProjects);
    }

    public static synchronized int computeWorkSum() {
        int result = 0;

        Collection<Project> values = allProjects.values();
        for (Project p : values) {
            result += p.getWorkSum();
        }
        workSumProjects.set(result);
        return result;
    }



    /** методы-утилиты */

    public static int doubleToInt(double argument) {
        return (int) (argument * 10);
    }

    public static double intToDouble(int argument) {
        double tmp = (double) argument / 10;
        BigDecimal result = new BigDecimal(Double.toString(tmp));
        result = result.setScale(1, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    /** Форматировщик даты. */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d");

    public static String formatDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parseDate(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean validDate(String dateString) {
        return parseDate(dateString) != null;
    }


}
