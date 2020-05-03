package model;

import java.util.Date;

public class Person extends Institution implements java.io.Serializable{

    private final String firstName;
    private final String lastName;
    private String fatherName;
    private final String idNum;
    private final String birthcertNum;
    private final String gender;
    private final Date birthday;

    public Person(String firstName, String lastName, String fatherName, String idNum, String birthcertNum, String gender, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.idNum = idNum;
        this.birthcertNum = birthcertNum;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public String getIdNum() {
        return idNum;
    }

    public String getBirthcertNum() {
        return birthcertNum;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "id: " + getIdNum() + ", name: " + getFirstName() + " "
                + getLastName() + ", father: " + getFatherName() + ", gender: "
                + getGender() + ", birthday" + getBirthday().toString();
    }
}
