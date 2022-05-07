package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.User;
import boundary.GraderView;
import boundary.IGraderFrame;
import boundary.RemoveSectionView;

/**
 *  Opens the Remove Section window from the Course Edit screen.
 *  @author Alex Titus
 */
public class OpenRemoveSectionWindowController implements ActionListener
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
	public OpenRemoveSectionWindowController(IGraderFrame rootView, User user, Course course)
	{
		this.rootView = rootView;
		this.user = user;
		this.course = course;
	}

	/**
	 *  Opens the Remove Section popup window.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		open();
	}
	
	/**
	 *  Opens the Remove Section popup window.
	 */
	public void open()
	{
		// Create new window
		GraderView gv = new GraderView("Remove Section");
		gv.setClosePolicyPopUp();
		
		// Create add entry screen
		RemoveSectionView rsv = new RemoveSectionView(gv, rootView, user, course);
		
		// Display
		gv.setNewView(rsv);
		gv.update();
		gv.display();
	}

}
