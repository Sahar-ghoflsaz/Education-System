package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import virtual.VProfessor;

public class Master extends TrainingServicesExpert implements java.io.Serializable {

    private final ArrayList<TrainingServicesExpert> servicesExperts;
    public VProfessor vProfessor;
    public static Master master = new Master(10, 600000, "829124", "Ai", "Samii", "Mohammad", "748", "2292876110", "Male", new Date(1992, 3, 5));

    public Master(int workingYears, int baseSallary, String employeeCode, String firstName, String lastName, String fatherName, String idNum, String birthcertNum, String gender, Date birthday) {
        super(workingYears,baseSallary,employeeCode,firstName,lastName,fatherName,idNum,birthcertNum,gender,birthday);
        this.servicesExperts = new ArrayList<>();
    }

    public static void loadMaster() {
        File f = new File("master.ser");
        if (f.exists() && !f.isDirectory()) {
            try {
                FileInputStream fileIn = new FileInputStream("master.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                master = (Master) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                System.out.println(i);
            } catch (ClassNotFoundException c) {
                System.out.println(c);
            }
        }else{
            master = new Master(10, 600000, "829124", "Ai", "Samii", 
                    "Mohammad", "748", "2292876110", "Male",
                    new Date(1992, 3, 5));
        }
    }

    public void employProfessor(Professor p) {
        professors.add(p);
    }

    public void employServiceExpert(TrainingServicesExpert t) {
        servicesExperts.add(t);
    }

    public ArrayList<TrainingServicesExpert> getServiceExperts() {
        return (ArrayList<TrainingServicesExpert>) servicesExperts.clone();
    }
    public TrainingServicesExpert getServiceExpert(String id) {
        for (TrainingServicesExpert t : servicesExperts) {
            if (t.getEmployeeCode().compareTo(id) == 0) {
                return t;
            }
        }
        return null;
    }


    public int getIncome() {
        int sum = 0;
        for (Lesson l : lessons) {
            sum += l.expense();
        }
        return sum;
    }

    public int getOutcome() {
        int sum = 0;
        for (Lesson l : lessons) {
            sum += l.getCost();
        }
        for(TrainingServicesExpert t : servicesExperts){
            sum += t.sallary();
        }
        for(Professor p : professors){
            sum += p.sallary();
        }
        sum += Master.master.sallary();
        return sum;
    }
    public int totalPersonnelCost(){
        int sum=0;
        for(TrainingServicesExpert t : servicesExperts){
            sum += t.sallary();
        }
        for(Professor p : professors){
            sum += p.sallary();
        }
        sum += Master.master.sallary();
        return sum;
    }

    public int getProfit() {
        return getIncome() - getOutcome();
    }


    @Override
    public String toString() {
        return super.toString()
                + ", service years: " + getWorkingYears();
    }
    
}
