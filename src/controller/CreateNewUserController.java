package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import utilities.GradebookFileReader;
import utilities.GradebookFileWriter;
import utilities.GradebookFileWriterException;
import utilities.UserFileReader;
import utilities.UserFileWriter;
import utilities.UserFileWriterException;
import entity.Gradebook;
import entity.User;
import boundary.CreateNewUserView;
import boundary.LogInView;

/**
 *  Creates a new user based on the information provided in the create new user screen.
 *  <p>
 *  Compares the new username against the list of known users and rejects the creation if
 *  there is a conflict.
 *  @author Alex Titus
 */
public class CreateNewUserController implements ActionListener
{
	/**
	 *  Indicates one of the following errors in user creation:
	 *  <ul><li> BAD_USERNAME: either username conflicted with existing one, or it was left empty </li>
	 *  <li> BAD_FIRST: first name was left empty </li>
	 *  <li> BAD_LAST: last name was left empty </li>
	 *  <li> BAD_PW: password was left empty </li></ul>
	 */
	public static enum CreateProblem { NO_ERROR, DUPLICATE_USER, BAD_USERNAME, BAD_FIRST, BAD_LAST, BAD_PW }
	private CreateNewUserView newUserInfo;
	private LogInView view;
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  The application window frame
	 *  @param newUserInfo  The new user panel
	 */
	public CreateNewUserController(LogInView view, CreateNewUserView newUserInfo)
	{
		this.newUserInfo = newUserInfo;
		this.view = view;
	}

	/**
	 *  Validates new user information and creates the user.
	 *  
	 *  @param ae  The action event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Validate the username
		String newUsername = newUserInfo.getEnteredUsername();
		String firstName = newUserInfo.getEnteredFirstName();
		String lastName = newUserInfo.getEnteredLastName();
		String password = newUserInfo.getEnteredPassword();
		CreateProblem error = validateInfo(newUsername, firstName, lastName, password);
		if(error == CreateProblem.NO_ERROR)
		{
			// Have a good username, create and add it to the list
			User user = createUser(newUsername, firstName, lastName, password);
			view.addUser(user);
			
			// Write out new user to file
			writeNewUser();
			
			// Create user directory and default gradebook file
			createUserDirectory(user);
			createGradebookFile(user);
			
			// Return to the log-in screen
			OpenLogInController olc = new OpenLogInController(view);
			olc.openLogin();
		}
		else
		{
			newUserInfo.showInvalidInfo(error);
		}
		view.update();
		view.display();
	}
	
	/*
	 *  Invalid if:
	 *  - username already taken
	 *  - username is empty
	 *  - first name is empty
	 *  - last name is empty
	 *  - password is empty
	 */
	private CreateProblem validateInfo(String newUsername, String firstName, String lastName, String password)
	{
		// Invalid if name already exists
		for(User u: view.getUsers())
		{
			if(u.getEmail().equals(newUsername))
			{
				// Already have a user with this email address/username
				return CreateProblem.DUPLICATE_USER;
			}
		}
		
		// Empty values check
		if(newUsername.isEmpty())
		{
			return CreateProblem.BAD_USERNAME;
		}
		if(firstName.isEmpty())
		{
			return CreateProblem.BAD_FIRST;
		}
		if(lastName.isEmpty())
		{
			return CreateProblem.BAD_LAST;
		}
		if(password.isEmpty())
		{
			return CreateProblem.BAD_PW;
		}
		
		// If all checks passed, information is valid
		return CreateProblem.NO_ERROR;
	}
	
	private User createUser(String newUsername, String firstName, String lastName, String password)
	{
		User u = null;  // Because we get user type from the combo box, this should always get set
		switch(newUserInfo.getUserType())
		{
		case PROFESSOR:
			int hashedPW = password.hashCode();
			u = new User(newUsername, firstName, lastName, hashedPW);
			break;
		}
		
		return u;
	}
	
	private void writeNewUser()
	{
		try
		{
			UserFileWriter writer = new UserFileWriter(UserFileReader.usersFileName);
			writer.writeUsers(view.getUsers());
		}
		catch (UserFileWriterException e)
		{
			// Print error
			e.printStackTrace();
			
			// TODO show message to user in interface
		}
	}
	
	private void createUserDirectory(User user)
	{
		// Create user directory
		String path = GradebookFileReader.gradebookDirectory + "u" + user.getID() + "/";
		File userDir = new File(path);
		userDir.mkdir();
	}
	
	private void createGradebookFile(User user)
	{
		String userDirPath = GradebookFileReader.gradebookDirectory + "u" + user.getID() + "/";
		String gradebookFile = userDirPath + "gradebook.xml";
		Gradebook gb = new Gradebook(user);
		try
		{
			GradebookFileWriter writer = new GradebookFileWriter(gradebookFile);
			
			// Write out gradebook
			writer.writeGradebook(gb);
		}
		catch (GradebookFileWriterException e)
		{
			// TODO Notify user of problem
			e.printStackTrace();
		}
	}

}
