package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.CreateNewUserController.CreateProblem;
import entity.User;
import boundary.IGraderFrame;
import boundary.UserOptionsMenuView;

/**
 *  Updates user information with the provided information.
 *  
 *  @author Alex Titus
 */
public class UpdateUserInfoController implements ActionListener
{
	private IGraderFrame rootView;
	private UserOptionsMenuView userInfo;

	public UpdateUserInfoController(IGraderFrame rootView, UserOptionsMenuView userInfo)
	{
		this.rootView = rootView;
		this.userInfo = userInfo;
	}
	
	/**
	 *  Validates input and updates the user's information if correct.
	 *   
	 *  @param e  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Validate information provided
		CreateProblem error = validateInformation();
		
		// Update if correct
		if(error == CreateProblem.NO_ERROR)
		{
			// Update
			updateInfo();
			
			// Return to main menu screen
			openMainMenu();
		}
		else  // Inform user of error
		{
			userInfo.showInvalidInfo(error);
		}
	}
	
	private CreateProblem validateInformation()
	{
		// TODO UpdateUserInfoController.validateInformation
		return CreateProblem.NO_ERROR;
	}
	
	private void updateInfo()
	{
		User u = userInfo.getUser();
		u.setFName(userInfo.getFirstName());
		u.setLName(userInfo.getLastName());
		u.setEmail(userInfo.getUsername());
	}
	
	private void openMainMenu()
	{
		OpenMainMenuController ommc = new OpenMainMenuController(rootView, userInfo.getUser());
		ommc.open();
	}

}
