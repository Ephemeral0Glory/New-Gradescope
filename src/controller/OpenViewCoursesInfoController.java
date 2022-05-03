package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.User;
import boundary.IGraderFrame;

public class OpenViewCoursesInfoController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	
	public OpenViewCoursesInfoController(IGraderFrame rootView, User user)
	{
		this.rootView = rootView;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO OpenviewCoursesInfoController.actionPerformed

	}

}
