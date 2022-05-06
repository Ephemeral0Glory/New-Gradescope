package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utilities.CourseFileReader;
import utilities.CourseFileReaderException;
import entity.Course;
import entity.Gradebook;
import entity.User;
import boundary.IGraderFrame;
import boundary.SelectCoursesView;

/**
 * 	Determines the course selected by the user and opens the course edit screen.
 *  @author Alex Titus
 */
public class SelectCourseController implements ActionListener
{
	private IGraderFrame rootView;
	private SelectCoursesView selectionInfo;
	private User user;
	private Gradebook gradebook;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame object
	 *  @param selectionInfo  The screen with the course selection
	 *  @param user  The user currently working
	 *  @param gradebook  The gradebook being worked on
	 */
	public SelectCourseController(IGraderFrame rootView,
			SelectCoursesView selectionInfo, User user, Gradebook gradebook)
	{
		this.rootView = rootView;
		this.selectionInfo = selectionInfo;
		this.user = user;
		this.gradebook = gradebook;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Determine course selected
		Course selectedCourse = selectionInfo.getSelectedCourse();
		if(selectedCourse == null)  // If a course wasn't selected
		{
			// Do nothing
			return;
		}
		
		try
		{
			// Load selected course from file
			Course loadedCourse;
			loadedCourse = loadCourse(selectedCourse);
			
			// Create and display the course edit screen
			OpenCourseController cv = new OpenCourseController(rootView, user,
					gradebook.getOwner(), loadedCourse, selectionInfo.getSelectedSemester());
			cv.open();
		}
		catch (CourseFileReaderException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}
	
	private Course loadCourse(Course selectedCourse) throws CourseFileReaderException
	{
		CourseFileReader reader = new CourseFileReader(gradebook.getOwner(), selectedCourse.getID());

		// Read file
		return reader.readCourse();
	}

}
