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
		setPreferredSize(new Dimension(1000, 20));
		int i = 0;
		
		JLabel sectionLabel = new JLabel(entry.getSection().getCode());
		sectionLabel.setBounds(5, 5, 60, 15);
		sectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		i++;
		setLayout(null);
		add(sectionLabel);
		
		JLabel idLabel = new JLabel(entry.getStudent().getBUID());
		idLabel.setBounds(70, 5, 80, 15);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		i++;
		add(idLabel);
		
		JLabel studentNameLabel = new JLabel(entry.getStudent().getFName() + " " + entry.getStudent().getLName());
		studentNameLabel.setBounds(155, 5, 150, 15);
		studentNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		i++;
		add(studentNameLabel);
		
		// Print all assignment grades
		GridBagConstraints gbc_assignmentGradeLabel = new GridBagConstraints();
		boolean haveAssignments = entry.getFinalGrade().getNumSubAssignments() != 0;
		int j = 0;
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
				assignmentGradeLabel.setBounds(375+(j*205), 5, 100, 15);
				i++;
				add(assignmentGradeLabel, gbc_assignmentGradeLabel);
				gbc_assignmentGradeLabel.gridx += 1;
				j++;
			}
		}
		
		// Print final grade
		JLabel finalGradeLabel = new JLabel(
				String.format("%.00f",
						entry.getFinalGrade().getGrade().getScore()));
		finalGradeLabel.setBounds(580, 5, 30, 15);
		finalGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(finalGradeLabel);
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
		widths[0] = 50;  // Section
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
		widths[0] = 0.08;  // Section
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
