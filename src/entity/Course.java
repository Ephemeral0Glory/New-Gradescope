package entity;
import java.util.*;

import utilities.IDFactory;

/**
 *  A university course.
 *  <p>
 *  It contains the students in the course and their assignments with grades.
 *  Course-level actions can be taken from here, such as adding or removing students and assignments.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class Course {

    private long id;
    private String name;
    private String code;
    private User owner;
    private RealAssignment template;
    private ArrayList<Entry> entries;
    private ArrayList<Section> sections;
    /*
     *  The following are aggregates:
     */
    private ArrayList<ArrayList<RealAssignment>> assignments;
    private ArrayList<Student> students;
    private ArrayList<Gradeable> finalGrades;

    /**
     *  Constructor.
     *  <p>
     *  Creates a completely empty course, without students or assignments.
     *  @param name  The name of the course
     *  @param code  The university course code
     *  @param owner  The owner (teacher) of the course
     */
    public Course(String name, String code, User owner) {
    	this.id = IDFactory.generateCourseID();
        this.name = name;
        this.code = code;
        this.owner = owner;
        this.template = new RealAssignment(name+" template", 0f);
        this.entries = new ArrayList<Entry>();
        this.sections = new ArrayList<Section>();
        createAggregates();
    }

    /**
     *  Constructor.
     *  <p>
     *  Creates a new course with the template of assignments.
     *  @param name  The name of the course
     *  @param code  The university course code
     *  @param owner  The owner (teacher) of the course
     *  @param template
     */
    public Course(String name, String code, User owner, RealAssignment template) {
        this(name, code, owner);
        this.template = template;
        createAggregates();
    }
    
    /**
     *  Constructor.
     *  <p>
     *  Used when loading a course from the gradebook file. Does not load or
     *  create the owner or entries, and should not be used for editing courses.
     *  To create a new course, use
     *  {@link Course(String name, String code, User owner, RealAssignment template)} or
     *  {@link Course(String name, String code, User owner)}.
     *  @param id  The ID of the course
     *  @param name  The name of the course
     *  @param code  The university course code
     */
    public Course(long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    /**
     *  Constructor.
     *  <p>
     *  Used when loading a course from the course file. To create a new course, use
     *  {@link Course(String name, String code, User owner, RealAssignment template)} or
     *  {@link Course(String name, String code, User owner)}. To load a course from
     *  the gradebook file, use {@link Course(long id, String name, String code)}.
     *  @param id  The ID of the course
     *  @param name  The name of the course
     *  @param code  The university course code
     *  @param owner  The owner (teacher) of the course
     *  @param entries  The grades table for the course
     */
    public Course(long id, String name, String code, User owner, RealAssignment template,
    		ArrayList<Entry> entries, ArrayList<Section> sections) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.owner = owner;
        this.template = template;
        this.entries = entries;
        this.sections = sections;
        createAggregates();
    }
    
    private void createAggregates()
    {
    	// Create assignments
    	/*
    	 *  Assignments is a list of the lowest (non-NullAssignment) level of the tree
    	 *  described by the template. 
    	 */
    	assignments = createAssignmentAggregate();
    	assignments.add(new ArrayList<RealAssignment>());
    	ArrayList<ArrayList<RealAssignment>> all = new ArrayList<ArrayList<RealAssignment>>(template.getNumSubAssignments()+1);
    	for(int i = 0; i < template.getNumSuccessors(); i++)
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
    	students = new ArrayList<Student>(entries.size());
    	for(Entry e: entries)
    	{
    		students.add(e.getStudent());
    	}
    	
    	// Create final grades
    	finalGrades = new ArrayList<Gradeable>(entries.size());
    	for(Entry e: entries)
    	{
    		finalGrades.add(e.getFinalGrade());
    	}
    }
    
    private ArrayList<ArrayList<RealAssignment>> createAssignmentAggregate()
    {
    	// Create list of leaf node assignments from the assignment tree 
    	ArrayList<ArrayList<RealAssignment>> aggregate = new ArrayList<ArrayList<RealAssignment>>();
    	// Initialize lists
    	for(int i = 0; i < template.getNumSuccessors(); i++)
    	{
    		aggregate.add(new ArrayList<RealAssignment>());
    	}
    	
    	// Fill lists
    	for(Entry e: entries)
    	{
    		// Get all leaf assignments in this entry
    		ArrayList<RealAssignment> entryAssignments = getLeafAssignmentList(e.getFinalGrade());
    		
    		// Transfer the information to the aggregate list
    		for(int i = 0; i < template.getNumSuccessors(); i++)
    		{
    			aggregate.get(i).add(entryAssignments.get(i));
    		}
    	}
    	
    	return aggregate;
    }
    
    private ArrayList<RealAssignment> getLeafAssignmentList(RealAssignment ra)
    {
    	ArrayList<RealAssignment> leafNodes = new ArrayList<RealAssignment>();
    	
    	// Check for leaf condition
    	if(ra.getNumSubAssignments() == 0)
    	{
    		// Have a leaf, return
    		leafNodes.add(ra);
    		return leafNodes;
    	}
    	else  // Have subassignments
    	{
    		for(Gradeable g: ra.getSubAssignments())
    		{
    			// Can cast because checked for NullAssignment above
    			RealAssignment sa = (RealAssignment) g;
    			
    			// Get all leaf assignments in this sub-assignment
    			ArrayList<RealAssignment> saLeafList = getLeafAssignmentList(sa);
    			
    			// Transfer information
    			leafNodes.addAll(saLeafList);
    		}
    		return leafNodes;
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
    
    public RealAssignment getTemplate()
    {
    	return this.template;
    }
    
    public boolean hasTemplate()
    {
    	return (this.template != null);
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
    
    public ArrayList<RealAssignment> getAggregateAssignment(int index)
    {
    	return this.assignments.get(index);
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
    
    @Override
    public String toString()
    {
    	return code + " " + name;
    }

}