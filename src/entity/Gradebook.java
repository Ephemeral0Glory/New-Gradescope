package entity;
import java.util.*;

import utilities.IDFactory;

/**
 *  The top-level grades organizer.
 *  <p>
 *  Contains all courses associated with a given user. Organizes courses
 *  by semester.
 *  
 *  @author David Sullo
 *  @author Alex Titus
 */
public class Gradebook {

    private long id; 
    private User owner;
    private ArrayList<Semester> semesters;

    public Gradebook(User owner) {
    	this.id = IDFactory.generateGradebookID();
        this.owner = owner;
        semesters = new ArrayList<Semester>();
    }

    public Gradebook(User owner, ArrayList<Semester> semesters) {
    	this(owner);
        this.semesters = semesters;
    }

    /**
     *  Constructor.
     *  <p>
     *  Used to load a gradebook from file. To create a new Gradebook, use
     *  {@link Gradebook(User owner, ArrayList&ltSemester&gt semesters)} or
     *  {@link Gradebook(User owner)}.
     *  @param id  Unique id associated with this object instance
     *  @param owner  The owner of this gradebook
     *  @param semesters  List of semesters this gradebook has courses for.
     *  	   Should have courses in each of these semesters.
     */
    public Gradebook(long id, User owner, ArrayList<Semester> semesters)
	{
		this.id = id;
		this.owner = owner;
		this.semesters = semesters;
	}

	public long getID() {
        return this.id;
    }

    public long setID(long newID) {
        this.id = newID;
        return this.id;
    }

    public User getOwner() {
        return this.owner;
    }

    public User setOwner(User newOwner) {
        this.owner = newOwner;
        return this.owner;
    }

    public ArrayList<Semester> getSemesters() {
        return this.semesters;
    }

    public void addSemester(Semester newSemester) {
        this.semesters.add(newSemester); 
    }

    public boolean removeSemester(Semester semesterToRemove) {
        return this.semesters.remove(semesterToRemove);
    }
    
    public boolean containsSemester(Semester testSemester)
    {
    	return this.semesters.contains(testSemester);
    }
    
}