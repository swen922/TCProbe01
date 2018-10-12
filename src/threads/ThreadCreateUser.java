package threads;

import user.Designer;
import user.Manager;
import user.User;

import java.util.concurrent.Callable;

public class ThreadCreateUser implements Callable<User> {

    private String nameLogin;
    private Class userClass;

    public ThreadCreateUser(String nameLogin, Class userClass) {
        this.nameLogin = nameLogin;
        this.userClass = userClass;
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
        if (userClass == Designer.class) {
            result = new Designer(nameLogin);
        }
        else if (userClass == Manager.class) {
            result = new Manager(nameLogin);
        }
        return result;
    }
}
