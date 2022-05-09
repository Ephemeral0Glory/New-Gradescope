package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            // first update entity information
            updateAllEntityInformation(updatedName, updatedWeight);

            // after updating entity update real assignment information
            this.parent.setName(updatedName);
            this.parent.setWeight(updatedWeight);

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
}
