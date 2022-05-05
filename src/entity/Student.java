package entity;

import utilities.IDFactory;

/**
 *  A student in the course.
 *  <p>
 *  Has a name, ID, and enrollment status.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class Student {

	private long id;
    private String fname;
    private String lname;
    private String buID;
    private StudentStatus enrollmentStatus;

    /**
     *  Constructor.
     *  <p>
     *  Used to create a new Student. To create a Student from a file, use
     *  {@link Student(long id, String fname, String lname, String buID, StudentStatus enrollmentStatus)}.
     *  @param fname
     *  @param lname
     *  @param buID
     *  @param enrollmentStatus
     */
    public Student(String fname, String lname, String buID, StudentStatus enrollmentStatus) {
    	this.id = IDFactory.generateStudentID();
        this.fname = fname;
        this.lname = lname;
        this.buID = buID;
        this.enrollmentStatus = enrollmentStatus;
    }
    
    /**
     *  Constructor.
     *  <p>
     *  Used when loading the Student from a file. To create a new Student, use
     *  {@link Student(String fname, String lname, String buID, StudentStatus enrollmentStatus)}.
     *  @param id
     *  @param fname
     *  @param lname
     *  @param buID
     *  @param enrollmentStatus
     */
    public Student(long id, String fname, String lname, String buID, StudentStatus enrollmentStatus)
    {
    	this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.buID = buID;
        this.enrollmentStatus = enrollmentStatus;
    }
    
    public long getID()
    {
    	return this.id;
    }

    public String getFName() {
        return this.fname;
    }

    public String setFName(String newFName) {
        this.fname = newFName;
        return this.fname;
    }

    public String getLName() {
        return this.lname;
    }
    
    public String setLName(String newLName) {
        this.lname = newLName;
        return this.lname;
    }

    public String getBUID() {
        return this.buID;
    }

    public String setNewBUID(String newBUID) {
        this.buID = newBUID;
        return this.buID;
    }

    public StudentStatus getEnrollmentStatus() {
        return this.enrollmentStatus;
    }

    public StudentStatus setEnrollmentStatus(String newStudentStatusStr) {
        StudentStatus newStudentStatus = StudentStatus.getStudentStatus(newStudentStatusStr);
        this.enrollmentStatus = newStudentStatus;
        return this.enrollmentStatus;
    }

}
