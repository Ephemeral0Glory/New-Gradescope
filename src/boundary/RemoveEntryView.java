package boundary;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import controller.ClosePopupWindowController;
import controller.RemoveEntryController;
import controller.RemoveEntryController.RemoveEntryProblem;
import entity.Course;
import entity.Entry;
import entity.User;

/**
 *  Allows the user to select a student to remove from the course.
 *  @author Seonghoon Cho
 */
public class RemoveEntryView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = 1223502123962778658L;
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private User user;
	private Course course;
	private JScrollPane listScrollPane;
	private JList<Entry> entryList;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The frame for this popup window
	 *  @param parentView  The application window frame
	 *  @param user  The current user
	 *  @param course  The course being modified
	 */
	public RemoveEntryView(IGraderFrame rootView, IGraderFrame parentView,
			User user, Course course)
	{
		this.rootView = rootView;
		this.parentView = parentView;
		this.user = user;
		this.course = course;
		setupPanel();
	}

	/**
	 *  @return  The remove student screen as a JPanel 
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
	
	private void setupPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.5, 0.5, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Remove an Entry");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.CENTER;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		gbc_titleLabel.weighty = 0.1;
		add(titleLabel, gbc_titleLabel);

		listScrollPane = new JScrollPane();
		listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.gridwidth = 2;
		gbc_listScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = 2;
		gbc_listScrollPane.weighty = 0.65;
		add(listScrollPane, gbc_listScrollPane);
		
		entryList = new JList<Entry>();
		entryList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		entryList.setModel(createEntryModel());
		entryList.setEnabled(true);
		entryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane.setViewportView(entryList);
		
		JButton selectButton = new JButton("Remove");
		selectButton.addActionListener(new RemoveEntryController(rootView, parentView, this, user));
		GridBagConstraints gbc_selectButton = new GridBagConstraints();
		gbc_selectButton.insets = new Insets(0, 10, 0, 5);
		gbc_selectButton.gridx = 0;
		gbc_selectButton.gridy = 3;
		gbc_selectButton.weighty = 0.15;
		add(selectButton, gbc_selectButton);
		
		JButton cancelButton = new JButton("Close");
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 10);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 3;
		gbc_cancelButton.weighty = 0.15;
		add(cancelButton, gbc_cancelButton);

	}

	private ListModel<Entry> createEntryModel()
	{
		DefaultListModel<Entry> model = new DefaultListModel<Entry>();
		
		// Fill model
		for(Entry e : course.getEntries())
		{
			model.addElement(e);
		}
		
		return model;
	}
	
	/**
	 *  Updates the list to show the students in the selected semester.
	 */
	public void updateEntryListing()
	{
		entryList = new JList<Entry>();
		entryList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		entryList.setModel(createEntryModel());
		entryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane.setViewportView(entryList);
	}
	
	/**
	 *  @return  The student selected by the user.
	 */
	public Entry getSelectedEntry()
	{
		return entryList.getSelectedValue();
	}
	
	/**
	 *  @return  The index in the list of the student selected by the user.
	 */
	public int getSelectedEntryIndex()
	{
		return entryList.getSelectedIndex();
	}
	
	
	public Course getCourse()
	{
		return course;
	}
	
	/**
	 *  Displays an "entry removed" message on this screen.
	 */
	public void showSuccess()
	{
		removeAll();
		repaint();
		updateEntryListing();
		setupPanelWithMessage(RemoveEntryProblem.NO_ERROR);
	}
	
	/**
	 *  Displays an error message to the user on this screen.
	 *  @param error  The error to display
	 */
	public void showError(RemoveEntryProblem error)
	{
		removeAll();
		repaint();
		setupPanelWithMessage(error);
	}
	
	private void setupPanelWithMessage(RemoveEntryProblem message)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.5, 0.5, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;  // First row
		
		JLabel titleLabel = new JLabel("Remove an Entry");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.CENTER;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = y;
		gbc_titleLabel.weighty = 0.1;
		add(titleLabel, gbc_titleLabel);
		y++;  // Next row

		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.gridwidth = 2;
		gbc_listScrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = y;
		gbc_listScrollPane.weighty = 0.65;
		add(listScrollPane, gbc_listScrollPane);
		y++;  // Next row
		
		listScrollPane.setViewportView(entryList);
		
		if(message == RemoveEntryProblem.NO_ERROR)
		{
			JLabel messageLabel = new JLabel("Entry removed.");
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
		if(message == RemoveEntryProblem.NO_ENTRY_SELECTION)
		{
			JLabel messageLabel = new JLabel("Please select an entry to remove.");
			messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
		
		JButton selectButton = new JButton("Remove");
		selectButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectButton.addActionListener(new RemoveEntryController(rootView, parentView, this, user));
		GridBagConstraints gbc_selectButton = new GridBagConstraints();
		gbc_selectButton.insets = new Insets(0, 10, 0, 5);
		gbc_selectButton.gridx = 0;
		gbc_selectButton.gridy = y;
		gbc_selectButton.weighty = 0.15;
		add(selectButton, gbc_selectButton);
		
		JButton cancelButton = new JButton("Close");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.addActionListener(new ClosePopupWindowController(rootView, parentView));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 10);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = y;
		gbc_cancelButton.weighty = 0.15;
		add(cancelButton, gbc_cancelButton);
	}

}
