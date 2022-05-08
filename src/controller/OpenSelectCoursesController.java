package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Gradebook;
import entity.User;
import boundary.IGraderFrame;
import boundary.SelectCoursesView;

/**
 *  Opens the select courses screen.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class OpenSelectCoursesController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	private boolean editingCourse;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param user  The current user
	 *  @param gradebook  The gradebook being accessed
	 *  @param editingCourse  Whether selecting a course to edit (true) or to display information about (false).
	 */
	public OpenSelectCoursesController(IGraderFrame rootView, User user, Gradebook gradebook,
			boolean editingCourse)
	{
		this.rootView = rootView;
		this.user = user;
		this.gradebook = gradebook;
		this.editingCourse = editingCourse;
	}

	/**
	 *  Opens the course select screen.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		open();
	}
	
	/**
	 *  Opens the course select screen.
	 */
	public void open()
	{
		// Create new screen
		SelectCoursesView scv = new SelectCoursesView(rootView, user, gradebook,
				editingCourse);
		
		// Set new screen
		rootView.setNewView(scv);
		
		// Update display
		rootView.update();
		rootView.display();
	}

}
