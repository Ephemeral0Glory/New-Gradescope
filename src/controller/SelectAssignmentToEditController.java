package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.*;
import boundary.*;

public class SelectAssignmentToEditController implements ActionListener {

    private IGraderFrame rootView;
    private IGraderFrame parentView;
    private User user;
    private Course course;
    private SelectAssignmentToEditView selectAssignmentToEditView;

    public SelectAssignmentToEditController(IGraderFrame rootView, IGraderFrame parentView, User user, Course course, SelectAssignmentToEditView selectAssignmentToEditView) {
        this.rootView = rootView;
        this.parentView = parentView;
        this.user = user;
        this.course = course;
        this.selectAssignmentToEditView = selectAssignmentToEditView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Gets selected assignment
        Gradeable selectedAssignment = selectAssignmentToEditView.getSelectedAssignment();
        if (selectedAssignment == null) { // If no assignment was selected
            // Do nothing
            return;
        }
        OpenEditAssignmentController opeac = new OpenEditAssignmentController(rootView, user, course, selectAssignmentToEditView);
        opeac.open();
    }
    
}
