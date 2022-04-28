import java.util.*;

public class Section {

    private long id; // TODO Make this auto-increment
    private long courseID;
    private String name;
    private String code;
    private ArrayList<Student> students;

    public Section(String name, long courseID, String code) {
        this.name = name;
        this.courseID = courseID;
        this.code = code;
    }

    public Section(String name, long courseID, String code, ArrayList<Student> students) {
        this.name = name;
        this.courseID = courseID;
        this.code = code;
        this.students = students;
    }

    public void addStudent(Student newStudent) {
        this.students.add(newStudent);
    }

    public boolean removeStudent(Student studentToRemove) {
        return this.students.remove(studentToRemove);
    }


}
