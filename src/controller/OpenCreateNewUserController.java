package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.CreateNewUserView;
import boundary.LogInView;

/**
 *  Opens the create new user screen.
 *  <p>
 *  Transfers any information entered in username field.
 *  @author Alex Titus
 */
public class OpenCreateNewUserController implements ActionListener
{
	private LogInView view;
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  The login screen object
	 */
	public OpenCreateNewUserController(LogInView view)
	{
		this.view = view;
	}

	/**
	 *  Creates the new user view, sets it as the new 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		CreateNewUserView cnuv = new CreateNewUserView(view.getEnteredUsername(), view);
		view.setNewView(cnuv);
		view.update();
		view.display();
	}

}
