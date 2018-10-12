package user;

import java.util.Objects;

public class Designer implements User {
    private final int IDNumber;
    private String nameLogin;
    private String fullName;
    private String email;

    public Designer(String nameLogin) {
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

