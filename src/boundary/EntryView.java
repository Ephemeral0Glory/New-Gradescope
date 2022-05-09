package boundary;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import entity.Entry;
import entity.Grade;

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

/**
 *  A subpanel representing a single row in the grades table.
 *  
 *  @author Alex Titus
 */
public class EntryView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = -3256838247108378657L;
	private Entry entry;
	private int[] columnWidths;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The overall window frame for the application
	 *  @param entry  The entry to represent
	 */
	public EntryView(Entry entry, int[] columnWidths)
	{
		super();
		this.entry = entry;
		this.columnWidths = columnWidths;
		setupPanel();
	}
	
	private void setupPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = columnWidths;
		gridBagLayout.rowHeights = new int[]{15, 0};
		setLayout(gridBagLayout);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		JLabel sectionLabel = new JLabel(entry.getSection().getCode());
		sectionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		sectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_sectionLabel = new GridBagConstraints();
		gbc_sectionLabel.anchor = GridBagConstraints.WEST;
		gbc_sectionLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_sectionLabel.insets = new Insets(0, 5, 0, 5);
		gbc_sectionLabel.gridx = 0;
		gbc_sectionLabel.gridy = 0;
		add(sectionLabel, gbc_sectionLabel);
		
		JLabel idLabel = new JLabel(entry.getStudent().getBUID());
		idLabel.setHorizontalAlignment(SwingConstants.LEFT);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.anchor = GridBagConstraints.WEST;
		gbc_idLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_idLabel.insets = new Insets(0, 5, 0, 5);
		gbc_idLabel.gridx = 1;
		gbc_idLabel.gridy = 0;
		add(idLabel, gbc_idLabel);
		
		JLabel studentNameLabel = new JLabel(entry.getStudent().getFName() + " " + entry.getStudent().getLName());
		studentNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		studentNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_studentNameLabel = new GridBagConstraints();
		gbc_studentNameLabel.anchor = GridBagConstraints.WEST;
		gbc_studentNameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_studentNameLabel.insets = new Insets(0, 5, 0, 5);
		gbc_studentNameLabel.gridx = 2;
		gbc_studentNameLabel.gridy = 0;
		add(studentNameLabel, gbc_studentNameLabel);
		
		// Print all assignment grades
		GridBagConstraints gbc_assignmentGradeLabel = new GridBagConstraints();
		boolean haveAssignments = entry.getFinalGrade().getNumSubAssignments() != 0;
		int x = 3;
		if(haveAssignments)  // Skip this if there are no assignments yet
		{
			gbc_assignmentGradeLabel.anchor = GridBagConstraints.WEST;
			gbc_assignmentGradeLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_assignmentGradeLabel.insets = new Insets(0, 5, 0, 5);
			gbc_assignmentGradeLabel.gridx = 3;
			gbc_assignmentGradeLabel.gridy = 0;
			for(Grade g: entry.getFinalGrade().getFlattenedSubAssignmentTreeGrades())
			{
				String commentMark = g.getComment().isEmpty() ? "" : "*";  // Note if a comment is present
				JLabel assignmentGradeLabel = new JLabel(String.format("%.00f", g.getScore()) + commentMark);
				assignmentGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
				assignmentGradeLabel.setHorizontalAlignment(SwingConstants.LEADING);
				gbc_assignmentGradeLabel.gridx = x;
				add(assignmentGradeLabel, gbc_assignmentGradeLabel);
				x++;
				gbc_assignmentGradeLabel.gridx += 1;
			}
		}
		
		// Print final grade
		JLabel finalGradeLabel = new JLabel(
				String.format("%.00f",
						entry.getFinalGrade().getGrade().getScore()));
		finalGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		finalGradeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_finalGradeLabel = new GridBagConstraints();
		gbc_finalGradeLabel.anchor = GridBagConstraints.EAST;
		gbc_finalGradeLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_finalGradeLabel.insets = new Insets(0, 5, 0, 10);
		gbc_finalGradeLabel.gridx = x+2;
		gbc_finalGradeLabel.gridy = 0;
		add(finalGradeLabel, gbc_finalGradeLabel);
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
