package boundary;

import javax.swing.JPanel;

import entity.Course;
import entity.Section;
import entity.User;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import controller.ClosePopupWindowController;
import controller.RemoveSectionController;
import controller.RemoveSectionController.RemoveSectionProblem;

/**
 *  Allows the user to remove a section from the course.
 *  
 *  @author Alex Titus
 */
public class RemoveSectionView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = -3607573242359109797L;
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private User user;
	private Course course;
	private JList<Section> sectionList;
	private JScrollPane sectionListScrollPane;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The window frame for this popup
	 *  @param parentView  The application window frame
	 *  @param user  The current user
	 *  @param course  The course being modified
	 */
	public RemoveSectionView(IGraderFrame rootView, IGraderFrame parentView, User user, Course course)
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
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Remove a Section");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(0, 5, 5, 0);
		gbc_title.weighty = 0.3;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		JLabel scrollPaneLabel = new JLabel("Select a section to remove:");
		scrollPaneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_scrollPaneLabel = new GridBagConstraints();
		gbc_scrollPaneLabel.gridwidth = 2;
		gbc_scrollPaneLabel.weighty = 0.1;
		gbc_scrollPaneLabel.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneLabel.gridx = 0;
		gbc_scrollPaneLabel.gridy = 1;
		add(scrollPaneLabel, gbc_scrollPaneLabel);
		
		sectionListScrollPane = new JScrollPane();
		sectionListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_sectionListScrollPane = new GridBagConstraints();
		gbc_sectionListScrollPane.gridwidth = 2;
		gbc_sectionListScrollPane.weighty = 0.5;
		gbc_sectionListScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_sectionListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_sectionListScrollPane.gridx = 0;
		gbc_sectionListScrollPane.gridy = 2;
		add(sectionListScrollPane, gbc_sectionListScrollPane);
		
		sectionList = new JList<Section>();
		sectionList.setModel(createListModel());
		sectionList.setSelectedIndex(-1);
		sectionList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sectionListScrollPane.setViewportView(sectionList);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removeButton.addActionListener(new RemoveSectionController(rootView, parentView, this, user, course));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.weighty = 0.2;
		gbc_removeButton.weightx = 0.5;
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.gridx = 0;
		gbc_removeButton.gridy = 3;
		add(removeButton, gbc_removeButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 5, 0);
		gbc_btnClose.weightx = 0.5;
		gbc_btnClose.weighty = 0.2;
		gbc_btnClose.gridx = 1;
		gbc_btnClose.gridy = 3;
		add(closeButton, gbc_btnClose);
	}
	
	private ListModel<Section> createListModel()
	{
		DefaultListModel<Section> model = new DefaultListModel<Section>();
		
		// Fill data
		for(Section s: course.getSections())
		{
			model.addElement(s);
		}
		
		return model;
	}
	
	public void showSuccess()
	{
		removeAll();
		setupPanelWithMessage(RemoveSectionProblem.NO_ERROR);
	}
	
	public void showError(RemoveSectionProblem error)
	{
		removeAll();
		setupPanelWithMessage(error);
	}
	
	private void setupPanelWithMessage(RemoveSectionProblem message)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;  // First row
		
		JLabel title = new JLabel("Remove a Section");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(0, 5, 5, 0);
		gbc_title.weighty = 0.3;
		gbc_title.gridx = 0;
		gbc_title.gridy = y;
		add(title, gbc_title);
		y++;  // Next row
		
		JLabel scrollPaneLabel = new JLabel("Select a section:");
		scrollPaneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_scrollPaneLabel = new GridBagConstraints();
		gbc_scrollPaneLabel.gridwidth = 2;
		gbc_scrollPaneLabel.weighty = 0.1;
		gbc_scrollPaneLabel.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneLabel.gridx = 0;
		gbc_scrollPaneLabel.gridy = y;
		add(scrollPaneLabel, gbc_scrollPaneLabel);
		y++;  // Next row
		
		GridBagConstraints gbc_sectionListScrollPane = new GridBagConstraints();
		gbc_sectionListScrollPane.gridwidth = 2;
		gbc_sectionListScrollPane.weighty = 0.5;
		gbc_sectionListScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_sectionListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_sectionListScrollPane.gridx = 0;
		gbc_sectionListScrollPane.gridy = y;
		add(sectionListScrollPane, gbc_sectionListScrollPane);
		y++;  // Next row
		
		sectionList.setModel(createListModel());
		sectionList.setSelectedIndex(-1);
		sectionList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sectionListScrollPane.setViewportView(sectionList);
		
		if(message == RemoveSectionProblem.NO_SELECTION)
		{
			JLabel messageLabel = new JLabel("Please select a section to remove.");
			messageLabel.setForeground(Color.RED);
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
		if(message == RemoveSectionProblem.NO_ERROR)
		{
			JLabel messageLabel = new JLabel("Section removed!");
			messageLabel.setForeground(Color.RED);
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
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removeButton.addActionListener(new RemoveSectionController(rootView, parentView, this, user, course));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.weighty = 0.2;
		gbc_removeButton.weightx = 0.5;
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.gridx = 0;
		gbc_removeButton.gridy = y;
		add(removeButton, gbc_removeButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		closeButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 5, 0);
		gbc_btnClose.weightx = 0.5;
		gbc_btnClose.weighty = 0.2;
		gbc_btnClose.gridx = 1;
		gbc_btnClose.gridy = y;
		add(closeButton, gbc_btnClose);
	}
	
	public Section getSelectedSection()
	{
		return sectionList.getSelectedValue();
	}
	
	public int getSelectedSectionIndex()
	{
		return sectionList.getSelectedIndex();
	}

	/**
	 *  @return The remove section screen as a JPanel
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

}
