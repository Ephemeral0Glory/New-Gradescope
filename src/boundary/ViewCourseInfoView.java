package boundary;

import javax.swing.JPanel;

import entity.Course;
import entity.Gradebook;
import entity.NullAssignment;
import entity.StudentStatus;
import entity.User;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;

import controller.ClosePopupWindowController;
import controller.OpenSelectCoursesController;

import javax.swing.JButton;

import java.awt.Font;

/**
 *  
 *  @author Seonghoon Steve Cho
 *  @author Alex Titus
 */
public class ViewCourseInfoView extends JPanel implements IGraderScreen {

	private static final long serialVersionUID = 1L;
	private IGraderFrame rootView;
	private IGraderFrame parentView;
	private User user;
	private Gradebook gradebook;
	private Course course;
	private boolean toSelectCourses;
	
	private JLabel lblTitle;
	private JLabel lblNumOfSectionsData;
	private JLabel lblNumOfAssignmentsData;
	private JLabel lblCurrentStudentsData;
	private JLabel lblStartStudentsData;
	private JLabel lblDroppedStudentsData;

	/**
	 * Create the panel.
	 */
	public ViewCourseInfoView(IGraderFrame rootView, IGraderFrame parentView,
			User user, Gradebook gradebook, Course course, boolean toMainMenu) {
		super();
		this.rootView = rootView;
		this.parentView = parentView;
		this.user = user;
		this.gradebook = gradebook;
		this.course = course;
		this.toSelectCourses = toMainMenu;

	
		setupPanel();
		// fill data
		fillData();
	}
	
	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblTitle = new JLabel("Course code and name will be displayed here");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.CENTER;
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(10, 5, 5, 5);
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		gbc_lblTitle.weighty = 0.3;
		gbc_lblTitle.weightx = 1.0;
		add(lblTitle, gbc_lblTitle);
		
		JLabel lblNumOfSections = new JLabel("Number of sections:");
		lblNumOfSections.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumOfSections.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblNumOfSections = new GridBagConstraints();
		gbc_lblNumOfSections.weightx = 1.0;
		gbc_lblNumOfSections.weighty = 0.1;
		gbc_lblNumOfSections.anchor = GridBagConstraints.EAST;
		gbc_lblNumOfSections.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOfSections.gridx = 0;
		gbc_lblNumOfSections.gridy = 1;
		add(lblNumOfSections, gbc_lblNumOfSections);
		
		lblNumOfSectionsData = new JLabel("###");
		lblNumOfSectionsData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNumOfSectionsData = new GridBagConstraints();
		gbc_lblNumOfSectionsData.weightx = 1.0;
		gbc_lblNumOfSectionsData.weighty = 0.1;
		gbc_lblNumOfSectionsData.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOfSectionsData.gridx = 1;
		gbc_lblNumOfSectionsData.gridy = 1;
		add(lblNumOfSectionsData, gbc_lblNumOfSectionsData);
		
		JLabel lblNumOfAssignments = new JLabel("Number of assignments:");
		lblNumOfAssignments.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNumOfAssignments.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNumOfAssignments = new GridBagConstraints();
		gbc_lblNumOfAssignments.weightx = 1.0;
		gbc_lblNumOfAssignments.weighty = 0.1;
		gbc_lblNumOfAssignments.anchor = GridBagConstraints.EAST;
		gbc_lblNumOfAssignments.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOfAssignments.gridx = 0;
		gbc_lblNumOfAssignments.gridy = 2;
		add(lblNumOfAssignments, gbc_lblNumOfAssignments);
		
		lblNumOfAssignmentsData = new JLabel("###");
		lblNumOfAssignmentsData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNumOfAssignmentsData = new GridBagConstraints();
		gbc_lblNumOfAssignmentsData.weightx = 1.0;
		gbc_lblNumOfAssignmentsData.weighty = 0.1;
		gbc_lblNumOfAssignmentsData.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOfAssignmentsData.gridx = 1;
		gbc_lblNumOfAssignmentsData.gridy = 2;
		add(lblNumOfAssignmentsData, gbc_lblNumOfAssignmentsData);
		
		JLabel lblCurrentStudents = new JLabel("Current registered students:");
		lblCurrentStudents.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCurrentStudents.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCurrentStudents = new GridBagConstraints();
		gbc_lblCurrentStudents.weightx = 1.0;
		gbc_lblCurrentStudents.weighty = 0.1;
		gbc_lblCurrentStudents.anchor = GridBagConstraints.EAST;
		gbc_lblCurrentStudents.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentStudents.gridx = 0;
		gbc_lblCurrentStudents.gridy = 3;
		add(lblCurrentStudents, gbc_lblCurrentStudents);
		
		lblCurrentStudentsData = new JLabel("###");
		lblCurrentStudentsData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCurrentStudentsData = new GridBagConstraints();
		gbc_lblCurrentStudentsData.weightx = 1.0;
		gbc_lblCurrentStudentsData.weighty = 0.1;
		gbc_lblCurrentStudentsData.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentStudentsData.gridx = 1;
		gbc_lblCurrentStudentsData.gridy = 3;
		add(lblCurrentStudentsData, gbc_lblCurrentStudentsData);
		
		JLabel lblStartStudents = new JLabel("Students at the start of semester:");
		lblStartStudents.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStartStudents.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblStartStudents = new GridBagConstraints();
		gbc_lblStartStudents.weighty = 0.1;
		gbc_lblStartStudents.weightx = 1.0;
		gbc_lblStartStudents.anchor = GridBagConstraints.EAST;
		gbc_lblStartStudents.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartStudents.gridx = 0;
		gbc_lblStartStudents.gridy = 4;
		add(lblStartStudents, gbc_lblStartStudents);
		
		lblStartStudentsData = new JLabel("###");
		lblStartStudentsData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblStartStudentsData = new GridBagConstraints();
		gbc_lblStartStudentsData.weightx = 1.0;
		gbc_lblStartStudentsData.weighty = 0.1;
		gbc_lblStartStudentsData.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartStudentsData.gridx = 1;
		gbc_lblStartStudentsData.gridy = 4;
		add(lblStartStudentsData, gbc_lblStartStudentsData);
		
		JLabel lblDroppedStudents = new JLabel("Dropped students:");
		lblDroppedStudents.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDroppedStudents.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDroppedStudents = new GridBagConstraints();
		gbc_lblDroppedStudents.weighty = 0.1;
		gbc_lblDroppedStudents.weightx = 1.0;
		gbc_lblDroppedStudents.insets = new Insets(0, 0, 5, 5);
		gbc_lblDroppedStudents.anchor = GridBagConstraints.EAST;
		gbc_lblDroppedStudents.gridx = 0;
		gbc_lblDroppedStudents.gridy = 5;
		add(lblDroppedStudents, gbc_lblDroppedStudents);
		
		lblDroppedStudentsData = new JLabel("###");
		lblDroppedStudentsData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDroppedStudentsData = new GridBagConstraints();
		gbc_lblDroppedStudentsData.weighty = 0.1;
		gbc_lblDroppedStudentsData.weightx = 1.0;
		gbc_lblDroppedStudentsData.insets = new Insets(0, 0, 5, 5);
		gbc_lblDroppedStudentsData.gridx = 1;
		gbc_lblDroppedStudentsData.gridy = 5;
		add(lblDroppedStudentsData, gbc_lblDroppedStudentsData);
		
		JButton btnOk = new JButton("Close");
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		if(toSelectCourses)
		{
			// On close, return to select courses screen
			btnOk.addActionListener(new OpenSelectCoursesController(rootView, user, gradebook, false));
		}
		else
		{
			// On close, close window and return to course edit screen
			btnOk.addActionListener(new ClosePopupWindowController(rootView, parentView));
		}
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.weighty = 0.3;
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 6;
		add(btnOk, gbc_btnOk);
	}

	private void fillData()
	{
		lblTitle.setText("<html>" + course.getCode() + "<p>(" + course.getName() + ")</html>");

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
			long realAssmtCount =
					course.getTemplate().getSubAssignments().stream()
							.filter(suba -> suba instanceof NullAssignment ? false : true)
									.count();

			lblNumOfAssignmentsData.setText(String.valueOf(realAssmtCount));
		} else {
			lblNumOfAssignmentsData.setText("0");
		}
	}
	
	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		fillData();
	}

}
