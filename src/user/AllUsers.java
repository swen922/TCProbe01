package user;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AllUsers {

    private static Map<Integer, User> users = new ConcurrentHashMap<>();

    /** Стандартные геттеры и сеттеры */

    public static Map<Integer, User> getUsers() {
        return users;
    }

    public static void setUsers(Map<Integer, User> users) {
        AllUsers.users = users;
    }



    /** Геттеры отдельных юзеров из мапы
     * @return null
     * */

    public static User getOneUser(int idUser) {
        if (isUserExist(idUser)) {
            return users.get(idUser);
        }
        return null;
    }

    public static User getOneUser(String userNameLogin) {
        if (isNameLoginExist(userNameLogin)) {
            for (User u : users.values()) {
                if (u.getNameLogin().equals(userNameLogin)) {
                    return u;
                }
            }
        }
        return null;
    }



    /** Добавление и удаление пользователя */

    public static boolean addUser(User user) {
        if (!users.containsKey(user.getIDNumber())) {
            users.put(user.getIDNumber(), user);
            return true;
        }
        return false;
    }

    public static boolean deleteUser(int idUser) {
        if (isUserExist(idUser)) {
            users.remove(idUser);
            return true;
        }
        return false;
    }



    /** Методы проверки существования юзера
     * * с данным ID и nameLogin
     */
    public static boolean isUserExist(int idNumber) {
        if (idNumber <= 0 || idNumber > IDCounter.getIDCounterAllUsers()) {
            return false;
        }
        return users.containsKey(idNumber);
    }

    public static boolean isNameLoginExist(String nameLog) {
        if (nameLog == null || nameLog.isEmpty()) {
            return false;
        }

        Collection<User> tmpUsers = users.values();

        for (User u : tmpUsers) {
            if (nameLog.equals(u.getNameLogin())) {
                return true;
            }
        }

        return false;
    }


}
