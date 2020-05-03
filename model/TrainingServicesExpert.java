package model;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Student.status;

public class TrainingServicesExpert extends Personnel implements java.io.Serializable{
    private final int workingYears;
    private int baseSallary;
    protected final ArrayList<Professor> professors;
    protected final ArrayList<Student> students;
    protected final ArrayList<Lesson> lessons;
    

    public TrainingServicesExpert(int  workingYears, int baseSallary, String employeeCode, String firstName, String lastName, String fatherName, String idNum, String birthcertNum, String gender, Date birthday) {
        super(employeeCode,firstName, lastName, fatherName, idNum, birthcertNum, gender, birthday);
        this. workingYears =  workingYears;
        this.baseSallary = baseSallary;
        this.professors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public int getWorkingYears() {
        return workingYears;
    }

    public void registerStudent(Student student){
        this.students.add(student);
    }
    
    public void addLesson(Lesson c){
        lessons.add(c);
        c.up();
    }
    
    public int geBaseSallary() {
        return baseSallary;
    }

    public void setBaseSallary(int baseSallary) {
        this.baseSallary =baseSallary;
    }

    
    public ArrayList<Student> getStudents(){
        return (ArrayList<Student>) students.clone();
    }
    
    public ArrayList<Professor> getProfessors(){
        return (ArrayList<Professor>) professors.clone();
    }
    
    public ArrayList<Lesson> getLessons(){
        return (ArrayList<Lesson>) lessons.clone();
    }
    
    public Professor getProfessor(String id){
        for (Professor p : professors) {
            if (p.getEmployeeCode().compareTo(id) == 0) {
                return p;
            }
        }
        return null;
    }
    
    public Student getStudent(String id){
        for (Student s : students) {
            if (s.getStudentNum().compareTo(id) == 0) {
                return s;
            }
        }
        return null;
    }
    
    public Lesson getLesson(String code){
            for (Lesson l : lessons) {
            if (l.getCode().compareTo(code) == 0) {
                return l;
            }
        }
        return null;
    }
    
    public void changeStudentState(String id,String newState){
        if(newState.compareTo("غیرفعال")==0)
            getStudent(id).setEducationalState(status.unavailable);
        if(newState.compareTo("فعال")==0)
            getStudent(id).setEducationalState(status.available);
        if(newState.compareTo("فارغ")==0){
            getStudent(id).setEducationalState(status.Graduated);
            getStudent(id).setGraduateDate(new Date((String) JOptionPane.showInputDialog("تاریخ فارغ التحصیلی را وارد کنید","")));
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                ", service years: " + getWorkingYears();
    }
    
    @Override
    public int sallary() {
        return (int) (geBaseSallary()*(1+0.05*getWorkingYears()));
    }

}
