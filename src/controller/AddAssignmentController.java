package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.*;
import boundary.*;
import controller.AddSectionController.SectionProblem;

public class AddAssignmentController implements ActionListener{
    public static enum AssignmentProblem {NO_ERROR, EMPTY_NAME, DUPLICATED_NAME, BAD_FLOAT};
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
            assignmentWeight = addAssignmentView.getWeight();
            Gradeable newAssignment = new RealAssignment(assignmentName, assignmentWeight);
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
