package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import entity.RealAssignment;
import boundary.CourseView;
import boundary.IGraderFrame;

/**
 *  Puts the column's information into CourseView's information panel.
 *  @author Alex Titus
 */
public class ColumnSelectedController implements MouseListener
{
	private IGraderFrame rootView;
	private CourseView courseView;
	private int columnIndex;
	
	/**
	 *  Constructor.
	 *  
	 *  @param rootView
	 *  @param courseView
	 *  @param ra
	 */
	public ColumnSelectedController(IGraderFrame rootView, CourseView courseView,
			int columnIndexInHeader)
	{
		this.rootView = rootView;
		this.courseView = courseView;
		columnIndex = columnIndexInHeader - 3;  // 3 for Section, Student, BUID
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Get column to display
		ArrayList<RealAssignment> column = courseView.getCourse().getAggregateAssignment(columnIndex);
		
		// Set up info panel
		courseView.showColumnInfo(column);
		
		// Update screen
		rootView.update();
		rootView.display();
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
	public void mousePressed(MouseEvent e)
	{
		// Ignore
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// Ignore
	}

}
