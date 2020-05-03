package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import virtual.VStudent;

public class Student extends Person implements java.io.Serializable {

    public enum status{
        available,
        unavailable,
        Graduated
    }
    private final String studentNum;
    private final Date enterance;
    private final String field;
    private final ArrayList<Lesson> lessons;
    private final ArrayList<Grade> grades;
    private double average;
    private status educationalState;
    private Date graduateDate;
    private int owedAmount;
    private boolean available;
    private boolean blocked;
    private boolean discount;
    public VStudent vStudent;

    public Student(String studentNum, Date enterance, String field ,String firstName, String lastName, String fatherName, String idNum, String birthcertNum, String gender, Date birthday) {
        super(firstName, lastName, fatherName, idNum, birthcertNum, gender, birthday);
        this.studentNum = studentNum;
        this.enterance = enterance;
        this.field = field;
        this.lessons = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.average = 0.0;
        this.educationalState =status.available;
        this.owedAmount = 0;
        this.available = true;
        this.blocked = false;
        this.discount = false;
        this.graduateDate=null;

    }

    public void discount() {
        discount = true;
    }

    public void block() {
        this.blocked = true;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void registerInLesson(Lesson lesson) {
        if (lesson.getCapacity() > 0 && lesson.getRegisterationDeadline().before(new Date())) {
            lesson.setCapacity(lesson.getCapacity() - 1);
            lessons.add(lesson);
            lesson.addStudent(this);
        }

    }

    @Override
    public String toString() {
        return super.toString()
                + ", average: " + Double.toString(getAverage()) + " status: " + getStatus();
    }

    public void payment(int amount) {
        this.owedAmount -= (int) ((double) amount * (discount ? 1.1 : 1.));
        discount = false;
    }

    public void participateInClass() {
        if (Master.master.vProfessor != null && available == true) {
            this.available = false;
            vStudent = new VStudent(this);
            vStudent.start();
            JOptionPane.showMessageDialog(null, "دانشجوی مربوطه در کلاس است");
        }else{
            JOptionPane.showMessageDialog(null, "یک کلاس دیگر در حال برگزاری است یا کلاسی وجود ندارد");
        }
    }

    public  void leaveTheClass() {
        if(available == false){
            this.available = true;
            vStudent.stop();
            vStudent = null;
            JOptionPane.showMessageDialog(null, "دانشجوی مربوطه کلاس را ترک کرد");
        }else{
            JOptionPane.showMessageDialog(null, "کلاسی برای ترک وجود ندارد");
        }
    }

    public String getStudentNum() {
        return studentNum;
    }

    public Date getEnterance() {
        return (Date) enterance.clone();
    }

    public String getField() {
        return field;
    }

    public ArrayList<Lesson> getLessons() {
        return (ArrayList<Lesson>) lessons.clone();
    }

    public ArrayList<Grade> getGrades() {
        return (ArrayList<Grade>) grades.clone();
    }

    public double getAverage() {
        return average;
    }


    public Date getGraduateDate() {
        return (Date) graduateDate.clone();
    }

    public int getOwedAmount() {
        return owedAmount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setEducationalState(status educationalState) {
        this.educationalState = educationalState;
        if(educationalState==status.unavailable){
            this.block();
        }
    }

    public void answerQuestion(String question) {
    }
    public status getEducationalState(){
        return educationalState;
    }
    public String getStatus(){
        if(getEducationalState()==status.available)
            return "فعال";
        if(getEducationalState()==status.unavailable)
            return "غیر فعال";
        else
            return "فارغ التحصیل";
    }
    public void setGraduateDate( Date graduateDate){
        this.graduateDate=graduateDate;
    }

    public void addGrade(Grade grade) {
        Grade g = grade.copy();
        grades.add(g);
        double sum = 0;
        for (Grade t : grades) {
            sum += t.getGrade();
        }
        average = sum / grades.size();
    }
}
