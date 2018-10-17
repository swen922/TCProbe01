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
    public static void saveTest() {

        Generator.generate();

        Loader loader = new Loader();
        System.out.println(loader.save());

        System.out.println(AllUsers.getOneUser("manager-1"));

        // Делаем копию созданных список перед тем, как обнулить их
        Map<Integer, User> users = new HashMap<>();
        Map<Integer, Project> allProjects = new HashMap<>();
        Map<Integer, Project> activeProjects = new HashMap<>();
        users.putAll(AllUsers.getUsers());
        allProjects.putAll(AllData.getAllProjects());
        activeProjects.putAll(AllData.getActiveProjects());

        // Обнуляем все три списка
        AllData.getAllProjects().clear();
        AllData.getActiveProjects().clear();
        AllUsers.getUsers().clear();

        System.out.println(AllUsers.getOneUser("manager-1"));

        // Звново читаем все данные
        System.out.println(loader.load());

        Assert.assertEquals(users, AllUsers.getUsers());
        Assert.assertEquals(allProjects, AllData.getAllProjects());
        Assert.assertEquals(activeProjects, AllData.getActiveProjects());

        System.out.println(AllUsers.getOneUser("manager-1"));
    }

}
