package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import entity.Course;
import entity.RealAssignment;
import entity.User;
import boundary.IGraderFrame;
import boundary.RemoveAssignmentView;

/**
 *  Removes an assignment from the course or displays an error message.
 *  @author Alex Titus
 */
public class RemoveAssignmentController implements ActionListener
{
	public static enum RemoveAssignmentProblem { NO_ERROR, NO_SELECTION }
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private RemoveAssignmentView assignmentsInfo;
	private User user;
	private Course course;
	
	public RemoveAssignmentController(IGraderFrame rootView, IGraderFrame parentView,
			RemoveAssignmentView sectionInfo, User user, Course course)
	{
		this.rootView = rootView;
		this.parentView = parentView;
		this.assignmentsInfo = sectionInfo;
		this.user = user;
		this.course = course;
	}

	/**
	 *  Removes an assignment from the course or displays an error message.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Validate information
		RemoveAssignmentProblem error = validateInformation();
		
		// Take action
		if(error == RemoveAssignmentProblem.NO_ERROR)
		{
			// Get assignment(s) to remove
			ArrayList<Long> assignmentsIDs = assignmentsInfo.getSelectedAssignmentsIDs();
			ArrayList<RealAssignment> assignmentsToRemove = findAssignments(assignmentsIDs, course.getTemplate());
			
			// Remove assignments
			for(RealAssignment ra: assignmentsToRemove)
			{
				course.removeAssignment(ra);
			}
			
			// Inform user
			assignmentsInfo.showSuccess();
			
			// Update parent
			parentView.update();
			parentView.display();
		}
		else
		{
			// Display error message
			assignmentsInfo.showError(error);
		}
		
		// Update display
		rootView.update();
		rootView.display();
	}
	
	private RemoveAssignmentProblem validateInformation()
	{
		// Check that at least one selection was made
		if(assignmentsInfo.getSelectedAssignmentsIDs().size() == 0)
		{
			// No selection
			return RemoveAssignmentProblem.NO_SELECTION;
		}
		
		// If we get here, no errors
		return RemoveAssignmentProblem.NO_ERROR;
	}
	
	private ArrayList<RealAssignment> findAssignments(ArrayList<Long> ids, RealAssignment assignment)
	{
		ArrayList<RealAssignment> assignments = new ArrayList<RealAssignment>(ids.size());
		
		// Find assignments for each id selected
		for(long id : ids)
		{
			// Search assignments list for assignment
			for(int i = 0; i < assignment.getNumSubAssignments(); i++)
			{
				// Can cast because checked for NullAssignment above (numSubAssignments!=0)
				RealAssignment ra = (RealAssignment) assignment.getSubAssignment(i);
				if(ra.getID() == id)
				{
					assignments.add(ra);
				}
				
				// Recursively check sub-assignments
				ArrayList<RealAssignment> saSelectedAssignments = findAssignments(ids, ra);
				
				// Combine lists
				assignments.addAll(saSelectedAssignments);
			}
		}
		
		return assignments;
	}

}
