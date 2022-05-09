package boundary;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import entity.Entry;
import entity.Grade;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

/**
 *  A subpanel representing a single row in the grades table.
 *  
 *  @author Alex Titus
 */
public class EntryView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = -3256838247108378657L;
	private Entry entry;
	private ArrayList<Integer> columnWidths;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The overall window frame for the application
	 *  @param entry  The entry to represent
	 */
	public EntryView(Entry entry, ArrayList<Integer> columnWidths)
	{
		super();
		this.entry = entry;
		this.columnWidths = columnWidths;
		setupPanel();
	}
	
	private void setupPanel()
	{
		GridBagLayout layout = new GridBagLayout();
//		layout.columnWidths = convertToArray(columnSizes);
		layout.columnWidths = calculateColumnWidths(columnWidths);
		layout.columnWeights = calculateColumnWeights();
		setLayout(layout);
		int i = 0;
		
		JLabel sectionLabel = new JLabel(entry.getSection().getCode());
		sectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		sectionLabel.setSize(columnSizes.get(i).width, sectionLabel.getSize().height);
		i++;
		GridBagConstraints gbc_sectionLabel = new GridBagConstraints();
		gbc_sectionLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_sectionLabel.anchor = GridBagConstraints.WEST;
		gbc_sectionLabel.insets = new Insets(0, 5, 0, 5);
		gbc_sectionLabel.gridx = 0;
		gbc_sectionLabel.gridy = 0;
		add(sectionLabel, gbc_sectionLabel);
		
		JLabel studentNameLabel = new JLabel(entry.getStudent().getFName() + " " + entry.getStudent().getLName());
		studentNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		studentNameLabel.setSize(columnSizes.get(i).width, studentNameLabel.getSize().height);
		i++;
		GridBagConstraints gbc_studentNameLabel = new GridBagConstraints();
		gbc_studentNameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_studentNameLabel.anchor = GridBagConstraints.WEST;
		gbc_studentNameLabel.insets = new Insets(0, 0, 0, 5);
		gbc_studentNameLabel.gridx = 1;
		gbc_studentNameLabel.gridy = 0;
		add(studentNameLabel, gbc_studentNameLabel);
		
		JLabel idLabel = new JLabel(entry.getStudent().getBUID());
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		idLabel.setSize(columnSizes.get(i).width, idLabel.getSize().height);
		i++;
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_idLabel.anchor = GridBagConstraints.WEST;
		gbc_idLabel.insets = new Insets(0, 0, 0, 5);
		gbc_idLabel.gridx = 2;
		gbc_idLabel.gridy = 0;
		add(idLabel, gbc_idLabel);
		
		// Print all assignment grades
		GridBagConstraints gbc_assignmentGradeLabel = new GridBagConstraints();
		boolean haveAssignments = entry.getFinalGrade().getNumSubAssignments() != 0;
		if(haveAssignments)  // Skip this if there are no assignments yet
		{
			gbc_assignmentGradeLabel.anchor = GridBagConstraints.WEST;
			gbc_assignmentGradeLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_assignmentGradeLabel.insets = new Insets(0, 0, 0, 5);
			gbc_assignmentGradeLabel.gridx = 3;
			gbc_assignmentGradeLabel.gridy = 0;
			for(Grade g: entry.getFinalGrade().getFlattenedSubAssignmentTreeGrades())
			{
				String commentMark = g.getComment().isEmpty() ? "" : "*";  // Note if a comment is present
				JLabel assignmentGradeLabel = new JLabel(String.format("%.00f", g.getScore()) + commentMark);
				assignmentGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//				assignmentGradeLabel.setSize(columnSizes.get(i).width, assignmentGradeLabel.getSize().height);
				i++;
				add(assignmentGradeLabel, gbc_assignmentGradeLabel);
				gbc_assignmentGradeLabel.gridx += 1;
			}
		}
		
		// Print final grade
		JLabel finalGradeLabel = new JLabel(
				String.format("%.00f",
						entry.getFinalGrade().getGrade().getScore()));
		finalGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		finalGradeLabel.setSize(columnSizes.get(i).width, finalGradeLabel.getSize().height);
		GridBagConstraints gbc_finalGradeLabel = new GridBagConstraints();
		gbc_finalGradeLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_finalGradeLabel.anchor = GridBagConstraints.WEST;
		gbc_finalGradeLabel.insets = new Insets(0, 0, 0, 5);
		// Add one to leave gap for the add assignments button in header
		gbc_finalGradeLabel.gridx = haveAssignments ? gbc_assignmentGradeLabel.gridx+1 : 4+1;
		gbc_finalGradeLabel.gridy = 0;
		add(finalGradeLabel, gbc_finalGradeLabel);
	}
	
	private int[] convertToArray(ArrayList<Integer> list)
	{
		int[] result = new int[list.size()];
		
		for(int i = 0; i < list.size(); i++)
		{
			result[i] = list.get(i);
		}
		
		return result;
	}
	
	private int[] calculateColumnWidths(ArrayList<Integer> list)
	{
		int numColumns = entry.getFinalGrade().getNumLeaves()+5+1;
		int[] widths = new int[numColumns];
		widths[0] = 60;  // Section
		widths[1] = 120;  // Student
		widths[2] = 85;  // BUID
		for(int i = 3; i < numColumns-2; i++)  // Assignments
		{
			widths[i] = list.get(i);
		}
		widths[numColumns-3] = 100;  // Add assignment button
		widths[numColumns-2] = 75;  // Final Grade
		widths[numColumns-1] = 0;  // Obligatory unused final column
		
		return widths;
	}
	
	private double[] calculateColumnWeights()
	{
		int numColumns = entry.getFinalGrade().getNumLeaves()+5+1;
		double[] widths = new double[numColumns];
		widths[0] = 0.1;  // Section
		widths[1] = 0.2;  // Student
		widths[2] = 0.1;  // BUID
		for(int i = 3; i < numColumns-2; i++)  // Assignments
		{
			widths[i] = 0.15;
		}
		widths[numColumns-3] = 0.2;  // Add assignment button
		widths[numColumns-2] = 0.1;  // Final Grade
		widths[numColumns-1] = Double.MIN_VALUE;  // Obligatory unused final column
		
		return widths;
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
		setupPanel();
	}
	
	public Entry getEntry()
	{
		return entry;
	}

}
