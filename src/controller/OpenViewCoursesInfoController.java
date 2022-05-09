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
import boundary.ViewCourseInfoView;

public class OpenViewCoursesInfoController implements ActionListener
{
	private IGraderFrame rootView;
	private SelectCoursesView courseInfo;
	private User user;
	private Gradebook gradebook;
	
	public OpenViewCoursesInfoController(IGraderFrame rootView,
			SelectCoursesView courseInfo, User user, Gradebook gradebook)
	{
		this.rootView = rootView;
		this.courseInfo = courseInfo;
		this.user = user;
		this.gradebook = gradebook;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		open();
	}
	
	public void open()
	{
		// Get course selected
		Course selectedCourse = courseInfo.getSelectedCourse();
		// If no course was selected
		if(selectedCourse == null)
		{
			// Do nothing
			return;
		}
		
		try
		{
			// Load course selected
			Course loadedCourse = loadCourse(selectedCourse);

			// Create course info screen
			ViewCourseInfoView vciv = new ViewCourseInfoView(rootView, null, user, gradebook, loadedCourse, true);
			
			// Display
			rootView.setNewView(vciv);
			rootView.update();
			rootView.display();
		}
		catch(CourseFileReaderException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}
	
	private Course loadCourse(Course courseHeadToLoad) throws CourseFileReaderException
	{
		CourseFileReader reader = new CourseFileReader(gradebook.getOwner(), courseHeadToLoad.getID());

		// Read file
		return reader.readCourse();
	}

}
