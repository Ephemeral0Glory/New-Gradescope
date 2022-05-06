package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.IGraderFrame;

/**
 *  Closes a popup window.
 *  @author Alex Titus
 */
public class ClosePopupWindowController implements ActionListener
{
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The frame of the popup window
	 *  @param parentView  The overall application window frame
	 */
	public ClosePopupWindowController(IGraderFrame rootView, IGraderFrame parentView)
	{
		this.rootView = rootView;
		this.parentView = parentView;
	}

	/**
	 *  Closes the popup window. Refreshes the parent window
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		returnToParent();
	}
	
	/**
	 *  Closes the popup window. Refreshes the parent window
	 */
	public void returnToParent()
	{
		// Close popup
		rootView.closeWindow();
		
		// Refresh parent
		parentView.update();
		parentView.display();
	}

}
