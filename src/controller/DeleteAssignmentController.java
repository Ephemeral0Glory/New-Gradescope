package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.RealAssignment;
import boundary.CourseInfoView;
import boundary.CourseView;
import boundary.IGraderFrame;

/**
 *  Deletes an assignment from the entries table and header.
 *  @author Alex Titus
 */
public class DeleteAssignmentController implements ActionListener
{
	private IGraderFrame rootView;
	private CourseInfoView assignmentInfo;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param assignmentInfo  The screen with the information about the assignments
	 */
	public DeleteAssignmentController(IGraderFrame rootView, CourseInfoView assignmentInfo)
	{
		this.rootView = rootView;
		this.assignmentInfo = assignmentInfo;
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
		// Get assignment to delete
		RealAssignment ra = assignmentInfo.getColumn().get(0);
		
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
