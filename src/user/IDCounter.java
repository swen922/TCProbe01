package user;

import java.util.concurrent.atomic.AtomicInteger;

public class IDCounter {
    public static volatile AtomicInteger IDCounterAllUsers = new AtomicInteger(0);

    public static int getIDCounterAllUsers() {
        return IDCounterAllUsers.get();
    }

    public static int incrementIdNumberAndGet() {
        return IDCounterAllUsers.incrementAndGet();
    }
}
