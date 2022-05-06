package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import entity.Entry;
import boundary.CourseView;
import boundary.EntryView;
import boundary.IGraderFrame;

/**
 *  Puts the entry's information into CourseView's information panel.
 *  @author Alex Titus
 */
public class EntrySelectedController implements MouseListener
{
	private IGraderFrame rootView;
	private CourseView courseView;

	/**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param courseView  The course edit screen
	 */
	public EntrySelectedController(IGraderFrame rootView, CourseView courseView)
	{
		this.rootView = rootView;
		this.courseView = courseView;
	}

	/**
	 *  Invoked when the mouse button has been clicked (pressed and released)
	 *  on an entry in the entries table. Updates the course edit screen's
	 *  info panel with the entry's information for editing.
	 */
	@Override
	public void mouseClicked(MouseEvent me)
	{
		// Get entry
		EntryView ev = (EntryView) me.getSource();
		Entry e = ev.getEntry();
		
		// Set up info panel
		courseView.showEntryInfo(e);
		
		// Update screen
		rootView.update();
		rootView.display();
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// Ignore
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Ignore
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// Ignore
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// Ignore
	}

}
