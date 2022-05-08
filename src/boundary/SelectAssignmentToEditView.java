package boundary;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.*;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller.*;

public class SelectAssignmentToEditView extends JPanel implements IGraderScreen {
	private IGraderFrame rootView;
	private IGraderFrame parentFrame;
	private User user;
	private Course course;
	private RealAssignment parent;
	private JList<Gradeable> assignmentList;
	
	public SelectAssignmentToEditView(IGraderFrame rootView, IGraderFrame parentFrame, User user, Course course, RealAssignment parent) {
		this.rootView = rootView;
		this.parentFrame = parentFrame;
		this.user = user;
		this.course = course;
		this.parent = parent;
		setupPanel();
		
	}
	
	public void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{182, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Select an Assignment to edit");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		JScrollPane listScrollPane = new JScrollPane();
		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.gridwidth = 3;
		gbc_listScrollPane.gridheight = 8;
		gbc_listScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = 1;
		add(listScrollPane, gbc_listScrollPane);
		
		if (parent.getNumSubAssignments() != 0) {
			ArrayList<Gradeable> subAssignments = parent.getSubAssignments();
			Gradeable[] gradeables = new Gradeable[subAssignments.size()];
			for (int i = 0; i < subAssignments.size(); i++) {
				gradeables[i] = subAssignments.get(i);
			}
			assignmentList = new JList<Gradeable>(gradeables);
		}
		else {
			assignmentList = new JList<Gradeable>();
		}
		listScrollPane.setViewportView(assignmentList);
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new OpenEditAssignmentController(rootView, user, course, parent));
		editButton.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.anchor = GridBagConstraints.EAST;
		gbc_editButton.insets = new Insets(0, 0, 0, 5);
		gbc_editButton.gridx = 0;
		gbc_editButton.gridy = 9;
		add(editButton, gbc_editButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new RemoveSelectedAssignmentController(rootView, parentFrame, user, course, parent, this));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.insets = new Insets(0, 0, 0, 5);
		gbc_removeButton.gridx = 1;
		gbc_removeButton.gridy = 9;
		add(removeButton, gbc_removeButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = 9;
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
	
	public Gradeable getSelectedAssignment()
	{
		return assignmentList.getSelectedValue();
	}



}
