package test;

import org.junit.Assert;
import org.junit.Test;
import project.AllData;
import project.Project;
import threads.*;
import user.AllUsers;
import user.Role;
import user.User;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ThreadsTest {

    @Test
    public void threadsTest() {

        Generator.generate();

        try {
            ThreadCreateUser threadCreateUser1 = new ThreadCreateUser("DESgnr-1000", Role.DESIGNER);
            Future<Boolean> tcu1 = ParallelExecutor.getService().submit(threadCreateUser1);
            Assert.assertTrue(tcu1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("new designer = \n" + AllUsers.getOneUser(13));
        System.out.println("");


        try {
            ThreadCreateUser threadCreateUser2 = new ThreadCreateUser("MANAgr-1000", Role.MANAGER);
            Future<Boolean> tcu2 = ParallelExecutor.getService().submit(threadCreateUser2);
            Assert.assertTrue(tcu2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("new manager = \n" + AllUsers.getOneUser(14));
        System.out.println("");


        try {
            ThreadCreateProject threadCreateProject1 = new ThreadCreateProject("Kellog", "Ludmila", "Проект для Келлога");
            Future<Boolean> tcp1 = ParallelExecutor.getService().submit(threadCreateProject1);
            Assert.assertTrue(tcp1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(AllData.getAnyProject(101));
        System.out.println("");


        System.out.println("Project id-45, worksum = " + AllData.getOneActiveProject(45).getWorkSum());
        try {
            ThreadAddWorkTime threadAddWorkTime1 = new ThreadAddWorkTime(45, LocalDate.now(), 9, 5.5);
            Future<Boolean> taw1 = ParallelExecutor.getService().submit(threadAddWorkTime1);
            Assert.assertTrue(taw1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Project id-45, worksum = " + AllData.getOneActiveProject(45).getWorkSum());
        System.out.println("");


        System.out.println(AllUsers.getOneUser(5));
        try {
            ThreadModifyUser threadModifyUser = new ThreadModifyUser(5, null, "50FullName", "");
            Future<Boolean> tmf = ParallelExecutor.getService().submit(threadModifyUser);
            Assert.assertTrue(tmf.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(AllUsers.getOneUser(5));
        System.out.println("");


        System.out.println(AllData.getAnyProject(50));
        try {
            ThreadModifyProject threadModifyProject = new ThreadModifyProject(50, null, "Karabas",
                    null, null, new Boolean(true), "My comment is NEW!", null, 555);
            Future<Boolean> tmpr = ParallelExecutor.getService().submit(threadModifyProject);
            Assert.assertTrue(tmpr.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(AllData.getAnyProject(50));
        System.out.println("");


        System.out.println(AllUsers.getOneUser(3));
        System.out.println("user id 3 exist = " + AllUsers.isUserExist(3));
        try {
            Assert.assertTrue(AllUsers.isUserExist(3));
            ThreadDeleteUser threadDeleteUser = new ThreadDeleteUser(3);
            Future<Boolean> tdu = ParallelExecutor.getService().submit(threadDeleteUser);
            Assert.assertTrue(tdu.get());
            Assert.assertFalse(AllUsers.isUserExist(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("user id 3 exist = " + AllUsers.isUserExist(3));



        System.out.println(AllData.getAnyProject(20));
        System.out.println("project id-20 exist = " + AllData.isProjectExist(20));
        try {
            Assert.assertTrue(AllData.isProjectExist(20));
            ThreadDeleteProject threadDeleteProject = new ThreadDeleteProject(20);
            Future<Boolean> tdp = ParallelExecutor.getService().submit(threadDeleteProject);
            Assert.assertTrue(tdp.get());
            Assert.assertFalse(AllData.isProjectExist(20));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("project id-20 exist = " + AllData.isProjectExist(20));
        System.out.println("");

        ParallelExecutor.getService().shutdown();

    }
}
