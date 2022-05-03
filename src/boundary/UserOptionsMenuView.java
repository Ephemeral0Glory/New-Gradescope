package boundary;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entity.User;
import entity.UserType;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import controller.ChangePasswordController;
import controller.ChangePasswordController.PasswordProblem;
import controller.CreateNewUserController.CreateProblem;
import controller.OpenMainMenuController;
import controller.UpdateUserInfoController;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JSeparator;

/**
 *  Menu for user management.
 *  <p>
 *  Allows the user to change password and name. 
 *  @author Alex Titus
 */
public class UserOptionsMenuView extends JPanel implements IGraderScreen
{
	private static final long serialVersionUID = -6483091830175077143L;
	private IGraderFrame rootView;
	private User user;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JComboBox<UserType> userTypeSelector;
	private JButton backButton;
	private JButton updateButton;
	private JButton changePasswordButton;
	private JSeparator separator;
	private JLabel lblOldPassword;
	private JPasswordField oldPasswordField;

	public UserOptionsMenuView(IGraderFrame rootView, User user)
	{
		super();
		this.rootView = rootView;
		this.user = user;
		setupPanel();
	}
	
	private void setupPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Create New User");
		title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.weighty = 0.4;
		gbc_title.gridwidth = 2;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_firstNameLabel.insets = new Insets(5, 0, 5, 5);
		gbc_firstNameLabel.weighty = 0.05;
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 1;
		add(firstNameLabel, gbc_firstNameLabel);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameField.setText(user.getFName());
		GridBagConstraints gbc_firstNameField = new GridBagConstraints();
		gbc_firstNameField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_firstNameField.insets = new Insets(5, 0, 5, 0);
		gbc_firstNameField.weighty = 0.05;
		gbc_firstNameField.gridx = 1;
		gbc_firstNameField.gridy = 1;
		add(firstNameField, gbc_firstNameField);
		firstNameField.setColumns(20);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lastNameLabel.insets = new Insets(5, 0, 5, 5);
		gbc_lastNameLabel.weighty = 0.05;
		gbc_lastNameLabel.gridx = 0;
		gbc_lastNameLabel.gridy = 2;
		add(lastNameLabel, gbc_lastNameLabel);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameField.setText(user.getLName());
		GridBagConstraints gbc_lastNameField = new GridBagConstraints();
		gbc_lastNameField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lastNameField.insets = new Insets(5, 0, 5, 0);
		gbc_lastNameField.weighty = 0.05;
		gbc_lastNameField.gridx = 1;
		gbc_lastNameField.gridy = 2;
		add(lastNameField, gbc_lastNameField);
		lastNameField.setColumns(25);
		
		JLabel usernameLabel = new JLabel("Username (email address):");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_usernameLabel.insets = new Insets(5, 0, 5, 5);
		gbc_usernameLabel.weighty = 0.05;
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 3;
		add(usernameLabel, gbc_usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setText(user.getEmail());
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_usernameField.insets = new Insets(5, 0, 5, 0);
		gbc_usernameField.weighty = 0.05;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 3;
		add(usernameField, gbc_usernameField);
		usernameField.setColumns(25);
		
		updateButton = new JButton("Update Information");
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateButton.addActionListener(new UpdateUserInfoController(rootView, this));
		
		JLabel userTypeLabel = new JLabel("User Type:");
		userTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_userTypeLabel = new GridBagConstraints();
		gbc_userTypeLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_userTypeLabel.insets = new Insets(5, 0, 5, 5);
		gbc_userTypeLabel.weighty = 0.05;
		gbc_userTypeLabel.gridx = 0;
		gbc_userTypeLabel.gridy = 4;
		add(userTypeLabel, gbc_userTypeLabel);
		
		userTypeSelector = new JComboBox<UserType>();
		userTypeSelector.setModel(new DefaultComboBoxModel<UserType>(UserType.values()));
		userTypeSelector.setSelectedIndex(0);
		userTypeSelector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_userTypeSelector = new GridBagConstraints();
		gbc_userTypeSelector.anchor = GridBagConstraints.SOUTHWEST;
		gbc_userTypeSelector.insets = new Insets(5, 0, 5, 0);
		gbc_userTypeSelector.weighty = 0.05;
		gbc_userTypeSelector.gridx = 1;
		gbc_userTypeSelector.gridy = 4;
		add(userTypeSelector, gbc_userTypeSelector);
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.insets = new Insets(5, 0, 5, 0);
		gbc_createButton.weighty = 0.05;
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = 5;
		add(updateButton, gbc_createButton);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 6;
		add(separator, gbc_separator);
		
		JLabel lblPassword = new JLabel("New Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblPassword.insets = new Insets(5, 0, 5, 5);
		gbc_lblPassword.weighty = 0.05;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 7;
		add(lblPassword, gbc_lblPassword);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		backButton.addActionListener(new OpenMainMenuController(rootView, user));
		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.insets = new Insets(5, 0, 0, 0);
		gbc_backButton.weighty = 0.2;
		gbc_backButton.gridx = 1;
		gbc_backButton.gridy = 11;
		add(backButton, gbc_backButton);
				
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_passwordField.insets = new Insets(5, 0, 5, 0);
		gbc_passwordField.weighty = 0.05;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 7;
		add(passwordField, gbc_passwordField);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
		gbc_lblConfirmPassword.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblConfirmPassword.insets = new Insets(5, 0, 5, 5);
		gbc_lblConfirmPassword.weighty = 0.05;
		gbc_lblConfirmPassword.gridx = 0;
		gbc_lblConfirmPassword.gridy = 8;
		add(lblConfirmPassword, gbc_lblConfirmPassword);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setColumns(20);
		confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_confirmPasswordField = new GridBagConstraints();
		gbc_confirmPasswordField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_confirmPasswordField.insets = new Insets(5, 0, 5, 0);
		gbc_confirmPasswordField.weighty = 0.05;
		gbc_confirmPasswordField.gridx = 1;
		gbc_confirmPasswordField.gridy = 8;
		add(confirmPasswordField, gbc_confirmPasswordField);
		
		lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
		gbc_lblOldPassword.weighty = 0.05;
		gbc_lblOldPassword.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblOldPassword.insets = new Insets(5, 0, 5, 5);
		gbc_lblOldPassword.gridx = 0;
		gbc_lblOldPassword.gridy = 9;
		add(lblOldPassword, gbc_lblOldPassword);
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setColumns(20);
		oldPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_oldPasswordField = new GridBagConstraints();
		gbc_oldPasswordField.anchor = GridBagConstraints.SOUTHWEST;
		gbc_oldPasswordField.weighty = 0.05;
		gbc_oldPasswordField.insets = new Insets(0, 0, 5, 0);
		gbc_oldPasswordField.gridx = 1;
		gbc_oldPasswordField.gridy = 9;
		add(oldPasswordField, gbc_oldPasswordField);
		
		changePasswordButton = new JButton("Change Password");
		changePasswordButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		changePasswordButton.addActionListener(new ChangePasswordController(rootView, this));
		GridBagConstraints gbc_btnChangePassword = new GridBagConstraints();
		gbc_btnChangePassword.insets = new Insets(0, 0, 5, 0);
		gbc_btnChangePassword.gridx = 1;
		gbc_btnChangePassword.gridy = 10;
		add(changePasswordButton, gbc_btnChangePassword);
	}
	
	@Override
	public JPanel getPanelContent()
	{
		return this;
	}

	@Override
	public void update()
	{
		setupPanel();
	}
	
	public void showInvalidInfo(CreateProblem error)
	{
		this.removeAll();
		setupPanelWithErrorMessage(error);
	}
	
	private void setupPanelWithErrorMessage(CreateProblem error)
	{
		// TODO UserOptionsMenuView.setupPanelWithErrorMessage
	}
	
	public void showInvalidPasswordInfo(PasswordProblem error)
	{
		this.removeAll();
		setupPanelWithPWErrorMessage(error);
	}
	
	private void setupPanelWithPWErrorMessage(PasswordProblem error)
	{
		// TODO UserOptionsMenuView.setupPanelWithPWErrorMessage
	}
	
	public User getUser()
	{
		return user;
	}
	
	public String getFirstName()
	{
		return firstNameField.getText();
	}
	
	public String getLastName()
	{
		return lastNameField.getText();
	}
	
	public String getUsername()
	{
		return usernameField.getText();
	}
	
	public String getNewPassword()
	{
		// This is technically insecure
		return new String(passwordField.getPassword());
	}
	
	public String getConfirmPassword()
	{
		// This is technically insecure
		return new String(confirmPasswordField.getPassword());
	}
	
	public String getOldPassword()
	{
		// This is technically insecure
		return new String(oldPasswordField.getPassword());
	}

}
