package entity;

import java.util.ArrayList;

public interface Gradeable {

    public float getWeightedGrade();
    public ArrayList<Grade> getFlattenedSubAssignmentTreeGrades();
    public int getNumLeaves();
    public Grade getGrade();

}