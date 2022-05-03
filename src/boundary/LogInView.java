package boundary;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.SwingConstants;

import controller.OpenCreateNewUserController;
import controller.LoginController;
import entity.User;

import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

/**
 *  The log-in screen.
 *  <p>
 *  This screen allows the user to log in or create a new user. Once the user
 *  correctly logs in, this screen will be disposed of.
 *  @author Alex Titus
 */
public class LogInView extends JFrame implements IGraderFrame {
	private static final long serialVersionUID = 5591687101660980073L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private ArrayList<User> users;
	
	/**
	 *  Constructor.
	 *  
	 *  Creates a log in screen with no backing user data.
	 */
	public LogInView() {
		super("Grader - Log in");
		setupFrame();
		users = new ArrayList<User>();
	}
	
	/**
	 *  Constructor.
	 *  <p>
	 *  Creates the log-in screen with backing user data.
	 *  @param users  List of known users
	 */
	public LogInView(ArrayList<User> users)
	{
		this();
		this.users = users;
	}
	
	private void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(50, 50, 600, 600);
		
		JPanel panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JLabel pwLabel = new JLabel("Password:");
		pwLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pwLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_pwLabel = new GridBagConstraints();
		gbc_pwLabel.weighty = 1.0;
		gbc_pwLabel.weightx = 1.0;
		gbc_pwLabel.anchor = GridBagConstraints.EAST;
		gbc_pwLabel.insets = new Insets(0, 0, 5, 5);
		gbc_pwLabel.gridx = 0;
		gbc_pwLabel.gridy = 2;
		panel.add(pwLabel, gbc_pwLabel);
		
		JLabel title = new JLabel("Welcome to Grader!");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 52));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weighty = 3.0;
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		panel.add(title, gbc_title);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.weighty = 1.0;
		gbc_usernameLabel.weightx = 1.0;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.anchor = GridBagConstraints.EAST;
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 1;
		panel.add(usernameLabel, gbc_usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.weighty = 1.0;
		gbc_usernameField.weightx = 1.0;
		gbc_usernameField.anchor = GridBagConstraints.WEST;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 1;
		panel.add(usernameField, gbc_usernameField);
		usernameField.setColumns(20);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.weighty = 1.0;
		gbc_passwordField.weightx = 1.0;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		panel.add(passwordField, gbc_passwordField);
		passwordField.setColumns(20);
		
		JButton createNewUserButton = new JButton("Create New User");
		createNewUserButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createNewUserButton.addActionListener(new OpenCreateNewUserController(this));
		GridBagConstraints gbc_createNewUserButton = new GridBagConstraints();
		gbc_createNewUserButton.weightx = 1.0;
		gbc_createNewUserButton.weighty = 2.0;
		gbc_createNewUserButton.insets = new Insets(0, 0, 0, 5);
		gbc_createNewUserButton.gridx = 0;
		gbc_createNewUserButton.gridy = 4;
		panel.add(createNewUserButton, gbc_createNewUserButton);
		
		JButton loginButton = new JButton("Log In");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginButton.addActionListener(new LoginController(this));
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.weighty = 2.0;
		gbc_loginButton.weightx = 1.0;
		gbc_loginButton.insets = new Insets(0, 0, 0, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 4;
		panel.add(loginButton, gbc_loginButton);
		
		setContentPane(panel);
		usernameField.requestFocus();
	}
	
	/**
	 *  Displays this frame and all of its subcomponents.
	 */
	@Override
	public void display()
	{
		this.setVisible(true);
	}

	/**
	 *  Revalidates the component hierarchy.
	 */
	@Override
	public void update()
	{
		revalidate();
	}
	
	/**
	 *  Closes this frame (window).
	 */
	@Override
	public void closeWindow()
	{
		dispose();
	}
	
	/**
	 *  Changes this frame's content pane to the given Grader screen.
	 *  @param gs  The new screen to display
	 */
	@Override
	public void setNewView(IGraderScreen gs)
	{
		setContentPane(gs.getPanelContent());
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public void addUser(User newUser)
	{
		users.add(newUser);
	}
	
	/**
	 *  Creates an "invalid login" message.
	 */
	public void showBadLogin()
	{
		JLabel errorMessage = new JLabel("Invalid username or password.");
		errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorMessage.setForeground(Color.RED);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.NORTH;
		add(errorMessage, gbc);
	}
	
	/**
	 *  Creates the default login screen
	 */
	public void showLogin()
	{
		// Display default (initial) screen
		setupFrame();
	}
	
	/**
	 *  @return  The text entered by the user in the username field
	 */
	public String getEnteredUsername()
	{
		return usernameField.getText();
	}
	
	/**
	 *  @return  The text entered by the user in the password field
	 */
	public String getEnteredPassword()
	{
		char[] pw = passwordField.getPassword();
		return new String(pw);  // This is technically insecure
	}

}
