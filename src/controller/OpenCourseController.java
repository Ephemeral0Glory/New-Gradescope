package controller;

import entity.Course;
import entity.User;
import boundary.CourseView;
import boundary.GraderView;
import boundary.IGraderFrame;

/**
 *  Creates and opens a CourseView inside a GraderView.
 *  <p>
 *  Discards the initial rootView. The new GraderView has a menu bar.
 *  @author Alex Titus
 *
 */
public class OpenCourseController
{
	private IGraderFrame rootView;
	private User user;
	private User owner;
	private Course course;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param user  The current user
	 *  @param owner  The owner of the gradebook this course came from 
	 *  @param course  The course to display
	 */
	public OpenCourseController(IGraderFrame rootView, User user, User owner, Course course)
	{
		this.rootView = rootView;
		this.user = user;
		this.owner = owner;
		this.course = course;
	}
	
	public void open()
	{
		// Close previous window
		rootView.closeWindow();
		
		// Create new frame, with menu bars
		IGraderFrame gv = setUpFrame();
		
		// Create and display course edit screen
		gv.setNewView(new CourseView(gv, user, owner, course));
		gv.update();
		gv.display();
	}
	
	private IGraderFrame setUpFrame()
	{
		GraderView gv = new GraderView();
		gv.setUpMenuBars();
		return gv;
	}

}
