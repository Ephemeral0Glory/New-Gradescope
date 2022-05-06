package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.*;
import entity.*;


public class OpenAddAssignmentWindowController implements ActionListener {

    private IGraderFrame rootView;
    private User user;
    private Course course;

    public OpenAddAssignmentWindowController(IGraderFrame rootView, User user, Course course) {
        this.rootView = rootView;
        this.user = user;
        this.course = course;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open(); 
    }

    public void open() {
        // Create new window
        GraderView gv = new GraderView("Add an Assignment");
        gv.setClosePolicyPopUp();

        // Create add assignment screen
        AddAssignmentView asv = new AddAssignmentView(gv, rootView, user, course);

        // Display
        gv.setNewView(asv);
        gv.update();
        gv.display();
    }
    
}
