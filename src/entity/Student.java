package entity;

public class Student {

	private long id;
    private String fname;
    private String lname;
    private String buID;
    private StudentStatus enrollmentStatus;

    public Student(String fname, String lname, String buID, StudentStatus enrollmentStatus) {
    	this.id = IDFactory.generateStudentID();
        this.fname = fname;
        this.lname = lname;
        this.buID = buID;
        this.enrollmentStatus = enrollmentStatus;
    }
    
    // TODO Student constructor for load from file
    
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
