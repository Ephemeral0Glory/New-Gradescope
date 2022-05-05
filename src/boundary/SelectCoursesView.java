package boundary;

import javax.swing.JPanel;

import entity.*;
import utilities.GradebookFileReader;
import utilities.GradebookFileReaderException;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 * 
 *  @author David Sullo
 */
public class SelectCoursesView extends JPanel implements IGraderScreen {
	private IGraderFrame rootView;
	private User user;
	private Gradebook gradebook;
	
	public SelectCoursesView(IGraderFrame rootView, User user, Gradebook gradebook) throws GradebookFileReaderException {
		this.rootView = rootView;
		this.user = user;
		this.gradebook = gradebook;
		setupPanel();
		
	}

	@Override
	public JPanel getPanelContent() {
		return this;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	private void setupPanel() throws GradebookFileReaderException {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Select a Course from the list below");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.gridwidth = 3;
		gbc_titleLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);

		
		
		JList<Course> courseList = new JList<Course>(getCourses());
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_courseList = new GridBagConstraints();
		gbc_courseList.gridwidth = 2;
		gbc_courseList.insets = new Insets(0, 0, 5, 5);
		gbc_courseList.fill = GridBagConstraints.BOTH;
		gbc_courseList.gridx = 0;
		gbc_courseList.gridy = 1;
		add(courseList, gbc_courseList);
		
		JScrollBar scrollBar = new JScrollBar();
		GridBagConstraints gbc_scrollBar = new GridBagConstraints();
		gbc_scrollBar.insets = new Insets(0, 0, 5, 0);
		gbc_scrollBar.anchor = GridBagConstraints.EAST;
		gbc_scrollBar.gridx = 2;
		gbc_scrollBar.gridy = 1;
		add(scrollBar, gbc_scrollBar);
		
		JButton selectButton = new JButton("Select");
		GridBagConstraints gbc_selectButton = new GridBagConstraints();
		gbc_selectButton.insets = new Insets(0, 0, 0, 5);
		gbc_selectButton.gridx = 0;
		gbc_selectButton.gridy = 2;
		add(selectButton, gbc_selectButton);
		
		JButton cancelButton = new JButton("Cancel");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 2;
		add(cancelButton, gbc_cancelButton);

	}

	private Course[] getCourses() throws GradebookFileReaderException {
		ArrayList<Semester> semesters = gradebook.getSemesters();
		ArrayList<Course> result = new ArrayList<Course>();
		Semester currSemester;
		for (int i = 0; i < semesters.size(); i++) {
			currSemester = semesters.get(i);
			for (int j = 0; j < currSemester.getCourses().size(); j++) {
				result.add(currSemester.getCourses().get(j));
			}
		}
		return (Course[]) result.toArray();

	}

}
