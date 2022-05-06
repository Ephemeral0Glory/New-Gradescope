package boundary;

import javax.swing.JPanel;

import entity.Course;
import entity.User;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import controller.AddAssignmentController;
import controller.ClosePopupWindowController;
import controller.AddAssignmentController.*;

import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;

public class AddAssignmentView extends JPanel implements IGraderScreen {
	private IGraderFrame rootView;
	private IGraderFrame parentFrame;
	private User user;
	private Course course;
	private JTextField assignmentNameField;
	private JTextField assignmentWeightField;
	
	public AddAssignmentView(IGraderFrame rootView, IGraderFrame parentFrame, User user, Course course) {
		this.rootView = rootView;
		this.parentFrame = parentFrame;
		this.user = user;
		this.course = course;
		setupPanel();
		
	}
	
	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Create an Assignment");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		JLabel assignmentName = new JLabel("Assignment Name: ");
		GridBagConstraints gbc_assignmentName = new GridBagConstraints();
		gbc_assignmentName.anchor = GridBagConstraints.EAST;
		gbc_assignmentName.insets = new Insets(0, 0, 5, 5);
		gbc_assignmentName.gridx = 1;
		gbc_assignmentName.gridy = 2;
		add(assignmentName, gbc_assignmentName);
		
		assignmentNameField = new JTextField();
		GridBagConstraints gbc_assignmentNameField = new GridBagConstraints();
		gbc_assignmentNameField.insets = new Insets(0, 0, 5, 0);
		gbc_assignmentNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_assignmentNameField.gridx = 2;
		gbc_assignmentNameField.gridy = 2;
		add(assignmentNameField, gbc_assignmentNameField);
		assignmentNameField.setColumns(10);
		
		JLabel assignmentWeight = new JLabel("Assignment Weight:");
		GridBagConstraints gbc_assignmentWeight = new GridBagConstraints();
		gbc_assignmentWeight.anchor = GridBagConstraints.EAST;
		gbc_assignmentWeight.insets = new Insets(0, 0, 5, 5);
		gbc_assignmentWeight.gridx = 1;
		gbc_assignmentWeight.gridy = 4;
		add(assignmentWeight, gbc_assignmentWeight);
		
		assignmentWeightField = new JTextField();
		GridBagConstraints gbc_assignmentWeightField = new GridBagConstraints();
		gbc_assignmentWeightField.insets = new Insets(0, 0, 5, 0);
		gbc_assignmentWeightField.fill = GridBagConstraints.HORIZONTAL;
		gbc_assignmentWeightField.gridx = 2;
		gbc_assignmentWeightField.gridy = 4;
		add(assignmentWeightField, gbc_assignmentWeightField);
		assignmentWeightField.setColumns(10);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new AddAssignmentController(rootView, parentFrame, user, this));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.insets = new Insets(0, 0, 0, 5);
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = 8;
		add(createButton, gbc_createButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = 8;
		add(cancelButton, gbc_cancelButton);
	}
	
	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		// Ignore

	}

	public Course getCourse() {
		return course;
	}

	public String getName() {
		return assignmentNameField.getText();
	}

	public float getWeight() {
		return Float.valueOf(assignmentWeightField.getText());
	}

	public void showError(AssignmentProblem error) {
		removeAll();
		setupPanelWithError(error);
	}

	private void setupPanelWithError(AssignmentProblem error) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 1;
		
		JLabel titleLabel = new JLabel("Create an Assignment");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		add(titleLabel, gbc_titleLabel);
		y += 2;
		
		JLabel assignmentName = new JLabel("Assignment Name: ");
		GridBagConstraints gbc_assignmentName = new GridBagConstraints();
		gbc_assignmentName.anchor = GridBagConstraints.EAST;
		gbc_assignmentName.insets = new Insets(0, 0, 5, 5);
		gbc_assignmentName.gridx = 1;
		gbc_assignmentName.gridy = y;
		add(assignmentName, gbc_assignmentName);
		
		assignmentNameField = new JTextField();
		GridBagConstraints gbc_assignmentNameField = new GridBagConstraints();
		gbc_assignmentNameField.insets = new Insets(0, 0, 5, 0);
		gbc_assignmentNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_assignmentNameField.gridx = 2;
		gbc_assignmentNameField.gridy = y;
		add(assignmentNameField, gbc_assignmentNameField);
		assignmentNameField.setColumns(10);
		y++;

		if (error == AssignmentProblem.EMPTY_NAME) {
			JLabel errorMessage = new JLabel("Please enter an assignment name.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}

		if (error == AssignmentProblem.DUPLICATED_NAME) {
			JLabel errorMessage = new JLabel("An assignment already exists with this name. Please try again");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel assignmentWeight = new JLabel("Assignment Weight:");
		GridBagConstraints gbc_assignmentWeight = new GridBagConstraints();
		gbc_assignmentWeight.anchor = GridBagConstraints.EAST;
		gbc_assignmentWeight.insets = new Insets(0, 0, 5, 5);
		gbc_assignmentWeight.gridx = 1;
		gbc_assignmentWeight.gridy = y;
		add(assignmentWeight, gbc_assignmentWeight);
		
		assignmentWeightField = new JTextField();
		GridBagConstraints gbc_assignmentWeightField = new GridBagConstraints();
		gbc_assignmentWeightField.insets = new Insets(0, 0, 5, 0);
		gbc_assignmentWeightField.fill = GridBagConstraints.HORIZONTAL;
		gbc_assignmentWeightField.gridx = 2;
		gbc_assignmentWeightField.gridy = y;
		add(assignmentWeightField, gbc_assignmentWeightField);
		assignmentWeightField.setColumns(10);
		y += 2;

		if (error == AssignmentProblem.BAD_FLOAT) {
			JLabel errorMessage = new JLabel("Please enter a valid assignment weight between 0.0 and 100.0");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 2;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new AddAssignmentController(rootView, parentFrame, user, this));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.insets = new Insets(0, 0, 0, 5);
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = y;
		add(createButton, gbc_createButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = 8;
		add(cancelButton, gbc_cancelButton);
	}

}
