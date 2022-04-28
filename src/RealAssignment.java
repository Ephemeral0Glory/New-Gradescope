import java.util.*;

public class RealAssignment implements Gradeable {

    private long id;
    private String name;
    private float weight;
    private Grade grade;
    private ArrayList<Gradeable> subAssignments;

    public RealAssignment(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public RealAssignment(String name, float weight, Grade grade) {
        this.name = name;
        this.weight = weight;
        this.grade = grade;
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

    public float getWeight() {
        return this.weight;
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

    @Override
    public float getWeightedGrade() {
        float totalGrade = 0;
        for (Gradeable subAssignment: this.subAssignments) {
            totalGrade += subAssignment.getWeightedGrade();
        }
        grade.setScore(totalGrade);
        return totalGrade * weight;
    }

}