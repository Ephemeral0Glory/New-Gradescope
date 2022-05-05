package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utilities.UserFileReader;
import utilities.UserFileReaderException;
import boundary.IGraderFrame;
import boundary.LogInView;

/**
 *  Logs the user out of the application.
 *  <p>
 *  At the end of the action, the application will be back at the login screen and
 *  all other windows will be closed.
 *  @author Alex Titus
 */
public class LogOutController implements ActionListener
{
	private IGraderFrame rootView;

	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The currently application frame
	 */
	public LogOutController(IGraderFrame rootView)
	{
		this.rootView = rootView;
	}
	
	/**
	 *  Discards the currently-open application frame and opens the log-in screen.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Close the application window currently open
		rootView.closeWindow();
		
		// Read users file
		try
		{
			UserFileReader reader = new UserFileReader(UserFileReader.usersFileName);
			// Open the login screen
			rootView = new LogInView(reader.readUsers());
			rootView.display();
		}
		catch (UserFileReaderException e1)
		{
			e1.printStackTrace();
		}
		
	}

}
