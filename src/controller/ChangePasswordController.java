package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.User;
import boundary.IGraderFrame;
import boundary.UserOptionsMenuView;

/**
 *  Validates new password and changes the user's password.
 *  
 *  @author Alex Titus
 */
public class ChangePasswordController implements ActionListener
{
	public static enum PasswordProblem { NO_ERROR, CONFIRM_FAIL, EMPTY_NEW, EMPTY_CONFIRM, EMPTY_OLD, BAD_OLD }
	private IGraderFrame rootView;
	private UserOptionsMenuView userInfo;
	
	public ChangePasswordController(IGraderFrame rootView, UserOptionsMenuView userInfo)
	{
		this.rootView = rootView;
		this.userInfo = userInfo;
	}
	
	/**
	 *  Validates the entered password information, then changes the password.
	 *  <p>
	 *  If password is invalid, displays the error message.
	 *  @param ae  The initiating event,  ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Validate entered information
		PasswordProblem error = validateInformation();
		
		if(error == PasswordProblem.NO_ERROR)
		{
			// Change password
			User u = userInfo.getUser();
			if(!u.changePassword(userInfo.getOldPassword(), userInfo.getNewPassword()))
			{
				// This means the old password was put in incorrectly, display error
				userInfo.showInvalidPasswordInfo(PasswordProblem.BAD_OLD);
			}
			// Else everything went well
			userInfo.showChangedPassword();
		}
		else
		{
			// Display error
			userInfo.showInvalidPasswordInfo(error);
		}
		
		// Update display
		rootView.update();
		rootView.display();
	}
	
	private PasswordProblem validateInformation()
	{
		String newPassword = userInfo.getNewPassword();
		String confirmPassword = userInfo.getConfirmPassword();
		String oldPassword = userInfo.getOldPassword();
		
		// Empty strings check
		if(newPassword.isEmpty())
		{
			return PasswordProblem.EMPTY_NEW;
		}
		if(confirmPassword.isEmpty())
		{
			return PasswordProblem.EMPTY_CONFIRM;
		}
		if(oldPassword.isEmpty())
		{
			return PasswordProblem.EMPTY_OLD;
		}
		
		// Matching confirm password check
		if(!newPassword.equals(confirmPassword))
		{
			return PasswordProblem.CONFIRM_FAIL;
		}
		
		// Otherwise, no problem with inputs
		return PasswordProblem.NO_ERROR;
	}

}
