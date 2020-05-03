package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lesson extends Institution implements java.io.Serializable{

    public enum state {
        available,
        unavailable
    }

    private String name;
    private int lessonNumber;
    private String position;
    private int time;
    private String code;
    private int capacity;
    private final int initalCapacity;
    private Date startDate;
    private Date endDate;
    private Date registerationDeadline;
    private Professor professor;
    private ArrayList<Student> students;
    private ArrayList<Grade> grades;
    private state lessonState;
    private static int numLesson;
    
    private int cost;
   

    public Lesson(String name,String code, String position, int time, Date registerationDeadline, Date startDate,Date endDate, int capacity, Professor professor, int cost) {
        this.name = name;
        this.code=code;
        this.position = position;
        this.time = time;
        this.registerationDeadline = registerationDeadline;
        this.grades = new ArrayList<>();
        this.startDate = startDate;
        this.endDate=endDate;
        this.capacity = capacity;
        this.initalCapacity = capacity;
        this.professor = professor;
        this.students = new ArrayList<>();
        this.cost = cost;
        this.lessonState=state.unavailable;
        this.numLesson=0;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void up(){
        numLesson++;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String name) {
        this.code = code;
    }
     
    public void addGrade(Grade g){
        grades.add(g);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public boolean couldStartClass(){
        return capacity < .1 * initalCapacity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public void addStudent(Student s){
        students.add(s);
    }
    
    public Date getRegisterationDeadline() {
        return registerationDeadline;
    }

    public void setRegisterationDeadline(Date registerationDeadline) {
        this.registerationDeadline = registerationDeadline;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date endDate) {
        this.startDate = endDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Professor getProfessor() {
        return professor.copy();
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public ArrayList<Student> getStudents() {
        return (ArrayList<Student>)students.clone();
    }
    
    public ArrayList<Grade> getGrades() {
        return (ArrayList<Grade>)grades.clone();
    }

    public int studentsCount() {
        return students.size();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public state getLessonState() {
        return lessonState;
    }
    
    public void setLessonState(state lessonState) {
        this.lessonState=lessonState;
    }
    public int tookHold(Date startDate,Date endDate){
        return endDate.getMinutes()- startDate.getMinutes();
    }
    public int expense(){
        return (Master.master.totalPersonnelCost()/numLesson+cost)/initalCapacity;
    }

    public Lesson copy() {
        try {
            return (Lesson) this.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return null;
    }
}
