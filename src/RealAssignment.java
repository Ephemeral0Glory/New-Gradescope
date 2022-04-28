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
        // TODO Auto-generated method stub
        return 0;
    }

}