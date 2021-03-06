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
		Font panelFont = new Font("Tahoma", Font.PLAIN, 16);
		
		JLabel title = new JLabel("Add a Section");
		title.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		gbc_title.weighty = 0.3;
		add(title, gbc_title);
		
		JLabel lblCourseCode = new JLabel("Course code:");
		lblCourseCode.setFont(panelFont);
		GridBagConstraints gbc_lblCourseCode = new GridBagConstraints();
		gbc_lblCourseCode.anchor = GridBagConstraints.EAST;
		gbc_lblCourseCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseCode.gridx = 0;
		gbc_lblCourseCode.gridy = 1;
		gbc_lblCourseCode.weighty = 0.1;
		add(lblCourseCode, gbc_lblCourseCode);
		
		lblCourseCodeData = new JLabel("***");
		lblCourseCodeData.setFont(panelFont);
		GridBagConstraints gbc_lblCourseCodeData = new GridBagConstraints();
		gbc_lblCourseCodeData.anchor = GridBagConstraints.WEST;
		gbc_lblCourseCodeData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCourseCodeData.gridx = 1;
		gbc_lblCourseCodeData.gridy = 1;
		gbc_lblCourseCodeData.weighty = 0.1;
		add(lblCourseCodeData, gbc_lblCourseCodeData);
		
		JLabel lblCourseName = new JLabel("Course name:");
		lblCourseName.setFont(panelFont);
		GridBagConstraints gbc_lblCourseName = new GridBagConstraints();
		gbc_lblCourseName.anchor = GridBagConstraints.EAST;
		gbc_lblCourseName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseName.gridx = 0;
		gbc_lblCourseName.gridy = 2;
		gbc_lblCourseName.weighty = 0.1;
		add(lblCourseName, gbc_lblCourseName);
		
		lblCourseNameData = new JLabel("***");
		lblCourseNameData.setFont(panelFont);
		GridBagConstraints gbc_lblCourseNameData = new GridBagConstraints();
		gbc_lblCourseNameData.anchor = GridBagConstraints.WEST;
		gbc_lblCourseNameData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCourseNameData.gridx = 1;
		gbc_lblCourseNameData.gridy = 2;
		gbc_lblCourseNameData.weighty = 0.1;
		add(lblCourseNameData, gbc_lblCourseNameData);
		
		JLabel lblSectionCode = new JLabel("Section code:");
		lblSectionCode.setFont(panelFont);
		GridBagConstraints gbc_lblSectionCode = new GridBagConstraints();
		gbc_lblSectionCode.anchor = GridBagConstraints.EAST;
		gbc_lblSectionCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblSectionCode.gridx = 0;
		gbc_lblSectionCode.gridy = 3;
		gbc_lblSectionCode.weighty = 0.1;
		add(lblSectionCode, gbc_lblSectionCode);
		
		txtSectionCode = new JTextField();
		txtSectionCode.setFont(panelFont);
		GridBagConstraints gbc_txtSectionCode = new GridBagConstraints();
		gbc_txtSectionCode.anchor = GridBagConstraints.WEST;
		gbc_txtSectionCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtSectionCode.fill = GridBagConstraints.NONE;
		gbc_txtSectionCode.gridx = 1;
		gbc_txtSectionCode.gridy = 3;
		gbc_txtSectionCode.weighty = 0.1;
		add(txtSectionCode, gbc_txtSectionCode);
		txtSectionCode.setColumns(10);
		
		JLabel lblSectionName = new JLabel("Section name:");
		lblSectionName.setFont(panelFont);
		GridBagConstraints gbc_lblSectionName = new GridBagConstraints();
		gbc_lblSectionName.anchor = GridBagConstraints.EAST;
		gbc_lblSectionName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSectionName.gridx = 0;
		gbc_lblSectionName.gridy = 4;
		gbc_lblSectionName.weighty = 0.1;
		add(lblSectionName, gbc_lblSectionName);
		
		txtSectionName = new JTextField();
		txtSectionName.setFont(panelFont);
		GridBagConstraints gbc_txtSectionName = new GridBagConstraints();
		gbc_txtSectionName.anchor = GridBagConstraints.WEST;
		gbc_txtSectionName.insets = new Insets(0, 0, 5, 0);
		gbc_txtSectionName.fill = GridBagConstraints.NONE;
		gbc_txtSectionName.gridx = 1;
		gbc_txtSectionName.gridy = 4;
		gbc_txtSectionName.weighty = 0.1;
		add(txtSectionName, gbc_txtSectionName);
		txtSectionName.setColumns(25);
		
		JButton btnConfirm = new JButton("Create");
		btnConfirm.setFont(panelFont);
		btnConfirm.addActionListener(new AddSectionController(rootView, user, this));
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = 5;
		gbc_btnConfirm.weighty = 0.3;
		gbc_btnConfirm.weightx = 0.5;
		add(btnConfirm, gbc_btnConfirm);
		
		JButton closeButton = new JButton("Close");
		closeButton.setFont(panelFont);
		closeButton.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 0, 5);
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 5;
		gbc_closeButton.weightx = 0.5;
		gbc_closeButton.weighty = 0.3;
		add(closeButton, gbc_closeButton);
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
		repaint();
		clearData();
		setupPanelWithError(SectionProblem.NO_ERROR);
	}
	
	public void showError(SectionProblem error)
	{
		removeAll();
		repaint();
		setupPanelWithError(error);
	}
	
	private void setupPanelWithError(SectionProblem error)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		Font panelFont = new Font("Tahoma", Font.PLAIN, 16);
		int y = 0;  // First row
		
		JLabel lblNewLabel = new JLabel("Add a Section");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = y;
		gbc_lblNewLabel.weighty = 0.3;
		add(lblNewLabel, gbc_lblNewLabel);
		y++;  // Next row
		
		JLabel lblCourseCode = new JLabel("Course code:");
		lblCourseCode.setFont(panelFont);
		GridBagConstraints gbc_lblCourseCode = new GridBagConstraints();
		gbc_lblCourseCode.anchor = GridBagConstraints.EAST;
		gbc_lblCourseCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseCode.gridx = 0;
		gbc_lblCourseCode.gridy = y;
		gbc_lblCourseCode.weighty = 0.1;
		add(lblCourseCode, gbc_lblCourseCode);
		
		GridBagConstraints gbc_lblCourseCodeData = new GridBagConstraints();
		gbc_lblCourseCodeData.anchor = GridBagConstraints.WEST;
		gbc_lblCourseCodeData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCourseCodeData.gridx = 1;
		gbc_lblCourseCodeData.gridy = y;
		gbc_lblCourseCodeData.weighty = 0.1;
		add(lblCourseCodeData, gbc_lblCourseCodeData);
		y++;  // Next row
		
		JLabel lblCourseName = new JLabel("Course name:");
		lblCourseName.setFont(panelFont);
		GridBagConstraints gbc_lblCourseName = new GridBagConstraints();
		gbc_lblCourseName.anchor = GridBagConstraints.EAST;
		gbc_lblCourseName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseName.gridx = 0;
		gbc_lblCourseName.gridy = y;
		gbc_lblCourseName.weighty = 0.1;
		add(lblCourseName, gbc_lblCourseName);
		
		GridBagConstraints gbc_lblCourseNameData = new GridBagConstraints();
		gbc_lblCourseNameData.anchor = GridBagConstraints.WEST;
		gbc_lblCourseNameData.insets = new Insets(0, 0, 5, 0);
		gbc_lblCourseNameData.gridx = 1;
		gbc_lblCourseNameData.gridy = y;
		gbc_lblCourseNameData.weighty = 0.1;
		add(lblCourseNameData, gbc_lblCourseNameData);
		y++;  // Next row
		
		JLabel lblSectionCode = new JLabel("Section code:");
		lblSectionCode.setFont(panelFont);
		GridBagConstraints gbc_lblSectionCode = new GridBagConstraints();
		gbc_lblSectionCode.anchor = GridBagConstraints.EAST;
		gbc_lblSectionCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblSectionCode.gridx = 0;
		gbc_lblSectionCode.gridy = y;
		gbc_lblSectionCode.weighty = 0.1;
		add(lblSectionCode, gbc_lblSectionCode);
		
		GridBagConstraints gbc_txtSectionCode = new GridBagConstraints();
		gbc_txtSectionCode.anchor = GridBagConstraints.WEST;
		gbc_txtSectionCode.insets = new Insets(0, 0, 5, 0);
		gbc_txtSectionCode.fill = GridBagConstraints.NONE;
		gbc_txtSectionCode.gridx = 1;
		gbc_txtSectionCode.gridy = y;
		gbc_txtSectionCode.weighty = 0.1;
		add(txtSectionCode, gbc_txtSectionCode);
		txtSectionCode.setColumns(10);
		y++;  // Next row
		
		if(error == SectionProblem.EMPTY_SECTION)
		{
			JLabel errorMessage = new JLabel("Please enter a section code.");
			errorMessage.setFont(panelFont);
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		if(error == SectionProblem.DUPLICATED_CODE)
		{
			JLabel errorMessage = new JLabel("This code already exists, please enter a different section code.");
			errorMessage.setFont(panelFont);
			errorMessage.setForeground(Color.RED);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.gridx = 1;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;
		}
		
		JLabel lblSectionName = new JLabel("Section name:");
		lblSectionName.setFont(panelFont);
		GridBagConstraints gbc_lblSectionName = new GridBagConstraints();
		gbc_lblSectionName.anchor = GridBagConstraints.EAST;
		gbc_lblSectionName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSectionName.gridx = 0;
		gbc_lblSectionName.gridy = y;
		gbc_lblSectionName.weighty = 0.1;
		add(lblSectionName, gbc_lblSectionName);
		
		GridBagConstraints gbc_txtSectionName = new GridBagConstraints();
		gbc_txtSectionName.anchor = GridBagConstraints.WEST;
		gbc_txtSectionName.insets = new Insets(0, 0, 5, 0);
		gbc_txtSectionName.fill = GridBagConstraints.NONE;
		gbc_txtSectionName.gridx = 1;
		gbc_txtSectionName.gridy = y;
		gbc_txtSectionName.weighty = 0.1;
		add(txtSectionName, gbc_txtSectionName);
		txtSectionName.setColumns(25);
		y += 1;  // Next row
		
		if(error == SectionProblem.NO_ERROR)
		{
			JLabel errorMessage = new JLabel("Section created.");
			errorMessage.setFont(panelFont);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = y;
			add(errorMessage, gbc);
			y++;  // Next row
		}
		
		JButton btnConfirm = new JButton("Create");
		btnConfirm.setFont(panelFont);
		btnConfirm.addActionListener(new AddSectionController(rootView, user, this));
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.anchor = GridBagConstraints.CENTER;
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = y;
		gbc_btnConfirm.weighty = 0.3;
		gbc_btnConfirm.weightx = 0.5;
		add(btnConfirm, gbc_btnConfirm);
		
		JButton btnCancel = new JButton("Close");
		btnCancel.setFont(panelFont);
		btnCancel.addActionListener(new ClosePopupWindowController(rootView, parentFrame));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.CENTER;
		gbc_btnCancel.insets = new Insets(0, 5, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = y;
		gbc_btnCancel.weightx = 0.5;
		gbc_btnCancel.weighty = 0.3;
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
