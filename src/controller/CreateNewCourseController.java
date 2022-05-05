package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.CreateNewCourseView;
import boundary.IGraderFrame;
import boundary.MainMenuView;
//import controller.CreateNewUserController.CreateProblem;
import entity.Course;
import entity.RealAssignment;
import entity.User;

public class CreateNewCourseController implements ActionListener {

	/**
	 *  Indicates one of the following errors in user creation:
	 *  <ul><li> BAD_COURSENAME: course name was left empty </li>
	 *  <li> BAD_COURSECODE: either course code conflicted with existing one, or it was left empty </li>
	 */
	public static enum CreateCourseProblem { NO_ERROR, BAD_COURSENAME, BAD_COURSECODE }
	private CreateNewCourseView newCourseInfo;
	private IGraderFrame rootView;
	private User user;
	
	public CreateNewCourseController(IGraderFrame rootView, CreateNewCourseView newCourseInfo, User user) {
		this.rootView = rootView;
		this.newCourseInfo = newCourseInfo;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Validate the course information
		String newCoursename = newCourseInfo.getEnteredCoursename();
		String newCoursecode = newCourseInfo.getEnteredCoursecode();
		
		CreateCourseProblem error = validateInfo(newCoursename, newCoursecode);
		
		if (error == CreateCourseProblem.NO_ERROR) {
			// Create course
			Course c = new Course(newCoursename, newCoursecode, user);
			
			// Display course created
			newCourseInfo.showCourseCreated();
		} else {
			// Display error
			newCourseInfo.showCourseCreationFailed();
		}
		
		// Return to main menu screen
		openMainMenu();

//		// Update display
//		rootView.update();
//		rootView.display();
	}
	
	public CreateCourseProblem validateInfo(String newCoursename, String newCoursecode) {
		// Empty values check
		if (newCoursename.isEmpty()) {
			return CreateCourseProblem.BAD_COURSENAME;
		}
		
		if (newCoursecode.isEmpty()) {
			return CreateCourseProblem.BAD_COURSECODE;
		}
		
		return CreateCourseProblem.NO_ERROR;
	}
	
	private void openMainMenu() {
		OpenMainMenuController ommc = new OpenMainMenuController(rootView, user);
		ommc.open();
	}
}
