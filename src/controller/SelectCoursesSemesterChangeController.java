package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.IGraderFrame;
import boundary.SelectCoursesView;

/**
 *  Changes the displayed courses in {@link SelectCoursesView} when the selected Semester changes.
 *  @author Alex Titus
 */
public class SelectCoursesSemesterChangeController implements ActionListener
{
	private IGraderFrame rootView;
	private SelectCoursesView screen;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param screen  The select courses screen
	 */
	public SelectCoursesSemesterChangeController(IGraderFrame rootView, SelectCoursesView screen)
	{
		this.rootView = rootView;
		this.screen = screen;
	}

	/**
	 *  Changes the courses listing to that of the newly selected semester.
	 *  
	 *  @param ae  Ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Change courses listed
		screen.updateCourseListing();
		
		// Update screen
		rootView.update();
		rootView.display();
	}

}
