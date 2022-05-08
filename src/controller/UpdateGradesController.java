package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import entity.RealAssignment;
import boundary.CourseInfoPanelView;
import boundary.IGraderFrame;

/**
 *  Updates each grade according the information provided.
 *  @author Alex Titus
 */
public class UpdateGradesController implements ActionListener
{
	private IGraderFrame rootView;
	private CourseInfoPanelView gradesInfo;
	private ArrayList<String> scores;
	private ArrayList<String> comments;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param gradesInfo  The screen with the grades information
	 */
	public UpdateGradesController(IGraderFrame rootView, CourseInfoPanelView gradesInfo)
	{
		this.rootView = rootView;
		this.gradesInfo = gradesInfo;
	}

	/**
	 *  Updates each grade with the information in gradesInfo.
	 *  
	 *  @param e  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Get grades to update
		ArrayList<RealAssignment> assignments = gradesInfo.getColumn();
		
		// Get grade information
		scores = gradesInfo.getGradesList();
		comments = gradesInfo.getCommentsList();
		
		// Update grades
		updateGrades(assignments);
		
		// Update display
		rootView.update();
		rootView.display();
	}
	
	private void updateGrades(ArrayList<RealAssignment> assignments)
	{
		// This assumes we can only set grades for leaf nodes
		for(int i = 0; i < assignments.size(); i++)
		{
			// Get assignment
			RealAssignment ra = assignments.get(i);
			
			// Update score of assignment's NullAssignment
			ra.getSubAssignment(0).getGrade().setScore(new Float(scores.get(i)));
			
			// Update comment for both the NullAssignment and this assignment
			ra.getGrade().setComment(comments.get(i));
			ra.getSubAssignment(0).getGrade().setComment(comments.get(i));
		}
	}

}
