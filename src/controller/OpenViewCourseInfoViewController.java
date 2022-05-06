package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.IGraderFrame;
//import boundary.UserOptionsMenuView;
import boundary.ViewCourseInfoView;
import entity.Course;
import entity.User;

public class OpenViewCourseInfoViewController implements ActionListener {

	private IGraderFrame rootView;
	private User user;
	private Course course;
	
	public OpenViewCourseInfoViewController(IGraderFrame rootView, User user, Course course) {
		this.rootView = rootView;
		this.user = user;
		this.course = course;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		// Create menu
//		ViewCourseInfoView vciv = new ViewCourseInfoView(rootView, user, course);
//		
//		// Display it
//		rootView.setNewView(vciv);
//		rootView.update();
//		rootView.display();
		
	}

}
