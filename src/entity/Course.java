package entity;
import java.util.*;

/**
 *  A university course.
 *  <p>
 *  It contains the students in the course and their assignments with grades.
 *  Course-level actions can be taken from here, such as adding or removing students and assignments.
 *  @author Alex Titus
 *  @author David Sullo
 */
public class Course {

    private long id;
    private String name;
    private String code;
    private User owner;
    private ArrayList<Entry> entries;
    private ArrayList<RealAssignment> template;
    private ArrayList<Section> sections;
    private ArrayList<ArrayList<RealAssignment>> assignments;
    private ArrayList<Student> students;
    private ArrayList<Gradeable> finalGrades;

    /**
     *  Constructor.
     *  <p>
     *  Creates a completely empty course, without students or assignments.
     *  @param name
     *  @param code
     *  @param owner
     */
    public Course(String name, String code, User owner) {
    	this.id = IDFactory.generateCourseID();
        this.name = name;
        this.code = code;
        this.owner = owner;
        this.entries = new ArrayList<Entry>();
        createAggregates();
    }

    public Course(String name, String code, User owner, ArrayList<RealAssignment> template) {
        this(name, code, owner);
        this.template = template;
        createAggregates();
    }

    /**
     *  Constructor.
     *  <p>
     *  Used when loading a course from a file.
     *  @param name  The name of the course
     *  @param code  The university course code
     *  @param owner  The owner (teacher) of the course
     *  @param entries  The grades table for the course
     *  @param id  The ID of the course
     */
    public Course(String name, String code, User owner, ArrayList<Entry> entries, long id) {
        this.name = name;
        this.code = code;
        this.owner = owner;
        this.id = id;
        this.entries = entries;
        createAggregates();
    }
    
    private void createAggregates()
    {
    	// Create sections
    	ArrayList<Section> sl = new ArrayList<Section>();
    	for(Entry e: entries)
    	{
    		Section s = e.getSection();
    		if(!sl.contains(s))
    		{
    			sl.add(s);
    		}
    	}
    	sections = sl;
    	
    	// Create assignments
    	// TODO Course.createAggregates need a better way to handle arbitrary sub-assignment depth, maybe keeps track of only the lowest (non-NullAssignment) level of assignments?
    	ArrayList<ArrayList<RealAssignment>> all = new ArrayList<ArrayList<RealAssignment>>(template.size());
    	for(int i = 0; i < template.size(); i++)
    	{
    		ArrayList<RealAssignment> al = new ArrayList<RealAssignment>(entries.size());
    		for(Entry e: entries)
    		{
    			/* Can cast because we know that the first sub-assignment level of each
        		 * Entry's finalGrade member are the assignments.
        		 * That is, Entry.finalGrade will never have NullAssignment as its subAssignment */
    			RealAssignment a = (RealAssignment) e.getAssignment(i);
    			al.add(a);
    		}
    		all.add(al);
    	}
    	
    	
    	// Create students
    	ArrayList<Student> stl = new ArrayList<Student>(entries.size());
    	for(Entry e: entries)
    	{
    		stl.add(e.getStudent());
    	}
    	
    	// Create final grades
    	ArrayList<Gradeable> gl = new ArrayList<Gradeable>(entries.size());
    	for(Entry e: entries)
    	{
    		gl.add(e.getFinalGrade());
    	}
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

    public ArrayList<ArrayList<RealAssignment>> getAssignments() {
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
    
    public User getOwner() {
    	return this.owner;
    }

    /**
     *  Adds the given assignment to the table.
     *  <p>
     *  First adds the assignment to each entry in the database. Then it creates
     *  a list of references to those assignments and adds it to Course.assignments.
     *  @param assignment
     */
    public void addAssignment(RealAssignment assignment) {
        // Add assignment to each entry
        for(Entry e: entries)
        {
        	e.addAssignment(new RealAssignment(assignment.getName(), assignment.getWeight()));
        }
        
    	// Create list of assignments from each row in entries
    	ArrayList<RealAssignment> list = new ArrayList<RealAssignment>(entries.size());
    	for(Entry e: entries)
    	{
    		/* Can cast because we know that the first sub-assignment level of each
    		 * Entry's finalGrade member are the assignments.
    		 * That is, Entry.finalGrade will never have NullAssignment as its subAssignment */
    		RealAssignment ra = (RealAssignment) e.getAssignment(assignments.size());
    		list.add(ra);
    	}
    	
    	// Add list to end of assignments list
        this.assignments.add(list);
        
    }

    /**
     *  Removes the given assignment from the table.
     *  <p>
     *  First it removes the assignment from each entry in the table. Then
     *  it removes the list in Course.assignments that represented this assignment
     *  in the table columns.
     *  @param assignmentToRemove  The assignment to remove
     *  @return  True if the assignment was removed. False otherwise.
     */
    public boolean removeAssignment(RealAssignment assignmentToRemove) {
        // Remove assignment from each entry
        for(Entry e: entries)
        {
        	e.removeAssignment(assignmentToRemove);
        }
        
    	// Find column that has this assignment
    	ArrayList<RealAssignment> columnToRemove = null;
    	for(ArrayList<RealAssignment> column: assignments)
    	{
    		RealAssignment ra = column.get(0);
    		if(ra.equals(assignmentToRemove))
    		{
    			columnToRemove = column;
    		}
    	}
    	
    	// Remove this column
        return this.assignments.remove(columnToRemove);
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

    /**
     *  Updates the final grades of all students in the course.
     */
    public void updateGrades() {
        for(Entry e: entries)
        {
        	e.updateGrade();
        }
    }

}