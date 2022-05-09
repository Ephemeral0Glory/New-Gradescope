package boundary;


import java.util.ArrayList;

import javax.swing.JPanel;

import entity.*;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller.*;
import java.awt.Font;

public class SelectAssignmentToEditView extends JPanel implements IGraderScreen {
	private static final long serialVersionUID = -3213344054405525640L;
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
	
	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Select an Assignment to Edit");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.insets = new Insets(5, 5, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		gbc_titleLabel.weighty = 0.15;
		add(titleLabel, gbc_titleLabel);
		
		JScrollPane listScrollPane = new JScrollPane();
		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.gridwidth = 3;
		gbc_listScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = 1;
		gbc_listScrollPane.weighty = 0.65;
		add(listScrollPane, gbc_listScrollPane);
		
		if (parent.getNumSubAssignments() != 0) {
			ArrayList<Gradeable> subAssignments = parent.getSubAssignments();
			Gradeable[] gradeables = new Gradeable[subAssignments.size()];
			for (int i = 0; i < subAssignments.size(); i++) {
				gradeables[i] = subAssignments.get(i);
			}
			assignmentList = new JList<Gradeable>(gradeables);
			assignmentList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		else {
			assignmentList = new JList<Gradeable>();
			assignmentList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		listScrollPane.setViewportView(assignmentList);
		
		JButton editButton = new JButton("Edit");
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		editButton.addActionListener(new OpenEditAssignmentController(rootView, user, course, this));
		editButton.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 0;
		gbc_editButton.gridy = 2;
		gbc_editButton.weightx = 1.0;
		gbc_editButton.weighty = 0.2;
		add(editButton, gbc_editButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removeButton.addActionListener(new RemoveSelectedAssignmentController(rootView, parentFrame, user, course, parent, this));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.gridx = 1;
		gbc_removeButton.gridy = 2;
		gbc_removeButton.weightx = 1.0;
		gbc_removeButton.weighty = 0.2;
		add(removeButton, gbc_removeButton);
		
		JButton cancelButton = new JButton("Close");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = 2;
		gbc_cancelButton.weightx = 1.0;
		gbc_cancelButton.weighty = 0.2;
		add(cancelButton, gbc_cancelButton);
	}

	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		// Update self
		removeAll();
		repaint();
		setupPanel();
		
		// Update parent application too
		parentFrame.update();
		parentFrame.display();
	}
	
	public Gradeable getSelectedAssignment()
	{
		return assignmentList.getSelectedValue();
	}



}
