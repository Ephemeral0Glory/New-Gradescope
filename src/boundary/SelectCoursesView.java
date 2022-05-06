package boundary;

import javax.swing.JPanel;

import entity.*;
import utilities.GradebookFileReaderException;

import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import controller.OpenMainMenuController;
import controller.SelectCourseController;

/**
 * 
 *  @author David Sullo
 *  @author Alex Titus
 */
public class SelectCoursesView extends JPanel implements IGraderScreen {
	private static final long serialVersionUID = 5906822060130097403L;
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	private JList<Course> courseList;
	
	public SelectCoursesView(IGraderFrame rootView, User user, Gradebook gradebook) throws GradebookFileReaderException {
		this.rootView = rootView;
		this.user = user;
		this.gradebook = gradebook;
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
	
	private void setupPanel() throws GradebookFileReaderException {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.5, 0.5, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.1, 0.75, 0.15, Double.MIN_VALUE};
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

		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.gridwidth = 2;
		gbc_listScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = 1;
		add(listScrollPane, gbc_listScrollPane);
		
		courseList = new JList<Course>(getCourses());
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane.setViewportView(courseList);
		
		JButton selectButton = new JButton("Select");
		selectButton.addActionListener(new SelectCourseController(rootView, this, user, gradebook));
		GridBagConstraints gbc_selectButton = new GridBagConstraints();
		gbc_selectButton.insets = new Insets(0, 10, 0, 5);
		gbc_selectButton.gridx = 0;
		gbc_selectButton.gridy = 2;
		add(selectButton, gbc_selectButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new OpenMainMenuController(rootView, user));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 10);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 2;
		add(cancelButton, gbc_cancelButton);

	}

	private Course[] getCourses() {
		ArrayList<Semester> semesters = gradebook.getSemesters();
		ArrayList<Course> allCourses = new ArrayList<Course>();
		Semester currSemester;
		for (int i = 0; i < semesters.size(); i++) {
			currSemester = semesters.get(i);
			for (int j = 0; j < currSemester.getCourses().size(); j++) {
				allCourses.add(currSemester.getCourses().get(j));
			}
		}
		Course[] courses = new Course[allCourses.size()];
		for(int i = 0; i < allCourses.size(); i++)
		{
			courses[i] = allCourses.get(i);
		}
		return courses;
	}
	
	public Course getSelectedCourse()
	{
		return courseList.getSelectedValue();
	}

}
