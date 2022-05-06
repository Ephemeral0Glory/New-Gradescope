package boundary;

import javax.swing.JPanel;
import entity.Course;
import entity.Student;
import entity.StudentStatus;
import entity.User;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

import controller.AddSectionController;
import controller.OpenAddSectionViewController;
import controller.OpenMainMenuController;
import controller.OpenViewCourseInfoViewController;

import javax.swing.JButton;

public class ViewCourseInfoView extends JPanel implements IGraderScreen {

	private static final long serialVersionUID = 1L;
	private IGraderFrame rootView;
	private User user;
	private Course course;
	
	private JLabel lblTitle;
	private JLabel lblNumOfSectionsData;
	private JLabel lblNumOfAssignmentsData;
	private JLabel lblCurrentStudentsData;
	private JLabel lblStartStudentsData;
	private JLabel lblDroppedStudentsData;

	/**
	 * Create the panel.
	 */
	public ViewCourseInfoView(IGraderFrame rootView, User user, Course course) {
		super();
		this.rootView = rootView;
		this.user = user;	
		this.course = course;

	
		setupPanel();
		// fill data
		lblTitle.setText(course.getCode() + "(" + course.getName() + ")");

		if (course.getSections() != null) {
			lblNumOfSectionsData.setText(String.valueOf(course.getSections().size()));
		} else {
			lblNumOfAssignmentsData.setText("0");
		}
		
		if (course.getStudents() != null) {
			long numOfCurrentStudents = course.getStudents().stream().filter(student -> student.getEnrollmentStatus() == StudentStatus.ACTIVE).count();
			long numOfDroppedStudents = course.getStudents().stream().filter(student -> student.getEnrollmentStatus() == StudentStatus.WITHDRAWN).count();
			long numOfStartStudents = numOfCurrentStudents + numOfDroppedStudents;
			
			lblCurrentStudentsData.setText(String.valueOf(numOfCurrentStudents));
			lblStartStudentsData.setText(String.valueOf(numOfStartStudents));
			lblDroppedStudentsData.setText(String.valueOf(numOfDroppedStudents));
		} else {
			lblCurrentStudentsData.setText("0");
			lblStartStudentsData.setText("0");
			lblDroppedStudentsData.setText("0");
		}
		
		if  (course.getAssignments() != null) {
			lblNumOfAssignmentsData.setText(String.valueOf(course.getAssignments().size()));
		} else {
			lblNumOfAssignmentsData.setText("0");
		}
	}
	
	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblTitle = new JLabel("Course code and name will be displayed here");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 5;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 1;
		add(lblTitle, gbc_lblTitle);
		
		JLabel lblNumOfSections = new JLabel("Number of sections:");
		lblNumOfSections.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblNumOfSections = new GridBagConstraints();
		gbc_lblNumOfSections.gridwidth = 2;
		gbc_lblNumOfSections.anchor = GridBagConstraints.EAST;
		gbc_lblNumOfSections.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOfSections.gridx = 1;
		gbc_lblNumOfSections.gridy = 3;
		add(lblNumOfSections, gbc_lblNumOfSections);
		
		lblNumOfSectionsData = new JLabel("###");
		GridBagConstraints gbc_lblNumOfSectionsData = new GridBagConstraints();
		gbc_lblNumOfSectionsData.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumOfSectionsData.gridx = 3;
		gbc_lblNumOfSectionsData.gridy = 3;
		add(lblNumOfSectionsData, gbc_lblNumOfSectionsData);
		
		JLabel lblNumOfAssignments = new JLabel("Number of assignments:");
		lblNumOfAssignments.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNumOfAssignments = new GridBagConstraints();
		gbc_lblNumOfAssignments.gridwidth = 2;
		gbc_lblNumOfAssignments.anchor = GridBagConstraints.EAST;
		gbc_lblNumOfAssignments.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOfAssignments.gridx = 1;
		gbc_lblNumOfAssignments.gridy = 4;
		add(lblNumOfAssignments, gbc_lblNumOfAssignments);
		
		lblNumOfAssignmentsData = new JLabel("###");
		GridBagConstraints gbc_lblNumOfAssignmentsData = new GridBagConstraints();
		gbc_lblNumOfAssignmentsData.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumOfAssignmentsData.gridx = 3;
		gbc_lblNumOfAssignmentsData.gridy = 4;
		add(lblNumOfAssignmentsData, gbc_lblNumOfAssignmentsData);
		
		JLabel lblCurrentStudents = new JLabel("Current registered students:");
		lblCurrentStudents.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCurrentStudents = new GridBagConstraints();
		gbc_lblCurrentStudents.gridwidth = 2;
		gbc_lblCurrentStudents.anchor = GridBagConstraints.EAST;
		gbc_lblCurrentStudents.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentStudents.gridx = 1;
		gbc_lblCurrentStudents.gridy = 5;
		add(lblCurrentStudents, gbc_lblCurrentStudents);
		
		lblCurrentStudentsData = new JLabel("###");
		GridBagConstraints gbc_lblCurrentStudentsData = new GridBagConstraints();
		gbc_lblCurrentStudentsData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentStudentsData.gridx = 3;
		gbc_lblCurrentStudentsData.gridy = 5;
		add(lblCurrentStudentsData, gbc_lblCurrentStudentsData);
		
		JLabel lblStartStudents = new JLabel("Students at the start of semester:");
		lblStartStudents.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblStartStudents = new GridBagConstraints();
		gbc_lblStartStudents.gridwidth = 2;
		gbc_lblStartStudents.anchor = GridBagConstraints.EAST;
		gbc_lblStartStudents.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartStudents.gridx = 1;
		gbc_lblStartStudents.gridy = 6;
		add(lblStartStudents, gbc_lblStartStudents);
		
		lblStartStudentsData = new JLabel("###");
		GridBagConstraints gbc_lblStartStudentsData = new GridBagConstraints();
		gbc_lblStartStudentsData.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartStudentsData.gridx = 3;
		gbc_lblStartStudentsData.gridy = 6;
		add(lblStartStudentsData, gbc_lblStartStudentsData);
		
		JLabel lblDroppedStudents = new JLabel("Dropped students:");
		lblDroppedStudents.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDroppedStudents = new GridBagConstraints();
		gbc_lblDroppedStudents.gridwidth = 2;
		gbc_lblDroppedStudents.insets = new Insets(0, 0, 5, 5);
		gbc_lblDroppedStudents.anchor = GridBagConstraints.EAST;
		gbc_lblDroppedStudents.gridx = 1;
		gbc_lblDroppedStudents.gridy = 7;
		add(lblDroppedStudents, gbc_lblDroppedStudents);
		
		lblDroppedStudentsData = new JLabel("###");
		GridBagConstraints gbc_lblDroppedStudentsData = new GridBagConstraints();
		gbc_lblDroppedStudentsData.insets = new Insets(0, 0, 5, 0);
		gbc_lblDroppedStudentsData.gridx = 3;
		gbc_lblDroppedStudentsData.gridy = 7;
		add(lblDroppedStudentsData, gbc_lblDroppedStudentsData);
		
		JButton btnAddSection = new JButton("Add section");
//		btnAddSection.addActionListener(new OpenViewCourseInfoViewController(rootView, user, course));
		btnAddSection.addActionListener(new OpenAddSectionViewController(rootView, user, course));
//		btnAddSection.addActionListener(new AddSectionController(rootView, user, null));
		GridBagConstraints gbc_btnAddSection = new GridBagConstraints();
		gbc_btnAddSection.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddSection.gridx = 1;
		gbc_btnAddSection.gridy = 10;
		add(btnAddSection, gbc_btnAddSection);
		
		JButton btnOk = new JButton("Ok");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		btnOk.addActionListener(new OpenMainMenuController(rootView, user));
		gbc_btnOk.gridx = 3;
		gbc_btnOk.gridy = 10;
		add(btnOk, gbc_btnOk);
	}

	@Override
	public JPanel getPanelContent() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
