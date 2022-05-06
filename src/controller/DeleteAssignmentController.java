package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.RealAssignment;
import boundary.CourseView;
import boundary.IGraderFrame;

/**
 *  Deletes an assignment from the entries table and header.
 *  @author Alex Titus
 */
public class DeleteAssignmentController implements ActionListener
{
	private IGraderFrame rootView;
	private RealAssignment ra;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param assignmentInfo  The screen with the information about the assignments
	 */
	public DeleteAssignmentController(IGraderFrame rootView, RealAssignment ra)
	{
		this.rootView = rootView;
		this.ra = ra;
	}

	/**
	 *  Deletes the assignment from the entries table and header.
	 *  
	 *  @param e  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		delete();
	}
	
	public void delete()
	{
		// Get course
		// Can cast because the only way to reach this method is via CourseView
		Course c = ((CourseView) rootView.getCurrentDisplay()).getCourse();
		
		// Remove assignment
		c.removeAssignment(ra);
		
		// Update display
		rootView.update();
		rootView.display();
	}

}
