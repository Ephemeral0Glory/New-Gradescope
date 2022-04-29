import java.util.*;

public class Course {

    private long id; // TODO Add autoincrement of ID 
    private String name;
    private String code;
    private ArrayList<Section> sections;
    private ArrayList<ArrayList<RealAssignment>> assignments;
    private ArrayList<Student> students;
    private ArrayList<Entry> entries;
    private ArrayList<Gradeable> finalGrades;

    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Course(String name, String code, ArrayList<RealAssignment> template) {
        this(name, code);
        this.assignments = createBlankAssignmentsFromTemplate(template);
    }

    public Course(String name, String code, ArrayList<RealAssignment> template, ArrayList<Entry> entries) {
        this(name, code, template);
        this.entries = entries;
    }
    
    private ArrayList<ArrayList<RealAssignment>> createBlankAssignmentsFromTemplate(ArrayList<RealAssignment> template) {
    	assignments = new ArrayList<ArrayList<RealAssignment>>(template.size());
    	
    	for(RealAssignment ra: template)
    	{
    		ArrayList<RealAssignment> newAssignList = new ArrayList<RealAssignment>();
    		newAssignList.add(new RealAssignment(ra.getName(), ra.getWeight()));
    		assignments.add(newAssignList);
    	}
    	
    	return assignments;
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

    public void addAssignment(RealAssignment assignment) {
    	// Create list of empty assignments for each row in entries
    	ArrayList<RealAssignment> list = new ArrayList<RealAssignment>(entries.size());
    	for(int i = 0; i < entries.size(); i++)
    	{
    		RealAssignment ra = new RealAssignment(assignment.getName(), assignment.getWeight());
    		list.add(ra);
    	}
    	
    	// Add list to end of assignments list
        this.assignments.add(list);
        
        // Add assignment to each entry
        for(Entry e: entries)
        {
        	e.addAssignment(new RealAssignment(assignment.getName(), assignment.getWeight()));
        }
    }

    public boolean removeAssignment(RealAssignment assignmentToRemove) {
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
    	
        // Remove assignment from each entry
        for(Entry e: entries)
        {
        	e.removeAssignment(assignmentToRemove);
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

    public void updateGrades() {
        // TODO Add actual implementation
        System.out.println();
    }

}