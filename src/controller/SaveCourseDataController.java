package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utilities.ConfigFileReader;
import utilities.ConfigFileWriter;
import utilities.ConfigFileWriterException;
import utilities.CourseFileWriter;
import utilities.CourseFileWriterException;
import utilities.GradebookFileReader;
import utilities.IDFactory;
import entity.Course;

/**
 *  Saves the course to its file.
 *  @author Alex Titus
 */
public class SaveCourseDataController implements ActionListener
{
	private Course course;
	
	/**
	 *  Constructor.
	 *  
	 *  @param course  The course to save, must have a valid owner
	 */
	public SaveCourseDataController(Course course)
	{
		this.course = course;
	}

	/**
	 *  Saves the course.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		save();
	}
	
	public void save()
	{
		try
		{
			// Get path to new course file
			String userDirPath = GradebookFileReader.gradebookDirectory + "u" + course.getOwner().getID() + "/";
			String courseFilePath = userDirPath + "course" + course.getID() + ".xml";
			CourseFileWriter writer = new CourseFileWriter(courseFilePath);
			
			// Write the new course
			writer.writeCourse(course);
			
			// Also save configurations
			ConfigFileWriter cwriter = new ConfigFileWriter(ConfigFileReader.configFileName);
			cwriter.writeConfig(IDFactory.getConfigs());
		}
		catch (CourseFileWriterException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		} catch (ConfigFileWriterException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}

}
