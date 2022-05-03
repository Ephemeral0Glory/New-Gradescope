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
			u.changePassword(userInfo.getOldPassword(), userInfo.getNewPassword());
		}
		else
		{
			// Display error
			userInfo.showInvalidPasswordInfo(error);
			rootView.update();
			rootView.display();
		}
	}
	
	private PasswordProblem validateInformation()
	{
		// TODO ChangePasswordController.validateInformation
		return PasswordProblem.NO_ERROR;
	}

}
