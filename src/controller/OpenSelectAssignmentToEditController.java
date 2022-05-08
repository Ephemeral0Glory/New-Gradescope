package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.*;
import entity.*;

public class OpenSelectAssignmentToEditController implements ActionListener {

    private IGraderFrame rootView;
    private User user;
    private Course course;
    private RealAssignment parent;

    public OpenSelectAssignmentToEditController(IGraderFrame rootView, User user, Course course, RealAssignment parent) {
        this.rootView = rootView;
        this.user = user;
        this.course = course;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open();        
    }

    public void open() {
        // Create new window
        GraderView gv = new GraderView("Edit an Assignment");
        gv.setClosePolicyPopUp();

        // Create select assignment screen
        SelectAssignmentToEditView satev = new SelectAssignmentToEditView(gv, rootView, user, course, parent);

        // Display
        gv.setNewView(satev);
        gv.update();
        gv.display();
    }
    
}