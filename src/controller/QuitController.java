package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.IGraderFrame;

/**
 *  Closes the application.
 *  
 *  @author Alex Titus
 */
public class QuitController implements ActionListener
{
	private IGraderFrame root;
	
	/**
	 *  Constructor.
	 *  
	 *  @param root  The application window currently open
	 */
	public QuitController(IGraderFrame root)
	{
		this.root = root;
	}
	
	/**
	 *  Closes the application.
	 *  
	 *  @param ae  The triggering ActionEvent, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Close window
		root.closeWindow();
		
		// End program
		System.exit(0);
	}

}
