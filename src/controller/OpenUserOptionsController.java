package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.User;
import boundary.IGraderFrame;
import boundary.UserOptionsMenuView;

/**
 *  Opens the user options menu.
 *  
 *  @author Alex Titus
 */
public class OpenUserOptionsController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	
	public OpenUserOptionsController(IGraderFrame rootView, User user)
	{
		this.rootView = rootView;
		this.user = user;
	}

	/**
	 *  Creates and opens the user options menu.
	 *  
	 *  @param ae  The initiating ActionEvent, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Create menu
		UserOptionsMenuView uomv = new UserOptionsMenuView(rootView, user);
		
		// Display it
		rootView.setNewView(uomv);
		rootView.update();
		rootView.display();
	}

}
