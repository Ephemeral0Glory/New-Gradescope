package boundary;

import javax.swing.JPanel;

import entity.User;
import utilities.GradebookFileReaderException;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;

import controller.LogOutController;
import controller.OpenNewCourseController;
import controller.OpenSelectCoursesController;
import controller.OpenUserOptionsController;
import controller.OpenViewCoursesInfoController;
import controller.QuitController;

/**
 *  The main menu for the Grader application.
 *  <p>
 *  From here the user will be able to manage courses and user details.
 *  @author Alex Titus
 *
 */
public class MainMenuView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = 7233321725027595441L;
	private IGraderFrame rootView;
	private User currentUser;
	
	public MainMenuView(IGraderFrame rootView, User currentUser) throws GradebookFileReaderException
	{
		this.rootView = rootView;
		this.currentUser = currentUser;
		setupPanel();
	}
	
	private void setupPanel() throws GradebookFileReaderException
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.35, 0.1, 0.1, 0.35, 0.1, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Welcome to Grader, " + currentUser.getFName());
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.weighty = 0.35;
		gbc_titleLabel.gridwidth = 2;
		gbc_titleLabel.fill = GridBagConstraints.BOTH;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		JButton createNewCourseButton = new JButton("Create New Course");
		createNewCourseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createNewCourseButton.addActionListener(new OpenNewCourseController(rootView, currentUser));
		GridBagConstraints gbc_createNewCourseButton = new GridBagConstraints();
		gbc_createNewCourseButton.weighty = 0.1;
		gbc_createNewCourseButton.weightx = 1.0;
		gbc_createNewCourseButton.fill = GridBagConstraints.BOTH;
		gbc_createNewCourseButton.insets = new Insets(0, 5, 5, 5);
		gbc_createNewCourseButton.gridx = 0;
		gbc_createNewCourseButton.gridy = 1;
		add(createNewCourseButton, gbc_createNewCourseButton);
		
		JButton selectCoursesButton = new JButton("Select Course");
		selectCoursesButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectCoursesButton.addActionListener(new OpenSelectCoursesController(rootView, currentUser));
		GridBagConstraints gbc_manageCoursesButton = new GridBagConstraints();
		gbc_manageCoursesButton.weighty = 0.1;
		gbc_manageCoursesButton.weightx = 1.0;
		gbc_manageCoursesButton.fill = GridBagConstraints.BOTH;
		gbc_manageCoursesButton.insets = new Insets(0, 0, 5, 5);
		gbc_manageCoursesButton.gridx = 1;
		gbc_manageCoursesButton.gridy = 1;
		add(selectCoursesButton, gbc_manageCoursesButton);
		
		JButton viewCourseInfoButton = new JButton("View Course Info");
		viewCourseInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		viewCourseInfoButton.addActionListener(new OpenViewCoursesInfoController(rootView, currentUser));
		GridBagConstraints gbc_viewCourseInfoButton = new GridBagConstraints();
		gbc_viewCourseInfoButton.weighty = 0.1;
		gbc_viewCourseInfoButton.fill = GridBagConstraints.BOTH;
		gbc_viewCourseInfoButton.insets = new Insets(0, 5, 5, 5);
		gbc_viewCourseInfoButton.gridx = 0;
		gbc_viewCourseInfoButton.gridy = 2;
		add(viewCourseInfoButton, gbc_viewCourseInfoButton);
		
		JButton userOptionsButton = new JButton("User Options");
		userOptionsButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userOptionsButton.addActionListener(new OpenUserOptionsController(rootView, currentUser));
		GridBagConstraints gbc_userOptionsButton = new GridBagConstraints();
		gbc_userOptionsButton.weighty = 0.1;
		gbc_userOptionsButton.fill = GridBagConstraints.BOTH;
		gbc_userOptionsButton.insets = new Insets(0, 0, 5, 5);
		gbc_userOptionsButton.gridx = 1;
		gbc_userOptionsButton.gridy = 2;
		add(userOptionsButton, gbc_userOptionsButton);
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		logOutButton.addActionListener(new LogOutController(rootView));
		GridBagConstraints gbc_logOutButton = new GridBagConstraints();
		gbc_logOutButton.fill = GridBagConstraints.BOTH;
		gbc_logOutButton.anchor = GridBagConstraints.SOUTH;
		gbc_logOutButton.insets = new Insets(0, 5, 5, 5);
		gbc_logOutButton.gridx = 0;
		gbc_logOutButton.gridy = 4;
		add(logOutButton, gbc_logOutButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		quitButton.addActionListener(new QuitController(rootView));
		GridBagConstraints gbc_quitButton = new GridBagConstraints();
		gbc_quitButton.fill = GridBagConstraints.BOTH;
		gbc_quitButton.anchor = GridBagConstraints.SOUTH;
		gbc_quitButton.insets = new Insets(0, 0, 5, 5);
		gbc_quitButton.gridx = 1;
		gbc_quitButton.gridy = 4;
		add(quitButton, gbc_quitButton);
	}

	/**
	 *  @return  This screen as a JPanel
	 */
	@Override
	public JPanel getPanelContent()
	{
		return this;
	}

	/**
	 *  Does nothing, no components to update
	 */
	@Override
	public void update()
	{
		// Do nothing
	}
	
	public User getCurrentUser()
	{
		return currentUser;
	}

}
