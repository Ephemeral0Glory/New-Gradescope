package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.Entry;
import boundary.CourseView;
import boundary.IGraderFrame;

/**
 *  Deletes the entry from the entries table.
 *  @author Alex Titus
 */
public class DeleteEntryController implements ActionListener
{
	private IGraderFrame rootView;
	private Entry entry;

	public DeleteEntryController(IGraderFrame rootView, Entry entry)
	{
		this.rootView = rootView;
		this.entry = entry;
	}
	
	/**
	 *  Deletes the entry from the entries table.
	 *  
	 *  @param ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		delete();
	}
	
	/**
	 *  Deletes the entry from the entries table
	 */
	public void delete()
	{
		// Get course
		Course c = ((CourseView) rootView.getCurrentDisplay()).getCourse();
		
		// Remove entry
		c.removeEntry(entry);
		
		// Update display
		rootView.update();
		rootView.display();
	}

}
