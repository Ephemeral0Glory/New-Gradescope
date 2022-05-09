package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

import entity.*;
import boundary.*;

import javax.swing.*;

public class UpdateNameWeightController implements ActionListener {
    public static enum UpdateNWProblem {NO_ERROR, EMPTY_NAME, BAD_FLOAT};
    private IGraderFrame rootView;
    private IGraderFrame parentView;
    private User user;
    private Course course;
    private RealAssignment parent;
    private EditAssignmentView editAssignmentView;

    public UpdateNameWeightController(IGraderFrame rootView, IGraderFrame parentView, User user, Course course, RealAssignment parent, EditAssignmentView editAssignmentView) {
        this.rootView = rootView;
        this.parentView = parentView;
        this.user = user;
        this.course = course;
        this.parent = parent;
        this.editAssignmentView = editAssignmentView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateNWProblem error = validateInformation();


        if (error == UpdateNWProblem.NO_ERROR) {
            String updatedName = editAssignmentView.getUpdatedName();
    //        Float updatedWeight = 0f;
            Float updatedWeight = editAssignmentView.getUpdatedWeight() / 100;
            System.out.println("proceed to update name and weight");

            System.out.println("1");
            // first update entity information
            updateAllEntityInformation(updatedName, updatedWeight);

            System.out.println("2");
            // after updating entity update real assignment information
            this.parent.setName(updatedName);
            this.parent.setWeight(updatedWeight);

            System.out.println("3");
            // update entity subAssignment information
            updateAllEntitySubAssignmentInformation();

            System.out.println("4");
            // update subassignment information
            updateSubAssignments();

            System.out.println("5");
            // Close window
            returnToParentView();
        }
        else {
            editAssignmentView.showError(error);
            rootView.update();
            rootView.display();
        }
    }
    
    private UpdateNWProblem validateInformation() {

        String updatedName = editAssignmentView.getUpdatedName();
        Float updatedWeight = 0f;
//        Float updatedWeight = editAssignmentView.getUpdatedWeight();

        // Check empty name
        if (updatedName.isEmpty()) {
            return UpdateNWProblem.EMPTY_NAME;
        }
        // TODO possibly figure out how to check if name is duplicate now

        // Checks for bad float (empty or invalid input)
        try {
            updatedWeight = editAssignmentView.getUpdatedWeight();
        }
        catch (Exception e) {
            return UpdateNWProblem.BAD_FLOAT;
        }
//        System.out.println("In validateInformation");
//        System.out.println("updateName: " + updatedName);
//        System.out.println("updateWeight: " + updatedWeight);
        // TODO possibly figure out how to check if float is invalid now
        return UpdateNWProblem.NO_ERROR;
    }

    private void returnToParentView() {
        // Close the window
        rootView.closeWindow();

        // Refresh the parent
        parentView.update();
        parentView.display();
    }

    private void updateAllEntityInformation(String updatedName, float updatedWeight) {
        course.getEntries().stream()
                .map(entry -> entry.getFinalGrade())
                .forEach(realAssignment -> updateRAInformation(realAssignment, updatedName, updatedWeight));
    }

    private void updateRAInformation(RealAssignment ra, String updatedName, float updatedWeight) {
//        System.out.println("in updateRAInformation");
        for (int i=0; i< ra.getNumSubAssignments(); i++) {
            RealAssignment subRa = (RealAssignment) ra.getSubAssignment(i);
//            System.out.println("parent name: " + parent.getName() + " weight: " + parent.getWeight());
//            System.out.println("subRa name: " + subRa.getName() + " weight: " + subRa.getWeight());
//
            if (subRa.getName().equals(parent.getName()) && subRa.getWeight() == parent.getWeight()) {
//                System.out.println("update name and weight");
                subRa.setName(updatedName);
                subRa.setWeight(updatedWeight);
            }
        }
    }

    private ArrayList<RealAssignment> getSubAssignments() {
        ArrayList<RealAssignment> subAssignments = new ArrayList<>();
        Student templateStudent = course.getTemplate().getStudent();
        HashMap<String, ArrayList<JTextField>> subAssignmentFields = editAssignmentView.getSubAssignmentFields();
        ArrayList<JTextField> subAssignmentNamesField = subAssignmentFields.get("names");
        ArrayList<JTextField> subAssignmentWeightsFields = subAssignmentFields.get("weights");
        for (int i = 0; i < subAssignmentNamesField.size(); i++) {
            subAssignments.add(
                    new RealAssignment(
                            subAssignmentNamesField.get(i).getText(),
                            Float.valueOf(subAssignmentWeightsFields.get(i).getText()),
                            new Grade(0f),
                            templateStudent));
        }
        return subAssignments;
    }

    private void updateSubAssignments() {
        // remove all subassignments
//        while(parent.getSubAssignment(0) != null){
//            parent.removeSubAssignment(parent.getSubAssignment(0));
//        }
        System.out.println("1. numSubAssignments :" + parent.getNumSubAssignments());

        // remove first
        if (parent.getNumSubAssignments() != 0) {
            for (Gradeable g : parent.getSubAssignmentsExceptNull()) {
                parent.removeSubAssignment(g);
            }
//            for (int i = 0; i < parent.getNumSubAssignments(); i++) {
//                parent.removeSubAssignment(parent.getSubAssignment(i));
//            }
        }

        // readd all;
        for (RealAssignment ra : getSubAssignments()) {
            parent.addSubAssignment(ra);
        }

        System.out.println("2. numSubAssignments :" + parent.getNumSubAssignments());
    }

    private void updateAllEntitySubAssignmentInformation() {
        System.out.println(" in updateAllEntitySubAssignmentInformation");
//        for (RealAssignment ra : getSubAssignments()) {
//            parent.addSubAssignment(ra);
            ArrayList<RealAssignment> finalGradeList =
                    (ArrayList<RealAssignment>)course.getEntries().stream()
                    .map(entry -> entry.getFinalGrade())
                    .collect(Collectors.toList());

            RealAssignment currentRa;
            for (RealAssignment ra : finalGradeList) {
                System.out.println("not null size: " + ra.getSubAssignmentsExceptNull().size());
//                if (ra.getSubAssignmentsExceptNull().size() == 0)
                for (Gradeable g : ra.getSubAssignmentsExceptNull()){
                    currentRa = (RealAssignment) g;
                    System.out.println("g name: " + currentRa.getName());
                    System.out.println("parent name: " +  parent.getName());

                    if (currentRa.equals(parent)) {
                        System.out.println("add subAssignment");
                        // delete first

//                        for (RealAssignment realAssignment : getSubAssignments()) {
//                            currentRa.addSubAssignment(realAssignment);
//                        }

                        // remove all subassignments
                        int i=0;
                        if (currentRa.getNumSubAssignments() != 0) {
                            for (Gradeable gToRemove : currentRa.getSubAssignmentsExceptNull()) {
                                System.out.println("delete " + i);
                                currentRa.removeSubAssignment(gToRemove);
                                i++;
                            }
//            for (int i = 0; i < parent.getNumSubAssignments(); i++) {
//                parent.removeSubAssignment(parent.getSubAssignment(i));
//            }
                        }

                        for (RealAssignment realAssignment : getSubAssignments()) {
                            currentRa.addSubAssignment(realAssignment);
                        }
                    }
                }
            }


//                    .map(ra ->
//                            ra.getFlattenedSubAssignmentTree().stream()
//                                    .filter(g -> g instanceof RealAssignment)
//                                    .filter(subra -> subra.equals(parent))
//                                    .count())
//                    .forEach(c -> System.out.println(c));
//                    .forEach(o -> o.ifPresent(g -> System.out.println(((RealAssignment) g).getName())));
//                                    .findAny())
//                    .forEach(o -> o.ifPresent(g -> System.out.println(((RealAssignment) g).getName())));
            System.out.println("printed.");
//                    .forEach(o -> o.ifPresent(g -> updateRASubAssignmentInformation(g, getSubAssignments())));
//        }


    }

    private void updateRASubAssignmentInformation(Gradeable g, ArrayList<RealAssignment> subAssignments) {

        for (RealAssignment aSub: subAssignments) {
            ((RealAssignment) g).addSubAssignment(aSub);
        }


//        for (int i=0; i< ra.getNumSubAssignments(); i++) {
//            RealAssignment subRa = (RealAssignment) ra.getSubAssignment(i);
//            if (subRa.getName().equals(parent.getName()) && subRa.getWeight() == parent.getWeight()) {
//                subRa.setName(updatedName);
//                subRa.setWeight(updatedWeight);
//            }
//        }
    }
}
