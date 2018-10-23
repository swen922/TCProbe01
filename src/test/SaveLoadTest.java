package test;


import load.Loader;
import org.junit.Assert;
import org.junit.Test;
import project.AllData;
import project.Project;
import user.AllUsers;
import user.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SaveLoadTest {

    @Test
    public void saveTest() {

        Generator.generate();

        AllUsers.getOneUser(12).setFullName("");
        AllUsers.deleteUser(3);
        System.out.println("deleted user id-3");

        Loader loader = new Loader();
        System.out.println(loader.save());

        System.out.println(AllUsers.getOneUser("manager-2"));

        // Делаем копию созданных списков перед тем, как обнулить их
        Map<Integer, User> users = new HashMap<>();
        Map<Integer, Project> allProjects = new HashMap<>();
        Map<Integer, Project> activeProjects = new HashMap<>();
        users.putAll(AllUsers.getUsers());
        allProjects.putAll(AllData.getAllProjects());
        activeProjects.putAll(AllData.getActiveProjects());
        int projectsID = AllData.getIdNumber();
        int usersIDs = AllUsers.getIDCounterAllUsers();
        System.out.println(projectsID);
        System.out.println(usersIDs);
        System.out.println(AllData.intToDouble(AllData.getWorkSumProjects()));

        // Обнуляем все три списка
        AllData.getAllProjects().clear();
        AllData.getActiveProjects().clear();
        AllUsers.getUsers().clear();
        AllData.setIdNumber(0);
        AllUsers.setIDCounterAllUsers(0);

        System.out.println(AllUsers.getOneUser("manager-2"));

        // Заново читаем все данные
        System.out.println(loader.load());

        Assert.assertEquals(users, AllUsers.getUsers());
        Assert.assertEquals(allProjects, AllData.getAllProjects());
        Assert.assertEquals(activeProjects, AllData.getActiveProjects());
        Assert.assertEquals(projectsID, AllData.getIdNumber());
        Assert.assertEquals(usersIDs, AllUsers.getIDCounterAllUsers());
        Assert.assertEquals(users.get(9), AllUsers.getOneUser(9));
        Assert.assertEquals(allProjects.get(35), AllData.getAnyProject(35));
        Assert.assertTrue(AllUsers.isUserDeleted(3));

        System.out.println(AllUsers.getOneUser("manager-2"));

        AllData.addWorkTime(21, LocalDate.now(), 8, 3.4);
        System.out.println(allProjects.get(21));
        System.out.println(AllData.getAnyProject(21));
        System.out.println(AllUsers.getOneUser(12).getFullName().isEmpty());

        System.out.println("user id-3 is deleted = " + AllUsers.isUserDeleted(3));


    }

}
