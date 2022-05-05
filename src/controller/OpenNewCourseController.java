package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.User;
import boundary.CreateNewCourseView;
import boundary.IGraderFrame;

public class OpenNewCourseController implements ActionListener
{
	
	private IGraderFrame rootView;
	private User user;
	
	public OpenNewCourseController(IGraderFrame view, User user)
	{
		this.rootView = view;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// Create menu
		CreateNewCourseView cncv = new CreateNewCourseView(rootView, user);
		
		// Display it
		rootView.setNewView(cncv);
		rootView.update();
		rootView.display();
	}

}
