package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.*;
import boundary.*;

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

        String updatedName = editAssignmentView.getUpdatedName();
        Float updatedWeight = 0f;
        
        if (error == UpdateNWProblem.NO_ERROR) {
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
}
