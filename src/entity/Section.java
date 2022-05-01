package entity;
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

    public long getID() {
        return this.id;
    }

    public long setID(long newID) {
        this.id = newID;
        return this.id;
    }

    public long getCourseID() {
        return this.courseID;
    }

    public long setCourseID(long newCourseID) {
        this.courseID = newCourseID;
        return this.courseID;
    }

    public String getName() {
        return this.name;
    }

    public String setName(String newName) {
        this.name = newName;
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String setCode(String newCode) {
        this.code = newCode;
        return this.code;
    }

    public void addStudent(Student newStudent) {
        this.students.add(newStudent);
    }

    public boolean removeStudent(Student studentToRemove) {
        return this.students.remove(studentToRemove);
    }

}