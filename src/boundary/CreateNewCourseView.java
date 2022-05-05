package boundary;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;

import controller.CreateNewCourseController;
import controller.OpenMainMenuController;
import entity.User;

import javax.swing.JButton;

public class CreateNewCourseView extends JPanel implements IGraderScreen {
	private JTextField nameField;
	private JTextField codeField;
	private IGraderFrame rootView;
	private User user;
	
	public CreateNewCourseView(IGraderFrame rootView, User user) {
		super();
		this.rootView = rootView;
		this.user = user;
		setupPanel();
		
	}
	
	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Create a New Course");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
		
		JLabel nameLabel = new JLabel("Course name: ");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 1;
		add(nameLabel, gbc_nameLabel);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.gridx = 2;
		gbc_nameField.gridy = 1;
		add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_codeLabel = new GridBagConstraints();
		gbc_codeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_codeLabel.anchor = GridBagConstraints.EAST;
		gbc_codeLabel.gridx = 1;
		gbc_codeLabel.gridy = 2;
		add(codeLabel, gbc_codeLabel);
		
		codeField = new JTextField();
		GridBagConstraints gbc_codeField = new GridBagConstraints();
		gbc_codeField.insets = new Insets(0, 0, 5, 0);
		gbc_codeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeField.gridx = 2;
		gbc_codeField.gridy = 2;
		add(codeField, gbc_codeField);
		codeField.setColumns(10);
		
		JButton createCourseButton = new JButton("Create");
		createCourseButton.addActionListener(new CreateNewCourseController(rootView, this, user));
		GridBagConstraints gbc_createCourseButton = new GridBagConstraints();
		gbc_createCourseButton.insets = new Insets(0, 0, 0, 5);
		gbc_createCourseButton.gridx = 1;
		gbc_createCourseButton.gridy = 4;
		add(createCourseButton, gbc_createCourseButton);
		
		JButton cancelCourseButton = new JButton("Cancel");
		cancelCourseButton.addActionListener(new OpenMainMenuController(rootView, user));
		GridBagConstraints gbc_cancelCourseButton = new GridBagConstraints();
		gbc_cancelCourseButton.gridx = 2;
		gbc_cancelCourseButton.gridy = 4;
		add(cancelCourseButton, gbc_cancelCourseButton);
	}

	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		removeAll();
		setupPanel();
	}
	
	/**
	 * @return The course name entered by the user
	 */
	public String getEnteredCoursename() {
		return nameField.getText();
	}

	/**
	 * @return The course code entered by the user
	 */
	public String getEnteredCoursecode() {
		return codeField.getText();
	}
	
	public void showCourseCreated() {
		
	}
	
	public void showCourseCreationFailed() {
		
	}
}
