package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.IGraderFrame;

/**
 *  Closes application window and exits the application.
 *  @author Alex Titus
 */
public class ExitApplicationController implements ActionListener
{
	private IGraderFrame rootView;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 */
	public ExitApplicationController(IGraderFrame rootView)
	{
		this.rootView = rootView;
	}

	/**
	 *  Closes application window and exit the application.
	 *  
	 *  @param ae  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		exit();
	}
	
	/**
	 *  Closes application window and exits the application.
	 */
	public void exit()
	{
		rootView.closeWindow();
		System.exit(0);
	}

}
