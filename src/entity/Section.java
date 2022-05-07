package entity;
import java.util.*;

import utilities.IDFactory;

/**
 *  A section of a course.
 *  <p>
 *  Contains students.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class Section {

    private long id;
    private long courseID;
    private String name;
    private String code;
    private ArrayList<Student> students;

    /**
     * 
     *  Constructor.
     *  <p>
     *  Used to create a new Section. To load a section from a file, use
     *  {@link Section(long id, long courseID, String name, String code, ArrayList<Student> students)}.
     *  Creates a blank list of students.
     *  @param name  The name of the section
     *  @param courseID  The unique identifier for the course this section is a part of
     *  @param code  The code of this section
     */
    public Section(String name, long courseID, String code) {
    	this.id = IDFactory.generateSectionID();
        this.name = name;
        this.courseID = courseID;
        this.code = code;
        this.students = new ArrayList<Student>();
    }

    /**
     *  Constructor.
     *  <p>
     *  Used to create a new Section. To load a section from a file, use
     *  {@link Section(long id, long courseID, String name, String code, ArrayList<Student> students)}.
     *  @param name  The name of the section
     *  @param courseID  The unique identifier for the course this section is a part of
     *  @param code  The code of this section
     *  @param students  The students in this section
     */
    public Section(String name, long courseID, String code, ArrayList<Student> students) {
        this(name, courseID, code);
        this.students = students;
    }
    
    /**
     *  Constructor.
     *  <p>
     *  Used when loading a section from a file. To create a new section, use
     *  {@link Section(String name, long courseID, String code, ArrayList<Student> students)} or
     *  {@link Section(String name, long courseID, String code)}.
     *  @param id  The unique identifier for this section
     *  @param courseID  The unique identifier for the course this section is a part of
     *  @param name  The name of this section
     *  @param code  The code of this section
     *  @param students  The students in this section
     */
    public Section(long id, long courseID, String name, String code, ArrayList<Student> students)
    {
    	this.id = id;
    	this.courseID = courseID;
    	this.name = name;
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
    
    public ArrayList<Student> getStudents()
    {
    	return this.students;
    }

    /**
     *  Adds a student to this section.
     *  @param newStudent  The student to add
     */
    public void addStudent(Student newStudent) {
        this.students.add(newStudent);
    }

    /**
     *  Removes a student from this section.
     *  @param studentToRemove  The student to remove
     *  @return  True if the student was present in this section and thus removed, false otherwise.
     */
    public boolean removeStudent(Student studentToRemove) {
        return this.students.remove(studentToRemove);
    }
    
    @Override
    public String toString()
    {
    	return code + ": " + name;
    }

}