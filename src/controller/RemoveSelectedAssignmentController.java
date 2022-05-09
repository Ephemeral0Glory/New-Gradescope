package controller;

import entity.*;
import boundary.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveSelectedAssignmentController implements ActionListener {

    private IGraderFrame rootView;
    private IGraderFrame parentView;
    private User user;
    private Course course;
    private RealAssignment parent;
    private SelectAssignmentToEditView selectedAssignmentView;

    public RemoveSelectedAssignmentController(IGraderFrame rootView,
    		IGraderFrame parentView, User user, Course course,
    		RealAssignment parent, SelectAssignmentToEditView selectAssignmentController) {
        this.rootView = rootView;
        this.parentView = parentView;
        this.user = user;
        this.course = course;
        this.parent = parent;
        this.selectedAssignmentView = selectAssignmentController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        RealAssignment selectedAssignment = (RealAssignment) selectedAssignmentView.getSelectedAssignment();
        if (selectedAssignment == null) { // No assignment selected
            return; // Do nothing
        }
        // Removes the selected assignment from the parent's list of subassignments;
        course.removeAssignment(selectedAssignment);

        // Update displays
        rootView.update();
        rootView.display();
    }
    
}
