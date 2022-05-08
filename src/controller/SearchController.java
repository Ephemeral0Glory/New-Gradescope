package controller;

import boundary.CourseView;
import boundary.CreateNewCourseView;
import boundary.IGraderFrame;
import entity.*;
import utilities.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  
 *  @author Seonghoon Steve Cho
 *  @author Alex Titus
 */
public class SearchController implements ActionListener {

	/**
	 *  Indicates one of the following errors in user creation:
	 *  <ul><li> BAD_COURSENAME: course name was left empty </li>
	 *  <li> BAD_COURSECODE: either course code conflicted with existing one, or it was left empty </li>
	 *  <li> SELECT_SEMESTER: a semester was not selected and the box marking create new semester mode was unmarked </li>
	 */
	public static enum SearchProblem { NO_ERROR, BAD_SEARCHTEXT }
//	private CreateNewCourseView newCourseInfo;
	private CourseView courseView;
	private IGraderFrame rootView;
	private User user;
//	private Gradebook gradebook;

	public SearchController(IGraderFrame rootView, User user, CourseView courseView) {
		this.rootView = rootView;
		this.courseView = courseView;
		this.user = user;
//		this.gradebook = gradebook;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Validate the course information
		String searchText = courseView.getSearchText();

		SearchProblem error = validateInfo(searchText);
		
		if (error == SearchProblem.NO_ERROR) {

			// send serchText to courseView
			courseView.update();
		} else {
			// Display error
//			newCourseInfo.showCourseCreationFailed(error);
		}

		// Update display
		rootView.update();
		rootView.display();
	}
	

	
	private SearchProblem validateInfo(String searchText) {
		// Empty values check
		if (searchText.isEmpty()) {
			return SearchProblem.BAD_SEARCHTEXT;
		}
		
		return SearchProblem.NO_ERROR;
	}
}
