package boundary;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import controller.UpdateNameWeightController.UpdateNWProblem;
import entity.*;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class EditAssignmentView extends JPanel implements IGraderScreen {
	private static final long serialVersionUID = -3143859592543000705L;
	private IGraderFrame rootView;
	private IGraderFrame parentFrame;
	private User user;
	private Course course;
	private RealAssignment parent;
	private JTextField updateNameField;
	private JTextField updateWeightField;
	private ArrayList<JTextField> subAssignmentNamesFields;
	private ArrayList<JTextField> subAssignmentWeightsFields;
	
	public EditAssignmentView(IGraderFrame rootView, IGraderFrame parentFrame, User user, Course course, RealAssignment parent, ArrayList<JTextField> subAssignmentNamesFields, ArrayList<JTextField> subAssignmentWeightsFields) {
		this.rootView = rootView;
		this.parentFrame = parentFrame;
		this.user = user;
		this.course = course;
		this.parent = parent;
		this.subAssignmentNamesFields = subAssignmentNamesFields;
		this.subAssignmentWeightsFields = subAssignmentWeightsFields;
		setupPanel(UpdateNWProblem.NO_ERROR);
		
	}

	private void setupPanel(UpdateNWProblem error) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;
		
		JLabel titleLabel = new JLabel("Edit an Assignment");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 4;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		add(titleLabel, gbc_titleLabel);
		y += 2;
		
		JLabel updateNameLabel = new JLabel("Update Name: ");
		updateNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_updateNameLabel = new GridBagConstraints();
		gbc_updateNameLabel.anchor = GridBagConstraints.EAST;
		gbc_updateNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_updateNameLabel.gridx = 1;
		gbc_updateNameLabel.gridy = y;
		add(updateNameLabel, gbc_updateNameLabel);
		
		updateNameField = new JTextField();
		updateNameField.setText(parent.getName());
		GridBagConstraints gbc_updateNameField = new GridBagConstraints();
		gbc_updateNameField.gridwidth = 2;
		gbc_updateNameField.insets = new Insets(0, 0, 5, 0);
		gbc_updateNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_updateNameField.gridx = 2;
		gbc_updateNameField.gridy = y;
		add(updateNameField, gbc_updateNameField);
		updateNameField.setColumns(10);
		y++;

		if (error == UpdateNWProblem.EMPTY_NAME) {
			JLabel errorMessage = new JLabel("Please enter an assignment name.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			gbc.gridwidth = 2;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel updateWeightLabel = new JLabel("Update Weight: "); 
		updateWeightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_updateWeightLabel = new GridBagConstraints();
		gbc_updateWeightLabel.anchor = GridBagConstraints.EAST;
		gbc_updateWeightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_updateWeightLabel.gridx = 1;
		gbc_updateWeightLabel.gridy = y;
		add(updateWeightLabel, gbc_updateWeightLabel);
		
		updateWeightField = new JTextField();
		updateWeightField.setText(Float.toString(parent.getWeight()));
		GridBagConstraints gbc_updateWeightField = new GridBagConstraints();
		gbc_updateWeightField.gridwidth = 2;
		gbc_updateWeightField.insets = new Insets(0, 0, 5, 0);
		gbc_updateWeightField.fill = GridBagConstraints.HORIZONTAL;
		gbc_updateWeightField.gridx = 2;
		gbc_updateWeightField.gridy = y;
		add(updateWeightField, gbc_updateWeightField);
		updateWeightField.setColumns(10);
		y += 2;

		if (error == UpdateNWProblem.BAD_FLOAT) {
			JLabel errorMessage = new JLabel("Please enter an assignment weight between 0.0 and 100.0");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			gbc.gridwidth = 2;
			add(errorMessage, gbc);
			y++;
		}

		if (!(subAssignmentNamesFields.isEmpty() && subAssignmentWeightsFields.isEmpty())) {
			JLabel subAssignmentLabel = new JLabel("Subassignments to add:");
			GridBagConstraints gbc_subAssignmentLabel = new GridBagConstraints();
			gbc_subAssignmentLabel.insets = new Insets(0, 0, 5, 5);
			gbc_subAssignmentLabel.gridx = 1;
			gbc_subAssignmentLabel.gridy = y;
			add(subAssignmentLabel, gbc_subAssignmentLabel);
			
			JLabel subNameLabel = new JLabel("Name: ");
			GridBagConstraints gbc_subNameLabel = new GridBagConstraints();
			gbc_subNameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_subNameLabel.gridx = 2;
			gbc_subNameLabel.gridy = y;
			add(subNameLabel, gbc_subNameLabel);
			
			JLabel subWeightLabel = new JLabel("Weight:");
			GridBagConstraints gbc_subWeightLabel = new GridBagConstraints();
			gbc_subWeightLabel.insets = new Insets(0, 0, 5, 0);
			gbc_subWeightLabel.gridx = 3;
			gbc_subWeightLabel.gridy = y;
			add(subWeightLabel, gbc_subWeightLabel);
			y++;

			for (int i = 0; i < subAssignmentNamesFields.size(); i++) {
				JTextField subAssignmentNameField = subAssignmentNamesFields.get(i);
				GridBagConstraints gbc_subAssignmentNameField = new GridBagConstraints();
				gbc_subAssignmentNameField.anchor = GridBagConstraints.WEST;
				gbc_subAssignmentNameField.insets = new Insets(0, 0, 5, 5);
				gbc_subAssignmentNameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_subAssignmentNameField.gridx = 1;
				gbc_subAssignmentNameField.gridy = y;
				gbc_subAssignmentNameField.weighty = 0.1;
				add(subAssignmentNameField, gbc_subAssignmentNameField);
				subAssignmentNameField.setColumns(10);
				
				JTextField subAssignmentWeightField = subAssignmentWeightsFields.get(i);
				GridBagConstraints gbc_subAssignmentWeightField = new GridBagConstraints();
				gbc_subAssignmentWeightField.anchor = GridBagConstraints.WEST;
				gbc_subAssignmentWeightField.insets = new Insets(0, 0, 5, 0);
				gbc_subAssignmentWeightField.fill = GridBagConstraints.NONE;
				gbc_subAssignmentWeightField.gridx = 2;
				gbc_subAssignmentWeightField.gridy = y;
				gbc_subAssignmentWeightField.weighty = 0.1;
				add(subAssignmentWeightField, gbc_subAssignmentWeightField);
				subAssignmentWeightField.setColumns(10);
				y++;
			}

			// TODO add error handling if one is invalid

		}
		
		JButton addSubButton = new JButton("Add Subassignment");
		// TODO add action listener to add a subassignment
		addSubButton.addActionListener(new EditAssignmentAddSubController(rootView, this));
		GridBagConstraints gbc_addSubButton = new GridBagConstraints();
		gbc_addSubButton.insets = new Insets(0, 0, 5, 5);
		gbc_addSubButton.gridx = 1;
		gbc_addSubButton.gridy = y;
		add(addSubButton, gbc_addSubButton);
		
		JButton editSubButton = new JButton("Edit Subassignments");
		editSubButton.addActionListener(new OpenSelectAssignmentToEditController(rootView, user, course, parent));
		GridBagConstraints gbc_editSubButton = new GridBagConstraints();
		gbc_editSubButton.insets = new Insets(0, 0, 5, 5);
		gbc_editSubButton.gridx = 2;
		gbc_editSubButton.gridy = y;
		add(editSubButton, gbc_editSubButton);
		y++;
		
		JButton updateElemButton = new JButton("Update Name/Weight");
		updateElemButton.addActionListener(new UpdateNameWeightController(rootView, parentFrame, user, course, parent, this)); 
		GridBagConstraints gbc_updateElemButton = new GridBagConstraints();
		gbc_updateElemButton.insets = new Insets(0, 0, 0, 5);
		gbc_updateElemButton.gridx = 1;
		gbc_updateElemButton.gridy = y;
		add(updateElemButton, gbc_updateElemButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
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
		// ignore
	}

	public String getUpdatedName() {
		return updateNameField.getText();
	}

	public Float getUpdatedWeight() {
		return Float.valueOf(updateWeightField.getText());
	}

	public void showError(UpdateNWProblem error) {
		removeAll();
		setupPanel(error);
	}

	public void showNewSubAssignment()
	{
		showError(UpdateNWProblem.NO_ERROR);
	}

	public void addSubAssignmentFields() {
		subAssignmentNamesFields.add(new JTextField());
		subAssignmentWeightsFields.add(new JTextField());
	}

}
