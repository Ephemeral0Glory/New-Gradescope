package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Scrollable;

import entity.Course;
import entity.RealAssignment;

/**
 *  Subpanel for {@link RemoveAssignmentView}. Contains the assignments which
 *  can be selected for deletion.
 *  @author Alex Titus
 */
public class RemoveAssignmentsSubPanelView extends JPanel implements IGraderScreen, Scrollable
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
		setLayout(new GridBagLayout());
		Font panelFont = new Font("Tahoma", Font.PLAIN, 16);
		RealAssignment template = course.getTemplate();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		for(int i = 0; i < template.getNumSubAssignments(); i++)
		{
			// Create parent label
			// Can cast because checked for NullAssignment above (numSubAssignments!=0)
			RealAssignment a = (RealAssignment) template.getSubAssignment(i);
			JLabel assignmentLabel = new JLabel(a.getName() + " (" + convert(a.getWeight()) + "%)");
			assignmentLabel.setFont(panelFont);
			gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(0, 5, 5, 5);
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
		Font panelFont = new Font("Tahoma", Font.PLAIN, 16);
		// Check if we've reached the bottom of the tree
		if(a.getNumSubAssignments() == 0)
		{
			// Place this assignment's editable text fields on the same row as the label
			gbc.gridy -= 1;
			
			// Grade
			JCheckBox assignmentSelector = new JCheckBox("Remove " + a.getName());
			assignmentSelector.setFont(panelFont);
			assignmentSelector.setActionCommand(a.getID()+"");
			gbc.insets = new Insets(0, 0, 5, 5);
			gbc.anchor = GridBagConstraints.WEST;
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
				JLabel assignmentLabel = new JLabel(sa.getName()+ " (" + convert(sa.getWeight()) + ")%");
				assignmentLabel.setFont(panelFont);
				gbc.anchor = GridBagConstraints.EAST;
				gbc.insets = new Insets(0, 0, 5, 5);
				add(assignmentLabel, gbc);
				gbc.gridx += 1;
				gbc.gridy += 1;

				// Recursively add labels and grade/comment input fields for all sub-assignments
				labelSubAssignments(sa, gbc);
				gbc.gridx = startingX;
			}
		}
	}
	
	private String convert(float weight)
	{
		float percentageWeight = weight * 100;
		return String.format("%.00f", percentageWeight);
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

	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		return null;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2)
	{
		return 50;
	}

	@Override
	public boolean getScrollableTracksViewportHeight()
	{
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth()
	{
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2)
	{
		return 10;
	}

}
