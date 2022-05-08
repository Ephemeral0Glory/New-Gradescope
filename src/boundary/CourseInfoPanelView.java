package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Scrollable;

import controller.DeleteAssignmentController;
import controller.DeleteEntryController;
import controller.UpdateEntryController;
import controller.UpdateGradesController;
import entity.Entry;
import entity.RealAssignment;
import entity.StudentStatus;

/**
 *  Contains detailed information for a row or column in the entry grades table.
 *  <p>
 *  Depending on what is displayed, this panel will contain either all of the details
 *  of an entry or for an assignment in editable form. Switch between the two modes with
 *  {@link CourseInfoPanelView.showEntry} or {@link CourseInfoPanelView.showColumn}.
 *  <p>
 *  The entry display mode shows and allows the user to edit a single student's
 *  first and last name, BUID, and status (@link StudentStatus}. After that it
 *  displays a list of assignments and sub-assignments with their respective grades.
 *  Sub-assignments are placed on the row under and one column to the right of their parent
 *  assignment. Parent assignments lose the ability to have their grades edited.
 *  At the bottom of the panel are two buttons. The first deletes the entry from the
 *  table and the second updates the entry with the information entered by the user.
 *  <p>
 *  The column display mode shows and allows the user to edit a single assignment's
 *  grades for all students. It displays each assignment as a row with the student's
 *  name, editable grade for that assignment, and an editable text area for comments.
 *  At the top and bottom of the panel are the same two buttons. The first deletes
 *  the assignment from the table and the second updates all of the assignment's grades
 *  comments with the information entered by the user.
 *  @author Alex Titus
 *
 */
public class CourseInfoPanelView extends JPanel implements IGraderScreen, Scrollable
{
	private static final long serialVersionUID = -1118231147797373119L;
	private IGraderFrame rootView;
	private Entry entry;
	private ArrayList<RealAssignment> column;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField buidField;
	private JComboBox<StudentStatus> statusSelector;
	private ArrayList<JTextField> gradesList;
	private ArrayList<JTextArea> commentsList;
	
	public CourseInfoPanelView(IGraderFrame rootView)
	{
		this.rootView = rootView;
	}
	
	public void showEntry(Entry entry)
	{
		this.entry = entry;
		this.column = null;  // Not displaying column
		// Clear and prepare lists
		this.gradesList = new ArrayList<JTextField>();
		this.commentsList = new ArrayList<JTextArea>();
		
		// Change this to display the entry
		this.removeAll();
		repaint();
		createInfoPanelRow(entry);
	}
	
	private void createInfoPanelRow(Entry entry)
	{
		// Set up panel
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Font panelFont = new Font("Tahoma", Font.PLAIN, 12);
		
		// Title
		JLabel title = new JLabel("Course information for student:");
		title.setFont(panelFont);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1.0;
		add(title, gbc);
		gbc.gridy += 1;
		gbc.gridwidth = 1;
		
		// Student information row
			// Name
		JLabel studentNameLabel = new JLabel("Student name:");
		studentNameLabel.setFont(panelFont);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.weightx = 0.4;
		add(studentNameLabel, gbc);
		gbc.gridx += 1;
		firstNameField = new JTextField(entry.getStudent().getFName());
		firstNameField.setFont(panelFont);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 0.3;
		add(firstNameField, gbc);
		gbc.gridx += 1;
		lastNameField = new JTextField(entry.getStudent().getLName());
		lastNameField.setFont(panelFont);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0.3;
		add(lastNameField, gbc);
		gbc.gridx += 1;
			// ID
		JLabel idLabel = new JLabel("BUID:");
		idLabel.setFont(panelFont);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 0.5;
		add(idLabel, gbc);
		gbc.gridx += 1;
		buidField = new JTextField(entry.getStudent().getBUID());
		buidField.setFont(panelFont);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0.5;
		add(buidField, gbc);
		gbc.gridx += 1;
			// Status
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setFont(panelFont);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 0.5;
		add(statusLabel, gbc);
		gbc.gridx += 1;
		statusSelector = new JComboBox<StudentStatus>(new DefaultComboBoxModel<StudentStatus>(StudentStatus.values()));
		statusSelector.setFont(panelFont);
		statusSelector.setSelectedItem(entry.getStudent().getEnrollmentStatus());
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 0.5;
		add(statusSelector, gbc);
		
		// Assignments
		gbc.gridx = 0;
		gbc.gridy += 1;
		gbc.weightx = 0.1;
		RealAssignment finalGrade = entry.getFinalGrade();
		for(int i = 0; i < finalGrade.getNumSubAssignments(); i++)
		{
			// Create parent label
			// Can cast because checked above (numSubAssignments!=0)
			RealAssignment a = (RealAssignment) finalGrade.getSubAssignment(i);
			JLabel assignmentLabel = new JLabel(a.getName() + " (" + convert(a.getWeight()) + "%)");
			assignmentLabel.setFont(panelFont);
			gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(0, 5, 5, 5);
			add(assignmentLabel, gbc);
			gbc.gridx += 1;
			gbc.gridy += 1;
			gbc.insets = new Insets(0, 0, 5, 5);
			
			// Recursively add labels and grade/comment input fields for all sub-assignments
			labelSubAssignments(a, gbc);
			gbc.gridx = 0;
		}
		
		// Buttons
		JButton deleteButton = new JButton("Delete Entry");
		deleteButton.setFont(panelFont);
		deleteButton.addActionListener(new DeleteEntryController(rootView, entry));
		gbc.gridx = 0;
		gbc.gridy += 1;
		gbc.anchor = GridBagConstraints.CENTER;
		add(deleteButton, gbc);
		
		JButton updateButton = new JButton("Update");
		updateButton.setFont(panelFont);
		updateButton.addActionListener(new UpdateEntryController(rootView, this));
		gbc.gridwidth = 2;
		gbc.gridx += 1;
		add(updateButton, gbc);
	}
	
	private String convert(float weight)
	{
		float percentageWeight = weight * 100;
		return String.format("%.00f", percentageWeight);
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
			JLabel gradeLabel = new JLabel("Grade:");
			gradeLabel.setFont(panelFont);
			gbc.anchor = GridBagConstraints.EAST;
			add(gradeLabel, gbc);
			gbc.gridx += 1;
			JTextField gradeField = new JTextField(a.getGrade().getScore() + "");
			gradeField.setFont(panelFont);
			gbc.anchor = GridBagConstraints.WEST;
			add(gradeField, gbc);
			gradesList.add(gradeField);
			gbc.gridx += 1;
			
			// Comments
			JLabel commentsLabel = new JLabel("Comments:");
			commentsLabel.setFont(panelFont);
			gbc.anchor = GridBagConstraints.EAST;
			add(commentsLabel, gbc);
			gbc.gridx += 1;
			JTextArea commentsArea = new JTextArea(a.getGrade().getComment(), 3, 20);
			commentsArea.setFont(panelFont);
			commentsArea.setEditable(true);
			commentsArea.setEnabled(true);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridheight = 2;
			gbc.gridwidth = 2;
			add(commentsArea, gbc);
			commentsList.add(commentsArea);
			
			// Set for next call
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.gridy += 2;
			gbc.gridx -= 3;
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
				JLabel assignmentLabel = new JLabel(sa.getName()+ " (" + convert(sa.getWeight()) + "%)");
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
	
	public void showColumn(ArrayList<RealAssignment> column)
	{
		this.column = column;
		this.entry = null;  // Not displaying entry
		// Clear and prepare lists
		gradesList = new ArrayList<JTextField>();
		commentsList = new ArrayList<JTextArea>();
		
		// Change this to display the column
		this.removeAll();
		repaint();
		createInfoPanelColumn(column);
	}
	
	private void createInfoPanelColumn(ArrayList<RealAssignment> column)
	{
		// Set up panel
		setLayout(new GridBagLayout());

		// Title
		String assignmentName = column.isEmpty() ? "No Assignment" : column.get(0).getName();
		JLabel title = new JLabel(assignmentName);
		title.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(title, gbc);

		// Fill panel
		int x = 0;
		int y = 1;
		Font tableFont = new Font("Tahoma", Font.PLAIN, 12);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridx = x;
		gbc.gridy = y;
		// This assumes that only columns of assignments with no sub-assignments
		// can be selected
		for(RealAssignment ra: column)
		{
			// Get name of student for assignment
			JLabel studentName = new JLabel(ra.getStudentName());
			studentName.setFont(tableFont);
			gbc.gridx = x;
			add(studentName, gbc);
			x++;  // Next column

			// Get assignment grade
			JLabel gradeLabel = new JLabel("Grade:");
			gradeLabel.setFont(tableFont);
			gbc.gridx = x;
			add(gradeLabel, gbc);
			x++;  // Next column
			JTextField gradeField = new JTextField(ra.getGrade().getScore()+"");
			gradeField.setFont(tableFont);
			gradeField.setText(ra.getGrade().getScore() + "");
			gbc.gridx = x;
			add(gradeField, gbc);
			gradesList.add(gradeField);
			x++;  // Next column

			// Get assignment comments
			JLabel commentsLabel = new JLabel("Comments:");
			commentsLabel.setFont(tableFont);
			gbc.gridx = x;
			add(commentsLabel, gbc);
			x++;  // Next column
			
			JTextArea commentsArea = new JTextArea(ra.getGrade().getComment(), 3, 20);
			gbc.gridx = x;
			gbc.gridheight = 2;
			add(commentsArea, gbc);
			commentsList.add(commentsArea);
			x++;  // Next column

			// Reset constraints
			y += 2;  // Next row
			x = 0;
			gbc.gridheight = 1;
			gbc.gridx = x;
			gbc.gridy = y;
		}

		// Add update grades, delete assignment buttons
		y++;
		gbc.gridy = y;
		JButton deleteAssignmentButton = new JButton("Delete Assignment");
		deleteAssignmentButton.setFont(tableFont);
		deleteAssignmentButton.addActionListener(new DeleteAssignmentController(rootView, column));
		gbc.gridx = 3;
		add(deleteAssignmentButton, gbc);
		JButton updateGradesButton = new JButton("Update Grades");
		updateGradesButton.setFont(tableFont);
		updateGradesButton.addActionListener(new UpdateGradesController(rootView, this));
		gbc.gridx = 4;
		add(updateGradesButton, gbc);
	}

	@Override
	public JPanel getPanelContent()
	{
		return this;
	}

	@Override
	public void update()
	{
		// Recreate either the row or column being displayed
		if(entry != null)
		{
			showEntry(entry);
		}
		if(column != null)
		{
			showColumn(column);
		}
	}
	
	public Entry getEntry()
	{
		return entry;
	}
	
	public ArrayList<RealAssignment> getColumn()
	{
		return column;
	}
	
	public ArrayList<String> getGradesList()
	{
		ArrayList<String> gradeScores = new ArrayList<String>(gradesList.size());
		for(JTextField scoreEntry: gradesList)
		{
			gradeScores.add(scoreEntry.getText());
		}
		return gradeScores;
	}
	
	public ArrayList<String> getCommentsList()
	{
		ArrayList<String> comments = new ArrayList<String>(commentsList.size());
		for(JTextArea entry: commentsList)
		{
			comments.add(entry.getText());
		}
		return comments;
	}
	
	public String getEnteredFName()
	{
		return firstNameField.getText();
	}
	
	public String getEnteredLName()
	{
		return lastNameField.getText();
	}
	
	public String getEnteredBUID()
	{
		return buidField.getText();
	}
	
	public StudentStatus getSelectedStatus()
	{
		return (StudentStatus) statusSelector.getSelectedItem();
	}

	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		return null;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
	{
		return 25;
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
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
	{
		return 10;
	}

}
