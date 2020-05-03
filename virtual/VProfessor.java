package virtual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;
import model.Professor;
import model.Student;

public class VProfessor extends Thread implements Serializable {

    Professor professor;
    Queue<String> questions;
    String question;
    String answer;
    boolean request;

    public void addQuestion(String question) {
        questions.add(question);
    }

    public String seeQuestion() {
        return question;
    }

    public void answer(String answer) {
        this.answer = answer;
    }

    public VProfessor(Professor professor) {
        this.professor = professor;
        questions = new LinkedList<>();
        question = "";
        answer = "";
        request = true;
    }

    @Override
    public void run() {
        while (true) {
            if (answer.compareTo("") == 0 && request) {
                if (questions.size() > 0) {
                    question = questions.poll();
                    request = false;
                }
            } else if(answer.compareTo("") != 0 && !request){
                JOptionPane.showMessageDialog(null, answer);
                answer = "";
                question = "";
                request = true;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void startClass() {
        this.start();
    }
}
