package entity;
import java.util.*;

import utilities.IDFactory;

/**
 *  An assignment with potential sub-assignments.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class RealAssignment implements Gradeable {

    private long id;
    private String name;
    private Student student;
    private float weight;
    private Grade grade;
    private int numSubAssignments;
    private ArrayList<Gradeable> subAssignments;

    /**
     *  Constructor.
     *  <p>
     *  Creates a RealAssignment with default student, no sub-assignments, and
     *  default grade.
     *  @param name  The name of this assignment
     *  @param weight  The weight of this assignment toward its parent's grade
     */
    public RealAssignment(String name, float weight) {
    	this.id = IDFactory.generateAssignmentID();
        this.name = name;
        this.student = new Student();
        this.weight = weight;
        grade = new Grade(0f);
        subAssignments = new ArrayList<Gradeable>();
        // Initialize with NullAssignment
        subAssignments.add(new NullAssignment(this.name, new Grade(this.grade.getScore())));
        numSubAssignments = 0;  // 0 because NullAssignment doesn't count
    }

    /**
     *  Constructor.
     *  <p>
     *  Creates a RealAssignment with given Student and Grade with no sub-assignments.
     *  @param name  The name of this assignment
     *  @param weight  The weight of this assignment toward its parent's grade
     *  @param grade  The grade received by the student for this assignment
     *  @param student  The student who owns this assignment
     */
    public RealAssignment(String name, float weight, Grade grade, Student student) {
        this(name, weight);
        this.grade = grade;
        // Redo add NullAssignment to get grade info
        subAssignments = new ArrayList<Gradeable>();
        subAssignments.add(new NullAssignment(this.name, new Grade(this.grade.getScore())));
        this.student = student;
    }
    
    /**
     *  Constructor.
     *  <p>
     *  Creates a RealAssignment with a given student, default grade, and
     *  sub-assignments based on a template assignment.
     *  @param name  The name of this assignment
     *  @param weight  The weight of this assignment toward its parent's grade
     *  @param student  The student who owns this assignment
     *  @param template  The template to base this assignment's sub-assignments on
     */
    public RealAssignment(String name, float weight, Student student, RealAssignment template)
    {
    	this(name, weight);
    	subAssignments = new ArrayList<Gradeable>();
    	this.student = student;
    	
    	// Use template to create sub-assignments
    	subAssignments = copyTemplate(template, student);
    	numSubAssignments = template.getNumSubAssignments();
    }
    
    /**
     *  Constructor.
     *  <p>
     *  Used when loading an assignment from file. To create a new RealAssignment, use
     *  {@link RealAssignment(String name, float weight, ArrayList<RealAssignment> template)},
     *  {@link RealAssignment(String name, float weight, Grade grade, Student student)}, or
     *  {@link RealAssignment(String name, float weight)}.
     *  @param id  The unique identifier for this object instance
     *  @param name  The name of the assignment
     *  @param student  The student whose grade this is
     *  @param weight  The weight of this assignment toward its parent's grade
     *  @param grade  The grade received for this assignment 
     *  @param numSubAssignments  The number of sub-assignments this assignment has
     *  @param subAssignments  The sub-assignments of this assignment
     */
    public RealAssignment(long id, String name, Student student, float weight,
    		Grade grade, int numSubAssignments, ArrayList<Gradeable> subAssignments)
    {
    	this.id = id;
    	this.name = name;
    	this.student = student;
    	this.weight = weight;
    	this.grade = grade;
    	this.numSubAssignments = numSubAssignments;
    	this.subAssignments = subAssignments;
    }

    public String toString() {
        return this.name + " with weight: " + Float.toString(this.weight);
    }
  
    private ArrayList<Gradeable> copyTemplate(RealAssignment template, Student owner)
    {
    	ArrayList<Gradeable> subAssignments = new ArrayList<Gradeable>();
    	
    	// Fill sub-assignments with new objects
    	for(Gradeable g: template.getSubAssignments())
    	{
    		subAssignments.add(copyGradeable(g, owner));
    	}
    	
    	return subAssignments;
    }
    
    private Gradeable copyGradeable(Gradeable g, Student owner)
    {
    	if(g instanceof RealAssignment)
    	{
    		RealAssignment ra = (RealAssignment) g;
    		RealAssignment copy = new RealAssignment(ra.getName(), ra.getWeight(), owner, ra);
    		return copy;
    	}
    	else  // Have a NullAssignment
    	{
    		NullAssignment na = (NullAssignment) g;
    		NullAssignment copy = new NullAssignment(na.getName(), new Grade());
    		return copy;
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

    @Override
    public Grade getGrade() {
        return this.grade;
    }

    public Grade setGrade(Grade newGrade) {
        this.grade = newGrade;
        return this.grade;
    }

    public float getWeight() {
        return this.weight;
    }
    
    public void setWeight(Float updatedWeight)
    {
    	this.weight = updatedWeight;
    }
    
    public Student getStudent()
    {
    	return this.student;
    }
    
    /**
     *  @return  The full name of this student, first name and last name appended
     */
    public String getStudentName()
    {
    	return this.student.getFName() + " " + this.student.getLName();
    }
    
    public ArrayList<Gradeable> getSubAssignments()
    {
    	return subAssignments;
    }
    
    public Gradeable getSubAssignment(int index) {
    	return subAssignments.get(index);
    }
    
    public int getNumSubAssignments()
    {
    	return numSubAssignments;
    }
    
    /**
     *  @return  The total number of leaf nodes in this assignment's sub-assignment tree
     */
    @Override
    public int getNumSuccessors()
    {
    	int successors = numSubAssignments;
    	for(Gradeable g: subAssignments)
    	{
    		successors += g.getNumSuccessors();
    	}
    	
    	return successors;
    }
    
    /**
     *  @return A list of Grade representing the leaf nodes grades of the subassignment tree
     */
    @Override
    public ArrayList<Grade> getFlattenedSubAssignmentTreeGrades()
    {
    	ArrayList<Grade> list = new ArrayList<Grade>();
    	
    	// Fill grades from sub-assignments
    	for(Gradeable g: subAssignments)
    	{
    		list.addAll(g.getFlattenedSubAssignmentTreeGrades());
    	}
    	
    	return list;
    }

    public void addSubAssignment(Gradeable subAssignment) {
    	// Check that we don't need to remove NullAssignment
    	if(numSubAssignments == 0)
    	{
    		// Remove the NullAssignment
    		subAssignments.remove(0);
    	}
        this.subAssignments.add(subAssignment);
        numSubAssignments += 1;
    }

    /**
     *  Removes the given assignment from the sub-assignment tree.
     *  <p>
     *  This may require going several layers deep into the tree. Removes the first
     *  incidence of the given sub-assignment tree.
     *  @param subAssignment  The sub-assignment to remove
     *  @return  True if the sub-assignment existed in the tree (and was thus removed). False otherwise
     */
    public boolean removeSubAssignment(Gradeable subAssignment) {
        boolean success = this.subAssignments.remove(subAssignment);
        
        if(success)
        {
        	numSubAssignments -= 1;
        	
        	// If we have removed the last sub-assignment 
        	if(numSubAssignments == 0)
        	{
        		// Add a NullAssignment
        		subAssignments.add(new NullAssignment(name, new Grade(grade.getScore(), grade.getComment())));
        	}
        }
        else  // Wasn't in this list of sub-assignments
        {
        	// If have sub-assignments to check
        	if(numSubAssignments != 0)
        	{
        		// Check the sub-assignment tree
        		for(Gradeable g: subAssignments)
        		{
        			// Can cast because checked for NullAssignment above
        			RealAssignment ra = (RealAssignment) g;
        			success = ra.removeSubAssignment(subAssignment);
        			if(success)
        			{
        				// Removed first incidence of subAssignment
        				break;
        			}
        		}
        	}
        	// Otherwise let it go through
        }
        
        return success;
    }

    public boolean verifySubweights() {
        float totalWeight = 0;
        for (Gradeable subAssignment: this.subAssignments) {
            totalWeight += ((RealAssignment) subAssignment).getWeight();
        }
        if (totalWeight > 1.00) {
            return false;
        }
        return true; 
    }

    /**
     *  Calculates the weighted contribution of this assignment to its parent assignment's grade.
     *  <p>
     *  First the sum of the weighted grades of the sub-assignments is calculated.
     *  Then the sum is set as this assignment's grade. Finally, the sum is multiplied
     *  by this assignment's weight. 
     *  @return  The weighted sum of this assignment's sub-assignment grades weighted by this assignment's weight
     */
    @Override
    public float getWeightedGrade() {
        float totalGrade = 0;
        for (Gradeable subAssignment: this.subAssignments) {
            totalGrade += subAssignment.getWeightedGrade();
        }
        grade.setScore(totalGrade);
        return totalGrade * weight;
    }
    
    /**
     *  Determines equality between RealAssignments.
     *  <p>
     *  A RealAssignment is equivalent if:
     *  <ol><li>it has the same name.</li>
     *  <li>it has the same weight.</li>
     *  @param o  The Object being compared
     *  @return  True if the Object being compared is a RealAssignment with the same weight and name
     */
    @Override
    public boolean equals(Object o) {
    	if(o instanceof RealAssignment)
    	{
    		RealAssignment other  = (RealAssignment) o;
    		if(this.name.equals(other.getName()) && this.weight == other.getWeight())
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }

}