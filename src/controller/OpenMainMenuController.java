package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.User;
import boundary.GraderView;
import boundary.IGraderFrame;
import boundary.LogInView;
import boundary.MainMenuView;

/**
 *  Creates and opens the main menu screen.
 *  
 *  @author Alex Titus
 */
public class OpenMainMenuController implements ActionListener
{
	private IGraderFrame rootView;
	private User user;
	
	public OpenMainMenuController(IGraderFrame rootView, User user)
	{
		this.rootView = rootView;
		this.user = user;
	}

	/**
	 *  Creates and opens the main menu screen.
	 *  
	 *  @param e  The initiating action, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		open();
	}
	
	/**
	 *  Opens the main menu screen.
	 *  <p>
	 *  If the root view is the log in screen, then that window will be closed
	 *  and a new window containing the main menu will be created. Otherwise,
	 *  the window will be kept.
	 */
	public void open()
	{
		// Want to close the login screen, but keep the grader screen
		if(rootView instanceof LogInView)
		{
			// Close the log in screen
			rootView.closeWindow();
			
			// Create new root view
			GraderView gv = new GraderView();
			
			// Create main menu screen
			MainMenuView mmv = new MainMenuView(gv, user);
			
			// Display main menu screen
			gv.setNewView(mmv);
			gv.update();
			gv.display();
		}
		else  // Keep window
		{
			// Create main menu screen
			MainMenuView mmv = new MainMenuView(rootView, user);
			
			// Swap displayed screen
			rootView.setNewView(mmv);
			
			// Display screen
			rootView.update();
			rootView.display();
		}
	}

}
