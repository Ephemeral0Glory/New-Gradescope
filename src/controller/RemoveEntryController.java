package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.Entry;
import entity.Section;
import entity.Student;
import entity.User;
import boundary.IGraderFrame;
import boundary.RemoveEntryView;
import boundary.RemoveStudentView;

/**
 *  Removes a student from the course or displays an error message.
 *  @author Seonghoon Cho
 */
public class RemoveEntryController implements ActionListener
{
	public static enum RemoveEntryProblem { NO_ERROR, NO_SECTION_SELECTION,
		NO_ENTRY_SELECTION }
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private RemoveEntryView entryInfo;
	private User user;
	
	public RemoveEntryController(IGraderFrame rootView, IGraderFrame parentView,
			RemoveEntryView entryInfo, User user)
	{
		this.rootView = rootView;
		this.parentView = parentView;
		this.entryInfo = entryInfo;
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
		RemoveEntryProblem error = validateInformation();
		
		// Take action
		if(error == RemoveEntryProblem.NO_ERROR)
		{
			// Get student to remove
			Entry e = entryInfo.getSelectedEntry();
			
			// Get section to remove from
//			Section s = entryInfo.getSelectedSection();
			Course course = entryInfo.getCourse();
			
			// Remove student from section
//			s.removeStudent(e);
			// Remove entry from course
			course.removeEntry(e);
			
			// Inform user
			entryInfo.showSuccess();
			
			// Update parent
			parentView.update();
			parentView.display();
		}
		else
		{
			// Display error message
			entryInfo.showError(error);
		}
		
		// Update display
		rootView.update();
		rootView.display();
	}
	
	private RemoveEntryProblem validateInformation()
	{
		// Check that a selection was made
//		if(entryInfo.getSelectedSectionIndex() == -1)
//		{
//			// No section selection
//			return RemoveEntryProblem.NO_SECTION_SELECTION;
//		}
		if(entryInfo.getSelectedEntryIndex() == -1)
		{
			// No student selection
			return RemoveEntryProblem.NO_ENTRY_SELECTION;
		}
		
		// If we get here, no errors
		return RemoveEntryProblem.NO_ERROR;
	}

}
