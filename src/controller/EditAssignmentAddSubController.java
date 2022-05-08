package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.EditAssignmentView;
import boundary.IGraderFrame;

/**
 *  Adds a sub-assignment to the edit assignment screen.
 *  @author David Sullo
 */
public class EditAssignmentAddSubController implements ActionListener {

    private IGraderFrame rootView;
    private EditAssignmentView screen;

        /**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param screen  The add assignment screen
	 */
	public EditAssignmentAddSubController(IGraderFrame rootView, EditAssignmentView screen)
	{
		this.rootView = rootView;
		this.screen = screen;
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        // Extends each JTextField ArrayList by one with a new member
        screen.addSubAssignmentFields();

        // Update display
        screen.showNewSubAssignment();
        
        // Update screen
        rootView.update();
        rootView.display();
    }
    
}