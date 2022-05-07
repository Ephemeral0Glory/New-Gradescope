package boundary;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import controller.AddEntryController;
import controller.AddEntryController.EntryProblem;
import controller.ClosePopupWindowController;
import controller.SectionChangeController;
import entity.Course;
import entity.Section;
import entity.Student;
import entity.StudentStatus;
import entity.User;

/**
 *  The add new entry screen.
 *  <p>
 *  User is able to enter information needed to create a new entry.
 *  @author Alex Titus
 */
public class AddEntryView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = -6511973169284808176L;
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private User user;
	private Course course;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField buidField;
	private JComboBox<Section> sectionSelector;
	private JComboBox<Student> studentSelector;
	private GridBagConstraints gbc_studentSelector;
	private JCheckBox createNewStudentSelector;
	private JComboBox<StudentStatus> studentStatusSelector;
	
	public AddEntryView(IGraderFrame rootView, IGraderFrame parentView, User user, Course course)
	{
		this.rootView = rootView;
		this.parentView = parentView;
		this.user = user;
		this.course = course;
		setupPanel();
	}
	
	private void setupPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Create New Entry");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		gbc_title.weighty = 0.3;
		add(title, gbc_title);
		
		JLabel selectSectionLabel = new JLabel("Select Student Section:");
		selectSectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		selectSectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_selectSectionLabel = new GridBagConstraints();
		gbc_selectSectionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectSectionLabel.anchor = GridBagConstraints.EAST;
		gbc_selectSectionLabel.gridx = 0;
		gbc_selectSectionLabel.gridy = 1;
		gbc_selectSectionLabel.weighty = 0.1;
		add(selectSectionLabel, gbc_selectSectionLabel);
		
		sectionSelector = new JComboBox<Section>();
		sectionSelector.setModel(createSectionComboBoxModel());
		sectionSelector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sectionSelector.setSelectedIndex(-1);
		sectionSelector.addActionListener(new SectionChangeController(rootView, this));
		GridBagConstraints gbc_sectionSelector = new GridBagConstraints();
		gbc_sectionSelector.insets = new Insets(0, 0, 5, 0);
		gbc_sectionSelector.anchor = GridBagConstraints.WEST;
		gbc_sectionSelector.gridx = 1;
		gbc_sectionSelector.gridy = 1;
		gbc_sectionSelector.weighty = 0.1;
		add(sectionSelector, gbc_sectionSelector);
		
		JLabel selectStudentLabel = new JLabel("Select Student:");
		selectStudentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		selectStudentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_selectStudentLabel = new GridBagConstraints();
		gbc_selectStudentLabel.anchor = GridBagConstraints.EAST;
		gbc_selectStudentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectStudentLabel.gridx = 0;
		gbc_selectStudentLabel.gridy = 2;
		gbc_selectStudentLabel.weighty = 0.1;
		add(selectStudentLabel, gbc_selectStudentLabel);
		
		studentSelector = new JComboBox<Student>();
		studentSelector.setEnabled(false);
		studentSelector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gbc_studentSelector = new GridBagConstraints();
		gbc_studentSelector.insets = new Insets(0, 0, 5, 0);
		gbc_studentSelector.anchor = GridBagConstraints.WEST;
		gbc_studentSelector.gridx = 1;
		gbc_studentSelector.gridy = 2;
		gbc_studentSelector.weighty = 0.1;
		add(studentSelector, gbc_studentSelector);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 2;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		add(separator, gbc_separator);
		
		JLabel createNewStudentLabel = new JLabel("Or:");
		createNewStudentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createNewStudentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_createNewStudentLabel = new GridBagConstraints();
		gbc_createNewStudentLabel.anchor = GridBagConstraints.EAST;
		gbc_createNewStudentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_createNewStudentLabel.gridx = 0;
		gbc_createNewStudentLabel.gridy = 4;
		gbc_createNewStudentLabel.weighty = 0.1;
		add(createNewStudentLabel, gbc_createNewStudentLabel);
		
		createNewStudentSelector = new JCheckBox("Create New Student");
		createNewStudentSelector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_createNewStudentSelector = new GridBagConstraints();
		gbc_createNewStudentSelector.insets = new Insets(0, 0, 5, 0);
		gbc_createNewStudentSelector.anchor = GridBagConstraints.WEST;
		gbc_createNewStudentSelector.gridx = 1;
		gbc_createNewStudentSelector.gridy = 4;
		gbc_createNewStudentSelector.weighty = 0.1;
		add(createNewStudentSelector, gbc_createNewStudentSelector);
		
		JLabel studentFirstNameLabel = new JLabel("New Student First Name:");
		studentFirstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		studentFirstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_studentFirstNameLabel = new GridBagConstraints();
		gbc_studentFirstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_studentFirstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_studentFirstNameLabel.gridx = 0;
		gbc_studentFirstNameLabel.gridy = 5;
		gbc_studentFirstNameLabel.weighty = 0.1;
		add(studentFirstNameLabel, gbc_studentFirstNameLabel);
		
		firstNameField = new JTextField();
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameField.anchor = GridBagConstraints.WEST;
		gbc_firstNameField.gridx = 1;
		gbc_firstNameField.gridy = 5;
		gbc_firstNameField.weighty = 0.1;
		add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(25);
		
		JLabel studentLastNameLabel = new JLabel("New Student Last Name:");
		studentLastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		studentLastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_studentLastNameLabel = new GridBagConstraints();
		gbc_studentLastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_studentLastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_studentLastNameLabel.gridx = 0;
		gbc_studentLastNameLabel.gridy = 6;
		gbc_studentLastNameLabel.weighty = 0.1;
		add(studentLastNameLabel, gbc_studentLastNameLabel);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameField.anchor = GridBagConstraints.WEST;
		gbc_lastNameField.gridx = 1;
		gbc_lastNameField.gridy = 6;
		gbc_lastNameField.weighty = 0.1;
		add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(25);
		
		JLabel newStudentBuidLabel = new JLabel("New Student BUID");
		newStudentBuidLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		newStudentBuidLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_newStudentBuidLabel = new GridBagConstraints();
		gbc_newStudentBuidLabel.insets = new Insets(0, 0, 5, 5);
		gbc_newStudentBuidLabel.anchor = GridBagConstraints.EAST;
		gbc_newStudentBuidLabel.gridx = 0;
		gbc_newStudentBuidLabel.gridy = 7;
		gbc_newStudentBuidLabel.weighty = 0.1;
		add(newStudentBuidLabel, gbc_newStudentBuidLabel);
		
		buidField = new JTextField();
		buidField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_buidField = new GridBagConstraints();
		gbc_buidField.insets = new Insets(0, 0, 5, 0);
		gbc_buidField.anchor = GridBagConstraints.WEST;
		gbc_buidField.gridx = 1;
		gbc_buidField.gridy = 7;
		gbc_buidField.weighty = 0.1;
		add(buidField, gbc_buidField);
		buidField.setColumns(10);
		
		JLabel newStudentEnrollmentLabel = new JLabel("New Student Enrollment Status:");
		newStudentEnrollmentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		newStudentEnrollmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_newStudentEnrollmentLabel = new GridBagConstraints();
		gbc_newStudentEnrollmentLabel.anchor = GridBagConstraints.EAST;
		gbc_newStudentEnrollmentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_newStudentEnrollmentLabel.gridx = 0;
		gbc_newStudentEnrollmentLabel.gridy = 8;
		gbc_newStudentEnrollmentLabel.weighty = 0.1;
		add(newStudentEnrollmentLabel, gbc_newStudentEnrollmentLabel);
		
		studentStatusSelector = new JComboBox<StudentStatus>();
		studentStatusSelector.setModel(new DefaultComboBoxModel<StudentStatus>(StudentStatus.values()));
		studentStatusSelector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentStatusSelector.setSelectedItem(StudentStatus.ACTIVE);  // Default to active
		GridBagConstraints gbc_studentStatusSelector = new GridBagConstraints();
		gbc_studentStatusSelector.insets = new Insets(0, 0, 5, 0);
		gbc_studentStatusSelector.anchor = GridBagConstraints.WEST;
		gbc_studentStatusSelector.gridx = 1;
		gbc_studentStatusSelector.gridy = 8;
		gbc_studentStatusSelector.weighty = 0.1;
		add(studentStatusSelector, gbc_studentStatusSelector);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 9;
		add(separator_1, gbc_separator_1);
		
		JButton createButton = new JButton("Create");
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createButton.addActionListener(new AddEntryController(rootView, user, course, this));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.insets = new Insets(0, 0, 0, 5);
		gbc_createButton.gridx = 0;
		gbc_createButton.gridy = 10;
		gbc_createButton.weighty = 0.3;
		add(createButton, gbc_createButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 10;
		gbc_closeButton.weighty = 0.3;
		add(closeButton, gbc_closeButton);
	}
	
	private DefaultComboBoxModel<Section> createSectionComboBoxModel()
	{
		DefaultComboBoxModel<Section> model = new DefaultComboBoxModel<Section>();
		
		// Load data
		for(Section s: course.getSections())
		{
			model.addElement(s);
		}
		
		return model;
	}

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
	
	public void showSuccess()
	{
		removeAll();
		setupPanelWithError(EntryProblem.NO_ERROR);
	}
	
	public void showError(EntryProblem error)
	{
		removeAll();
		setupPanelWithError(error);
	}
	
	private void setupPanelWithError(EntryProblem error)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;  // First row
		
		JLabel title = new JLabel("Create New Entry");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 0;
		gbc_title.gridy = y;
		gbc_title.weighty = 0.3;
		add(title, gbc_title);
		y++;  // Next row
		
		JLabel selectSectionLabel = new JLabel("Select Student Section:");
		selectSectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		selectSectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_selectSectionLabel = new GridBagConstraints();
		gbc_selectSectionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectSectionLabel.anchor = GridBagConstraints.EAST;
		gbc_selectSectionLabel.gridx = 0;
		gbc_selectSectionLabel.gridy = y;
		gbc_selectSectionLabel.weighty = 0.1;
		add(selectSectionLabel, gbc_selectSectionLabel);
		
		GridBagConstraints gbc_sectionSelector = new GridBagConstraints();
		gbc_sectionSelector.insets = new Insets(0, 0, 5, 0);
		gbc_sectionSelector.anchor = GridBagConstraints.WEST;
		gbc_sectionSelector.gridx = 1;
		gbc_sectionSelector.gridy = y;
		gbc_sectionSelector.weighty = 0.1;
		add(sectionSelector, gbc_sectionSelector);
		y++;  // Next row
		
		if(error == EntryProblem.EMPTY_SECTION)
		{
			JLabel errorMessage = new JLabel("Please select a section");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JLabel selectStudentLabel = new JLabel("Select Student:");
		selectStudentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		selectStudentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_selectStudentLabel = new GridBagConstraints();
		gbc_selectStudentLabel.anchor = GridBagConstraints.EAST;
		gbc_selectStudentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectStudentLabel.gridx = 0;
		gbc_selectStudentLabel.gridy = y;
		gbc_selectStudentLabel.weighty = 0.1;
		add(selectStudentLabel, gbc_selectStudentLabel);
		
		gbc_studentSelector = new GridBagConstraints();
		gbc_studentSelector.insets = new Insets(0, 0, 5, 0);
		gbc_studentSelector.anchor = GridBagConstraints.WEST;
		gbc_studentSelector.gridx = 1;
		gbc_studentSelector.gridy = y;
		gbc_studentSelector.weighty = 0.1;
		add(studentSelector, gbc_studentSelector);
		y++;  // Next row
		
		if(error == EntryProblem.EMPTY_STUDENT)
		{
			JLabel errorMessage = new JLabel("Please select a student");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		if(error == EntryProblem.DUPLICATED_STUDENT)
		{
			JLabel errorMessage = new JLabel("That student already has an entry, please select another.");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = y;
		add(separator, gbc_separator);
		y++;  // Next row
		
		JLabel createNewStudentLabel = new JLabel("Or:");
		createNewStudentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createNewStudentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_createNewStudentLabel = new GridBagConstraints();
		gbc_createNewStudentLabel.anchor = GridBagConstraints.EAST;
		gbc_createNewStudentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_createNewStudentLabel.gridx = 0;
		gbc_createNewStudentLabel.gridy = y;
		gbc_createNewStudentLabel.weighty = 0.1;
		add(createNewStudentLabel, gbc_createNewStudentLabel);
		
		GridBagConstraints gbc_createNewStudentSelector = new GridBagConstraints();
		gbc_createNewStudentSelector.insets = new Insets(0, 0, 5, 0);
		gbc_createNewStudentSelector.anchor = GridBagConstraints.WEST;
		gbc_createNewStudentSelector.gridx = 1;
		gbc_createNewStudentSelector.gridy = y;
		gbc_createNewStudentSelector.weighty = 0.1;
		add(createNewStudentSelector, gbc_createNewStudentSelector);
		y++;  // Next row
		
		if(error == EntryProblem.EMPTY_STUDENT)
		{
			JLabel errorMessage = new JLabel("Or please create a new student in this section");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JLabel studentFirstNameLabel = new JLabel("New Student First Name:");
		studentFirstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		studentFirstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_studentFirstNameLabel = new GridBagConstraints();
		gbc_studentFirstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_studentFirstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_studentFirstNameLabel.gridx = 0;
		gbc_studentFirstNameLabel.gridy = y;
		gbc_studentFirstNameLabel.weighty = 0.1;
		add(studentFirstNameLabel, gbc_studentFirstNameLabel);
		
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameField.anchor = GridBagConstraints.WEST;
		gbc_firstNameField.gridx = 1;
		gbc_firstNameField.gridy = y;
		gbc_firstNameField.weighty = 0.1;
		add(firstNameField, gbc_firstNameField);
		y++;  // Next row
		
		if(error == EntryProblem.EMPTY_STUDENT_FNAME)
		{
			JLabel errorMessage = new JLabel("Please enter a first name for this student");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JLabel studentLastNameLabel = new JLabel("New Student Last Name:");
		studentLastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		studentLastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_studentLastNameLabel = new GridBagConstraints();
		gbc_studentLastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_studentLastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_studentLastNameLabel.gridx = 0;
		gbc_studentLastNameLabel.gridy = y;
		gbc_studentLastNameLabel.weighty = 0.1;
		add(studentLastNameLabel, gbc_studentLastNameLabel);
		
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameField.anchor = GridBagConstraints.WEST;
		gbc_lastNameField.gridx = 1;
		gbc_lastNameField.gridy = y;
		gbc_lastNameField.weighty = 0.1;
		add(lastNameField, gbc_lastNameField);
		y++;  // Next row
		
		if(error == EntryProblem.EMPTY_STUDENT_LNAME)
		{
			JLabel errorMessage = new JLabel("Please enter a last name for this student");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JLabel newStudentBuidLabel = new JLabel("New Student BUID");
		newStudentBuidLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		newStudentBuidLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_newStudentBuidLabel = new GridBagConstraints();
		gbc_newStudentBuidLabel.insets = new Insets(0, 0, 5, 5);
		gbc_newStudentBuidLabel.anchor = GridBagConstraints.EAST;
		gbc_newStudentBuidLabel.gridx = 0;
		gbc_newStudentBuidLabel.gridy = y;
		gbc_newStudentBuidLabel.weighty = 0.1;
		add(newStudentBuidLabel, gbc_newStudentBuidLabel);
		
		GridBagConstraints gbc_buidField = new GridBagConstraints();
		gbc_buidField.insets = new Insets(0, 0, 5, 0);
		gbc_buidField.anchor = GridBagConstraints.WEST;
		gbc_buidField.gridx = 1;
		gbc_buidField.gridy = y;
		gbc_buidField.weighty = 0.1;
		add(buidField, gbc_buidField);
		y++;  // Next row
		
		if(error == EntryProblem.EMPTY_STUDENT_BUID)
		{
			JLabel errorMessage = new JLabel("Please enter a BUID for this student");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JLabel newStudentEnrollmentLabel = new JLabel("New Student Enrollment Status:");
		newStudentEnrollmentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		newStudentEnrollmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_newStudentEnrollmentLabel = new GridBagConstraints();
		gbc_newStudentEnrollmentLabel.anchor = GridBagConstraints.EAST;
		gbc_newStudentEnrollmentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_newStudentEnrollmentLabel.gridx = 0;
		gbc_newStudentEnrollmentLabel.gridy = y;
		gbc_newStudentEnrollmentLabel.weighty = 0.1;
		add(newStudentEnrollmentLabel, gbc_newStudentEnrollmentLabel);
		
		GridBagConstraints gbc_studentStatusSelector = new GridBagConstraints();
		gbc_studentStatusSelector.insets = new Insets(0, 0, 5, 0);
		gbc_studentStatusSelector.anchor = GridBagConstraints.WEST;
		gbc_studentStatusSelector.gridx = 1;
		gbc_studentStatusSelector.gridy = y;
		gbc_studentStatusSelector.weighty = 0.1;
		add(studentStatusSelector, gbc_studentStatusSelector);
		y++;  // Next row
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = y;
		add(separator_1, gbc_separator_1);
		y++;  // Next row
		
		if(error == EntryProblem.NO_ERROR)
		{
			JLabel successMessage = new JLabel("Entry created!");
			successMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 0);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(successMessage, gbc);
			y++;  // Next row
		}
		
		JButton createButton = new JButton("Create");
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createButton.addActionListener(new AddEntryController(rootView, user, course, this));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.insets = new Insets(0, 0, 0, 5);
		gbc_createButton.gridx = 0;
		gbc_createButton.gridy = y;
		gbc_createButton.weighty = 0.3;
		add(createButton, gbc_createButton);
		
		
		JButton closeButton = new JButton("Close");
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = y;
		gbc_closeButton.weighty = 0.3;
		add(closeButton, gbc_closeButton);
	}
	
	public void updateStudentListing()
	{
		remove(studentSelector);
		studentSelector = new JComboBox<Student>();
		studentSelector.setEnabled(true);
		studentSelector.setModel(createStudentComboBoxModel());
		add(studentSelector, gbc_studentSelector);
	}
	
	private DefaultComboBoxModel<Student> createStudentComboBoxModel()
	{
		DefaultComboBoxModel<Student> model = new DefaultComboBoxModel<Student>();
		
		// Load data
		for(Student s: getSelectedSection().getStudents())
		{
			model.addElement(s);
		}
		
		return model;
	}
	
	public Section getSelectedSection()
	{
		return (Section) sectionSelector.getSelectedItem();
	}
	
	public Student getSelectedStudent()
	{
		return (Student) studentSelector.getSelectedItem();
	}
	
	public boolean creatingNewStudent()
	{
		return createNewStudentSelector.isSelected();
	}
	
	public String getEnteredFirstName()
	{
		return firstNameField.getText();
	}
	
	public String getEnteredLastName()
	{
		return lastNameField.getText();
	}
	
	public String getEnteredBUID()
	{
		return buidField.getText();
	}
	
	public StudentStatus getSelectedStatus()
	{
		return (StudentStatus) studentStatusSelector.getSelectedItem();
	}

}
