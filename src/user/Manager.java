package user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "manager")
public class Manager implements User {
    private int IDNumber = 0;
    private String nameLogin;
    private Role role;
    private String fullName;
    private String email;

    public Manager(String nameLogin) {
        this.IDNumber = AllUsers.incrementIdNumberAndGet();
        this.nameLogin = nameLogin.toLowerCase();
        this.role = Role.MANAGER;
    }

    public Manager() {
    }

    @XmlElement(name = "manageridnumber")
    public int getIDNumber() {
        return IDNumber;
    }

    @XmlElement(name = "managernamelogin")
    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String newNameLogin) {
        this.nameLogin = newNameLogin;
    }

    @XmlElement(name = "managerrole")
    public Role getRole() {
        return role;
    }

    public void setRole(Role newrole) {
        this.role = newrole;
    }

    @XmlElement(name = "managerfullname")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String newFullName) {
        this.fullName = newFullName;
    }

    @XmlElement(name = "manageremail")
    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
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
