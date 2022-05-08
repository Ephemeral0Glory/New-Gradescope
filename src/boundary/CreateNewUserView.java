package boundary;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import entity.UserType;

import javax.swing.JButton;

import controller.CreateNewUserController;
import controller.CreateNewUserController.CreateProblem;
import controller.OpenLogInController;

/**
 *  The create user screen.
 *  <p>
 *  The user can specify username (email address), password, and user type.
 *  @author Alex Titus
 *
 */
public class CreateNewUserView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = 4230852139446502982L;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JComboBox<UserType> userTypeSelector;
	JButton cancelButton;
	JButton createButton;
	
	/**
	 *  Constructor.
	 *  
	 *  Creates the screen with a blank username field.
	 */
	public CreateNewUserView() {
		super();
		setupPanel("", null);
	}
	
	/**
	 *  Constructor.
	 *  
	 *  Creates the screen with a filled username field.
	 *  @param usernameInfo  The pre-fill string for the username. Taken from the username field in the log-in screen. 
	 */
	public CreateNewUserView(String usernameInfo, LogInView view)
	{
		super();
		setupPanel(usernameInfo, view);
	}
	
	private void setupPanel(String usernameInfo, LogInView view)
	{
		String username = usernameInfo.isEmpty()? "user@example.com": usernameInfo;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Create New User");
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weighty = 0.4;
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_firstNameLabel.insets = new Insets(5, 0, 0, 0);
		gbc_firstNameLabel.weighty = 0.05;
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 1;
		add(firstNameLabel, gbc_firstNameLabel);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_firstNameField.insets = new Insets(5, 0, 0, 0);
		gbc_firstNameField.weighty = 0.05;
		gbc_firstNameField.gridx = 1;
		gbc_firstNameField.gridy = 1;
		add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(20);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lastNameLabel.insets = new Insets(5, 0, 0, 0);
		gbc_lastNameLabel.weighty = 0.05;
		gbc_lastNameLabel.gridx = 0;
		gbc_lastNameLabel.gridy = 2;
		add(lastNameLabel, gbc_lastNameLabel);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lastNameField.insets = new Insets(5, 0, 0, 0);
		gbc_lastNameField.weighty = 0.05;
		gbc_lastNameField.gridx = 1;
		gbc_lastNameField.gridy = 2;
		add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(25);
		
		JLabel usernameLabel = new JLabel("Username (email address):");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_usernameLabel.insets = new Insets(5, 0, 0, 0);
		gbc_usernameLabel.weighty = 0.05;
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 3;
		add(usernameLabel, gbc_usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setText(username);
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_usernameField.insets = new Insets(5, 0, 0, 0);
		gbc_usernameField.weighty = 0.05;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 3;
		add(usernameField, gbc_usernameField);
		usernameField.setColumns(25);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblPassword.insets = new Insets(5, 0, 0, 0);
		gbc_lblPassword.weighty = 0.05;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 4;
		add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_passwordField.insets = new Insets(5, 0, 0, 0);
		gbc_passwordField.weighty = 0.05;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		add(passwordField, gbc_passwordField);
		
		JLabel userTypeLabel = new JLabel("User Type:");
		userTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_userTypeLabel = new GridBagConstraints();
		gbc_userTypeLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_userTypeLabel.insets = new Insets(5, 0, 0, 0);
		gbc_userTypeLabel.weighty = 0.05;
		gbc_userTypeLabel.gridx = 0;
		gbc_userTypeLabel.gridy = 5;
		add(userTypeLabel, gbc_userTypeLabel);
		
		userTypeSelector = new JComboBox<UserType>();
		userTypeSelector.setModel(new DefaultComboBoxModel<UserType>(UserType.values()));
		userTypeSelector.setSelectedIndex(0);
		userTypeSelector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_userTypeSelector = new GridBagConstraints();
		gbc_userTypeSelector.anchor = GridBagConstraints.SOUTHWEST;
		gbc_userTypeSelector.insets = new Insets(5, 0, 0, 0);
		gbc_userTypeSelector.weighty = 0.05;
		gbc_userTypeSelector.gridx = 1;
		gbc_userTypeSelector.gridy = 5;
		add(userTypeSelector, gbc_userTypeSelector);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.addActionListener(new OpenLogInController(view));
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(5, 0, 0, 0);
		gbc_cancelButton.weighty = 0.2;
		gbc_cancelButton.gridx = 0;
		gbc_cancelButton.gridy = 6;
		add(cancelButton, gbc_cancelButton);
		
		createButton = new JButton("Create");
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createButton.addActionListener(new CreateNewUserController(view, this));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.insets = new Insets(5, 0, 0, 0);
		gbc_createButton.weighty = 0.2;
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = 6;
		add(createButton, gbc_createButton);
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
	 *  @return  The first name entered by the user
	 */
	public String getEnteredFirstName()
	{
		return firstNameField.getText();
	}
	
	/**
	 *  @return  The last name entered by the user
	 */
	public String getEnteredLastName()
	{
		return lastNameField.getText();
	}
	
	/**
	 *  @return  The username entered by the user
	 */
	public String getEnteredUsername()
	{
		return usernameField.getText();
	}
	
	/**
	 *  @return  The password entered by the user
	 */
	public String getEnteredPassword()
	{
		char[] pw = passwordField.getPassword();
		return new String(pw);
	}
	
	/**
	 *  @return  The type of new user selected by the user
	 */
	public UserType getUserType()
	{
		return (UserType) userTypeSelector.getSelectedItem();
	}
	
	public void showInvalidInfo(CreateProblem err)
	{
		this.removeAll();
		setupPanelWithErrorMessage(err);
	}
	
	private void setupPanelWithErrorMessage(CreateProblem err)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		int y = 0;  // Row 1

		JLabel title = new JLabel("Create New User");
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weighty = 0.4;
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 0;
		gbc_title.gridy = y;
		add(title, gbc_title);
		
		y += 1;  // Next row

		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.weighty = 0.05;
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = y;
		add(firstNameLabel, gbc_firstNameLabel);

		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.weighty = 0.05;
		gbc_firstNameField.anchor = GridBagConstraints.WEST;
		gbc_firstNameField.gridx = 1;
		gbc_firstNameField.gridy = y;
		add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(20);
		
		y += 1;  // Next row
		if(err == CreateProblem.BAD_FIRST)
		{
			// Display error message under first name input
			JLabel errorMessage = new JLabel("Invalid first name, can't be empty");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y += 1;  // Move to next row
		}

		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.weighty = 0.05;
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.gridx = 0;
		gbc_lastNameLabel.gridy = y;
		add(lastNameLabel, gbc_lastNameLabel);

		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.weighty = 0.05;
		gbc_lastNameField.anchor = GridBagConstraints.WEST;
		gbc_lastNameField.gridx = 1;
		gbc_lastNameField.gridy = y;
		add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(25);
		
		y += 1;  // Next row
		if(err == CreateProblem.BAD_LAST)
		{
			// Display error message under first name input
			JLabel errorMessage = new JLabel("Invalid last name, can't be empty");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y += 1;
		}

		JLabel usernameLabel = new JLabel("Username (email address):");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.weighty = 0.05;
		gbc_usernameLabel.anchor = GridBagConstraints.EAST;
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = y;
		add(usernameLabel, gbc_usernameLabel);

		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.weighty = 0.05;
		gbc_usernameField.anchor = GridBagConstraints.WEST;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = y;
		add(usernameField, gbc_usernameField);
		usernameField.setColumns(25);
		
		y += 1;  // Next row
		if(err == CreateProblem.BAD_USERNAME)
		{
			// Display error message under first name input
			JLabel errorMessage = new JLabel("Invalid username, must be valid email address");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y += 1;
		}
		if(err == CreateProblem.DUPLICATE_USER)
		{
			// Display error message under first name input
			JLabel errorMessage = new JLabel("A user already exists with this email address");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y += 1;
		}

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.weighty = 0.05;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = y;
		add(lblPassword, gbc_lblPassword);

		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.weighty = 0.05;
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = y;
		add(passwordField, gbc_passwordField);
		
		y += 1;  // Next row
		if(err == CreateProblem.BAD_PW)
		{
			// Display error message under first name input
			JLabel errorMessage = new JLabel("Invalid password, can't be empty");
			errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y += 1;
		}

		JLabel userTypeLabel = new JLabel("User Type:");
		userTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_userTypeLabel = new GridBagConstraints();
		gbc_userTypeLabel.weighty = 0.05;
		gbc_userTypeLabel.anchor = GridBagConstraints.EAST;
		gbc_userTypeLabel.gridx = 0;
		gbc_userTypeLabel.gridy = y;
		add(userTypeLabel, gbc_userTypeLabel);

		GridBagConstraints gbc_userTypeSelector = new GridBagConstraints();
		gbc_userTypeSelector.weighty = 0.05;
		gbc_userTypeSelector.anchor = GridBagConstraints.WEST;
		gbc_userTypeSelector.gridx = 1;
		gbc_userTypeSelector.gridy = y;
		add(userTypeSelector, gbc_userTypeSelector);
		
		y += 1;  // Next row

		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.weighty = 0.3;
		gbc_cancelButton.gridx = 0;
		gbc_cancelButton.gridy = y;
		add(cancelButton, gbc_cancelButton);

		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.weighty = 0.3;
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = y;
		add(createButton, gbc_createButton);
	}

	/**
	 *  Does nothing.
	 */
	@Override
	public void update()
	{
		// Ignore
	}

	public void focusFirstNameField() {
		firstNameField.requestFocus();
	}

}
