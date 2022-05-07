package boundary;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextField;

import controller.AddSectionController;
import controller.AddSectionController.SectionProblem;
import controller.ClosePopupWindowController;
import entity.Course;
import entity.User;

public class AddSectionView extends JPanel implements IGraderScreen {
	private static final long serialVersionUID = -5698463297192395963L;
	private JTextField txtSectionCode;
	private JLabel lblCourseCodeData;
	private JLabel lblCourseNameData;
	private IGraderFrame rootView;
	private IGraderFrame parentFrame;
	private User user;
	private Course course;
	private JTextField txtSectionName;
	
	/**
	 * Create the panel.
	 */
	public AddSectionView(IGraderFrame rootView, IGraderFrame parentFrame, User user, Course course) {
		this.rootView = rootView;
		this.parentFrame = parentFrame;
		this.user = user;
		this.course = course;
		
		setupPanel();
		
		// Fill data
		lblCourseCodeData.setText(course.getCode());
		lblCourseNameData.setText(course.getName());
	}

	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Add a Section");
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		JLabel lblCourseCode = new JLabel("Course code:");
		GridBagConstraints gbc_lblCourseCode = new GridBagConstraints();
		gbc_lblCourseCode.anchor = GridBagConstraints.EAST;
		gbc_lblCourseCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseCode.gridx = 0;
		gbc_lblCourseCode.gridy = 1;
		add(lblCourseCode, gbc_lblCourseCode);
		
		lblCourseCodeData = new JLabel("***");
		GridBagConstraints gbc_lblCourseCodeData = new GridBagConstraints();
		gbc_lblCourseCodeData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCourseCodeData.gridx = 1;
		gbc_lblCourseCodeData.gridy = 1;
		add(lblCourseCodeData, gbc_lblCourseCodeData);
		
		JLabel lblCourseName = new JLabel("Course name:");
		GridBagConstraints gbc_lblCourseName = new GridBagConstraints();
		gbc_lblCourseName.anchor = GridBagConstraints.EAST;
		gbc_lblCourseName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseName.gridx = 0;
		gbc_lblCourseName.gridy = 2;
		add(lblCourseName, gbc_lblCourseName);
		
		lblCourseNameData = new JLabel("***");
		GridBagConstraints gbc_lblCourseNameData = new GridBagConstraints();
		gbc_lblCourseNameData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCourseNameData.gridx = 1;
		gbc_lblCourseNameData.gridy = 2;
		add(lblCourseNameData, gbc_lblCourseNameData);
		
		JLabel lblSectionCode = new JLabel("Section code:");
		GridBagConstraints gbc_lblSectionCode = new GridBagConstraints();
		gbc_lblSectionCode.anchor = GridBagConstraints.EAST;
		gbc_lblSectionCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblSectionCode.gridx = 0;
		gbc_lblSectionCode.gridy = 3;
		add(lblSectionCode, gbc_lblSectionCode);
		
		txtSectionCode = new JTextField();
		GridBagConstraints gbc_txtSectionCode = new GridBagConstraints();
		gbc_txtSectionCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtSectionCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSectionCode.gridx = 1;
		gbc_txtSectionCode.gridy = 3;
		add(txtSectionCode, gbc_txtSectionCode);
		txtSectionCode.setColumns(10);
		
		JLabel lblSectionName = new JLabel("Section name:");
		GridBagConstraints gbc_lblSectionName = new GridBagConstraints();
		gbc_lblSectionName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSectionName.gridx = 0;
		gbc_lblSectionName.gridy = 4;
		add(lblSectionName, gbc_lblSectionName);
		
		txtSectionName = new JTextField();
		GridBagConstraints gbc_txtSectionName = new GridBagConstraints();
		gbc_txtSectionName.insets = new Insets(0, 0, 5, 0);
		gbc_txtSectionName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSectionName.gridx = 1;
		gbc_txtSectionName.gridy = 4;
		add(txtSectionName, gbc_txtSectionName);
		txtSectionName.setColumns(10);
		
//		JButton btnAddStudent = new JButton("Add Student");
//		GridBagConstraints gbc_btnAddStudent = new GridBagConstraints();
//		gbc_btnAddStudent.insets = new Insets(0, 0, 0, 5);
//		gbc_btnAddStudent.gridx = 0;
//		gbc_btnAddStudent.gridy = 5;
//		add(btnAddStudent, gbc_btnAddStudent);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new AddSectionController(rootView, parentFrame, user, this));
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = 5;
		add(btnConfirm, gbc_btnConfirm);
		
		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 5;
		add(btnCancel, gbc_btnCancel);
	}
	
	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		// Ignore
	}
	
	public void showSuccess()
	{
		removeAll();
		clearData();
		setupPanelWithError(SectionProblem.NO_ERROR);
	}
	
	public void showError(SectionProblem error)
	{
		removeAll();
		setupPanelWithError(error);
	}
	
	private void setupPanelWithError(SectionProblem error)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		int y = 0;  // First row
		
		JLabel lblNewLabel = new JLabel("Add a Section");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = y;
		add(lblNewLabel, gbc_lblNewLabel);
		y++;  // Next row
		
		JLabel lblCourseCode = new JLabel("Course code:");
		GridBagConstraints gbc_lblCourseCode = new GridBagConstraints();
		gbc_lblCourseCode.anchor = GridBagConstraints.EAST;
		gbc_lblCourseCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseCode.gridx = 0;
		gbc_lblCourseCode.gridy = y;
		add(lblCourseCode, gbc_lblCourseCode);
		
		GridBagConstraints gbc_lblCourseCodeData = new GridBagConstraints();
		gbc_lblCourseCodeData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCourseCodeData.gridx = 1;
		gbc_lblCourseCodeData.gridy = y;
		add(lblCourseCodeData, gbc_lblCourseCodeData);
		y++;  // Next row
		
		JLabel lblCourseName = new JLabel("Course name:");
		GridBagConstraints gbc_lblCourseName = new GridBagConstraints();
		gbc_lblCourseName.anchor = GridBagConstraints.EAST;
		gbc_lblCourseName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseName.gridx = 0;
		gbc_lblCourseName.gridy = y;
		add(lblCourseName, gbc_lblCourseName);
		
		GridBagConstraints gbc_lblCourseNameData = new GridBagConstraints();
		gbc_lblCourseNameData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCourseNameData.gridx = 1;
		gbc_lblCourseNameData.gridy = y;
		add(lblCourseNameData, gbc_lblCourseNameData);
		y++;  // Next row
		
		JLabel lblSectionCode = new JLabel("Section code:");
		GridBagConstraints gbc_lblSectionCode = new GridBagConstraints();
		gbc_lblSectionCode.anchor = GridBagConstraints.EAST;
		gbc_lblSectionCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblSectionCode.gridx = 0;
		gbc_lblSectionCode.gridy = y;
		add(lblSectionCode, gbc_lblSectionCode);
		
		GridBagConstraints gbc_txtSectionCode = new GridBagConstraints();
		gbc_txtSectionCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtSectionCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSectionCode.gridx = 1;
		gbc_txtSectionCode.gridy = y;
		add(txtSectionCode, gbc_txtSectionCode);
		txtSectionCode.setColumns(10);
		y++;  // Next row
		
		if(error == SectionProblem.EMPTY_SECTION)
		{
			JLabel errorMessage = new JLabel("Please enter a section code.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		if(error == SectionProblem.DUPLICATED_CODE)
		{
			JLabel errorMessage = new JLabel("This code already exists, please enter a different section code.");
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel lblSectionName = new JLabel("Section name:");
		GridBagConstraints gbc_lblSectionName = new GridBagConstraints();
		gbc_lblSectionName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSectionName.gridx = 0;
		gbc_lblSectionName.gridy = y;
		add(lblSectionName, gbc_lblSectionName);
		
		GridBagConstraints gbc_txtSectionName = new GridBagConstraints();
		gbc_txtSectionName.insets = new Insets(0, 0, 5, 0);
		gbc_txtSectionName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSectionName.gridx = 1;
		gbc_txtSectionName.gridy = y;
		add(txtSectionName, gbc_txtSectionName);
		txtSectionName.setColumns(10);
		y += 1;  // Next row
		
//		JButton btnAddStudent = new JButton("Add Student");
//		GridBagConstraints gbc_btnAddStudent = new GridBagConstraints();
//		gbc_btnAddStudent.insets = new Insets(0, 0, 0, 5);
//		gbc_btnAddStudent.gridx = 0;
//		gbc_btnAddStudent.gridy = y;
//		add(btnAddStudent, gbc_btnAddStudent);
		
		if(error == SectionProblem.NO_ERROR)
		{
			JLabel errorMessage = new JLabel("Section created!");
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JButton btnConfirm = new JButton("Create");
		btnConfirm.addActionListener(new AddSectionController(rootView, parentFrame, user, this));
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = y;
		add(btnConfirm, gbc_btnConfirm);
		
		JButton btnCancel = new JButton("Close");
		btnCancel.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 5, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = y;
		add(btnCancel, gbc_btnCancel);
	}
	
	private void clearData()
	{
		txtSectionCode.setText("");
		txtSectionName.setText("");
	}
	
	public String getCode() {
		return txtSectionCode.getText();
	}
	
	public String getName() {
		return txtSectionName.getText();
	}
	
	public Course getCourse() {
		return course;
	}

}
