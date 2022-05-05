package entity;

import java.util.ArrayList;

import utilities.IDFactory;

public class NullAssignment implements Gradeable {

    private long id;
    private String name;
    private Grade grade;

    /**
     *  Constructor.
     *  <p>
     *  Used to create a new NullAssignment. To load one from a file, use
     *  {@link NullAssignment(long id, String name, Grade grade)}.
     *  @param name  The name of this assignment
     */
    public NullAssignment(String name) {
    	this.id = IDFactory.generateAssignmentID();
        this.name = name;
    }

    /**
     *  Constructor.
     *  <p>
     *  Used to create a new NullAssignment. To load one from a file, use
     *  {@link NullAssignment(long id, String name, Grade grade)}.
     *  @param name  The name of this assignment
     *  @param grade  The grade associated with this assignment
     */
    public NullAssignment(String name, Grade grade) {
        this(name);
        this.grade = grade;
    }
    
    /**
     *  Constructor.
     *  <p>
     *  Used to load NullAssignment from file. To create a new one, use
     *  {@link NullAssignment(String name, Grade grade)} or
     *  {@link NullAssignment(String name)}.
     *  @param id  The unique identifier for this object instance
     *  @param name  The name of this assignment
     *  @param grade  The grade associated with this assignment
     */
    public NullAssignment(long id, String name, Grade grade)
    {
    	
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

    public Grade getGrade() {
        return this.grade;
    }

    public Grade setGrade(Grade newGrade) {
        this.grade = newGrade;
        return this.grade;
    }

    @Override
    public float getWeightedGrade() {
        return this.grade.getScore();
    }
    
    /**
     *  @return  A list of the single Grade member of this assignment
     */
    @Override
    public ArrayList<Grade> getFlattenedSubAssignmentTreeGrades()
    {
    	ArrayList<Grade> list = new ArrayList<Grade>(1);
    	list.add(grade);
    	return list;
    }
    
    @Override
    public int getNumSuccessors()
    {
    	return 0;
    }
    
}