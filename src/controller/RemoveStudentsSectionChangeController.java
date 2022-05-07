package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.IGraderFrame;
import boundary.RemoveStudentView;

/**
 *  Changes the displayed Students in {@link RemoveStudentView} when the selected Section changes.
 *  @author Alex Titus
 */
public class RemoveStudentsSectionChangeController implements ActionListener
{
	private IGraderFrame rootView;
	private RemoveStudentView screen;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param screen  The remove student screen
	 */
	public RemoveStudentsSectionChangeController(IGraderFrame rootView, RemoveStudentView screen)
	{
		this.rootView = rootView;
		this.screen = screen;
	}

	/**
	 *  Changes the student listing to that of the newly selected section.
	 *  
	 *  @param ae  Ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Change courses listed
		screen.updateStudentListing();
		
		// Update screen
		rootView.update();
		rootView.display();
	}

}
