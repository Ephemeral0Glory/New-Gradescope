package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Section;
import entity.Student;
import entity.User;
import boundary.IGraderFrame;
import boundary.RemoveStudentView;

/**
 *  Removes a student from the course or displays an error message.
 *  @author Alex Titus
 */
public class RemoveStudentController implements ActionListener
{
	public static enum RemoveStudentProblem { NO_ERROR, NO_SECTION_SELECTION,
		NO_STUDENT_SELECTION }
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private RemoveStudentView studentInfo;
	private User user;
	
	public RemoveStudentController(IGraderFrame rootView, IGraderFrame parentView,
			RemoveStudentView studentInfo, User user)
	{
		this.rootView = rootView;
		this.parentView = parentView;
		this.studentInfo = studentInfo;
		this.user = user;
	}

	/**
	 *  Removes a section from the course or displays an error message.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Validate information
		RemoveStudentProblem error = validateInformation();
		
		// Take action
		if(error == RemoveStudentProblem.NO_ERROR)
		{
			// Get student to remove
			Student st = studentInfo.getSelectedStudent();
			
			// Get section to remove from
			Section s = studentInfo.getSelectedSection();
			
			// Remove student from section
			s.removeStudent(st);

			// update list
			studentInfo.updateStudentListing();

			// Inform user
			studentInfo.showSuccess();
			
			// Update parent
			parentView.update();
			parentView.display();
		}
		else
		{
			// Display error message
			studentInfo.showError(error);
		}
		
		// Update display
		rootView.update();
		rootView.display();
	}
	
	private RemoveStudentProblem validateInformation()
	{
		// Check that a selection was made
		if(studentInfo.getSelectedSectionIndex() == -1)
		{
			// No section selection
			return RemoveStudentProblem.NO_SECTION_SELECTION;
		}
		if(studentInfo.getSelectedStudentIndex() == -1)
		{
			// No student selection
			return RemoveStudentProblem.NO_STUDENT_SELECTION;
		}
		
		// If we get here, no errors
		return RemoveStudentProblem.NO_ERROR;
	}

}
