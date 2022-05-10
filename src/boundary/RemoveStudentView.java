package boundary;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import controller.ClosePopupWindowController;
import controller.RemoveStudentController;
import controller.RemoveStudentController.RemoveStudentProblem;
import controller.RemoveStudentsSectionChangeController;
import entity.Course;
import entity.Section;
import entity.Student;
import entity.User;

/**
 *  Allows the user to select a student to remove from the course.
 *  @author Alex Titus
 */
public class RemoveStudentView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = 311497057519528692L;
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private User user;
	private Course course;
	private JScrollPane listScrollPane;
	private JList<Student> studentList;
	private JComboBox<Section> sectionSelector;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The frame for this popup window
	 *  @param parentView  The application window frame
	 *  @param user  The current user
	 *  @param course  The course being modified
	 */
	public RemoveStudentView(IGraderFrame rootView, IGraderFrame parentView,
			User user, Course course)
	{
		this.rootView = rootView;
		this.parentView = parentView;
		this.user = user;
		this.course = course;
		setupPanel();
	}

	/**
	 *  @return  The remove student screen as a JPanel 
	 */
	@Override
	public JPanel getPanelContent()
	{
		return this;
	}

	@Override
	public void update()
	{
		// Ignore
	}
	
	private void setupPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.5, 0.5, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Remove a Student");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.CENTER;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		gbc_titleLabel.weighty = 0.1;
		add(titleLabel, gbc_titleLabel);
		
		JLabel selectSemesterLabel = new JLabel("Select Section:");
		selectSemesterLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectSemesterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_selectSemesterLabel = new GridBagConstraints();
		gbc_selectSemesterLabel.anchor = GridBagConstraints.EAST;
		gbc_selectSemesterLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectSemesterLabel.gridx = 0;
		gbc_selectSemesterLabel.gridy = 1;
		gbc_selectSemesterLabel.weighty = 0.1;
		add(selectSemesterLabel, gbc_selectSemesterLabel);
		
		sectionSelector = new JComboBox<Section>();
		sectionSelector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sectionSelector.setModel(createComboBoxModel());
		sectionSelector.setSelectedIndex(-1);  // Defaults to no selection
		sectionSelector.addActionListener(new RemoveStudentsSectionChangeController(rootView, this));
		GridBagConstraints gbc_semesterSelectBox = new GridBagConstraints();
		gbc_semesterSelectBox.anchor = GridBagConstraints.WEST;
		gbc_semesterSelectBox.insets = new Insets(0, 0, 5, 0);
		gbc_semesterSelectBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_semesterSelectBox.gridx = 1;
		gbc_semesterSelectBox.gridy = 1;
		gbc_semesterSelectBox.weighty = 0.1;
		add(sectionSelector, gbc_semesterSelectBox);

		listScrollPane = new JScrollPane();
		listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.gridwidth = 2;
		gbc_listScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = 2;
		gbc_listScrollPane.weighty = 0.65;
		add(listScrollPane, gbc_listScrollPane);
		
		studentList = new JList<Student>();
		studentList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentList.setEnabled(false);  // Initially not selectable (also blank)
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane.setViewportView(studentList);
		
		JButton selectButton = new JButton("Remove");
		selectButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectButton.addActionListener(new RemoveStudentController(rootView, parentView, this, user));
		GridBagConstraints gbc_selectButton = new GridBagConstraints();
		gbc_selectButton.insets = new Insets(0, 10, 0, 5);
		gbc_selectButton.gridx = 0;
		gbc_selectButton.gridy = 3;
		gbc_selectButton.weighty = 0.15;
		add(selectButton, gbc_selectButton);
		
		JButton cancelButton = new JButton("Close");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 10);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 3;
		gbc_cancelButton.weighty = 0.15;
		add(cancelButton, gbc_cancelButton);

	}
	
	private DefaultComboBoxModel<Section> createComboBoxModel()
	{
		DefaultComboBoxModel<Section> model = new DefaultComboBoxModel<Section>();
		for(Section s: course.getSections())
		{
			model.addElement(s);
		}
		return model;
	}

	private ListModel<Student> createStudentsModel()
	{
		DefaultListModel<Student> model = new DefaultListModel<Student>();
		
		// Fill model
		for(Student s : getSelectedSection().getStudents())
		{
			model.addElement(s);
		}
		
		return model;
	}
	
	/**
	 *  Updates the list to show the students in the selected semester.
	 */
	public void updateStudentListing()
	{
		studentList = new JList<Student>();
		studentList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentList.setModel(createStudentsModel());
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane.setViewportView(studentList);
	}
	
	/**
	 *  @return  The student selected by the user.
	 */
	public Student getSelectedStudent()
	{
		return studentList.getSelectedValue();
	}
	
	/**
	 *  @return  The index in the list of the student selected by the user.
	 */
	public int getSelectedStudentIndex()
	{
		return studentList.getSelectedIndex();
	}
	
	/**
	 *  @return  The section selected by the user.
	 */
	public Section getSelectedSection()
	{
		return (Section) sectionSelector.getSelectedItem();
	}
	
	/**
	 *  @return  The index in the list of the section selected by the user.
	 */
	public int getSelectedSectionIndex()
	{
		return sectionSelector.getSelectedIndex();
	}
	
	/**
	 *  Displays a "student removed" message on this screen.
	 */
	public void showSuccess()
	{
		removeAll();
		repaint();
		setupPanelWithMessage(RemoveStudentProblem.NO_ERROR);
	}
	
	/**
	 *  Displays an error message to the user on this screen.
	 *  @param error  The error to display
	 */
	public void showError(RemoveStudentProblem error)
	{
		removeAll();
		repaint();
		setupPanelWithMessage(error);
	}
	
	private void setupPanelWithMessage(RemoveStudentProblem message)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.5, 0.5, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;  // First row
		
		JLabel titleLabel = new JLabel("Remove a Student");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.CENTER;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		gbc_titleLabel.weighty = 0.1;
		add(titleLabel, gbc_titleLabel);
		y++;  // Next row
		
		JLabel selectSectionLabel = new JLabel("Select Section:");
		selectSectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectSectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_selectSectionLabel = new GridBagConstraints();
		gbc_selectSectionLabel.anchor = GridBagConstraints.EAST;
		gbc_selectSectionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectSectionLabel.gridx = 0;
		gbc_selectSectionLabel.gridy = y;
		gbc_selectSectionLabel.weighty = 0.1;
		add(selectSectionLabel, gbc_selectSectionLabel);
		
		GridBagConstraints gbc_sectionSelectBox = new GridBagConstraints();
		gbc_sectionSelectBox.anchor = GridBagConstraints.WEST;
		gbc_sectionSelectBox.insets = new Insets(0, 0, 5, 0);
		gbc_sectionSelectBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_sectionSelectBox.gridx = 1;
		gbc_sectionSelectBox.gridy = y;
		gbc_sectionSelectBox.weighty = 0.1;
		add(sectionSelector, gbc_sectionSelectBox);
		y++;  // Next row
		
		if(message == RemoveStudentProblem.NO_SECTION_SELECTION)
		{
			JLabel messageLabel = new JLabel("Please selection a section");
			messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			messageLabel.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 5, 5, 5);
			gbc.gridx = 0;
			gbc.gridy = y;
			add(messageLabel, gbc);
			y++;  // Next row
		}

		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.gridwidth = 2;
		gbc_listScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = y;
		gbc_listScrollPane.weighty = 0.65;
		add(listScrollPane, gbc_listScrollPane);
		y++;  // Next row
		
		listScrollPane.setViewportView(studentList);
		
		if(message == RemoveStudentProblem.NO_ERROR)
		{
			JLabel messageLabel = new JLabel("Student removed.");
			messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(0, 5, 5, 5);
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = y;
			add(messageLabel, gbc);
			y++;  // Next row
		}
		if(message == RemoveStudentProblem.NO_STUDENT_SELECTION)
		{
			JLabel messageLabel = new JLabel("Please select a student to remove.");
			messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			messageLabel.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(0, 5, 5, 5);
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = y;
			add(messageLabel, gbc);
			y++;  // Next row
		}
		
		JButton selectButton = new JButton("Remove");
		selectButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectButton.addActionListener(new RemoveStudentController(rootView, parentView, this, user));
		GridBagConstraints gbc_selectButton = new GridBagConstraints();
		gbc_selectButton.insets = new Insets(0, 10, 0, 5);
		gbc_selectButton.gridx = 0;
		gbc_selectButton.gridy = y;
		gbc_selectButton.weighty = 0.15;
		add(selectButton, gbc_selectButton);
		
		JButton cancelButton = new JButton("Close");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 10);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = y;
		gbc_cancelButton.weighty = 0.15;
		add(cancelButton, gbc_cancelButton);
	}

}
