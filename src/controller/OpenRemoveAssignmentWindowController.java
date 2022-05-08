package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.User;
import boundary.GraderView;
import boundary.IGraderFrame;
import boundary.RemoveAssignmentView;

/**
 *  Opens the Remove Assignment window from the Course Edit screen.
 *  @author Alex Titus
 */
public class OpenRemoveAssignmentWindowController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	private Course course;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param user  The current user
	 *  @param course  The course being modified
	 */
	public OpenRemoveAssignmentWindowController(IGraderFrame rootView, User user, Course course)
	{
		this.rootView = rootView;
		this.user = user;
		this.course = course;
	}

	/**
	 *  Opens the Remove Assignment popup window.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		open();
	}
	
	/**
	 *  Opens the Remove Assignment popup window.
	 */
	public void open()
	{
		// Create new window
		GraderView gv = new GraderView("Remove Assignment");
		gv.setClosePolicyPopUp();
		
		// Create add entry screen
		RemoveAssignmentView rsv = new RemoveAssignmentView(gv, rootView, user, course);
		
		// Display
		gv.setNewView(rsv);
		gv.update();
		gv.display();
	}

}
