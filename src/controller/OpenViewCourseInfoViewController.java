package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.IGraderFrame;
//import boundary.UserOptionsMenuView;
import boundary.ViewCourseInfoView;
import entity.Course;
import entity.User;

/**
 *  Opens the Course Info screen.
 *  
 *  @author Seonghoon Steve Cho
 *  @author Alex Titus
 */
public class OpenViewCourseInfoViewController implements ActionListener {

	private IGraderFrame rootView;
	private User user;
	private Course course;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The window application frame
	 *  @param user  The current user
	 *  @param course  The course to display info for
	 */
	public OpenViewCourseInfoViewController(IGraderFrame rootView, User user, Course course) {
		this.rootView = rootView;
		this.user = user;
		this.course = course;
	}
	
	/**
	 *  Opens the course info screen.
	 *  
	 *  @param e  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		open();
	}
	
	/**
	 *  Opens the course info screen.
	 */
	public void open()
	{
		// Create menu
		ViewCourseInfoView vciv = new ViewCourseInfoView(rootView, user, course);

		// Display it
		rootView.setNewView(vciv);
		rootView.update();
		rootView.display();
	}

}
