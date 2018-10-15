package user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "designer")
public class Designer implements User {
    private final int IDNumber;
    private String nameLogin;
    private String fullName;
    private String email;

    public Designer(String nameLogin) {
        this.IDNumber = AllUsers.incrementIdNumberAndGet();
        this.nameLogin = nameLogin.toLowerCase();
    }

    @XmlElement(name = "designeridnumber")
    public int getIDNumber() {
        return IDNumber;
    }

    @XmlElement(name = "desigernamelogin")
    public String getNameLogin() {
        return nameLogin;
    }

    public void setNameLogin(String newNameLogin) {
        this.nameLogin = newNameLogin;
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

    /** Используем только ID-номер дизайнера для equals,
     * потому что остальные поля могут измениться **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Designer designer = (Designer) o;
        return getIDNumber() == designer.getIDNumber();
    }

    /** Используем только ID-номер дизайнера для hashCode,
     * потому что остальные поля могут измениться **/
    @Override
    public int hashCode() {
        return Objects.hash(getIDNumber());
    }

    @Override
    public String toString() {
        return "Designer{" +
                "IDNumber=" + IDNumber +
                ", nameLogin='" + nameLogin + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

