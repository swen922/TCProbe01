package threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelExecutor {

    private ParallelExecutor() {
    }

    private static ExecutorService service = Executors.newFixedThreadPool(3);

    public static ExecutorService getService() {
        return service;
    }

}
