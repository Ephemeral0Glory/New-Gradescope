package boundary;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import controller.UpdateNameWeightController.UpdateNWProblem;
import entity.*;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;

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
	
	public EditAssignmentView(IGraderFrame rootView, IGraderFrame parentFrame,
			User user, Course course, RealAssignment parent,
			ArrayList<JTextField> subAssignmentNamesFields,
			ArrayList<JTextField> subAssignmentWeightsFields) {
		this.rootView = rootView;
		this.parentFrame = parentFrame;
		this.user = user;
		this.course = course;
		this.parent = parent;
		this.subAssignmentNamesFields = subAssignmentNamesFields;
		this.subAssignmentWeightsFields = subAssignmentWeightsFields;
		
		// Initialize assignment text fields
		updateNameField = new JTextField();
		updateNameField.setText(parent.getName());
		updateNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateWeightField = new JTextField();
		updateWeightField.setText(Float.toString(Math.round(parent.getWeight() * 100)));
		updateWeightField.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(5, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		gbc_titleLabel.weighty = 0.3;
		add(titleLabel, gbc_titleLabel);
		y += 2;
		
		JLabel updateNameLabel = new JLabel("Update Name: ");
		updateNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_updateNameLabel = new GridBagConstraints();
		gbc_updateNameLabel.anchor = GridBagConstraints.EAST;
		gbc_updateNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_updateNameLabel.gridx = 1;
		gbc_updateNameLabel.gridy = y;
		gbc_updateNameLabel.weighty = 0.1;
		add(updateNameLabel, gbc_updateNameLabel);
		
		GridBagConstraints gbc_updateNameField = new GridBagConstraints();
		gbc_updateNameField.anchor = GridBagConstraints.WEST;
		gbc_updateNameField.gridwidth = 2;
		gbc_updateNameField.insets = new Insets(0, 0, 5, 0);
		gbc_updateNameField.fill = GridBagConstraints.NONE;
		gbc_updateNameField.gridx = 2;
		gbc_updateNameField.gridy = y;
		gbc_updateNameField.weighty = 0.1;
		add(updateNameField, gbc_updateNameField);
		updateNameField.setColumns(10);
		y++;

		if (error == UpdateNWProblem.EMPTY_NAME) {
			JLabel errorMessage = new JLabel("Please enter an assignment name.");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			gbc.gridwidth = 2;
			add(errorMessage, gbc);
			y++;
		}

		if (error == UpdateNWProblem.DUPLICATE_NAME) {
			JLabel errorMessage = new JLabel("That assignment already exists, please choose a different name.");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			gbc.gridwidth = 2;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel updateWeightLabel = new JLabel("Update Weight (%): "); 
		updateWeightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateWeightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_updateWeightLabel = new GridBagConstraints();
		gbc_updateWeightLabel.anchor = GridBagConstraints.EAST;
		gbc_updateWeightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_updateWeightLabel.gridx = 1;
		gbc_updateWeightLabel.gridy = y;
		gbc_updateWeightLabel.weighty = 0.1;
		add(updateWeightLabel, gbc_updateWeightLabel);
		
		GridBagConstraints gbc_updateWeightField = new GridBagConstraints();
		gbc_updateWeightField.anchor = GridBagConstraints.WEST;
		gbc_updateWeightField.gridwidth = 2;
		gbc_updateWeightField.insets = new Insets(0, 0, 5, 0);
		gbc_updateWeightField.fill = GridBagConstraints.NONE;
		gbc_updateWeightField.gridx = 2;
		gbc_updateWeightField.gridy = y;
		gbc_updateWeightField.weighty = 0.1;
		add(updateWeightField, gbc_updateWeightField);
		updateWeightField.setColumns(10);
		y += 2;

		if (error == UpdateNWProblem.BAD_FLOAT) {
			JLabel errorMessage = new JLabel("Please enter an assignment weight between 0.0 and 100.0");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
			subAssignmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_subAssignmentLabel = new GridBagConstraints();
			gbc_subAssignmentLabel.insets = new Insets(0, 0, 5, 5);
			gbc_subAssignmentLabel.gridx = 0;
			gbc_subAssignmentLabel.gridy = y;
			gbc_subAssignmentLabel.weighty = 0.1;
			add(subAssignmentLabel, gbc_subAssignmentLabel);
			
			JLabel subNameLabel = new JLabel("Name: ");
			subNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_subNameLabel = new GridBagConstraints();
			gbc_subNameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_subNameLabel.gridx = 1;
			gbc_subNameLabel.gridy = y;
			gbc_subNameLabel.weighty = 0.1;
			add(subNameLabel, gbc_subNameLabel);
			
			JLabel subWeightLabel = new JLabel("Weight (%):");
			subWeightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_subWeightLabel = new GridBagConstraints();
			gbc_subWeightLabel.insets = new Insets(0, 0, 5, 0);
			gbc_subWeightLabel.gridx = 2;
			gbc_subWeightLabel.gridy = y;
			gbc_subWeightLabel.weighty = 0.1;
			add(subWeightLabel, gbc_subWeightLabel);
			y++;

			for (int i = 0; i < subAssignmentNamesFields.size(); i++) {
				JTextField subAssignmentNameField = subAssignmentNamesFields.get(i);
				subAssignmentNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_subAssignmentNameField = new GridBagConstraints();
				gbc_subAssignmentNameField.anchor = GridBagConstraints.WEST;
				gbc_subAssignmentNameField.insets = new Insets(0, 0, 5, 5);
				gbc_subAssignmentNameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_subAssignmentNameField.gridx = 1;
				gbc_subAssignmentNameField.gridy = y;
				add(subAssignmentNameField, gbc_subAssignmentNameField);
				subAssignmentNameField.setColumns(10);
				
				JTextField subAssignmentWeightField = subAssignmentWeightsFields.get(i);
				subAssignmentWeightField.setFont(new Font("Tahoma", Font.PLAIN, 16));
				GridBagConstraints gbc_subAssignmentWeightField = new GridBagConstraints();
				gbc_subAssignmentWeightField.anchor = GridBagConstraints.WEST;
				gbc_subAssignmentWeightField.insets = new Insets(0, 0, 5, 0);
				gbc_subAssignmentWeightField.fill = GridBagConstraints.NONE;
				gbc_subAssignmentWeightField.gridx = 2;
				gbc_subAssignmentWeightField.gridy = y;
				add(subAssignmentWeightField, gbc_subAssignmentWeightField);
				subAssignmentWeightField.setColumns(10);
				y++;
			}

			// TODO add error handling if one is invalid

		}
		
		JButton addSubButton = new JButton("Add Subassignment");
		addSubButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addSubButton.addActionListener(new EditAssignmentAddSubController(rootView, this));
		GridBagConstraints gbc_addSubButton = new GridBagConstraints();
		gbc_addSubButton.insets = new Insets(0, 0, 5, 5);
		gbc_addSubButton.gridx = 1;
		gbc_addSubButton.gridy = y;
		gbc_addSubButton.weighty = 0.1;
		add(addSubButton, gbc_addSubButton);
		
		JButton editSubButton = new JButton("Edit Subassignments");
		editSubButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		editSubButton.addActionListener(new OpenSelectAssignmentToEditController(rootView, user, course, parent));
		GridBagConstraints gbc_editSubButton = new GridBagConstraints();
		gbc_editSubButton.insets = new Insets(0, 0, 5, 5);
		gbc_editSubButton.gridx = 2;
		gbc_editSubButton.gridy = y;
		gbc_editSubButton.weighty = 0.1;
		add(editSubButton, gbc_editSubButton);
		y++;
		
		JButton updateElemButton = new JButton("Update Name/Weight");
		updateElemButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateElemButton.addActionListener(new UpdateNameWeightController(rootView, parentFrame,
				user, course, parent, this)); 
		GridBagConstraints gbc_updateElemButton = new GridBagConstraints();
		gbc_updateElemButton.insets = new Insets(0, 0, 0, 5);
		gbc_updateElemButton.gridx = 1;
		gbc_updateElemButton.gridy = y;
		gbc_updateElemButton.weighty = 0.2;
		add(updateElemButton, gbc_updateElemButton);
		
		JButton cancelButton = new JButton("Close");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = y;
		gbc_cancelButton.weighty = 0.2;
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

	public String getUpdatedName() {
		return updateNameField.getText();
	}

	public Float getUpdatedWeight() {
		return Float.valueOf(updateWeightField.getText());
	}

	public void showError(UpdateNWProblem error) {
		removeAll();
		repaint();
		setupPanel(error);
	}

	public void showNewSubAssignment()
	{
		removeAll();
		repaint();
		showError(UpdateNWProblem.NO_ERROR);
	}

	public void addSubAssignmentFields() {
		subAssignmentNamesFields.add(new JTextField());
		subAssignmentWeightsFields.add(new JTextField());
	}
	
	public ArrayList<String> getSubAssignmentNames()
	{
		ArrayList<String> names = new ArrayList<String>(subAssignmentNamesFields.size());
		
		// Get data
		for(JTextField nameField: subAssignmentNamesFields)
		{
			names.add(nameField.getText());
		}
		
		return names;
	}
	
	public ArrayList<Float> getSubAssignmentWeights()
	{
		ArrayList<Float> weights = new ArrayList<Float>(subAssignmentWeightsFields.size());
		
		// Get data
		for(JTextField weightField: subAssignmentWeightsFields)
		{
			weights.add( Float.valueOf(weightField.getText()) / 100.0f );
		}
		
		return weights;
	}

	public HashMap<String, ArrayList<JTextField>> getSubAssignmentFields(){
		HashMap<String, ArrayList<JTextField>> assignmentsField = new HashMap<>();

		assignmentsField.put("names", subAssignmentNamesFields);
		assignmentsField.put("weights", subAssignmentWeightsFields);

		return assignmentsField;

	}

}
