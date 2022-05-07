package controller;

import boundary.AddStudentView;
import boundary.IGraderFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Changes the displayed sections in {@link AddStudentView} when the selected section changes.
 *  @author David Sullo
 */
public class AddStudentSectionChangeController implements ActionListener {
    
    private IGraderFrame rootView;
    private AddStudentView screen;

    /**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame
	 *  @param screen  The add student screen
	 */
	public AddStudentSectionChangeController(IGraderFrame rootView, AddStudentView screen) {
        this.rootView = rootView;
        this.screen = screen;
    }

    /**
	 *  Changes the courses listing to that of the newly selected semester.
	 *  
	 *  @param ae  Ignored
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{		
		// Update screen
		rootView.update();
		rootView.display();
	}


}
