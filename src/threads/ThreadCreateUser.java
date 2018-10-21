package threads;

import user.Designer;
import user.Manager;
import user.Role;
import user.User;

import java.util.concurrent.Callable;

public class ThreadCreateUser implements Callable<User> {

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
    public User call() throws Exception {
        User result = null;
        if (role.equals(Role.DESIGNER)) {
            result = new Designer(nameLogin);
        }
        else if (role.equals(Role.MANAGER)) {
            result = new Manager(nameLogin);
        }
        return result;
    }
}
