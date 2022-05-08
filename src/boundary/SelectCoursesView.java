package boundary;

import javax.swing.JPanel;

import entity.*;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import controller.OpenMainMenuController;
import controller.OpenViewCoursesInfoController;
import controller.SelectCourseController;
import controller.SelectCoursesSemesterChangeController;

/**
 *  Allows the user to select a course to open for a selected semester.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class SelectCoursesView extends JPanel implements IGraderScreen {
	private static final long serialVersionUID = 5906822060130097403L;
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	private boolean editingCourse;
	private JScrollPane listScrollPane;
	private JList<Course> courseList;
	private JComboBox<Semester> semesterSelector;
	
	public SelectCoursesView(IGraderFrame rootView, User user, Gradebook gradebook,
			boolean editingCourse) {
		this.rootView = rootView;
		this.user = user;
		this.gradebook = gradebook;
		this.editingCourse = editingCourse;
		setupPanel();
	}

	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		// Ignore
	}
	
	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.5, 0.5, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.1, 0.1, 0.65, 0.15, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Select a Course from the list below");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		JLabel selectSemesterLabel = new JLabel("Select Semester:");
		selectSemesterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_selectSemesterLabel = new GridBagConstraints();
		gbc_selectSemesterLabel.anchor = GridBagConstraints.EAST;
		gbc_selectSemesterLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectSemesterLabel.gridx = 0;
		gbc_selectSemesterLabel.gridy = 1;
		add(selectSemesterLabel, gbc_selectSemesterLabel);
		
		semesterSelector = new JComboBox<Semester>();
		semesterSelector.setModel(createComboBoxModel());
		semesterSelector.setSelectedIndex(gradebook.getSemesters().size()-1);  // Most recently created
		semesterSelector.addActionListener(new SelectCoursesSemesterChangeController(rootView, this));
		GridBagConstraints gbc_semesterSelectBox = new GridBagConstraints();
		gbc_semesterSelectBox.anchor = GridBagConstraints.WEST;
		gbc_semesterSelectBox.insets = new Insets(0, 0, 5, 0);
		gbc_semesterSelectBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_semesterSelectBox.gridx = 1;
		gbc_semesterSelectBox.gridy = 1;
		add(semesterSelector, gbc_semesterSelectBox);

		listScrollPane = new JScrollPane();
		listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.gridwidth = 2;
		gbc_listScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = 2;
		add(listScrollPane, gbc_listScrollPane);
		
		courseList = new JList<Course>(getCourses());
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane.setViewportView(courseList);
		
		JButton selectButton = new JButton("Select");
		if(editingCourse)  // Opening course edit
		{
			selectButton.addActionListener(new SelectCourseController(rootView, this, user, gradebook));
		}
		else  // Opening info
		{
			selectButton.addActionListener(new OpenViewCoursesInfoController(rootView, this, user, gradebook));
		}
		GridBagConstraints gbc_selectButton = new GridBagConstraints();
		gbc_selectButton.insets = new Insets(0, 10, 0, 5);
		gbc_selectButton.gridx = 0;
		gbc_selectButton.gridy = 3;
		add(selectButton, gbc_selectButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new OpenMainMenuController(rootView, user));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 10);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 3;
		add(cancelButton, gbc_cancelButton);

	}
	
	private DefaultComboBoxModel<Semester> createComboBoxModel()
	{
		DefaultComboBoxModel<Semester> model = new DefaultComboBoxModel<Semester>();
		for(Semester s: gradebook.getSemesters())
		{
			model.addElement(s);
		}
		return model;
	}

	private Course[] getCourses() {
		// Get courses for selected semester
		Semester semester = getSelectedSemester();
		if(semester == null)  // If tried to access before creating course
		{
			return new Course[0];
		}
		ArrayList<Course> semesterCourses = semester.getCourses();
		
		// Convert to array for JList
		Course[] courses = new Course[semesterCourses.size()];
		for(int i = 0; i < semesterCourses.size(); i++)
		{
			courses[i] = semesterCourses.get(i);
		}
		return courses;
	}
	
	public void updateCourseListing()
	{
		courseList = new JList<Course>(getCourses());
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane.setViewportView(courseList);
	}
	
	public Course getSelectedCourse()
	{
		return courseList.getSelectedValue();
	}
	
	public Semester getSelectedSemester()
	{
		return (Semester) semesterSelector.getSelectedItem();
	}

}
