package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.User;
import boundary.AddSectionView;
import boundary.GraderView;
import boundary.IGraderFrame;

/**
 *  Opens the Add New Section window from the Course Edit screen.
 *  @author Alex Titus
 */
public class OpenAddSectionWindowController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	private Course course;
	
	public OpenAddSectionWindowController(IGraderFrame rootView, User user, Course course)
	{
		this.rootView = rootView;
		this.user = user;
		this.course = course;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		open();
	}
	
	public void open()
	{
		// Create new window
		GraderView gv = new GraderView("Add New Section");
		gv.setClosePolicyPopUp();
		
		// Create add section screen
		AddSectionView asv = new AddSectionView(gv, rootView, user, course);
		
		// Display
		gv.setNewView(asv);
		gv.update();
		gv.display();
	}

}
