package virtual;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Master;
import model.Student;
import view.StudentForm;

public class VStudent extends Thread  implements Serializable{

    ArrayList<Student> students;
    String question;
    String old;
    Student student;

    public VStudent(Student student) {
        this.student = student;
        old = "";
        question = "";
    }

    public void answer(String answer) {
        Master.master.vProfessor.answer(answer); 
    }

    @Override
    public void run() {
        while (Master.master.vProfessor != null) {
            old = question;
            question = Master.master.vProfessor.seeQuestion();
            if(old.compareTo(question) != 0){
                StudentForm.stf.showQuestion(question);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
            }
        }
        JOptionPane.showMessageDialog(null, "کلاس مجازی پایان یافته است");
        student.leaveTheClass();
    }

    public void startClass() {
        this.start();
    }
}
