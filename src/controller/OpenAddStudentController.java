package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.*;
import boundary.*;

public class OpenAddStudentController implements ActionListener{

    private IGraderFrame rootView;
    private User user;
    private Course course;

    public OpenAddStudentController(IGraderFrame rootView, User user, Course course) {
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
        GraderView gv = new GraderView("Add a Student");
        gv.setClosePolicyPopUp();

        // Create add student screen
        AddStudentView asv = new AddStudentView(gv, rootView, user, course);

        // Display
        gv.setNewView(asv);
        gv.update();
        gv.display();
    }
    
}
