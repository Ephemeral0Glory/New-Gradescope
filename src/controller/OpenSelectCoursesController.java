package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utilities.GradebookFileReaderException;
import entity.User;
import boundary.IGraderFrame;
import boundary.SelectCoursesView;

public class OpenSelectCoursesController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	
	public OpenSelectCoursesController(IGraderFrame rootView, User user)
	{
		this.rootView = rootView;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		open();
	}
	
	public void open()
	{
		try
		{
			SelectCoursesView scv = new SelectCoursesView(rootView, user);
			rootView.setNewView(scv);
			rootView.update();
			rootView.display();
		} catch (GradebookFileReaderException e)
		{
			e.printStackTrace();
			
			// TODO notify user of problem
		}
	}

}
