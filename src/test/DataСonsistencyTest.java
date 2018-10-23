package test;

import org.junit.Assert;
import project.AllData;
import project.Project;
import user.AllUsers;

import java.util.HashMap;
import java.util.Map;

public class DataСonsistencyTest {

    public void deleteUserCheckData() {
        Generator.generate();

        int workSum1 = AllData.getWorkSumProjects();
        System.out.println("before = " + workSum1);

        Map<Integer, Project> allproject1 = new HashMap<>();
        allproject1.putAll(AllData.getAllProjects());

        AllUsers.deleteUser(5);
        AllUsers.deleteUser(6);
        AllUsers.deleteUser(7);

        int workSum2 = AllData.getWorkSumProjects();
        System.out.println("after = " + workSum2);

        Assert.assertEquals(workSum1, workSum2);
        Assert.assertEquals(allproject1, AllData.getAllProjects());
        System.out.println(allproject1 == AllData.getAllProjects());


    }
}
