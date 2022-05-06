package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.Gradebook;
import entity.User;
import boundary.AddSectionView;
import boundary.CreateNewCourseView;
import boundary.IGraderFrame;
import boundary.ViewCourseInfoView;

public class OpenAddSectionViewController implements ActionListener
{
	
	private IGraderFrame rootView;
	private User user;
	private Course course;
//	private Gradebook gradebook;
	
	public OpenAddSectionViewController(IGraderFrame rootView, User user, Course course)
	{
		this.rootView = rootView;
		this.user = user;
		this.course = course;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Create menu
		AddSectionView asv = new AddSectionView(rootView, user, course);
		
		// Display it
		rootView.setNewView(asv);
		rootView.update();
		rootView.display();
		
	}

}
