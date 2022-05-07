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
		courseCodeDataLabel.setText(course.getCode());
		courseNameDataLabel.setText(course.getName());
	}

	public void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Add a student");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 4;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 1;
		add(titleLabel, gbc_titleLabel);
		
		JLabel courseCodeLabel = new JLabel("Course Code: ");
		courseCodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_courseCodeLabel = new GridBagConstraints();
		gbc_courseCodeLabel.anchor = GridBagConstraints.EAST;
		gbc_courseCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_courseCodeLabel.gridx = 2;
		gbc_courseCodeLabel.gridy = 3;
		add(courseCodeLabel, gbc_courseCodeLabel);
		
		courseCodeDataLabel = new JLabel("***");
		GridBagConstraints gbc_courseCodeDataLabel = new GridBagConstraints();
		gbc_courseCodeDataLabel.insets = new Insets(0, 0, 5, 0);
		gbc_courseCodeDataLabel.gridx = 3;
		gbc_courseCodeDataLabel.gridy = 3;
		add(courseCodeDataLabel, gbc_courseCodeDataLabel);
		
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_courseNameLabel = new GridBagConstraints();
		gbc_courseNameLabel.anchor = GridBagConstraints.EAST;
		gbc_courseNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_courseNameLabel.gridx = 2;
		gbc_courseNameLabel.gridy = 4;
		add(courseNameLabel, gbc_courseNameLabel);
		
		courseNameDataLabel = new JLabel("***");
		GridBagConstraints gbc_courseNameDataLabel = new GridBagConstraints();
		gbc_courseNameDataLabel.insets = new Insets(0, 0, 5, 0);
		gbc_courseNameDataLabel.gridx = 3;
		gbc_courseNameDataLabel.gridy = 4;
		add(courseNameDataLabel, gbc_courseNameDataLabel);
		
		JLabel sectionLabel = new JLabel("Section: ");
		sectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_sectionLabel = new GridBagConstraints();
		gbc_sectionLabel.anchor = GridBagConstraints.EAST;
		gbc_sectionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sectionLabel.gridx = 2;
		gbc_sectionLabel.gridy = 5;
		add(sectionLabel, gbc_sectionLabel);
		
		sectionSelector = new JComboBox<Section>();
		sectionSelector.setModel(createComboBoxModel());
		sectionSelector.setSelectedIndex(course.getSections().size() - 1);
		sectionSelector.addActionListener(new AddStudentSectionChangeController(rootView, this));
		GridBagConstraints gbc_sectionSelector = new GridBagConstraints();
		gbc_sectionSelector.insets = new Insets(0, 0, 5, 0);
		gbc_sectionSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_sectionSelector.gridx = 3;
		gbc_sectionSelector.gridy = 5;
		add(sectionSelector, gbc_sectionSelector);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 2;
		gbc_firstNameLabel.gridy = 7;
		add(firstNameLabel, gbc_firstNameLabel);
		
		firstNameField = new JTextField();
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameField.gridx = 3;
		gbc_firstNameField.gridy = 7;
		add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.gridx = 2;
		gbc_lastNameLabel.gridy = 8;
		add(lastNameLabel, gbc_lastNameLabel);
		
		lastNameField = new JTextField();
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameField.gridx = 3;
		gbc_lastNameField.gridy = 8;
		add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(10);
		
		JLabel buidLabel = new JLabel("BU ID: ");
		GridBagConstraints gbc_buidLabel = new GridBagConstraints();
		gbc_buidLabel.anchor = GridBagConstraints.EAST;
		gbc_buidLabel.insets = new Insets(0, 0, 5, 5);
		gbc_buidLabel.gridx = 2;
		gbc_buidLabel.gridy = 9;
		add(buidLabel, gbc_buidLabel);
		
		buidField = new JTextField();
		GridBagConstraints gbc_buidField = new GridBagConstraints();
		gbc_buidField.insets = new Insets(0, 0, 5, 0);
		gbc_buidField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buidField.gridx = 3;
		gbc_buidField.gridy = 9;
		add(buidField, gbc_buidField);
		buidField.setColumns(10);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new AddStudentController(rootView, parentFrame, this, user, course));
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmButton.gridx = 2;
		gbc_confirmButton.gridy = 11;
		add(confirmButton, gbc_confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = 11;
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
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 1;
		
		JLabel titleLabel = new JLabel("Add a student");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 4;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		add(titleLabel, gbc_titleLabel);
		y += 2;
		
		JLabel courseCodeLabel = new JLabel("Course Code: ");
		courseCodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_courseCodeLabel = new GridBagConstraints();
		gbc_courseCodeLabel.anchor = GridBagConstraints.EAST;
		gbc_courseCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_courseCodeLabel.gridx = 2;
		gbc_courseCodeLabel.gridy = y;
		add(courseCodeLabel, gbc_courseCodeLabel);
		
		courseCodeDataLabel = new JLabel("***");
		GridBagConstraints gbc_courseCodeDataLabel = new GridBagConstraints();
		gbc_courseCodeDataLabel.insets = new Insets(0, 0, 5, 0);
		gbc_courseCodeDataLabel.gridx = 3;
		gbc_courseCodeDataLabel.gridy = y;
		add(courseCodeDataLabel, gbc_courseCodeDataLabel);
		y++;
		
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_courseNameLabel = new GridBagConstraints();
		gbc_courseNameLabel.anchor = GridBagConstraints.EAST;
		gbc_courseNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_courseNameLabel.gridx = 2;
		gbc_courseNameLabel.gridy = y;
		add(courseNameLabel, gbc_courseNameLabel);
		
		JLabel courseNameDataLabel = new JLabel("***");
		GridBagConstraints gbc_courseNameDataLabel = new GridBagConstraints();
		gbc_courseNameDataLabel.insets = new Insets(0, 0, 5, 0);
		gbc_courseNameDataLabel.gridx = 3;
		gbc_courseNameDataLabel.gridy = y;
		add(courseNameDataLabel, gbc_courseNameDataLabel);
		y++;
		
		JLabel sectionLabel = new JLabel("Section: ");
		sectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_sectionLabel = new GridBagConstraints();
		gbc_sectionLabel.anchor = GridBagConstraints.EAST;
		gbc_sectionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sectionLabel.gridx = 2;
		gbc_sectionLabel.gridy = y;
		add(sectionLabel, gbc_sectionLabel);
		
		sectionSelector = new JComboBox<Section>();
		sectionSelector.setModel(createComboBoxModel());
		sectionSelector.setSelectedIndex(course.getSections().size() - 1);
		sectionSelector.addActionListener(new AddStudentSectionChangeController(rootView, this));
		GridBagConstraints gbc_sectionSelector = new GridBagConstraints();
		gbc_sectionSelector.insets = new Insets(0, 0, 5, 0);
		gbc_sectionSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_sectionSelector.gridx = 3;
		gbc_sectionSelector.gridy = y;
		add(sectionSelector, gbc_sectionSelector);
		y+= 2;

		if (error == AddStudentProblem.NO_SECTION) {
			JLabel errorMessage = new JLabel("Please choose a section.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 2;
		gbc_firstNameLabel.gridy = y;
		add(firstNameLabel, gbc_firstNameLabel);
		
		firstNameField = new JTextField();
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameField.gridx = 3;
		gbc_firstNameField.gridy = y;
		add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(10);
		y++;

		if (error == AddStudentProblem.NO_F_NAME) {
			JLabel errorMessage = new JLabel("Please enter a first name.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.gridx = 2;
		gbc_lastNameLabel.gridy = y;
		add(lastNameLabel, gbc_lastNameLabel);
		
		lastNameField = new JTextField();
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameField.gridx = 3;
		gbc_lastNameField.gridy = y;
		add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(10);
		y++;

		if (error == AddStudentProblem.NO_L_NAME) {
			JLabel errorMessage = new JLabel("Please enter a last name.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel buidLabel = new JLabel("BU ID: ");
		GridBagConstraints gbc_buidLabel = new GridBagConstraints();
		gbc_buidLabel.anchor = GridBagConstraints.EAST;
		gbc_buidLabel.insets = new Insets(0, 0, 5, 5);
		gbc_buidLabel.gridx = 2;
		gbc_buidLabel.gridy = y;
		add(buidLabel, gbc_buidLabel);
		
		buidField = new JTextField();
		GridBagConstraints gbc_buidField = new GridBagConstraints();
		gbc_buidField.insets = new Insets(0, 0, 5, 0);
		gbc_buidField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buidField.gridx = 3;
		gbc_buidField.gridy = y;
		add(buidField, gbc_buidField);
		buidField.setColumns(10);
		y += 2;

		if (error == AddStudentProblem.NO_BUID) {
			JLabel errorMessage = new JLabel("Please enter a BU ID.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}

		if (error == AddStudentProblem.BAD_BUID) {
			JLabel errorMessage = new JLabel("Please enter a valid BU ID in the format 'UXXXXXXXX'.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}

		if (error == AddStudentProblem.DUPLICATED_STUDENT) {
			JLabel errorMessage = new JLabel("A student with this BU ID already exists, please try again.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new AddStudentController(rootView, parentFrame, this, user, course));
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmButton.gridx = 2;
		gbc_confirmButton.gridy = y;
		add(confirmButton, gbc_confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = y;
		add(cancelButton, gbc_cancelButton);
	}

}
