package boundary;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.SwingConstants;

import controller.CreateNewCourseController;
import controller.CreateNewCourseController.CreateCourseProblem;
import controller.OpenMainMenuController;
import entity.Gradebook;
import entity.Season;
import entity.Semester;
import entity.User;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SpinnerNumberModel;

/**
 *  The create new course screen.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class CreateNewCourseView extends JPanel implements IGraderScreen {
	private static final long serialVersionUID = -5928149496471688167L;
	private JTextField nameField;
	private JTextField codeField;
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	private JSpinner newSemesterYearSelector;
	private JComboBox<Season> newSemesterSeasonSelector;
	private JCheckBox createNewSemesterBox;
	private JComboBox<Semester> semesterSelector;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param user  The current user
	 *  @param gradebook  The gradebook being modified
	 */
	public CreateNewCourseView(IGraderFrame rootView, User user, Gradebook gradebook) {
		super();
		this.rootView = rootView;
		this.user = user;
		this.gradebook = gradebook;
		setupPanel();
	}
	
	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Create a New Course");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
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
		semesterSelector.setSelectedIndex(-1);
		GridBagConstraints gbc_semesterSelectBox = new GridBagConstraints();
		gbc_semesterSelectBox.anchor = GridBagConstraints.WEST;
		gbc_semesterSelectBox.insets = new Insets(0, 0, 5, 0);
		gbc_semesterSelectBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_semesterSelectBox.gridx = 1;
		gbc_semesterSelectBox.gridy = 1;
		add(semesterSelector, gbc_semesterSelectBox);
		
		createNewSemesterBox = new JCheckBox("Create new semester");
		GridBagConstraints gbc_createNewSemesterBox = new GridBagConstraints();
		gbc_createNewSemesterBox.anchor = GridBagConstraints.WEST;
		gbc_createNewSemesterBox.insets = new Insets(0, 0, 5, 0);
		gbc_createNewSemesterBox.gridx = 1;
		gbc_createNewSemesterBox.gridy = 2;
		add(createNewSemesterBox, gbc_createNewSemesterBox);
		
		JLabel semesterYearLabel = new JLabel("New Semester Name:");
		semesterYearLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_semesterYearLabel = new GridBagConstraints();
		gbc_semesterYearLabel.anchor = GridBagConstraints.EAST;
		gbc_semesterYearLabel.insets = new Insets(0, 0, 5, 5);
		gbc_semesterYearLabel.gridx = 0;
		gbc_semesterYearLabel.gridy = 3;
		add(semesterYearLabel, gbc_semesterYearLabel);
		
		newSemesterYearSelector = new JSpinner();
		newSemesterYearSelector.setModel(new SpinnerNumberModel(new Integer(2022), new Integer(1854), null, new Integer(1)));
		newSemesterYearSelector.setEditor(new JSpinner.NumberEditor(newSemesterYearSelector, "#"));
		GridBagConstraints gbc_newSemesterYearSelector = new GridBagConstraints();
		gbc_newSemesterYearSelector.anchor = GridBagConstraints.WEST;
		gbc_newSemesterYearSelector.insets = new Insets(0, 0, 5, 0);
		gbc_newSemesterYearSelector.gridx = 1;
		gbc_newSemesterYearSelector.gridy = 3;
		add(newSemesterYearSelector, gbc_newSemesterYearSelector);
		
		JLabel semesterSeasonLabel = new JLabel("New Semester Season:");
		semesterSeasonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_semesterSeasonLabel = new GridBagConstraints();
		gbc_semesterSeasonLabel.anchor = GridBagConstraints.EAST;
		gbc_semesterSeasonLabel.insets = new Insets(0, 0, 5, 5);
		gbc_semesterSeasonLabel.gridx = 0;
		gbc_semesterSeasonLabel.gridy = 4;
		add(semesterSeasonLabel, gbc_semesterSeasonLabel);
		
		newSemesterSeasonSelector = new JComboBox<Season>();
		newSemesterSeasonSelector.setModel(new DefaultComboBoxModel<Season>(Season.values()));
		GridBagConstraints gbc_newSemesterSeasonSelector = new GridBagConstraints();
		gbc_newSemesterSeasonSelector.anchor = GridBagConstraints.WEST;
		gbc_newSemesterSeasonSelector.insets = new Insets(0, 0, 5, 0);
		gbc_newSemesterSeasonSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_newSemesterSeasonSelector.gridx = 1;
		gbc_newSemesterSeasonSelector.gridy = 4;
		add(newSemesterSeasonSelector, gbc_newSemesterSeasonSelector);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 5;
		add(separator, gbc_separator);
		
		JLabel nameLabel = new JLabel("Course name: ");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 6;
		add(nameLabel, gbc_nameLabel);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 6;
		add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_codeLabel = new GridBagConstraints();
		gbc_codeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_codeLabel.anchor = GridBagConstraints.EAST;
		gbc_codeLabel.gridx = 0;
		gbc_codeLabel.gridy = 7;
		add(codeLabel, gbc_codeLabel);
		
		codeField = new JTextField();
		GridBagConstraints gbc_codeField = new GridBagConstraints();
		gbc_codeField.insets = new Insets(0, 0, 5, 0);
		gbc_codeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeField.gridx = 1;
		gbc_codeField.gridy = 7;
		add(codeField, gbc_codeField);
		codeField.setColumns(10);
		
		JButton createCourseButton = new JButton("Create");
		createCourseButton.addActionListener(new CreateNewCourseController(rootView, this, user, gradebook));
		GridBagConstraints gbc_createCourseButton = new GridBagConstraints();
		gbc_createCourseButton.insets = new Insets(0, 0, 0, 5);
		gbc_createCourseButton.gridx = 0;
		gbc_createCourseButton.gridy = 9;
		add(createCourseButton, gbc_createCourseButton);
		
		JButton cancelCourseButton = new JButton("Cancel");
		cancelCourseButton.addActionListener(new OpenMainMenuController(rootView, user));
		GridBagConstraints gbc_cancelCourseButton = new GridBagConstraints();
		gbc_cancelCourseButton.gridx = 1;
		gbc_cancelCourseButton.gridy = 9;
		add(cancelCourseButton, gbc_cancelCourseButton);
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

	/**
	 *  @return  The create new course screen, as a JPanel
	 */
	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		// Ignore
	}
	
	/**
	 * @return The course name entered by the user
	 */
	public String getEnteredCoursename() {
		return nameField.getText();
	}

	/**
	 * @return The course code entered by the user
	 */
	public String getEnteredCoursecode() {
		return codeField.getText();
	}
	
	/**
	 *  @return  The year for a new semester, as chosen by the user
	 */
	public int getEnteredSemesterYear()
	{
		return ((SpinnerNumberModel) newSemesterYearSelector.getModel()).getNumber().intValue();
	}
	
	/**
	 *  @return  Whether the create new semester box has been marked
	 */
	public boolean creatingNewSemester()
	{
		return createNewSemesterBox.isSelected();
	}
	
	/**
	 *  @return  The index of the selected semester. -1 means no selection.
	 */
	public int getSelectedSemesterIndex()
	{
		return semesterSelector.getSelectedIndex();
	}
	
	/**
	 *  @return  The selected Season for the new semester chosen by the user
	 */
	public Season getEnteredSemesterSeason()
	{
		return (Season) newSemesterSeasonSelector.getSelectedItem();
	}
	
	/**
	 *  @return The semester selected by the user. Null if there is no selection.
	 */
	public Semester getEnteredSemester()
	{
		return (Semester) semesterSelector.getSelectedItem();
	}
	
	/**
	 *  Updates the screen to show the error message.
	 *  @param error  The error to display a message for
	 */
	public void showCourseCreationFailed(CreateCourseProblem error) {
		removeAll();
		repaint();
		setupPanelWithErrorMessage(error);
	}
	
	private void setupPanelWithErrorMessage(CreateCourseProblem error)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;  // First row
		
		JLabel titleLabel = new JLabel("Create a New Course");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		add(titleLabel, gbc_titleLabel);
		y++;  // Next row
		
		JLabel selectSemesterLabel = new JLabel("Select Semester:");
		selectSemesterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_selectSemesterLabel = new GridBagConstraints();
		gbc_selectSemesterLabel.anchor = GridBagConstraints.EAST;
		gbc_selectSemesterLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectSemesterLabel.gridx = 0;
		gbc_selectSemesterLabel.gridy = y;
		add(selectSemesterLabel, gbc_selectSemesterLabel);
		
		GridBagConstraints gbc_semesterSelectBox = new GridBagConstraints();
		gbc_semesterSelectBox.anchor = GridBagConstraints.WEST;
		gbc_semesterSelectBox.insets = new Insets(0, 0, 5, 0);
		gbc_semesterSelectBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_semesterSelectBox.gridx = 1;
		gbc_semesterSelectBox.gridy = y;
		add(semesterSelector, gbc_semesterSelectBox);
		y++;  // Next row
		
		if(error.equals(CreateCourseProblem.SELECT_SEMESTER))
		{
			JLabel errorMessage = new JLabel("Please select a semester or create a new one.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 5);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		GridBagConstraints gbc_createNewSemesterBox = new GridBagConstraints();
		gbc_createNewSemesterBox.anchor = GridBagConstraints.WEST;
		gbc_createNewSemesterBox.insets = new Insets(0, 0, 5, 0);
		gbc_createNewSemesterBox.gridx = 1;
		gbc_createNewSemesterBox.gridy = y;
		add(createNewSemesterBox, gbc_createNewSemesterBox);
		y++;  // Next row
		
		JLabel semesterYearLabel = new JLabel("New Semester Name:");
		semesterYearLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_semesterYearLabel = new GridBagConstraints();
		gbc_semesterYearLabel.anchor = GridBagConstraints.EAST;
		gbc_semesterYearLabel.insets = new Insets(0, 0, 5, 5);
		gbc_semesterYearLabel.gridx = 0;
		gbc_semesterYearLabel.gridy = y;
		add(semesterYearLabel, gbc_semesterYearLabel);
		
		GridBagConstraints gbc_newSemesterYearSelector = new GridBagConstraints();
		gbc_newSemesterYearSelector.anchor = GridBagConstraints.WEST;
		gbc_newSemesterYearSelector.insets = new Insets(0, 0, 5, 0);
		gbc_newSemesterYearSelector.gridx = 1;
		gbc_newSemesterYearSelector.gridy = y;
		add(newSemesterYearSelector, gbc_newSemesterYearSelector);
		y++;  // Next row
		
		JLabel semesterSeasonLabel = new JLabel("New Semester Season:");
		semesterSeasonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_semesterSeasonLabel = new GridBagConstraints();
		gbc_semesterSeasonLabel.anchor = GridBagConstraints.EAST;
		gbc_semesterSeasonLabel.insets = new Insets(0, 0, 5, 5);
		gbc_semesterSeasonLabel.gridx = 0;
		gbc_semesterSeasonLabel.gridy = y;
		add(semesterSeasonLabel, gbc_semesterSeasonLabel);
		
		GridBagConstraints gbc_newSemesterSeasonSelector = new GridBagConstraints();
		gbc_newSemesterSeasonSelector.anchor = GridBagConstraints.WEST;
		gbc_newSemesterSeasonSelector.insets = new Insets(0, 0, 5, 0);
		gbc_newSemesterSeasonSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_newSemesterSeasonSelector.gridx = 1;
		gbc_newSemesterSeasonSelector.gridy = y;
		add(newSemesterSeasonSelector, gbc_newSemesterSeasonSelector);
		y++;  // Next row
		
		if(error.equals(CreateCourseProblem.DUPLICATE_SEMESTER))
		{
			JLabel errorMessage = new JLabel("This semester already exists, please select it above.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 5);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = y;
		add(separator, gbc_separator);
		y++;  // Next row
		
		JLabel nameLabel = new JLabel("Course name: ");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = y;
		add(nameLabel, gbc_nameLabel);
		
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = y;
		add(nameField, gbc_nameField);
		nameField.setColumns(10);
		y++;  // Next row
		
		if(error.equals(CreateCourseProblem.BAD_COURSENAME))
		{
			JLabel errorMessage = new JLabel("Please enter a name for this course.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 5);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_codeLabel = new GridBagConstraints();
		gbc_codeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_codeLabel.anchor = GridBagConstraints.EAST;
		gbc_codeLabel.gridx = 0;
		gbc_codeLabel.gridy = y;
		add(codeLabel, gbc_codeLabel);
		
		GridBagConstraints gbc_codeField = new GridBagConstraints();
		gbc_codeField.insets = new Insets(0, 0, 5, 0);
		gbc_codeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeField.gridx = 1;
		gbc_codeField.gridy = y;
		add(codeField, gbc_codeField);
		codeField.setColumns(10);
		y++;  // Next row
		
		if(error.equals(CreateCourseProblem.BAD_COURSECODE))
		{
			JLabel errorMessage = new JLabel("Please enter a code for this course.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 5);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		if(error.equals(CreateCourseProblem.DUPLICATE_COURSE))
		{
			JLabel errorMessage = new JLabel("This course already exists for this semester.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 10, 5);
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JButton createCourseButton = new JButton("Create");
		createCourseButton.addActionListener(new CreateNewCourseController(rootView, this, user, gradebook));
		GridBagConstraints gbc_createCourseButton = new GridBagConstraints();
		gbc_createCourseButton.insets = new Insets(0, 0, 0, 5);
		gbc_createCourseButton.gridx = 0;
		gbc_createCourseButton.gridy = y;
		add(createCourseButton, gbc_createCourseButton);
		
		JButton cancelCourseButton = new JButton("Cancel");
		cancelCourseButton.addActionListener(new OpenMainMenuController(rootView, user));
		GridBagConstraints gbc_cancelCourseButton = new GridBagConstraints();
		gbc_cancelCourseButton.gridx = 1;
		gbc_cancelCourseButton.gridy = y;
		add(cancelCourseButton, gbc_cancelCourseButton);
	}
}
