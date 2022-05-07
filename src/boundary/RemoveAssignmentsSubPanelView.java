package boundary;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Course;
import entity.RealAssignment;

/**
 *  Subpanel for {@link RemoveAssignmentView}. Contains the assignments which
 *  can be selected for deletion.
 *  @author Alex Titus
 */
public class RemoveAssignmentsSubPanelView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = 79793323660856663L;
	private Course course;
	private ArrayList<JCheckBox> assignmentSelections;
	
	/**
	 *  Constructor.
	 *  
	 *  @param course  The course whose assignments are to be displayed
	 */
	public RemoveAssignmentsSubPanelView(Course course)
	{
		this.course = course;
		assignmentSelections = new ArrayList<JCheckBox>();
		setupPanel();
	}
	
	private void setupPanel()
	{
		Font panelFont = new Font("Tahoma", Font.PLAIN, 12);
		RealAssignment template = course.getTemplate();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		for(int i = 0; i < template.getNumSubAssignments(); i++)
		{
			// Create parent label
			// Can cast because checked for NullAssignment above (numSubAssignments!=0)
			RealAssignment a = (RealAssignment) template.getSubAssignment(i);
			JLabel assignmentLabel = new JLabel(a.getName() + " " + a.getWeight() + "%");
			assignmentLabel.setFont(panelFont);
			gbc.anchor = GridBagConstraints.EAST;
			add(assignmentLabel, gbc);
			gbc.gridx += 1;
			gbc.gridy += 1;

			// Recursively add labels and grade/comment input fields for all sub-assignments
			labelSubAssignments(a, gbc);
			gbc.gridx = 0;
		}
	}
	
	private void labelSubAssignments(RealAssignment a, GridBagConstraints gbc)
	{
		Font panelFont = new Font("Tahoma", Font.PLAIN, 12);
		// Check if we've reached the bottom of the tree
		if(a.getNumSubAssignments() == 0)
		{
			// Place this assignment's editable text fields on the same row as the label
			gbc.gridy -= 1;
			
			// Grade
			JCheckBox assignmentSelector = new JCheckBox("Remove " + a.getName());
			assignmentSelector.setFont(panelFont);
			assignmentSelector.setActionCommand(a.getID()+"");
			gbc.anchor = GridBagConstraints.EAST;
			add(assignmentSelector, gbc);
			assignmentSelections.add(assignmentSelector);
			gbc.gridx += 1;
			
			// Set for next call
			gbc.gridy += 1;
			gbc.gridx -= 1;
		}
		else  // Have sub-assignments
		{
			int startingX = gbc.gridx;
			// Label each sub-assignment
			for(int i = 0; i < a.getNumSubAssignments(); i++)
			{
				// Create parent label
				// Can cast because checked for sub-assignments above
				RealAssignment sa = (RealAssignment) a.getSubAssignment(i);
				JLabel assignmentLabel = new JLabel(sa.getName()+ " " + sa.getWeight() + "%");
				assignmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				gbc.anchor = GridBagConstraints.EAST;
				add(assignmentLabel, gbc);
				gbc.gridx += 1;
				gbc.gridy += 1;

				// Recursively add labels and grade/comment input fields for all sub-assignments
				labelSubAssignments(sa, gbc);
				gbc.gridx = startingX;
			}
		}
	}

	@Override
	public JPanel getPanelContent()
	{
		return this;
	}

	@Override
	public void update()
	{
		removeAll();
		assignmentSelections = new ArrayList<JCheckBox>();
		setupPanel();
	}
	
	public ArrayList<Long> getSelectedAssignmentsIDs()
	{
		ArrayList<Long> selectedAssignments = new ArrayList<Long>();
		
		for(JCheckBox selection: assignmentSelections)
		{
			// If user marked this assignment for deletion
			if(selection.isSelected())
			{
				// Find assignment to add to list
				long selectedAssignmentID = new Long(selection.getActionCommand());
				selectedAssignments.add(selectedAssignmentID);
			}
		}
		
		return selectedAssignments;
	}

}
