package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.AddEntryView;
import boundary.IGraderFrame;
import entity.Course;
import entity.Entry;
import entity.Student;
import entity.StudentStatus;
import entity.User;

/**
 *  Opens the add entry popup window.
 *  @author Alex Titus
 */
public class AddEntryController implements ActionListener
{
	/**
	 *  Indicates one of the following errors in entry creation:
	 *  <ul><li> EMPTY_SECTION: section was not selected </li>
	 *  <li> EMPTY_STUDENT: not creating a new student and student was not selected </li>
	 *  <li> DUPLICATED_STUDENT: selected student already has an entry or created
	 *   student's BUID is the same as an existing student's BUID in this course </li>
	 *  <li> EMPTY_STUDENT_FNAME: creating a new student and the first name is empty </li>
	 *  <li> EMPTY_STUDENT_LNAME: creating a new student and the last name is empty </li>
	 *  <li> EMPTY_STUDENT_BUID: creating a new student and the BUID is empty </li>
	 */
	public static enum EntryProblem { NO_ERROR, EMPTY_SECTION, EMPTY_STUDENT, DUPLICATED_STUDENT,
		EMPTY_STUDENT_FNAME, EMPTY_STUDENT_LNAME, EMPTY_STUDENT_BUID }
	private IGraderFrame rootView;
	private User user;
	private Course course;
	private AddEntryView entryInfo;
	
	public AddEntryController(IGraderFrame rootView, User user, Course course, AddEntryView entryInfo)
	{
		this.rootView = rootView;
		this.user = user;
		this.course = course;
		this.entryInfo = entryInfo;
	}

	/**
	 *  Validates the information entered and creates the new entry.
	 *  <p>
	 *  If the entered information fails validation, displays the error
	 *  on the add entry screen. Closes the window after success.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Validate information
		EntryProblem error = validateInformation();
		
		// Take action
		if(error == EntryProblem.NO_ERROR)
		{
			// If creating new student
			Student s;
			if(entryInfo.creatingNewStudent())
			{
				s = createNewStudent();
			}
			else  // Selected one
			{
				s = entryInfo.getSelectedStudent();
			}
			
			// Create entry
			Entry e = new Entry(entryInfo.getSelectedSection(), s,
					course.getTemplate());
			
			// Add entry
			course.addEntry(e);
			
			// Tell user
			entryInfo.showSuccess();
		}
		else
		{
			// Inform user of error
			entryInfo.showError(error);
		}
		
		// Update display
		rootView.update();
		rootView.display();
	}
	
	private EntryProblem validateInformation()
	{
		// Blank strings check
		if(entryInfo.getSelectedSection() == null)
		{
			return EntryProblem.EMPTY_SECTION;
		}
		if(!entryInfo.creatingNewStudent() && entryInfo.getSelectedStudent() == null)
		{
			return EntryProblem.EMPTY_STUDENT;
		}
		if(entryInfo.creatingNewStudent())
		{
			if(entryInfo.getEnteredBUID().isEmpty())
			{
				return EntryProblem.EMPTY_STUDENT_BUID;
			}
			else if(entryInfo.getEnteredFirstName().isEmpty())
			{
				return EntryProblem.EMPTY_STUDENT_FNAME;
			}
			else if(entryInfo.getEnteredLastName().isEmpty())
			{
				return EntryProblem.EMPTY_STUDENT_LNAME;
			}
		}
		
		// Duplicated student checks
		if(course.hasEntry(entryInfo.getSelectedStudent()))
		{
			return EntryProblem.DUPLICATED_STUDENT;
		}
		if(course.hasEntryBUID(entryInfo.getEnteredBUID()))
		{
			return EntryProblem.DUPLICATED_STUDENT;
		}
		
		// Otherwise, no problem
		return EntryProblem.NO_ERROR;
	}
	
	private Student createNewStudent()
	{
		String firstName = entryInfo.getEnteredFirstName();
		String lastName = entryInfo.getEnteredLastName();
		String buid = entryInfo.getEnteredBUID();
		StudentStatus status = entryInfo.getSelectedStatus();
		return new Student(firstName, lastName, buid, status);
	}

}
