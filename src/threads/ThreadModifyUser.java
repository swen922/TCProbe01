package threads;

import user.*;

import java.util.concurrent.Callable;

public class ThreadModifyUser implements Callable<Boolean> {

    // текущие значения
    private int IDNumber;

    // новые значения
    private String newNameLogin;
    private String newFullName;
    private String newEmail;

    public ThreadModifyUser(int IDNumber, String newNameLogin, String newFullName, String newEmail) {
        this.IDNumber = IDNumber;

        // пошли новые поля
        this.newNameLogin = newNameLogin;
        this.newFullName = newFullName;
        this.newEmail = newEmail;
    }

    @Override
    public Boolean call() throws Exception {
        User user = AllUsers.getOneUser(this.IDNumber);
        boolean result = false;
        if (newNameLogin != null) {
            user.setNameLogin(newNameLogin);
            result = true;
        }
        if (newFullName != null) {
            user.setFullName(newFullName);
            result = true;
        }
        if (newEmail != null) {
            user.setEmail(newEmail);
            result = true;
        }
        return result;
    }
}
