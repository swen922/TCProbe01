package threads;

import user.*;

import java.util.concurrent.Callable;

public class ThreadCreateUser implements Callable<Boolean> {

    private String nameLogin;
    private Role role;

    public ThreadCreateUser(String nameLogin, Role role) {
        this.nameLogin = nameLogin;
        this.role = role;
    }

    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String nameLogin) {
        this.nameLogin = nameLogin;
    }

    @Override
    public Boolean call() throws Exception {
        User user = null;
        if (role.equals(Role.DESIGNER)) {
            user = new Designer(nameLogin);
        }
        else if (role.equals(Role.MANAGER)) {
            user = new Manager(nameLogin);
        }
        return AllUsers.addUser(user);
    }
}
