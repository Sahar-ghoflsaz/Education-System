/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public abstract class Personnel extends Person {
    private final String employeeCode;
    public Personnel(String employeeCode, String firstName, String lastName, String fatherName, String idNum, String birthcertNum, String gender, Date birthday){
        super(firstName, lastName, fatherName, idNum, birthcertNum, gender, birthday);
        this.employeeCode = employeeCode;
    }
    public String getEmployeeCode() {
        return employeeCode;
    }
    public abstract int sallary();
}
