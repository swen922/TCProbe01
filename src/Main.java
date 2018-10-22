import project.AllData;
import project.AllDataWrapper;
import project.Project;
import project.WorkTime;
import test.SaveLoadTest;
import test.ThreadsTest;
import threads.ParallelExecutor;
import threads.ThreadCreateProject;
import threads.ThreadCreateUser;
import user.AllUsers;
import user.Designer;
import user.Manager;
import user.User;
import threads.ParallelExecutor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {

        ThreadsTest threadsTest = new ThreadsTest();
        threadsTest.threadsTest();

    }
}
