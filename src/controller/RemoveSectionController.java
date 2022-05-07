package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.Section;
import entity.User;
import boundary.IGraderFrame;
import boundary.RemoveSectionView;

/**
 *  Removes a section from the course or displays an error message.
 *  @author Alex Titus
 */
public class RemoveSectionController implements ActionListener
{
	public static enum RemoveSectionProblem { NO_ERROR, NO_SELECTION }
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private RemoveSectionView sectionInfo;
	private User user;
	private Course course;
	
	public RemoveSectionController(IGraderFrame rootView, IGraderFrame parentView,
			RemoveSectionView sectionInfo, User user, Course course)
	{
		this.rootView = rootView;
		this.parentView = parentView;
		this.sectionInfo = sectionInfo;
		this.user = user;
		this.course = course;
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
		RemoveSectionProblem error = validateInformation();
		
		// Take action
		if(error == RemoveSectionProblem.NO_ERROR)
		{
			// Get section to remove
			Section s = sectionInfo.getSelectedSection();
			
			// Remove section
			course.removeSection(s);
			
			// Inform user
			sectionInfo.showSuccess();
			
			// Update parent
			parentView.update();
			parentView.display();
		}
		else
		{
			// Display error message
			sectionInfo.showError(error);
		}
		
		// Update display
		rootView.update();
		rootView.display();
	}
	
	private RemoveSectionProblem validateInformation()
	{
		// Check that a selection was made
		if(sectionInfo.getSelectedSectionIndex() == -1)
		{
			// No selection
			return RemoveSectionProblem.NO_SELECTION;
		}
		
		// If we get here, no errors
		return RemoveSectionProblem.NO_ERROR;
	}

}
