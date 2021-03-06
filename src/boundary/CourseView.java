package boundary;

import javax.swing.JPanel;

import controller.*;
import entity.Course;
import entity.Entry;
import entity.RealAssignment;
import entity.Semester;
import entity.User;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

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
	private Course course;
	private Semester semester;
	private JTextField searchField;
	private JScrollPane tableScrollPane;
	private JPanel tableHeader;
	private int[] headerColumnWidths;
	private JPanel entriesTable;
	private JScrollPane infoPanelScrollPane;
	private CourseInfoPanelView infoPanel;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView
	 *  @param user  The current user
	 *  @param owner  The owner of the gradebook this course comes from
	 *  @param course  The course being modified
	 */
	public CourseView(IGraderFrame rootView, User user, Course course,
			Semester semester) {
		super();
		this.rootView = rootView;
		this.user = user;
		this.course = course;
		this.semester = semester;
		setupPanel();
	}
	
	private void setupPanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc_global = new GridBagConstraints();
		gbc_global.anchor = GridBagConstraints.CENTER;
		gbc_global.fill = GridBagConstraints.BOTH;
		gbc_global.insets = new Insets(0, 5, 5, 5);
		gbc_global.weightx = 1.0;

		tableScrollPane = new JScrollPane();
		gbc_global.gridx = 0;
		gbc_global.gridy = 1;
		gbc_global.weighty = 0.4;
		add(tableScrollPane, gbc_global);
		
		tableHeader = createTableHeader();
		tableScrollPane.setColumnHeaderView(tableHeader);

		entriesTable = createEntriesTable("");
		tableScrollPane.setViewportView(entriesTable);

		infoPanelScrollPane = new JScrollPane();
		Border b = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		infoPanelScrollPane.setBorder(BorderFactory.createTitledBorder(b, "Information"));
		infoPanelScrollPane.getViewport().setBackground(new Color(240, 240, 240));
		gbc_global.fill = GridBagConstraints.BOTH;
		gbc_global.gridx = 0;
		gbc_global.gridy = 2;
		gbc_global.weighty = 0.6;
		add(infoPanelScrollPane, gbc_global);

		infoPanel = new CourseInfoPanelView(rootView, this);
		infoPanelScrollPane.setViewportView(infoPanel);

		JPanel topPanel = new JPanel();
		gbc_global.insets = new Insets(5, 5, 5, 5);
		gbc_global.gridy = 0;
		gbc_global.weighty = 0;
		add(topPanel, gbc_global);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.5, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.6, 0.4, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);

		JLabel courseName = new JLabel(course.toString());
		courseName.setHorizontalAlignment(SwingConstants.CENTER);
		courseName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_courseName = new GridBagConstraints();
		gbc_courseName.insets = new Insets(0, 0, 5, 0);
		gbc_courseName.gridwidth = 5;
		gbc_courseName.gridx = 0;
		gbc_courseName.gridy = 0;
		topPanel.add(courseName, gbc_courseName);

		JLabel semesterLabel = new JLabel(semester.toString());
		semesterLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_semesterLabel = new GridBagConstraints();
		gbc_semesterLabel.anchor = GridBagConstraints.WEST;
		gbc_semesterLabel.insets = new Insets(0, 10, 0, 5);
		gbc_semesterLabel.gridx = 0;
		gbc_semesterLabel.gridy = 1;
		topPanel.add(semesterLabel, gbc_semesterLabel);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchButton.addActionListener(new SearchController(rootView, user, this));
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

	private JPanel createEntriesTable(String searchText)
	{
		// Set up panel
		JPanel table = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		table.setLayout(layout);

		// Add entries
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		List<Entry> filteredEntry;
		if (!searchText.isEmpty())
		{
			filteredEntry = course.getEntries().stream()
					 .filter(entry -> entry.getStudent().getFName().toLowerCase().contains(searchText.toLowerCase()))
					 .collect(Collectors.toList());
		} else {
			filteredEntry = course.getEntries();
		}

		for(Entry e: filteredEntry)
		{
			EntryView ev = new EntryView(e, headerColumnWidths);
			ev.addMouseListener(new EntrySelectedController(rootView, this));
			table.add(ev, gbc);
			gbc.gridy += 1;
		}

		// Add entry button
		JButton addEntryButton = new JButton("Add Entry");
		addEntryButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addEntryButton.addActionListener(new OpenAddEntryWindowController(rootView, user, course));
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weighty = 1.0;
		table.add(addEntryButton, gbc);

		return table;
	}
	
	private JPanel createTableHeader()
	{
		headerColumnWidths = calculateColumnWidths();
		JPanel header = new JPanel();
		GridBagLayout headerLayout = new GridBagLayout();
		headerLayout.columnWidths = headerColumnWidths;
		header.setLayout(headerLayout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.weightx = 0;
		Font headerFont = new Font("Tahoma", Font.BOLD, 16);
		
		// Assignments
		RealAssignment template = course.getTemplate();  // Take a final grade assignment
		int greatestDepth = calculateSubAssignmentTreeDepth(template);
		gbc.gridx = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		for(int i = 0; i < template.getNumSubAssignments(); i++)
		{
			// Add this label
			RealAssignment ra = (RealAssignment) template.getSubAssignment(i);
			JLabel assignmentLabel = new JLabel(ra.getName() + " (" + convert(ra.getWeight()) + "%)");
			assignmentLabel.setFont(headerFont);
			assignmentLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			if(ra.getNumSubAssignments() == 0)  // If a leaf node
			{
				assignmentLabel.addMouseListener(new ColumnSelectedController(rootView, this, gbc.gridx));
			}
			if(ra.getNumLeaves() == 1)  // If this is a leaf node
			{
				// Only take one column
				gbc.gridwidth = 1;
				gbc.anchor = GridBagConstraints.WEST;
			}
			else  // Has leaves under it
			{
				// Take as many columns as have leaves under this assignment
				assignmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
				gbc.gridwidth = ra.getNumLeaves();
				gbc.anchor = GridBagConstraints.CENTER;  // Center over them
			}
			// Want all of the main grade-containing assignments on the bottom row
			gbc.gridy = (ra.getNumSubAssignments() == 0) ? greatestDepth : 0;
			header.add(assignmentLabel, gbc);
			gbc.gridy += 1;
			// Need to advance to next column if there are no sub-assignments
			gbc.gridx += (ra.getNumSubAssignments() == 0) ? 1 : 0;

			// Recursively add sub-assignment labels
			labelSubAssignments(ra, header, gbc, greatestDepth);
		}
		
		// Add Assignment Button
		JButton addAssignmentButton = new JButton("Add Assignment");
		addAssignmentButton.setFont(headerFont);
		addAssignmentButton.addActionListener(new OpenAddAssignmentWindowController(rootView, user, course));
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 1;
		gbc.gridy = greatestDepth;
		header.add(addAssignmentButton, gbc);
		gbc.gridx += 1;
		
		// Final Grade
		JLabel finalGradeLabel = new JLabel("Final Grade");
		finalGradeLabel.setFont(headerFont);
		gbc.insets = new Insets(5, 5, 0, 5);
		header.add(finalGradeLabel, gbc);
		
		// Section
		JLabel sectionLabel = new JLabel("Section");
		sectionLabel.setFont(headerFont);
		gbc.gridx = 0;
		gbc.gridy = greatestDepth;
		gbc.insets = new Insets(5, 5, 0, 0);
		header.add(sectionLabel, gbc);
		
		// BUID
		JLabel idLabel = new JLabel("BUID");
		idLabel.setFont(headerFont);
		gbc.gridx = 1;
		header.add(idLabel, gbc);
		
		// Student
		JLabel studentLabel = new JLabel("Student");
		studentLabel.setFont(headerFont);
		gbc.gridx = 2;
		header.add(studentLabel, gbc);
		
		return header;
	}
	
	private String convert(float weight)
	{
		float percentageWeight = weight * 100;
		return String.format("%.00f", percentageWeight);
	}
	
	private int[] calculateColumnWidths()
	{
		int[] columns = new int[course.getTemplate().getNumLeaves()+5+1];
		
		// Fill data
		columns[0] = 70;  // "Section"
		columns[1] = 80;  // "BUID"
		columns[2] = 150; // "Student"
		// Assignments
		ArrayList<Integer> estimates = estimateAssignmentColWidths(course.getTemplate());
		for(int i = 0; i < estimates.size(); i++)
		{
			columns[i+3] = estimates.get(i);
		}
		columns[columns.length-3] = 125;  // "Add Assignment" button
		columns[columns.length-2] = 128;  // "Final Grade"
		columns[columns.length-1] = 0;  // Obligatory final column
		
		return columns;
	}
	
	/*
	 *  This should overestimate the actual display width of the assignment's labels
	 *  by a little bit.
	 */
	private ArrayList<Integer> estimateAssignmentColWidths(RealAssignment ra)
	{
		ArrayList<Integer> estimates = new ArrayList<Integer>();
		
		// If there are no sub-assignments
		if(ra.getNumSubAssignments() == 0)
		{
			// Have leaf node, estimate size and add to list
			int nameLen = ra.getName().length();
			int estSize = nameLen * 11 + 70;  // +75 for weight printout
			estimates.add(estSize);
			return estimates;
		}
		else  // Have sub-assignments to estimate size for
		{
			for(int i = 0; i < ra.getNumSubAssignments(); i++)
			{
				// Get sub-assignment
				RealAssignment sa = (RealAssignment) ra.getSubAssignment(i);

				// Recursively estimate sub-assignment widths
				ArrayList<Integer> subAssignmentEstimates = estimateAssignmentColWidths(sa);
				
				// Transfer
				estimates.addAll(subAssignmentEstimates);
			}
		}
		
		return estimates;
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
				JLabel assignmentLabel = new JLabel(sa.getName() + " (" + convert(sa.getWeight()) + "%)");
				assignmentLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				assignmentLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				if(sa.getNumSubAssignments() == 0)  // If a leaf node
				{
					assignmentLabel.addMouseListener(new ColumnSelectedController(rootView, this, gbc.gridx));
				}
				if(sa.getNumLeaves() == 1)  // If this is a leaf node
				{
					// Only take one column
					gbc.gridwidth = 1;
					gbc.anchor = GridBagConstraints.WEST;
				}
				else
				{
					// Take as many columns as have leaves under this assignment
					assignmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
					gbc.gridwidth = sa.getNumLeaves();
					gbc.anchor = GridBagConstraints.CENTER;  // Center over them
				}
				gbc.gridy = sa.getNumSubAssignments() == 0 ? greatestDepth : currentDepth;
				header.add(assignmentLabel, gbc);
				gbc.gridy += 1;
				// Need to advance to next column if there are no sub-assignments
				gbc.gridx += (sa.getNumSubAssignments() == 0) ? 1 : 0;

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
		// Update all grades in case anything changed
		course.updateGrades();

		// Update header if the assignments changed
		tableHeader = createTableHeader();
		tableScrollPane.setColumnHeaderView(tableHeader);

		// Update entries table
		entriesTable = createEntriesTable(getSearchText());
		tableScrollPane.setViewportView(entriesTable);

		// Update course info panel
		infoPanel.update();
		
		// If entry or column has just been removed, hide infoPanel
		// So that deleted thing isn't shown
		if(infoPanel.getColumn() != null &&
				!course.hasAssignment(infoPanel.getColumn().get(0)))
		{
			hideInfoPanelScrollPane();
		}
		else if(infoPanel.getEntry() != null &&
				!course.hasEntry(infoPanel.getEntry()))
		{
			hideInfoPanelScrollPane();
		}  // Otherwise leave it
	}
	
	/**
	 *  Update info panel to show the entry's information.
	 *  @param e  The entry to display
	 */
	public void showEntryInfo(Entry e)
	{
		infoPanel.showEntry(e);
		infoPanelScrollPane.setViewportView(infoPanel);
		infoPanelScrollPane.setVisible(true);
	}
	
	/**
	 *  Update info panel to show the assignment column's information.
	 *  @param column  The assignment list to display
	 */
	public void showColumnInfo(ArrayList<RealAssignment> column)
	{
		infoPanel.showColumn(column);
		infoPanelScrollPane.setViewportView(infoPanel);
		infoPanelScrollPane.setVisible(true);
	}
	
	/**
	 *  @return  The course being modified in this screen
	 */
	public Course getCourse()
	{
		return course;
	}

	/**
	 *  @return  The text the user was searching for
	 */
	public String getSearchText() {
		return searchField.getText();
	}

	public void hideInfoPanelScrollPane() {
		infoPanelScrollPane.setVisible(false);
	}

	public Dimension getInfoPaneScrollPanelSize() {
		return infoPanelScrollPane.getSize();
	}
}
