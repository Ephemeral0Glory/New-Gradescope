package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Gradebook;
import entity.User;
import utilities.GradebookFileReader;
import utilities.GradebookFileReaderException;
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
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application's window frame object
	 *  @param user  The current user
	 */
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
		try {
			open();
		} catch (GradebookFileReaderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 *  Opens the main menu screen.
	 *  <p>
	 *  If the root view is the log in screen, then that window will be closed
	 *  and a new window containing the main menu will be created. Otherwise,
	 *  the window will be kept.
	 * @throws GradebookFileReaderException  If error encountered while reading gradebook file
	 */
	public void open() throws GradebookFileReaderException
	{
		// Get user's gradebook
		Gradebook gb = new GradebookFileReader(GradebookFileReader.gradebookDirectory).readGradebook(user);
		
		// Want to close the login screen, but keep the grader screen
		if(rootView instanceof LogInView)
		{
			// Close the log in screen
			rootView.closeWindow();
			
			// Create new root view
			GraderView gv = new GraderView();
			
			// Create main menu screen
			MainMenuView mmv = new MainMenuView(gv, user, gb);
			
			// Display main menu screen
			gv.setNewView(mmv);
			gv.update();
			gv.display();
		}
		else  // Keep window
		{
			// Create main menu screen
			MainMenuView mmv = new MainMenuView(rootView, user, gb);
			
			// Swap displayed screen
			rootView.setNewView(mmv);
			
			// Display screen
			rootView.update();
			rootView.display();
		}
	}

}
