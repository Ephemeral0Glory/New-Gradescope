package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import boundary.*;
import entity.*;

/**
 * 
 *  @author David Sullo
 */
public class OpenEditAssignmentController implements ActionListener {

    private IGraderFrame rootView;
    private User user;
    private Course course;
    private SelectAssignmentToEditView selectAssignmentToEditView;
    
    public OpenEditAssignmentController(IGraderFrame rootView, User user,
    		Course course, SelectAssignmentToEditView selectAssignmentToEditView) {
        this.rootView = rootView;
        this.user = user;
        this.course = course;
        this.selectAssignmentToEditView = selectAssignmentToEditView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open();
    }
    
    public void open() {
        // Create new window
        GraderView gv = new GraderView("Edit an Assignment");
        gv.setClosePolicyPopUp();
        RealAssignment parent = (RealAssignment) selectAssignmentToEditView.getSelectedAssignment();
        // Create edit assignment screen
        ArrayList<Gradeable> subAssignments = parent.getSubAssignments();
        ArrayList<JTextField> subAssignmentNames = new ArrayList<JTextField>();
        ArrayList<JTextField> subAssignmentWeights = new ArrayList<JTextField>();
        if (parent.getNumSubAssignments() != 0) {
            for (Gradeable g: subAssignments) {
                JTextField currJTextFieldName = new JTextField();
                JTextField currJTextFieldWeight = new JTextField();
                currJTextFieldName.setText(((RealAssignment) g).getName());
                currJTextFieldWeight.setText(Float.toString(((RealAssignment) g).getWeight()));
                subAssignmentNames.add(currJTextFieldName);
                subAssignmentWeights.add(currJTextFieldWeight);
            }
        }
        EditAssignmentView eav = new EditAssignmentView(gv, rootView, user, course, parent, subAssignmentNames, subAssignmentWeights);
        // Display
        gv.setNewView(eav);
        gv.update();
        gv.display();
    }
}
