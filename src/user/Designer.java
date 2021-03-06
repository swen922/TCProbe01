package user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "designer")
public class Designer implements User {
    private int IDNumber;
    private String nameLogin;
    private Role role = Role.DESIGNER;
    private String fullName;
    private String email;

    /**
     * TODO в графон вставить проверку nameLogin на уникальность,
     * чтобы не было дублей логина в системе
     * */


    public Designer(String nameLogin) {
        this.IDNumber = AllUsers.incrementIdNumberAndGet();
        this.nameLogin = nameLogin.toLowerCase();
    }

    public Designer() {
    }

    @XmlElement(name = "designeridnumber")
    public int getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(int newIDNumber) {
        this.IDNumber = newIDNumber;
    }

    @XmlElement(name = "desigernamelogin")
    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String newNameLogin) {
        this.nameLogin = newNameLogin;
    }

    @XmlElement(name = "designerrole")
    public Role getRole() {
        return role;
    }

    public void setRole(Role newrole) {
        this.role = newrole;
    }

    @XmlElement(name = "designerfullname")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String newFullName) {
        this.fullName = newFullName;
    }

    @XmlElement(name = "designeremail")
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
        Designer designer = (Designer) o;
        return getIDNumber() == designer.getIDNumber() &&
                Objects.equals(getNameLogin(), designer.getNameLogin()) &&
                getRole() == designer.getRole() &&
                Objects.equals(getFullName(), designer.getFullName()) &&
                Objects.equals(getEmail(), designer.getEmail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIDNumber(), getNameLogin(), getRole(), getFullName(), getEmail());
    }

    @Override
    public String toString() {
        return "Designer{" +
                "IDNumber=" + IDNumber +
                ", nameLogin='" + nameLogin + '\'' +
                ", role=" + role +
                ", fullName='" + fullName + '\'' +
                '}' + "\n";
    }
}

