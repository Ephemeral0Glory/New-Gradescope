package entity;
import java.util.*;

public class RealAssignment implements Gradeable {

    private long id;
    private String name;
    private Student student;
    private float weight;
    private Grade grade;
    private ArrayList<Gradeable> subAssignments;

    public RealAssignment(String name, float weight) {
    	this.id = IDFactory.generateAssignmentID();
        this.name = name;
        this.weight = weight;
        grade = new Grade(0f);
    }

    public RealAssignment(String name, float weight, Grade grade, Student student) {
        this(name, weight);
        this.grade = grade;
        this.student = student;
    }
    
    public RealAssignment(String name, float weight, ArrayList<RealAssignment> template)
    {
    	this(name, weight);
    	subAssignments = new ArrayList<Gradeable>();
    	
    	// Use template to create sub-assignments
    	for(RealAssignment a: template)
    	{
    		subAssignments.add(a);
    	}
    }
    
    // TODO RealAssignment constructor for load from file

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
    
    public void setWeight(int newWeight)
    {
    	this.weight = newWeight;
    }
    
    public String getStudentName()
    {
    	return this.student.getFName() + " " + this.student.getLName();
    }
    
    public Gradeable getSubAssignment(int index) {
    	return subAssignments.get(index);
    }
    
    /**
     *  @return A list of Grade representing the leaf nodes grades of the subassignment tree
     */
    public ArrayList<Grade> getFlattenedSubAssignmentTreeGrades()
    {
    	ArrayList<Grade> list = new ArrayList<Grade>();
    	for(Gradeable g: subAssignments)
    	{
    		list.addAll(g.getFlattenedSubAssignmentTreeGrades());
    	}
    	
    	return list;
    }

    public void addSubAssignment(Gradeable subAssignment) {
        this.subAssignments.add(subAssignment);
    }

    public boolean removeSubAssignment(Gradeable subAssignment) {
        return this.subAssignments.remove(subAssignment);
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