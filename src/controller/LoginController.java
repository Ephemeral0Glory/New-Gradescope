package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import utilities.GradebookFileReaderException;
import boundary.LogInView;
import entity.User;

/**
 *  Attempts to log in a user.
 *  <p>
 *  Compares the username and password against the known usernames and hashed passwords.
 *  If successful, creates the user with user-type-specific permissions (TODO user-type permissions)
 *  and creates and displays the main screen. Otherwise, it displays an error message that the
 *  given username and password combination was incorrect.
 *  @author Alex Titus
 */
public class LoginController implements ActionListener, KeyListener {
	private LogInView view;
	
	public LoginController(LogInView view)
	{
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		proceedToLogin();
	}
	
	private User determineUser(ArrayList<User> users, String username)
	{
		for(User u: users)
		{
			if(u.getEmail().equals(username))
			{
				return u;
			}
		}
		
		// If we get here, then we don't know this user
		return null;
	}
	
	private void createMainMenu(User user)
	{
		// Use open controller to open main menu
		OpenMainMenuController ommc = new OpenMainMenuController(view, user);
		try
		{
			ommc.open();
		} catch (GradebookFileReaderException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			proceedToLogin();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void proceedToLogin() {
		ArrayList<User> users = view.getUsers();
		User user = determineUser(users, view.getEnteredUsername());

		// If couldn't find user for that username
		if(user == null)
		{
			// Display bad login message and end
			view.showBadLogin();
			view.update();
			view.display();
			return;
		}

		// Have user, check password
		if(user.checkPassword(view.getEnteredPassword()))
		{
			// Correct password, go to main menu
			createMainMenu(user);
		}
		else
		{
			// Bad password, show bad login message
			view.showBadLogin();
			view.update();
			view.display();
		}
	}
}
