package threads;

import project.AllData;
import user.AllUsers;

import java.util.concurrent.Callable;

public class ThreadDeleteUser implements Callable<Boolean> {
    private int IDNumber;

    public ThreadDeleteUser(int IDNumber) {
        this.IDNumber = IDNumber;
    }

    @Override
    public Boolean call() throws Exception {
        return AllUsers.deleteUser(IDNumber);
    }
}
