package user;

import java.util.Objects;

public class Manager implements User {
    private final int IDNumber;
    private String nameLogin;
    private String fullName;
    private String email;

    public Manager(String nameLogin) {
        this.IDNumber = IDCounter.incrementIdNumberAndGet();
        this.nameLogin = nameLogin.toLowerCase();
    }

    @Override
    public int getIDNumber() {
        return IDNumber;
    }

    @Override
    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String nameLogin) {
        this.nameLogin = nameLogin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return getIDNumber() == manager.getIDNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIDNumber());
    }

    @Override
    public String toString() {
        return "Manager{" +
                "IDNumber=" + IDNumber +
                ", nameLogin='" + nameLogin + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
