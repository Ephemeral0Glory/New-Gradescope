package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.AddAssignmentView;
import boundary.IGraderFrame;

/**
 *  Adds a sub-assignment to the add assignment screen.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class AddAssignmentAddSubController implements ActionListener {
 
    private IGraderFrame rootView;
    private AddAssignmentView screen;

    /**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param screen  The add assignment screen
	 */
	public AddAssignmentAddSubController(IGraderFrame rootView, AddAssignmentView screen)
	{
		this.rootView = rootView;
		this.screen = screen;
	}

    /**
	 *  Changes the courses listing to that of the newly selected semester.
	 *  
	 *  @param ae  Ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
        // Extends each JTextField ArrayList by one with a new member
		screen.addSubAssignmentFields();
		
		// Update display
		screen.showNewSubAssignment();
		
		// Update screen
		rootView.update();
		rootView.display();
	}
}