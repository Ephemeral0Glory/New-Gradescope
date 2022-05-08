package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import entity.*;
import boundary.*;

public class AddAssignmentController implements ActionListener{
    public static enum AssignmentProblem {NO_ERROR, EMPTY_NAME, DUPLICATED_NAME, BAD_FLOAT, EMPTY_SUB_NAME, DUPLICATE_SUB_NAME, BAD_SUB_WEIGHT, INVALID_SUB_WEIGHT}; // BAD_SUB_WEIGHT refers to incorrect input; INVALID_SUB_WEIGHT total either exceeds 100 or is below 0
    private IGraderFrame rootView;
    private IGraderFrame parentView;
    private User user;
    private AddAssignmentView addAssignmentView;

    public AddAssignmentController(IGraderFrame rootView, IGraderFrame parentView, User user, AddAssignmentView addAssignmentView) {
        this.rootView = rootView;
        this.parentView = parentView;
        this.user = user;
        this.addAssignmentView = addAssignmentView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AssignmentProblem error = validateInformation();

        String assignmentName = addAssignmentView.getName();
        Float assignmentWeight = 0f;

        if (error == AssignmentProblem.NO_ERROR) {
            // Create and add Assignment where appropriate
<<<<<<< HEAD
            assignmentWeight = addAssignmentView.getWeight() / 100;
=======
            assignmentWeight = addAssignmentView.getWeight() / 100.0f;
>>>>>>> 8e746dd60a3bba6e6bc77b1b7b1e8e4e74a51e9d
            Gradeable newAssignment = new RealAssignment(assignmentName, assignmentWeight);
            ArrayList<JTextField> subAssignmentNamesFields = addAssignmentView.getSubAssignmentNamesFields();
            ArrayList<JTextField> subAssignmentWeightsFields = addAssignmentView.getSubAssignmentWeightsFields();
            if (!(subAssignmentNamesFields.isEmpty() && subAssignmentWeightsFields.isEmpty())) {
                for (int i = 0; i < subAssignmentNamesFields.size(); i++) {
                    String currName = subAssignmentNamesFields.get(i).getText();
                    Float currFloat = Float.valueOf(subAssignmentWeightsFields.get(i).getText()) / 100;
                    RealAssignment currSubAssignment = new RealAssignment(currName, currFloat);
                    ((RealAssignment) newAssignment).addSubAssignment(currSubAssignment);
                }
            }
            Course course = addAssignmentView.getCourse();
            course.getTemplate().addSubAssignment(newAssignment);
            course.addAssignment((RealAssignment) newAssignment);
            // Close window
            returnToParentView();
        }
        else { // Error occurred

            // Tell user about problem
            addAssignmentView.showError(error);
            rootView.update();
            rootView.display();
        }
        
    }

    private AssignmentProblem validateInformation() {

        String assignmentName = addAssignmentView.getName();
        Float assigmentWeight = 0f;

        // Check empty name
        if (assignmentName.isEmpty()) {
            return AssignmentProblem.EMPTY_NAME;
        }

        // Check for duplicate assignment name
        for (Gradeable a: addAssignmentView.getCourse().getTemplate().getSubAssignments()) {
            if (addAssignmentView.getCourse().getTemplate().getNumSubAssignments() == 0) {
                break;
            }
            if (assignmentName.equals(((RealAssignment) a).getName())) {
                return AssignmentProblem.DUPLICATED_NAME;
            }
        }

        // Checks for bad float (empty or invalid input)
        try {
            assigmentWeight = addAssignmentView.getWeight();
        }
        catch (Exception e) {
            return AssignmentProblem.BAD_FLOAT;
        }

        if (assigmentWeight < 0f || assigmentWeight > 100f) {
            return AssignmentProblem.BAD_FLOAT;
        }

        // Checks for errors within the subassignments
        ArrayList<JTextField> subAssignmentNamesFields = addAssignmentView.getSubAssignmentNamesFields();
        ArrayList<JTextField> subAssignmentWeightsFields = addAssignmentView.getSubAssignmentWeightsFields();
        if (!(subAssignmentNamesFields.isEmpty() && subAssignmentWeightsFields.isEmpty())) {
            // Checks for errors with subassignment names
            ArrayList<String> subAssignmentNames = new ArrayList<String>();
            for (JTextField nameField: subAssignmentNamesFields) {
                String currString = nameField.getText();
                // Checks if its empty
                if (currString.isEmpty()) {
                    return AssignmentProblem.EMPTY_SUB_NAME;
                }
                // Checks if multiple subassignments have same name
                if (subAssignmentNames.contains(currString)) {
                    return AssignmentProblem.DUPLICATE_SUB_NAME;
                }
                subAssignmentNames.add(currString);
            }
            float totalWeight = 0f;
            for (JTextField weightField: subAssignmentWeightsFields) {
                float subAssignmentWeight = 0f;
                try {
                    subAssignmentWeight = Float.valueOf(weightField.getText());
                }
                catch (Exception e) {
                    return AssignmentProblem.BAD_SUB_WEIGHT;
                }
        
                if (assigmentWeight < 0f || assigmentWeight > 100f) {
                    return AssignmentProblem.BAD_SUB_WEIGHT;
                }
                totalWeight += subAssignmentWeight;
            }
            if (totalWeight > 100f || totalWeight < 0f) {
                return AssignmentProblem.INVALID_SUB_WEIGHT;
            }

        }


        return AssignmentProblem.NO_ERROR;
    }

    private void returnToParentView() {
        // Close the Add Assignment Window
        rootView.closeWindow();

        // Refresh the parent
        parentView.update();
        parentView.display();
    }
     
}
