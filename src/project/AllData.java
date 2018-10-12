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

    /*private List<Project> activeProjects = new CopyOnWriteArrayList<>();
    private List<Project> allProjects = new CopyOnWriteArrayList<>();*/

    private static Map<Integer, Project> activeProjects = new ConcurrentHashMap<>();

    private static Map<Integer, Project> allProjects = new ConcurrentHashMap<>();



    /** Стандартные геттеры и сеттеры */

    public static int getIdNumber() {
        return idNumber.get();
    }

    public static Map<Integer, Project> getActiveProjects() {
        return activeProjects;
    }

    public static synchronized void setActiveProjects(Map<Integer, Project> activeProjects) {
        AllData.activeProjects = activeProjects;
    }

    public static Map<Integer, Project> getAllProjects() {
        return allProjects;
    }

    public static synchronized void setAllProjects(Map<Integer, Project> allProjects) {
        AllData.allProjects = allProjects;
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
            return true;
        }
        return false;
    }

    public static boolean deleteProject(int deadProject) {
        if (isProjectExist(deadProject)) {
            allProjects.remove(deadProject);
            activeProjects.remove(deadProject);
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



    /** Метод сверки и синхронизации двух списков */
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



    /** методы-утилиты */

    public static int incrementIdNumberAndGet() {
        return idNumber.incrementAndGet();

    }

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
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

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
