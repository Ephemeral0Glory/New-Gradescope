package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Gradebook;
import entity.User;
import boundary.CreateNewCourseView;
import boundary.IGraderFrame;

public class OpenNewCourseController implements ActionListener
{
	
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	
	public OpenNewCourseController(IGraderFrame view, User user, Gradebook gradebook)
	{
		this.rootView = view;
		this.user = user;
		this.gradebook = gradebook;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// Create menu
		CreateNewCourseView cncv = new CreateNewCourseView(rootView, user, gradebook);
		
		// Display it
		rootView.setNewView(cncv);
		rootView.update();
		rootView.display();
	}

}
