package boundary;

import javax.swing.JPanel;

import entity.Course;
import entity.Entry;
import entity.RealAssignment;
import entity.StudentStatus;
import entity.User;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *  Displays the information about a course.
 *  <p>
 *  Consists broadly of 3 components:
 *  <ul><li>a title area, with the name of the course and the table headers</li>
 *  <li>the grades table, a series of {@link EntryView} panels</li>
 *  <li>the info panel, where grades and comments can be edited</li>
 *  
 *  @author Alex Titus
 */
public class CourseView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = 3924557341183124315L;
	private IGraderFrame rootView;
	private User user;
	private Course course;
	private JTextField searchField;
	
	/**
	 *  Constructor.
	 *  
	 */
	public CourseView(IGraderFrame rootView, User user, Course course) {
		super();
		this.rootView = rootView;
		this.user = user;
		this.course = course;
		setupPanel();
		
	}
	
	private void setupPanel()
	{
		setLayout(new BorderLayout(0, 5));

		JScrollPane tableScrollPane = new JScrollPane();
		add(tableScrollPane, BorderLayout.CENTER);

		JPanel entriesTable = createEntriesTable();
		tableScrollPane.setViewportView(entriesTable);

		JScrollPane infoPanelScrollPane = new JScrollPane();
		add(infoPanelScrollPane, BorderLayout.SOUTH);

		JPanel infoPanel = new JPanel();
		infoPanelScrollPane.setViewportView(infoPanel);

		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.6, 0.4, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);

		JLabel courseName = new JLabel("Course");
		courseName.setHorizontalAlignment(SwingConstants.CENTER);
		courseName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_courseName = new GridBagConstraints();
		gbc_courseName.insets = new Insets(0, 0, 5, 0);
		gbc_courseName.gridwidth = 5;
		gbc_courseName.gridx = 0;
		gbc_courseName.gridy = 0;
		topPanel.add(courseName, gbc_courseName);

		JLabel semesterLabel = new JLabel("Semester");
		semesterLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_semesterLabel = new GridBagConstraints();
		gbc_semesterLabel.anchor = GridBagConstraints.WEST;
		gbc_semesterLabel.insets = new Insets(0, 10, 0, 5);
		gbc_semesterLabel.gridx = 0;
		gbc_semesterLabel.gridy = 1;
		topPanel.add(semesterLabel, gbc_semesterLabel);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.gridx = 4;
		gbc_searchButton.gridy = 1;
		topPanel.add(searchButton, gbc_searchButton);

		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		topPanel.add(searchLabel, gbc_lblNewLabel);

		searchField = new JTextField();
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.insets = new Insets(0, 0, 0, 5);
		gbc_searchField.anchor = GridBagConstraints.WEST;
		gbc_searchField.gridx = 3;
		gbc_searchField.gridy = 1;
		topPanel.add(searchField, gbc_searchField);
		searchField.setColumns(15);
	}
	
	private JPanel createEntriesTable()
	{
		// Set up panel
		JPanel table = new JPanel();
		table.setLayout(new GridBagLayout());
		
		// Add entries
		int y = 0;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = y;
		for(Entry e: course.getEntries())
		{
			EntryView ev = new EntryView(e);
//			ev.addMouseListener(new EntrySelectedController(rootView, this));
			table.add(ev, gbc);
			y += 1;
			gbc.gridy = y;
		}
		
		return table;
	}
	
	private JPanel createInfoPanelColumn(ArrayList<RealAssignment> column)
	{
		// Set up panel
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridBagLayout());
		
		// Title
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		String assignmentName = column.isEmpty() ? "No Assignment" : column.get(0).getName();
		JLabel title = new JLabel(assignmentName);
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		infoPanel.add(title, gbc);
		
		// Fill panel
		Font tableFont = new Font("Tahoma", Font.PLAIN, 12);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 1;
		for(RealAssignment ra: column)
		{
			// Get name of student for assignment
			JLabel studentName = new JLabel(ra.getStudentName());
			studentName.setFont(tableFont);
			infoPanel.add(studentName, gbc);
			gbc.gridx++;
			
			// Get assignment grade
			JLabel gradeLabel = new JLabel("Grade:");
			gradeLabel.setFont(tableFont);
			infoPanel.add(gradeLabel, gbc);
			gbc.gridx++;
			JTextField gradeField = new JTextField();
			gradeField.setFont(tableFont);
			gradeField.setText(ra.getGrade().getScore() + "");
			infoPanel.add(gradeField, gbc);
			gbc.gridx++;
			
			// Get assignment comments
			JLabel commentsLabel = new JLabel("Comments:");
			commentsLabel.setFont(tableFont);
			infoPanel.add(commentsLabel, gbc);
			gbc.gridx++;
			JTextArea commentsArea = new JTextArea();
			commentsArea.setFont(tableFont);
			commentsArea.setText(ra.getGrade().getComment());
			gbc.gridheight = 2;
			infoPanel.add(commentsArea, gbc);
			
			// Reset constraints
			gbc.gridheight = 1;
			gbc.gridx = 0;
			gbc.gridy += 2;
		}
		
		// Add update grades, delete assignment buttons
		JButton deleteAssignmentButton = new JButton("Delete Assignment");
		deleteAssignmentButton.setFont(tableFont);
//		deleteAssignmentButton.addActionListener(new DeleteAssignmentController(rootView, course));
		gbc.gridx = 3;
		infoPanel.add(deleteAssignmentButton, gbc);
		JButton updateGradesButton = new JButton("Update Grades");
		updateGradesButton.setFont(tableFont);
//		updateGradesButton.addActionListener(new UpdateGradesController(rootView, course));
		gbc.gridx = 4;
		infoPanel.add(updateGradesButton, gbc);
		
		return infoPanel;
	}
	
	private JPanel createInfoPanelRow(Entry entry)
	{
		// Set up panel
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Font panelFont = new Font("Tahoma", Font.PLAIN, 12);
		
		// Title
		JLabel title = new JLabel("Course information for student:");
		title.setFont(panelFont);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 6;
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.anchor = GridBagConstraints.WEST;
		infoPanel.add(title, gbc);
		gbc.gridy += 1;
		gbc.gridwidth = 1;
		
		// Student information row
			// Name
		JLabel studentNameLabel = new JLabel("Student name:");
		studentNameLabel.setFont(panelFont);
		gbc.anchor = GridBagConstraints.EAST;
		infoPanel.add(studentNameLabel, gbc);
		gbc.gridx += 1;
		JTextField studentFirstNameField = new JTextField(entry.getStudent().getFName());
		studentFirstNameField.setFont(panelFont);
		gbc.anchor = GridBagConstraints.WEST;
		infoPanel.add(studentFirstNameField, gbc);
		gbc.gridx += 1;
		JTextField studentLastNameField = new JTextField(entry.getStudent().getLName());
		studentLastNameField.setFont(panelFont);
		infoPanel.add(studentLastNameField, gbc);
		gbc.gridx += 1;
			// ID
		JLabel idLabel = new JLabel("BUID:");
		idLabel.setFont(panelFont);
		gbc.anchor = GridBagConstraints.EAST;
		infoPanel.add(idLabel, gbc);
		gbc.gridx += 1;
		JTextField idField = new JTextField(entry.getStudent().getBUID());
		idField.setFont(panelFont);
		gbc.anchor = GridBagConstraints.WEST;
		infoPanel.add(idField, gbc);
		gbc.gridx += 1;
			// Status
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setFont(panelFont);
		gbc.anchor = GridBagConstraints.EAST;
		infoPanel.add(statusLabel, gbc);
		gbc.gridx += 1;
		JComboBox<StudentStatus> statusSelector = new JComboBox<StudentStatus>(
				new DefaultComboBoxModel<StudentStatus>(StudentStatus.values()));
		statusSelector.setFont(panelFont);
		gbc.anchor = GridBagConstraints.WEST;
		infoPanel.add(statusSelector, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		// Assignments
		
		
		return infoPanel;
	}

	/**
	 *  @return A JPanel representing the course screen
	 */
	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
