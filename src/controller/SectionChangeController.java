package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.AddEntryView;
import boundary.IGraderFrame;

/**
 *  Changes the displayed students in {@link AddEntryView} when the selected Section changes.
 *  @author Alex Titus
 */
public class SectionChangeController implements ActionListener
{
	private IGraderFrame rootView;
	private AddEntryView screen;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param screen  The add entry screen
	 */
	public SectionChangeController(IGraderFrame rootView, AddEntryView screen)
	{
		this.rootView = rootView;
		this.screen = screen;
	}

	/**
	 *  Changes the students listing to that of the newly selected section.
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
