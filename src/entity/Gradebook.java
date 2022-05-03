package entity;
import java.util.*;

public class Gradebook {

    private long id; 
    private User owner;
    private ArrayList<Semester> semesters;

    public Gradebook(User owner) {
    	this.id = IDFactory.generateGradebookID();
        this.owner = owner;
    }

    public Gradebook(User owner, ArrayList<Semester> semesters) {
    	this.id = IDFactory.generateGradebookID();
        this.owner = owner;
        this.semesters = semesters;
    }
    
    // TODO Gradebook load from file constructor

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
    
}