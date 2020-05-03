package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import virtual.VProfessor;

public class Professor extends Personnel implements java.io.Serializable {

    
    private int wage;
    private final int rank;
    private final String trainingOrientation;
    private final ArrayList<Lesson> lessons;
    private boolean available;

    public Professor(String employeeCode, int wage, int rank, String trainingOrientation, String firstName, String lastName, String fatherName, String idNum, String birthcertNum, String gender, Date birthday) {
        
        super(employeeCode,firstName,lastName,fatherName,idNum,birthcertNum,gender,birthday);
        this.wage= wage;
        this.rank = rank;
        this.trainingOrientation = trainingOrientation;
        this.lessons = new ArrayList<>();
        this.available = true;
    }

    

    public int getWage() {
        return wage;
    }

    public void setWage(int sallary) {
        this.wage = wage;
    }

    public int getRank() {
        return rank;
    }

    public String getTrainingOrientation() {
        return trainingOrientation;
    }

    public ArrayList<Lesson> getLessons() {
        return (ArrayList<Lesson>) lessons.clone();
    }

    public void gradeStudent(Student student, Grade grade) {
        student.addGrade(grade);
        grade.getLessonCode().addGrade(grade);
    }

    public void startClass() {
        if (available == true) {
            available = false;
            Master.master.vProfessor = new VProfessor(this);
            Master.master.vProfessor.startClass();
            
            JOptionPane.showMessageDialog(null, "استاد مربوطه در کلاس است");
        }else{
            JOptionPane.showMessageDialog(null, "یک کلاس دیگر در حال برگزاری است");
        }
    }

    public void finishClass() {
        if (available == false) {
            available = true;
            Master.master.vProfessor.stop();
            Master.master.vProfessor = null;
            JOptionPane.showMessageDialog(null, "استاد مربوطه کلاس را ترک کرد");
        }else{
            JOptionPane.showMessageDialog(null, "کلاسی برای ترک وجود ندارد");
        }
    }

    public void designQuestion(String question) {
        if (available == false) {
            Master.master.vProfessor.addQuestion(question);
        }else{
            JOptionPane.showMessageDialog(null, "کلاسی وجود ندارد");
        }
    }

    public void applyGrades(Lesson lesson) {
        Student max = lesson.getStudents().get(0);
        double grade = -1.0;
        for (int i = 0; i < lesson.getGrades().size(); i++) {
            Grade g = lesson.getGrades().get(i);
            if (g.getGrade() > grade) {
                grade = g.getGrade();
                max = lesson.getStudents().get(i);
            }
        }
        max.discount();
    }

    @Override
    public String toString() {
        return super.toString()
                + ", orientation: " + getTrainingOrientation();
    }
   

    public Professor copy() {
        try {
            return (Professor) clone();
        } catch (CloneNotSupportedException ex) {
        }
        return null;
    }

    @Override
    public int sallary() {
        return (int) ((getRank()*0.05+1)* getWage() * 36 * lessons.size());
    }
}
