package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.User;
import boundary.AddSectionView;
import boundary.IGraderFrame;

/**
 *  
 *  @author Seonghoon Steve Cho
 *  @author Alex Titus
 *
 */
public class OpenAddSectionViewController implements ActionListener
{
	
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private User user;
	private Course course;
	
	public OpenAddSectionViewController(IGraderFrame rootView, IGraderFrame parentView,
			User user, Course course)
	{
		this.rootView = rootView;
		this.parentView = parentView;
		this.user = user;
		this.course = course;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		open();
	}
	
	public void open()
	{
		// Create menu
		AddSectionView asv = new AddSectionView(rootView, parentView, user, course);
		
		// Display it
		rootView.setNewView(asv);
		rootView.update();
		rootView.display();
	}

}
