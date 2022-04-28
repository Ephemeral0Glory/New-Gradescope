import java.util.*;

public class Entry {

    private long id; // TODO Add autoincrement of ID 
    private Section section;
    private Student student;
    private ArrayList<RealAssignment> finalGrades;

    public Entry(Section section, Student student, ArrayList<RealAssignment> template) {
        this.section = section;
        this.student = student;
        this.finalGrades = template; // unsure if this is what it is supposed to be
    }

    public long getID() {
        return this.id;
    }

    public long setID(long newID) {
        this.id = newID;
        return this.id;
    }

    public Section getSection() {
        return this.section;
    }

    public Section setSection(Section newSection) {
        this.section = newSection;
        return this.section;
    }

    public Student getStudent() {
        return this.student;
    }

    public Student setStudent(Student newStudent) {
        this.student = newStudent;
        return this.student;
    }

    public ArrayList<RealAssignment> getFinalGrades() {
        return this.finalGrades;
    }

    public void addAssignment(RealAssignment assignment) {
        this.finalGrades.add(assignment);
    }

    public boolean removeAssignment(RealAssignment assignmentToRemove) {
        return this.finalGrades.remove(assignmentToRemove);
    }

}