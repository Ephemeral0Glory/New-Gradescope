package boundary;

import javax.swing.JPanel;

import entity.Course;
import entity.Entry;
import entity.RealAssignment;
import entity.User;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 *  Displays the information about a course.
 *  <p>
 *  Consists broadly of 3 components:
 *  <ul><li>a title area, with the name of the course and the table headers</li>
 *  <li>the grades table, a series of {@link EntryView} panels</li>
 *  <li>the info panel, where grades and comments can be edited</li>
 *  </ul>
 *  
 *  @author Alex Titus
 */
public class CourseView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = 3924557341183124315L;
	private IGraderFrame rootView;
	private User user;
	private User owner;
	private Course course;
	private JTextField searchField;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView
	 *  @param user  The current user
	 *  @param owner  The owner of the gradebook this course comes from
	 *  @param course
	 */
	public CourseView(IGraderFrame rootView, User user, User owner, Course course) {
		super();
		this.rootView = rootView;
		this.user = user;
		this.owner = owner;
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
		
		JPanel tableHeader = createTableHeader();
		tableScrollPane.setColumnHeaderView(tableHeader);

		JScrollPane infoPanelScrollPane = new JScrollPane();
		add(infoPanelScrollPane, BorderLayout.SOUTH);

		JPanel infoPanel = new CourseInfoView(rootView);
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
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		for(Entry e: course.getEntries())
		{
			EntryView ev = new EntryView(e);
//			ev.addMouseListener(new EntrySelectedController(rootView, this));
			table.add(ev, gbc);
			gbc.gridy += 1;
		}
		
		// Add entry button
		JButton addEntryButton = new JButton("Add Entry");
		addEntryButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		addEntryButton.addActionListener(new AddEntryController(rootView, course));
		table.add(addEntryButton, gbc);
		
		return table;
	}
	
	private JPanel createTableHeader()
	{
		JPanel header = new JPanel();
		header.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Font headerFont = new Font("Tahoma", Font.BOLD, 16);
		
		// Assignments
		RealAssignment template = course.getTemplate();  // Take a final grade assignment
		int greatestDepth = 0;
		// At the start we don't have a template at all
		if(template != null)
		{
			greatestDepth = calculateSubAssignmentTreeDepth(template);
			gbc.gridx = 3;
			for(int i = 0; i < template.getNumSubAssignments(); i++)
			{
				// Add this label
				RealAssignment ra = (RealAssignment) template.getSubAssignment(i);
				JLabel assignmentLabel = new JLabel(ra.getName() + " " + ra.getWeight() + "%");
				assignmentLabel.setFont(headerFont);
				gbc.gridwidth = ra.getNumSuccessors() == 0 ? 1 : ra.getNumSuccessors();
				// Want all of the main grade-containing assignments on the bottom row
				gbc.gridy = (ra.getNumSubAssignments() == 0) ? greatestDepth : 0;
				header.add(assignmentLabel, gbc);
				gbc.gridy += 1;
				// Need to advance to next column if there are no sub-assignments
				gbc.gridx += (ra.getNumSubAssignments() == 0) ? 1 : 0;

				// Recursively add sub-assignment labels
				labelSubAssignments(ra, header, gbc, greatestDepth);
			}
		}
		
		// Add Assignment Button
		JButton addAssignmentButton = new JButton("Add Assignment");
		addAssignmentButton.setFont(headerFont);
//		addAssignmentButton.addActionListener(new AddAssignmentController(rootView, course));
		gbc.gridwidth = 1;
		gbc.gridy = greatestDepth;
		header.add(addAssignmentButton, gbc);
		gbc.gridx += 1;
		
		// Final Grade
		JLabel finalGradeLabel = new JLabel("Final Grade");
		finalGradeLabel.setFont(headerFont);
		header.add(finalGradeLabel, gbc);
		
		// Section
		JLabel sectionLabel = new JLabel("Section");
		sectionLabel.setFont(headerFont);
		gbc.gridx = 0;
		gbc.gridy = greatestDepth;
		gbc.anchor = GridBagConstraints.CENTER;
		header.add(sectionLabel, gbc);
		
		// Student
		JLabel studentLabel = new JLabel("Student");
		studentLabel.setFont(headerFont);
		gbc.gridx = 1;
		header.add(studentLabel, gbc);
		
		// BUID
		JLabel idLabel = new JLabel("BUID");
		idLabel.setFont(headerFont);
		gbc.gridx = 2;
		header.add(idLabel, gbc);
		
		return header;
	}
	
	private int calculateSubAssignmentTreeDepth(RealAssignment template)
	{
		int totalDepth = 0;
		
		// If template has no assignments
		if(template.getNumSubAssignments() == 0)
		{
			// Then have no depth
			return totalDepth;
		}
		// Else have assignments
		totalDepth += 1;
		int greatestDepth = 0;
		// Find deepest branch
		for(int i = 0; i < template.getNumSubAssignments(); i++)
		{
			// Can cast because checked for NullAssignment case above
			RealAssignment ra = (RealAssignment) template.getSubAssignment(i);
			
			// Get maximum depth of this branch
			int branchDepth = calculateSubAssignmentTreeDepth(ra);
			
			// If this is the new deepest branch
			if(branchDepth > greatestDepth)
			{
				// Have new best depth
				greatestDepth = branchDepth;
			}
		}
		
		// Add deepest branch to total
		totalDepth += greatestDepth;
		
		return totalDepth;
	}
	
	private void labelSubAssignments(RealAssignment ra, JPanel header, GridBagConstraints gbc, int greatestDepth)
	{
		// If there are no sub-assignments
		if(ra.getNumSubAssignments() == 0)
		{
			// We're done, return
			gbc.gridy -= 1;
			return;
		}
		else  // Have sub-assignments to label
		{
			int currentDepth = gbc.gridy;
			for(int i = 0; i < ra.getNumSubAssignments(); i++)
			{
				// Add this label
				RealAssignment sa = (RealAssignment) ra.getSubAssignment(i);
				JLabel assignmentLabel = new JLabel(sa.getName() + " " + sa.getWeight() + "%");
				assignmentLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				gbc.gridwidth = sa.getNumSuccessors() == 0 ? 1 : sa.getNumSuccessors();
				gbc.gridy = sa.getNumSubAssignments() == 0 ? greatestDepth : currentDepth;
				header.add(assignmentLabel, gbc);
				gbc.gridx += 1;
				gbc.gridy += 1;

				// Recursively add sub-assignment labels
				labelSubAssignments(sa, header, gbc, greatestDepth);
			}
		}
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
