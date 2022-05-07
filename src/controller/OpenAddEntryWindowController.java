package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.User;
import boundary.AddEntryView;
import boundary.GraderView;
import boundary.IGraderFrame;

/**
 *  Opens the Add New Entry window from the Course Edit screen.
 *  @author Alex Titus
 */
public class OpenAddEntryWindowController implements ActionListener
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
	public OpenAddEntryWindowController(IGraderFrame rootView, User user, Course course)
	{
		this.rootView = rootView;
		this.user = user;
		this.course = course;
	}

	/**
	 *  Opens the Add Entry popup window.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		open();
	}
	
	/**
	 *  Opens the Add Entry popup window.
	 */
	public void open()
	{
		// Create new window
		GraderView gv = new GraderView("Add New Entry");
		gv.setClosePolicyPopUp();
		
		// Create add entry screen
		AddEntryView aev = new AddEntryView(gv, rootView, user, course);
		
		// Display
		gv.setNewView(aev);
		gv.update();
		gv.display();
	}

}
