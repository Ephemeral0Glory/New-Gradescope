import java.util.*;

public class Course {

    private long id; // TODO Add autoincrement of ID 
    private String name;
    private String code;
    private ArrayList<Section> sections;
    private ArrayList<RealAssignment> assignments;
    private ArrayList<Student> students;
    private ArrayList<Entry> entries;
    private ArrayList<Gradeable> finalGrades;

    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Course(String name, String code, ArrayList<RealAssignment> template) {
        this.name = name;
        this.code = code;
        this.assignments = template;
    }

    public Course(String name, String code, ArrayList<RealAssignment> template, ArrayList<Entry> entries) {
        this.name = name;
        this.code = code;
        this.assignments = template;
        this.entries = entries;
    }

    public long getID() {
        return this.id;
    }

    public long setID(long newID) {
        this.id = newID;
        return this.id;
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

    public ArrayList<Section> getSections() {
        return this.sections;
    }

    public ArrayList<RealAssignment> getAssignments() {
        return this.assignments;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public ArrayList<Entry> getEntries() {
        return this.entries;
    }

    public ArrayList<Gradeable> getFinalGrades() {
        return this.finalGrades;
    }

    public void addAssignment(RealAssignment assignment) {
        this.assignments.add(assignment);
    }

    public boolean removeAssignment(RealAssignment assignmentToRemove) {
        return this.assignments.remove(assignmentToRemove);
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public boolean removeSection(Section sectionToRemove) {
        return this.sections.remove(sectionToRemove);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public boolean removeStudent(Student studentToRemove) {
        return this.students.remove(studentToRemove);
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public boolean removeEntry(Entry entryToRemove) {
        return this.entries.remove(entryToRemove);
    }

    public void updateGrades() {
        // TODO Add actual implementation
        System.out.println();
    }

}