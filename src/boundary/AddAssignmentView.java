package boundary;

import javax.swing.JPanel;

import entity.Course;
import entity.User;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagConstraints;


import controller.*;
import controller.AddAssignmentController.AssignmentProblem;

import javax.swing.JLabel;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AddAssignmentView extends JPanel implements IGraderScreen {
	private IGraderFrame rootView;
	private IGraderFrame parentFrame;
	private User user;
	private Course course;
	private JTextField assignmentNameField;
	private JTextField assignmentWeightField;
	private ArrayList<JTextField> subAssignmentNamesFields;
	private ArrayList<JTextField> subAssignmentWeightsFields;
	
	public AddAssignmentView(IGraderFrame rootView, IGraderFrame parentFrame, User user, Course course, ArrayList<JTextField> subAssignmentNamesFields, ArrayList<JTextField> subAssignmentWeightsFields) {
		this.rootView = rootView;
		this.parentFrame = parentFrame;
		this.user = user;
		this.course = course;
		this.subAssignmentNamesFields = subAssignmentNamesFields;
		this.subAssignmentWeightsFields = subAssignmentWeightsFields;
		setupPanel(AssignmentProblem.NO_ERROR);
	}
	
	private void setupPanel(AssignmentProblem error) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		int y = 0;
		
		JLabel titleLabel = new JLabel("Create an Assignment");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		gbc_titleLabel.weightx = 0.3;
		gbc_titleLabel.weighty = 0.3;
		add(titleLabel, gbc_titleLabel);
		y += 2;
		
		JLabel assignmentName = new JLabel("Assignment Name: ");
		GridBagConstraints gbc_assignmentName = new GridBagConstraints();
		gbc_assignmentName.anchor = GridBagConstraints.EAST;
		gbc_assignmentName.insets = new Insets(0, 0, 5, 5);
		gbc_assignmentName.gridx = 1;
		gbc_assignmentName.gridy = y;
		gbc_assignmentName.weightx = 0.1;
		gbc_assignmentName.weighty = 0.1;
		add(assignmentName, gbc_assignmentName);
		
		assignmentNameField = new JTextField();
		GridBagConstraints gbc_assignmentNameField = new GridBagConstraints();
		gbc_assignmentNameField.insets = new Insets(0, 0, 5, 0);
		gbc_assignmentNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_assignmentNameField.gridx = 2;
		gbc_assignmentNameField.gridy = y;
		gbc_assignmentNameField.weightx = 0.1;
		gbc_assignmentNameField.weighty = 0.1;
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
		gbc_assignmentWeight.weightx = 0.1;
		gbc_assignmentWeight.weighty = 0.1;		
		add(assignmentWeight, gbc_assignmentWeight);
		
		assignmentWeightField = new JTextField();
		GridBagConstraints gbc_assignmentWeightField = new GridBagConstraints();
		gbc_assignmentWeightField.insets = new Insets(0, 0, 5, 0);
		gbc_assignmentWeightField.fill = GridBagConstraints.HORIZONTAL;
		gbc_assignmentWeightField.gridx = 2;
		gbc_assignmentWeightField.gridy = y;
		gbc_assignmentWeightField.weightx = 0.1;
		gbc_assignmentWeightField.weighty = 0.1;
		add(assignmentWeightField, gbc_assignmentWeightField);
		assignmentWeightField.setColumns(10);
		y+=2;

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
		
		if (!(subAssignmentNamesFields.isEmpty() && subAssignmentWeightsFields.isEmpty())) {
			JLabel subAssignmentLabel = new JLabel("Current Subassignments:");
			subAssignmentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_subAssignmentLabel = new GridBagConstraints();
			gbc_subAssignmentLabel.anchor = GridBagConstraints.EAST;
			gbc_subAssignmentLabel.insets = new Insets(0, 0, 5, 5);
			gbc_subAssignmentLabel.gridx = 0;
			gbc_subAssignmentLabel.gridy = y;
			gbc_subAssignmentLabel.weightx = 0.1;
			gbc_subAssignmentLabel.weighty = 0.1;
			add(subAssignmentLabel, gbc_subAssignmentLabel);
			
			JLabel subNameLabel = new JLabel("Name");
			GridBagConstraints gbc_subNameLabel = new GridBagConstraints();
			gbc_subNameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_subNameLabel.gridx = 1;
			gbc_subNameLabel.gridy = y;
			gbc_subNameLabel.weightx = 0.1;
			gbc_subNameLabel.weighty = 0.1;
			add(subNameLabel, gbc_subNameLabel);
			
			JLabel subWeightLabel = new JLabel("Weight");
			GridBagConstraints gbc_subWeightLabel = new GridBagConstraints();
			gbc_subWeightLabel.insets = new Insets(0, 0, 5, 0);
			gbc_subWeightLabel.gridx = 2;
			gbc_subWeightLabel.gridy = y; // formerly 5
			gbc_subWeightLabel.weightx = 0.1;
			gbc_subWeightLabel.weighty = 0.1;
			add(subWeightLabel, gbc_subWeightLabel);
			y++; // before loop, currently at 6	
			
			for (int i = 0; i < subAssignmentNamesFields.size(); i++) {
				JTextField subAssignmentNameField = subAssignmentNamesFields.get(i);
				GridBagConstraints gbc_subAssignmentNameField = new GridBagConstraints();
				gbc_subAssignmentNameField.insets = new Insets(0, 0, 5, 5);
				gbc_subAssignmentNameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_subAssignmentNameField.gridx = 1;
				gbc_subAssignmentNameField.gridy = y;
				gbc_subAssignmentNameField.weightx = 0.1;
				gbc_subAssignmentNameField.weighty = 0.1;
				add(subAssignmentNameField, gbc_subAssignmentNameField);
				subAssignmentNameField.setColumns(10);
				
				JTextField subAssignmentWeightField = subAssignmentWeightsFields.get(i);
				GridBagConstraints gbc_subAssignmentWeightField = new GridBagConstraints();
				gbc_subAssignmentWeightField.insets = new Insets(0, 0, 5, 0);
				gbc_subAssignmentWeightField.fill = GridBagConstraints.HORIZONTAL;
				gbc_subAssignmentWeightField.gridx = 2;
				gbc_subAssignmentWeightField.gridy = y;
				gbc_subAssignmentWeightField.weightx = 0.1;
				gbc_subAssignmentWeightField.weighty = 0.1;
				add(subAssignmentWeightField, gbc_subAssignmentWeightField);
				subAssignmentWeightField.setColumns(10);
				y++;
			}
			// add error handling if one of these is invalid
			if (error == AssignmentProblem.EMPTY_SUB_NAME) {
				JLabel errorMessage = new JLabel("One or more of your subassignments is missing a name. Please try again");
				errorMessage.setForeground(Color.RED);
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.anchor = GridBagConstraints.NORTH;
				gbc.gridx = 2;
				gbc.gridy = y;
				
				
				add(errorMessage, gbc);
				y++;
			}
			if (error == AssignmentProblem.DUPLICATE_SUB_NAME) {
				JLabel errorMessage = new JLabel("One or more of your subassignments has the same name. Please try again");
				errorMessage.setForeground(Color.RED);
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.anchor = GridBagConstraints.NORTH;
				gbc.gridx = 2;
				gbc.gridy = y;
				
				
				add(errorMessage, gbc);
				y++;
			}
			if (error == AssignmentProblem.BAD_SUB_WEIGHT) {
				JLabel errorMessage = new JLabel("One or more of your subassignments has an invalid weight. Please try again");
				errorMessage.setForeground(Color.RED);
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.anchor = GridBagConstraints.NORTH;
				gbc.gridx = 2;
				gbc.gridy = y;
				
				
				add(errorMessage, gbc);
				y++;
			}
			if (error == AssignmentProblem.INVALID_SUB_WEIGHT) {
				JLabel errorMessage = new JLabel("The total weight of subassignments must be between 0.0 and 100. Please try again");
				errorMessage.setForeground(Color.RED);
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.anchor = GridBagConstraints.NORTH;
				gbc.gridx = 2;
				gbc.gridy = y;
				
				
				add(errorMessage, gbc);
				y++;
			}
			y += 2;
		}
		JButton addSubButton = new JButton("Add Subassignment");
		addSubButton.setHorizontalAlignment(SwingConstants.RIGHT);
		// add action listener to recall this page to update with new row
		addSubButton.addActionListener(new AddAssignmentAddSubController(rootView, this));
		GridBagConstraints gbc_addSubButton = new GridBagConstraints();
		gbc_addSubButton.anchor = GridBagConstraints.EAST;
		gbc_addSubButton.insets = new Insets(0, 0, 0, 5);
		gbc_addSubButton.gridx = 0;
		gbc_addSubButton.gridy = y;
		add(addSubButton, gbc_addSubButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new AddAssignmentController(rootView, parentFrame, user, this));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.insets = new Insets(0, 0, 0, 5);
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = y;
		add(createButton, gbc_createButton);
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = y;
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

	public ArrayList<JTextField> getSubAssignmentNamesFields() {
		return subAssignmentNamesFields;
	}

	public ArrayList<JTextField> getSubAssignmentWeightsFields() {
		return subAssignmentWeightsFields;
	}

	public void showError(AssignmentProblem error) {
		removeAll();
		setupPanel(error);
	}

	public void addSubAssignmentFields() {
		subAssignmentNamesFields.add(new JTextField());
		subAssignmentWeightsFields.add(new JTextField());
	}

}