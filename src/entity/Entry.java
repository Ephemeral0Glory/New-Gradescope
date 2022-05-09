package entity;
import utilities.IDFactory;

/**
 *  A entry in the course's grades database table.
 *  <p>
 *  An entry contains the {@link Student}, the {@link Section} the student belongs to, and the assignments
 *  with their grades. The final grade is represented as a {@link RealAssignment}, and the
 *  assignments in the course are its sub-assignments. 
 *  @author David Sullo
 *  @author Alex Titus
 */
public class Entry {

    private long id;
    private Section section;
    private Student student;
    private RealAssignment finalGrade;

    /**
     *  Constructor.
     *  <p>
     *  Create an entry with the given section, student, and assignments from template.
     *  Used to create a new entry. To load an entry from a file, use
     *  {@link Entry(long id, Section section, Student student, RealAssignment finalGrade)}.
     *  @param section  The section the student belongs to
     *  @param student  The student to whom these grades belong
     *  @param template  The assignments for the course
     */
    public Entry(Section section, Student student, RealAssignment template) {
    	this.id = IDFactory.generateEntryID();
        this.section = section;
        this.student = student;
        this.finalGrade = new RealAssignment("Final Grade", 1.0f, student, template);
    }
    
    /**
     *  Constructor.
     *  <p>
     *  Used when loading an entry from a file. To create a new entry, use
     *  {@link Entry(Section section, Student student, ArrayList<RealAssignment> template)}.
     *  @param id
     *  @param section
     */
    public Entry(long id, Section section, Student student, RealAssignment finalGrade)
    {
    	this.id = id;
    	this.section = section;
    	this.student = student;
    	this.finalGrade = finalGrade;
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

    public RealAssignment getFinalGrade() {
        return this.finalGrade;
    }
    
    public Gradeable getAssignment(int index) {
    	return finalGrade.getSubAssignment(index);
    }
    
    public RealAssignment getAssignment(RealAssignment ra)
    {
    	if(ra.equals(finalGrade))
    	{
    		return finalGrade;
    	}
    	else
    	{
    		return finalGrade.getSubAssignment(ra);
    	}
    }

    public void addAssignment(RealAssignment assignment) {
        this.finalGrade.addSubAssignment(assignment);
    }

    public void addAssignment(RealAssignment assignment, RealAssignment toAssignment) {
        this.finalGrade.addSubAssignment(assignment, toAssignment);
    }

    public boolean removeAssignment(RealAssignment assignmentToRemove) {
        return this.finalGrade.removeSubAssignment(assignmentToRemove);
    }
    
    /**
     *  Updates the final grade and the grade of all sub-assignments.
     */
    public void updateGrade()
    {
    	this.finalGrade.getWeightedGrade();
    }
    
    @Override
    public String toString()
    {
    	return "Section " + section.getCode() + ":   " + student.getLName() + ", " + student.getFName() + "   ID: " + student.getBUID(); 
    }

}