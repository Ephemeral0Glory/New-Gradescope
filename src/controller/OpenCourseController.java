package controller;

import entity.Course;
import entity.Semester;
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
	private Semester semester;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param user  The current user
	 *  @param owner  The owner of the gradebook this course came from 
	 *  @param course  The course to display
	 */
	public OpenCourseController(IGraderFrame rootView, User user, User owner,
			Course course, Semester semester)
	{
		this.rootView = rootView;
		this.user = user;
		this.owner = owner;
		this.course = course;
		this.semester = semester;
	}
	
	public void open()
	{
		// Close previous window
		rootView.closeWindow();
		
		// Create new frame, with menu bars
		GraderView gv = new GraderView();
		
		// Create and display course edit screen
		gv.setNewView(new CourseView(gv, user, owner, course, semester));
		gv.setUpMenuBars(course, user);
		gv.update();
		gv.display();
	}

}
