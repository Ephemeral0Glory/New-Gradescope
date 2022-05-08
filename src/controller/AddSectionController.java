package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.Section;
import entity.User;
import boundary.AddSectionView;
import boundary.IGraderFrame;

/**
 *  Opens the add section popup window.
 *  @author Seonghoon Steve Cho
 *  @author Alex Titus
 */
public class AddSectionController implements ActionListener {
	
	public static enum SectionProblem { NO_ERROR, EMPTY_SECTION, DUPLICATED_CODE }
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private User user;
	private AddSectionView addSectionView;
	
	public AddSectionController(IGraderFrame rootView, IGraderFrame parentFrame,
			User user, AddSectionView addSectionView) {
		this.rootView = rootView;
		this.parentView = parentFrame;
		this.user = user;
		this.addSectionView = addSectionView;
	}

	/**
	 *  Validates the information entered and creates the new section.
	 *  <p>
	 *  If the entered information fails validation, displays the error
	 *  on the add section screen. Closes the window after success.
	 *  
	 *  @param e  The initiating event, ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		SectionProblem error = validateInformation();
		
		Course course = addSectionView.getCourse();
		long courseID = course.getID();

		if (error == SectionProblem.NO_ERROR) {
			// Create and add new section
			Section newSection = new Section(addSectionView.getName(), courseID, addSectionView.getCode());
			course.addSection(newSection);

			returnToParentView();
		}
		else  // Had a problem
		{
			// Tell user about problem
			addSectionView.showError(error);
			rootView.update();
			rootView.display();
		}
	}
	
	private SectionProblem validateInformation() {
		String sectionCode = addSectionView.getCode();
		
		// Check empty code
		if (sectionCode.isEmpty()) {
			return SectionProblem.EMPTY_SECTION;
		}
		
		// Check for duplicate code
		for(Section s: addSectionView.getCourse().getSections())
		{
			if(sectionCode.equals(s.getCode()))
			{
				return SectionProblem.DUPLICATED_CODE;
			}
		}
		
		return SectionProblem.NO_ERROR;
	}

	private void returnToParentView() {
		// Close add student window
		rootView.closeWindow();

		// Refresh parent
		parentView.update();
		parentView.display();

	}

}
