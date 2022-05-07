package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import entity.Entry;
import entity.RealAssignment;
import entity.StudentStatus;
import boundary.CourseInfoPanelView;
import boundary.IGraderFrame;

/**
 *  Updates the entry's information from the provided.
 *  @author Alex Titus
 */
public class UpdateEntryController implements ActionListener
{
	private IGraderFrame rootView;
	private CourseInfoPanelView entryInfo;
	private ArrayList<String> scores;
	private int scoresIndex;
	private ArrayList<String> comments;
	private int commentsIndex;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param entryInfo  The panel with the new entry information
	 */
	public UpdateEntryController(IGraderFrame rootView, CourseInfoPanelView entryInfo)
	{
		this.rootView = rootView;
		this.entryInfo = entryInfo;
		scores = entryInfo.getGradesList();
		comments = entryInfo.getCommentsList();
		scoresIndex = 0;
		commentsIndex = 0;
	}

	/**
	 *  Updates the information of the entry in entryInfo.
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Get entry
		Entry e = entryInfo.getEntry();
		
		// Update entry info
		updateEntry(e);
		
		// Refresh the display
		rootView.update();
		rootView.display();
	}
	
	private void updateEntry(Entry e)
	{
		// Update first name
		String newFirstName = entryInfo.getEnteredFName();
		e.getStudent().setFName(newFirstName);
		
		// Update last name
		String newLastName = entryInfo.getEnteredLName();
		e.getStudent().setLName(newLastName);

		// Update BUID
		String newBUID = entryInfo.getEnteredBUID();
		e.getStudent().setNewBUID(newBUID);
		
		// Update status
		StudentStatus newStatus = entryInfo.getSelectedStatus();
		e.getStudent().setEnrollmentStatus(newStatus);
		
		// Update grades
		RealAssignment finalGrade = e.getFinalGrade();
		scoresIndex = 0;  // Reset from last time
		commentsIndex = 0;  // Reset from last time
		// Go through tree of sub-assignments and update leaf node's grade scores and comments
		for(int i = 0; i < finalGrade.getNumSubAssignments(); i++)
		{
			// Create parent label
			// Can cast because checked above (numSubAssignments!=0)
			RealAssignment a = (RealAssignment) finalGrade.getSubAssignment(i);
			
			// Recursively update grade score and comment for all sub-assignments
			updateSubAssignments(a);
		}
		// Update all scores based on new leaf node scores
		finalGrade.getWeightedGrade();
	}
	
	private void updateSubAssignments(RealAssignment a)
	{
		// Check if we've reached the bottom of the tree
		if(a.getNumSubAssignments() == 0)
		{
			// Update the NullAssigment's grade that takes care of this RealAssignment
			// Update Grade Score
			float score = new Float(scores.get(scoresIndex));
			scoresIndex++;
			a.getSubAssignment(0).getGrade().setScore(score);
			
			// Comments
			String comment = comments.get(commentsIndex);
			commentsIndex++;
			a.getSubAssignment(0).getGrade().setComment(comment);
		}
		else  // Have sub-assignments
		{
			// Update each sub-assignment
			for(int i = 0; i < a.getNumSubAssignments(); i++)
			{
				// Create parent label
				// Can cast because checked for sub-assignments above
				RealAssignment sa = (RealAssignment) a.getSubAssignment(i);

				// Recursively update grade score and comment for all sub-assignments
				updateSubAssignments(sa);
			}
		}
	}

}
