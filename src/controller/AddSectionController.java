package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Course;
import entity.Section;
import entity.User;
import boundary.AddSectionView;
import boundary.IGraderFrame;
//import boundary.UserOptionsMenuView;
import boundary.ViewCourseInfoView;

public class AddSectionController implements ActionListener {
	
	public static enum SectionProblem { NO_ERROR, EMPTY_SECTION, DUPLICATED_CODE }
	private IGraderFrame rootView;
	private User user;
	private AddSectionView addSectionView;
	
	public AddSectionController(IGraderFrame rootView, User user, AddSectionView addSectionView) {
		this.rootView = rootView;
		this.user = user;
		this.addSectionView = addSectionView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SectionProblem error = validateInformation();
		
		Course course = addSectionView.getCourse();
		long courseID = course.getID();

		if (error == SectionProblem.NO_ERROR) {
			Section newSection = new Section(addSectionView.getName(), courseID, addSectionView.getCode());
			course.addSection(newSection);
		}
		
		openViewCourseInfoView(course);
		
	}
	
	private SectionProblem validateInformation() {
		String sectionCode = addSectionView.getCode();
		
		if (sectionCode.isEmpty()) {
			return SectionProblem.EMPTY_SECTION;
		}
		
		return SectionProblem.NO_ERROR;
	}
	
	private void openViewCourseInfoView(Course course) {
//		OpenViewCourseInfoViewController ovcivc = new OpenViewCourseInfoViewController(rootView, user, course);
		// Create menu
		ViewCourseInfoView vciv = new ViewCourseInfoView(rootView, user, course);
		
		// Display it
		rootView.setNewView(vciv);
		rootView.update();
		rootView.display();
	}

}
