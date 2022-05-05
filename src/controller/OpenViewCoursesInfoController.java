package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Gradebook;
import entity.User;
import boundary.IGraderFrame;

public class OpenViewCoursesInfoController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	
	public OpenViewCoursesInfoController(IGraderFrame rootView, User user, Gradebook gradebook)
	{
		this.rootView = rootView;
		this.user = user;
		this.gradebook = gradebook;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO OpenviewCoursesInfoController.actionPerformed

	}

}
