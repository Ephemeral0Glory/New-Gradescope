package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utilities.GradebookFileReaderException;
import entity.*;
import boundary.CreateNewCourseView;
import boundary.IGraderFrame;
import boundary.SelectCoursesView;

public class OpenSelectCoursesController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	
	public OpenSelectCoursesController(IGraderFrame rootView, User user, Gradebook gradebook) throws GradebookFileReaderException
	{
		this.rootView = rootView;
		this.user = user;
		this.gradebook = gradebook;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		open();
	}
	
	public void open()
	{
		try
		{
			SelectCoursesView scv = new SelectCoursesView(rootView, user, gradebook);
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
