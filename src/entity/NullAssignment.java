package entity;

import java.util.ArrayList;

public class NullAssignment implements Gradeable {

    private long id; // TODO make this autoincrement
    private String name;
    private Grade grade;

    public NullAssignment(String name) {
    	this.id = IDFactory.generateAssignmentID();
        this.name = name;
    }

    public NullAssignment(String name, Grade grade) {
        this(name);
        this.grade = grade;
    }
    
    // TODO NullAssignment constructor for load from file

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