package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.LogInView;

/**
 *  Opens the log in screen.
 *  @author Alex Titus
 */
public class OpenLogInController implements ActionListener
{
	private LogInView view;
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  The window frame for the login screen
	 */
	public OpenLogInController(LogInView view)
	{
		this.view = view;
	}

	/**
	 *  Displays the log-in screen.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		openLogin();
	}
	
	/**
	 *  Displays the log-in screen.
	 */
	public void openLogin()
	{
		view.showLogin();
		view.update();
		view.display();
	}

}
