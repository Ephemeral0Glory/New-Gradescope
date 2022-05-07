package boundary;

import controller.*;
import controller.AddStudentController.AddStudentProblem;
import entity.*;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class AddStudentView extends JPanel implements IGraderScreen {
	private static final long serialVersionUID = -5806686822559957555L;
	private IGraderFrame rootView;
	private IGraderFrame parentFrame;
	private User user;
	private Course course;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField buidField;
	private JLabel courseCodeDataLabel;
	private JLabel courseNameDataLabel;
	private JComboBox<Section> sectionSelector;
	
	public AddStudentView(IGraderFrame rootView, IGraderFrame parentFrame, User user, Course course) {
		this.rootView = rootView;
		this.parentFrame = parentFrame;
		this.user = user;
		this.course = course;
		setupPanel();
	}

	public void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Add a student");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		gbc_titleLabel.weighty = 0.3;
		add(titleLabel, gbc_titleLabel);
		
		JLabel courseCodeLabel = new JLabel("Course Code: ");
		courseCodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_courseCodeLabel = new GridBagConstraints();
		gbc_courseCodeLabel.anchor = GridBagConstraints.EAST;
		gbc_courseCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_courseCodeLabel.gridx = 0;
		gbc_courseCodeLabel.gridy = 1;
		gbc_courseCodeLabel.weighty = 0.1;
		add(courseCodeLabel, gbc_courseCodeLabel);
		
		courseCodeDataLabel = new JLabel(course.getCode());
		GridBagConstraints gbc_courseCodeDataLabel = new GridBagConstraints();
		gbc_courseCodeDataLabel.insets = new Insets(0, 0, 5, 0);
		gbc_courseCodeDataLabel.gridx = 1;
		gbc_courseCodeDataLabel.gridy = 1;
		gbc_courseCodeDataLabel.weighty = 0.1;
		add(courseCodeDataLabel, gbc_courseCodeDataLabel);
		
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_courseNameLabel = new GridBagConstraints();
		gbc_courseNameLabel.anchor = GridBagConstraints.EAST;
		gbc_courseNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_courseNameLabel.gridx = 0;
		gbc_courseNameLabel.gridy = 2;
		gbc_courseNameLabel.weighty = 0.1;
		add(courseNameLabel, gbc_courseNameLabel);
		
		courseNameDataLabel = new JLabel(course.getName());
		GridBagConstraints gbc_courseNameDataLabel = new GridBagConstraints();
		gbc_courseNameDataLabel.insets = new Insets(0, 0, 5, 0);
		gbc_courseNameDataLabel.gridx = 1;
		gbc_courseNameDataLabel.gridy = 2;
		gbc_courseNameDataLabel.weighty = 0.1;
		add(courseNameDataLabel, gbc_courseNameDataLabel);
		
		JLabel sectionLabel = new JLabel("Section: ");
		sectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_sectionLabel = new GridBagConstraints();
		gbc_sectionLabel.anchor = GridBagConstraints.EAST;
		gbc_sectionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sectionLabel.gridx = 0;
		gbc_sectionLabel.gridy = 3;
		gbc_sectionLabel.weighty = 0.1;
		add(sectionLabel, gbc_sectionLabel);
		
		sectionSelector = new JComboBox<Section>();
		sectionSelector.setModel(createComboBoxModel());
		sectionSelector.setSelectedIndex(course.getSections().size() - 1);
		sectionSelector.addActionListener(new AddStudentSectionChangeController(rootView, this));
		GridBagConstraints gbc_sectionSelector = new GridBagConstraints();
		gbc_sectionSelector.insets = new Insets(0, 0, 5, 0);
		gbc_sectionSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_sectionSelector.gridx = 1;
		gbc_sectionSelector.gridy = 3;
		gbc_sectionSelector.weighty = 0.1;
		add(sectionSelector, gbc_sectionSelector);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 4;
		gbc_firstNameLabel.weighty = 0.1;
		add(firstNameLabel, gbc_firstNameLabel);
		
		firstNameField = new JTextField();
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameField.gridx = 1;
		gbc_firstNameField.gridy = 4;
		gbc_firstNameField.weighty = 0.1;
		add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.gridx = 0;
		gbc_lastNameLabel.gridy = 5;
		gbc_lastNameLabel.weighty = 0.1;
		add(lastNameLabel, gbc_lastNameLabel);
		
		lastNameField = new JTextField();
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameField.gridx = 1;
		gbc_lastNameField.gridy = 5;
		gbc_lastNameField.weighty = 0.1;
		add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(10);
		
		JLabel buidLabel = new JLabel("BU ID: ");
		GridBagConstraints gbc_buidLabel = new GridBagConstraints();
		gbc_buidLabel.anchor = GridBagConstraints.EAST;
		gbc_buidLabel.insets = new Insets(0, 0, 5, 5);
		gbc_buidLabel.gridx = 0;
		gbc_buidLabel.gridy = 6;
		gbc_buidLabel.weighty = 0.1;
		add(buidLabel, gbc_buidLabel);
		
		buidField = new JTextField();
		GridBagConstraints gbc_buidField = new GridBagConstraints();
		gbc_buidField.insets = new Insets(0, 0, 5, 0);
		gbc_buidField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buidField.gridx = 1;
		gbc_buidField.gridy = 6;
		gbc_buidField.weighty = 0.1;
		add(buidField, gbc_buidField);
		buidField.setColumns(10);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new AddStudentController(rootView, parentFrame, this, user, course));
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmButton.gridx = 0;
		gbc_confirmButton.gridy = 7;
		gbc_confirmButton.weighty = 0.3;
		add(confirmButton, gbc_confirmButton);
		
		JButton cancelButton = new JButton("Close");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 7;
		gbc_cancelButton.weighty = 0.3;
		add(cancelButton, gbc_cancelButton);
	}

	private DefaultComboBoxModel<Section> createComboBoxModel() {
		DefaultComboBoxModel<Section> model = new DefaultComboBoxModel<Section>();
		for (Section s: course.getSections()) {
			model.addElement(s);
		}
		return model;
	}

	public String getFirstName() {
		return firstNameField.getText();
	}

	public String getLastName() {
		return lastNameField.getText();
	}

	public String getBUID() {
		return buidField.getText();
	}

	public Section getSelectedSection() {
		return (Section) sectionSelector.getSelectedItem();
	}

	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		// ignore

	}

	public void showError(AddStudentProblem error) {
		removeAll();
		setupPanelWithError(error);
	}

	private void setupPanelWithError(AddStudentProblem error) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;  // First row
		
		JLabel titleLabel = new JLabel("Add a student");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		gbc_titleLabel.weighty = 0.3;
		add(titleLabel, gbc_titleLabel);
		y++;  // Next row
		
		JLabel courseCodeLabel = new JLabel("Course Code: ");
		courseCodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_courseCodeLabel = new GridBagConstraints();
		gbc_courseCodeLabel.anchor = GridBagConstraints.EAST;
		gbc_courseCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_courseCodeLabel.gridx = 0;
		gbc_courseCodeLabel.gridy = y;
		gbc_courseCodeLabel.weighty = 0.1;
		add(courseCodeLabel, gbc_courseCodeLabel);
		
		GridBagConstraints gbc_courseCodeDataLabel = new GridBagConstraints();
		gbc_courseCodeDataLabel.insets = new Insets(0, 0, 5, 0);
		gbc_courseCodeDataLabel.gridx = 1;
		gbc_courseCodeDataLabel.gridy = y;
		gbc_courseCodeDataLabel.weighty = 0.1;
		add(courseCodeDataLabel, gbc_courseCodeDataLabel);
		y++;  // Next row
		
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_courseNameLabel = new GridBagConstraints();
		gbc_courseNameLabel.anchor = GridBagConstraints.EAST;
		gbc_courseNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_courseNameLabel.gridx = 0;
		gbc_courseNameLabel.gridy = y;
		gbc_courseNameLabel.weighty = 0.1;
		add(courseNameLabel, gbc_courseNameLabel);
		
		GridBagConstraints gbc_courseNameDataLabel = new GridBagConstraints();
		gbc_courseNameDataLabel.insets = new Insets(0, 0, 5, 0);
		gbc_courseNameDataLabel.gridx = 1;
		gbc_courseNameDataLabel.gridy = y;
		gbc_courseNameDataLabel.weighty = 0.1;
		add(courseNameDataLabel, gbc_courseNameDataLabel);
		y++;  // Next row
		
		JLabel sectionLabel = new JLabel("Section: ");
		sectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_sectionLabel = new GridBagConstraints();
		gbc_sectionLabel.anchor = GridBagConstraints.EAST;
		gbc_sectionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sectionLabel.gridx = 0;
		gbc_sectionLabel.gridy = y;
		gbc_sectionLabel.weighty = 0.1;
		add(sectionLabel, gbc_sectionLabel);
		
		GridBagConstraints gbc_sectionSelector = new GridBagConstraints();
		gbc_sectionSelector.insets = new Insets(0, 0, 5, 0);
		gbc_sectionSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_sectionSelector.gridx = 1;
		gbc_sectionSelector.gridy = y;
		gbc_sectionSelector.weighty = 0.1;
		add(sectionSelector, gbc_sectionSelector);
		y++;  // Next row

		if (error == AddStudentProblem.NO_SECTION) {
			JLabel errorMessage = new JLabel("Please choose a section.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = y;
		gbc_firstNameLabel.weighty = 0.1;
		add(firstNameLabel, gbc_firstNameLabel);
		
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameField.gridx = 1;
		gbc_firstNameField.gridy = y;
		gbc_firstNameField.weighty = 0.1;
		add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(10);
		y++;  // Next row

		if (error == AddStudentProblem.NO_F_NAME) {
			JLabel errorMessage = new JLabel("Please enter a first name.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.gridx = 0;
		gbc_lastNameLabel.gridy = y;
		gbc_lastNameLabel.weighty = 0.1;
		add(lastNameLabel, gbc_lastNameLabel);
		
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameField.gridx = 1;
		gbc_lastNameField.gridy = y;
		gbc_lastNameField.weighty = 0.1;
		add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(10);
		y++;  // Next row

		if (error == AddStudentProblem.NO_L_NAME) {
			JLabel errorMessage = new JLabel("Please enter a last name.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JLabel buidLabel = new JLabel("BU ID: ");
		GridBagConstraints gbc_buidLabel = new GridBagConstraints();
		gbc_buidLabel.anchor = GridBagConstraints.EAST;
		gbc_buidLabel.insets = new Insets(0, 0, 5, 5);
		gbc_buidLabel.gridx = 0;
		gbc_buidLabel.gridy = y;
		gbc_buidLabel.weighty = 0.1;
		add(buidLabel, gbc_buidLabel);
		
		GridBagConstraints gbc_buidField = new GridBagConstraints();
		gbc_buidField.insets = new Insets(0, 0, 5, 0);
		gbc_buidField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buidField.gridx = 1;
		gbc_buidField.gridy = y;
		gbc_buidField.weighty = 0.1;
		add(buidField, gbc_buidField);
		buidField.setColumns(10);
		y++;  // Next row

		if (error == AddStudentProblem.NO_BUID) {
			JLabel errorMessage = new JLabel("Please enter a BU ID.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}

		if (error == AddStudentProblem.BAD_BUID) {
			JLabel errorMessage = new JLabel("Please enter a valid BU ID in the format 'UXXXXXXXX'.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}

		if (error == AddStudentProblem.DUPLICATED_STUDENT) {
			JLabel errorMessage = new JLabel("A student with this BU ID already exists, please try again.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new AddStudentController(rootView, parentFrame, this, user, course));
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmButton.gridx = 0;
		gbc_confirmButton.gridy = y;
		gbc_confirmButton.weighty = 0.3;
		add(confirmButton, gbc_confirmButton);
		
		JButton cancelButton = new JButton("Close");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = y;
		gbc_cancelButton.weighty = 0.3;
		add(cancelButton, gbc_cancelButton);
	}

}
