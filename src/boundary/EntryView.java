package boundary;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import entity.Entry;
import entity.Grade;

import javax.swing.JLabel;
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
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The overall window frame for the application
	 *  @param entry  The entry to represent
	 */
	public EntryView(Entry entry)
	{
		super();
		this.entry = entry;
		setupPanel();
	}
	
	private void setupPanel()
	{
		setLayout(new GridBagLayout());
		
		JLabel sectionLabel = new JLabel(entry.getSection().getName());
		sectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_sectionLabel = new GridBagConstraints();
		gbc_sectionLabel.insets = new Insets(0, 0, 0, 5);
		gbc_sectionLabel.gridx = 0;
		gbc_sectionLabel.gridy = 0;
		add(sectionLabel, gbc_sectionLabel);
		
		JLabel studentNameLabel = new JLabel(entry.getStudent().getFName() + " " + entry.getStudent().getLName());
		GridBagConstraints gbc_studentNameLabel = new GridBagConstraints();
		gbc_studentNameLabel.insets = new Insets(0, 0, 0, 5);
		gbc_studentNameLabel.gridx = 1;
		gbc_studentNameLabel.gridy = 0;
		add(studentNameLabel, gbc_studentNameLabel);
		
		JLabel idLabel = new JLabel(entry.getStudent().getBUID());
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 0, 5);
		gbc_idLabel.gridx = 2;
		gbc_idLabel.gridy = 0;
		add(idLabel, gbc_idLabel);
		
		// Print all assignment grades
		GridBagConstraints gbc_assignmentGradeLabel = new GridBagConstraints();
		gbc_assignmentGradeLabel.insets = new Insets(0, 0, 0, 5);
		gbc_assignmentGradeLabel.gridx = 3;
		gbc_assignmentGradeLabel.gridy = 0;
		for(Grade g: entry.getFinalGrade().getFlattenedSubAssignmentTreeGrades())
		{
			String commentMark = g.getComment().isEmpty() ? "" : "*";  // Note if a comment is present
			JLabel assignmentGradeLabel = new JLabel(g.getScore() + commentMark);
			assignmentGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			add(assignmentGradeLabel, gbc_assignmentGradeLabel);
			gbc_assignmentGradeLabel.gridx += 1;
		}
		
		// Print final grade
		JLabel finalGradeLabel = new JLabel(entry.getFinalGrade().getGrade().getScore() + "");
		finalGradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_finalGradeLabel = new GridBagConstraints();
		gbc_finalGradeLabel.gridx = gbc_assignmentGradeLabel.gridx;
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
