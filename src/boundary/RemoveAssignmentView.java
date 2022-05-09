package boundary;

import javax.swing.JPanel;

import entity.Course;
import entity.User;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import controller.ClosePopupWindowController;
import controller.RemoveAssignmentController;
import controller.RemoveAssignmentController.RemoveAssignmentProblem;

/**
 *  Allows the user to select an assignment to delete from the course template.
 *  @author Alex Titus
 */
public class RemoveAssignmentView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = 2864723418309376655L;
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private User user;
	private Course course;
	private RemoveAssignmentsSubPanelView assignmentsPanel;

	public RemoveAssignmentView(IGraderFrame rootView, IGraderFrame parentView,
			User user, Course course)
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
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Remove an Assignment");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weighty = 0.2;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		JLabel selectAnAssignmentLabel = new JLabel("Select an assignment to remove:");
		selectAnAssignmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_selectAnAssignmentLabel = new GridBagConstraints();
		gbc_selectAnAssignmentLabel.weighty = 0.1;
		gbc_selectAnAssignmentLabel.insets = new Insets(0, 5, 5, 5);
		gbc_selectAnAssignmentLabel.gridx = 0;
		gbc_selectAnAssignmentLabel.gridy = 1;
		add(selectAnAssignmentLabel, gbc_selectAnAssignmentLabel);
		
		JScrollPane assignmentsScrollPane = new JScrollPane();
		GridBagConstraints gbc_assignmentsScrollPane = new GridBagConstraints();
		gbc_assignmentsScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_assignmentsScrollPane.weighty = 0.65;
		gbc_assignmentsScrollPane.gridwidth = 2;
		gbc_assignmentsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_assignmentsScrollPane.gridx = 0;
		gbc_assignmentsScrollPane.gridy = 2;
		add(assignmentsScrollPane, gbc_assignmentsScrollPane);
		
		assignmentsPanel = new RemoveAssignmentsSubPanelView(course);
		assignmentsScrollPane.setViewportView(assignmentsPanel.getPanelContent());
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removeButton.addActionListener(new RemoveAssignmentController(rootView, parentView, this, user, course));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.weightx = 0.5;
		gbc_removeButton.weighty = 0.15;
		gbc_removeButton.insets = new Insets(0, 0, 0, 5);
		gbc_removeButton.gridx = 0;
		gbc_removeButton.gridy = 3;
		add(removeButton, gbc_removeButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.weightx = 0.5;
		gbc_closeButton.weighty = 0.15;
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 3;
		add(closeButton, gbc_closeButton);
	}
	
	/**
	 *  @return  The remove assignment screen as a JPanel
	 */
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
		setupPanelWithMessage(RemoveAssignmentProblem.NO_ERROR);
	}
	
	public void showError(RemoveAssignmentProblem error)
	{
		removeAll();
		setupPanelWithMessage(error);
	}
	
	private void setupPanelWithMessage(RemoveAssignmentProblem message)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;  // First row
		
		JLabel title = new JLabel("Remove an Assignment");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weighty = 0.2;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 0;
		gbc_title.gridy = y;
		add(title, gbc_title);
		y++;  // Next row
		
		JLabel selectAnAssignmentLabel = new JLabel("Select an assignment to remove:");
		selectAnAssignmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_selectAnAssignmentLabel = new GridBagConstraints();
		gbc_selectAnAssignmentLabel.weighty = 0.1;
		gbc_selectAnAssignmentLabel.insets = new Insets(0, 5, 5, 5);
		gbc_selectAnAssignmentLabel.gridx = 0;
		gbc_selectAnAssignmentLabel.gridy = y;
		add(selectAnAssignmentLabel, gbc_selectAnAssignmentLabel);
		y++;  // Next row
		
		JScrollPane assignmentsScrollPane = new JScrollPane();
		GridBagConstraints gbc_assignmentsScrollPane = new GridBagConstraints();
		gbc_assignmentsScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_assignmentsScrollPane.weighty = 0.65;
		gbc_assignmentsScrollPane.gridwidth = 2;
		gbc_assignmentsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_assignmentsScrollPane.gridx = 0;
		gbc_assignmentsScrollPane.gridy = y;
		add(assignmentsScrollPane, gbc_assignmentsScrollPane);
		y++;  // Next row
		
		assignmentsScrollPane.setViewportView(assignmentsPanel.getPanelContent());
		
		if(message == RemoveAssignmentProblem.NO_ERROR)
		{
			JLabel messageLabel = new JLabel("Assignment(s) removed.");
			messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(0, 5, 5, 5);
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = y;
			add(messageLabel, gbc);
			y++;  // Next row
		}
		if(message == RemoveAssignmentProblem.NO_SELECTION)
		{
			JLabel messageLabel = new JLabel("Please select an assignment.");
			messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			messageLabel.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(0, 5, 5, 5);
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = y;
			add(messageLabel, gbc);
			y++;  // Next row
		}
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removeButton.addActionListener(new RemoveAssignmentController(rootView, parentView, this, user, course));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.weightx = 0.5;
		gbc_removeButton.weighty = 0.15;
		gbc_removeButton.insets = new Insets(0, 0, 0, 5);
		gbc_removeButton.gridx = 0;
		gbc_removeButton.gridy = y;
		add(removeButton, gbc_removeButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.weightx = 0.5;
		gbc_closeButton.weighty = 0.15;
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = y;
		add(closeButton, gbc_closeButton);
	}
	
	/**
	 *  @return  A list of the RealAssignment IDs of assignments the user marked for deletion
	 */
	public ArrayList<Long> getSelectedAssignmentsIDs()
	{
		return assignmentsPanel.getSelectedAssignmentsIDs();
	}

}
