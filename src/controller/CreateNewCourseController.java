package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utilities.ConfigFileReader;
import utilities.ConfigFileWriter;
import utilities.ConfigFileWriterException;
import utilities.CourseFileWriter;
import utilities.CourseFileWriterException;
import utilities.GradebookFileReader;
import utilities.GradebookFileReaderException;
import utilities.GradebookFileWriter;
import utilities.GradebookFileWriterException;
import utilities.IDFactory;
import boundary.CreateNewCourseView;
import boundary.IGraderFrame;
//import controller.CreateNewUserController.CreateProblem;
import entity.Course;
import entity.Gradebook;
import entity.Season;
import entity.Semester;
import entity.User;

/**
 *  
 *  @author Seonghoon Steve Cho
 *  @author Alex Titus
 */
public class CreateNewCourseController implements ActionListener {

	/**
	 *  Indicates one of the following errors in user creation:
	 *  <ul><li> BAD_COURSENAME: course name was left empty </li>
	 *  <li> BAD_COURSECODE: either course code conflicted with existing one, or it was left empty </li>
	 *  <li> SELECT_SEMESTER: a semester was not selected and the box marking create new semester mode was unmarked </li> 
	 */
	public static enum CreateCourseProblem { NO_ERROR, BAD_COURSENAME, BAD_COURSECODE, SELECT_SEMESTER }
	private CreateNewCourseView newCourseInfo;
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	
	public CreateNewCourseController(IGraderFrame rootView, CreateNewCourseView newCourseInfo, User user,
			Gradebook gradebook) {
		this.rootView = rootView;
		this.newCourseInfo = newCourseInfo;
		this.user = user;
		this.gradebook = gradebook;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Validate the course information
		String newCoursename = newCourseInfo.getEnteredCoursename();
		String newCoursecode = newCourseInfo.getEnteredCoursecode();
		
		CreateCourseProblem error = validateInfo(newCoursename, newCoursecode);
		
		if (error == CreateCourseProblem.NO_ERROR) {
			// Determine whether to use an existing semester or to create a new one
			Semester semesterToAddTo = getSemesterToAddTo();
			
			// Create course
			Course c = new Course(newCoursename, newCoursecode, user);
			
			// Add course to semester
			semesterToAddTo.addCourse(c);
			
			// Update the gradebook file
			updateGradebookFile();
			
			// Update the config file
			updateConfigFile();
			
			// Create a new course file
			createCourseFile(c);
			
			// Return to main menu screen
			openMainMenu();
		} else {
			// Display error
			newCourseInfo.showCourseCreationFailed(error);
		}

		// Update display
		rootView.update();
		rootView.display();
	}
	
	private Semester getSemesterToAddTo()
	{
		/*
		 *  Will either get selected semester or create a new one
		 *  and add that to the gradebook
		 */
		if(newCourseInfo.creatingNewSemester())
		{
			// Create a new semester
			int year = newCourseInfo.getEnteredSemesterYear();
			Season season = newCourseInfo.getEnteredSemesterSeason();
			Semester s = new Semester(season.toString(), year);
			
			// Add semester to gradebook
			gradebook.addSemester(s);
			
			// Return this semester for use
			return s;
		}
		else  // Use the selected one
		{
			return newCourseInfo.getEnteredSemester();
		}
	}
	
	private CreateCourseProblem validateInfo(String newCoursename, String newCoursecode) {
		// Empty values check
		if (newCoursename.isEmpty()) {
			return CreateCourseProblem.BAD_COURSENAME;
		}
		
		if (newCoursecode.isEmpty()) {
			return CreateCourseProblem.BAD_COURSECODE;
		}
		if (!newCourseInfo.creatingNewSemester() &&
				newCourseInfo.getSelectedSemesterIndex() == -1)
		{
			return CreateCourseProblem.SELECT_SEMESTER;
		}
		
		return CreateCourseProblem.NO_ERROR;
	}
	
	private void openMainMenu() {
		OpenMainMenuController ommc = new OpenMainMenuController(rootView, user);
		try
		{
			ommc.open();
		} catch (GradebookFileReaderException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}
	
	private void updateGradebookFile()
	{
		try
		{
			// Get path to gradebook file
			String userDirPath = GradebookFileReader.gradebookDirectory + "u" + user.getID() + "/";
			String gradebookFile = userDirPath + "gradebook.xml";
			GradebookFileWriter writer = new GradebookFileWriter(gradebookFile);
			
			// Write the gradebook
			writer.writeGradebook(gradebook);
		} catch (GradebookFileWriterException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}
	
	private void updateConfigFile()
	{
		try
		{
			ConfigFileWriter writer = new ConfigFileWriter(ConfigFileReader.configFileName);
			
			// Write out new config state
			writer.writeConfig(IDFactory.getConfigs());
		}
		catch(ConfigFileWriterException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}
	
	private void createCourseFile(Course c)
	{
		try
		{
			// Get path to new course file
			String userDirPath = GradebookFileReader.gradebookDirectory + "u" + user.getID() + "/";
			String courseFilePath = userDirPath + "course" + c.getID() + ".xml";
			CourseFileWriter writer = new CourseFileWriter(courseFilePath);
			
			// Write the new course
			writer.writeCourse(c);
		}
		catch (CourseFileWriterException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}
}
