package mocking;

public class Student {
    StudentService ss;
    Student(StudentService studentService){
        this.ss = studentService;
    }
    int getAverageMarks(){
        return ss.getTotalMark()/ ss.getTotalStudent();
    }
}
