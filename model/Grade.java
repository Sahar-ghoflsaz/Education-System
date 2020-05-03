package model;

public class Grade implements java.io.Serializable{

    private Lesson lesson;
    private double grade;

    public Grade(Lesson lesson, double grade) {
        this.lesson = lesson;
        this.grade = grade;
    }

    public Lesson getLessonCode() {
        return lesson;
    }

    public double getGrade() {
        return grade;
    }

    public Grade copy() {
        try {
            return (Grade) clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
        
        
    }

}
