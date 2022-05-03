package entity;

import java.util.ArrayList;

public interface Gradeable {

    public float getWeightedGrade();
    public ArrayList<Grade> getFlattenedSubAssignmentTreeGrades();

}