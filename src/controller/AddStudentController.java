package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boundary.*;
import entity.*;

/**
 * 	Determines the student selected by the user and adds the student to the course and respective section.
 *  @author David Sullo
 */
public class AddStudentController implements ActionListener {

    public static enum AddStudentProblem {NO_ERROR, NO_F_NAME, NO_L_NAME,
    	NO_BUID, BAD_BUID, NO_SECTION, DUPLICATED_STUDENT};
    private IGraderFrame rootView;
    private AddStudentView studentInfo;
    private User user;
    private Course course;

    /**
	 *  Constructor.
	 *  
	 *  @param rootView  The application window frame object
	 *  @param studentInfo  The screen with add student
	 *  @param user  The user currently working
	 *  @param course  The course being worked on
	 */
    public AddStudentController(IGraderFrame rootView, AddStudentView studentInfo,
    		User user, Course course) {
        this.rootView = rootView;
        this.studentInfo = studentInfo;
        this.user = user;
        this.course = course;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        AddStudentProblem error = validateInformation();

        String firstName = studentInfo.getFirstName();
        String lastName = studentInfo.getLastName();
        String buID = studentInfo.getBUID();
        Section section = studentInfo.getSelectedSection();

        if (error == AddStudentProblem.NO_ERROR) {
            // Create and add Student where appropriate
            Student newStudent = new Student(firstName, lastName, buID, StudentStatus.ACTIVE);
            course.addStudent(newStudent);
            section.addStudent(newStudent);
            
            // Tell user about success
            studentInfo.showSuccess();
        }
        else { // Error occurred

            // Tell user about problem
            studentInfo.showError(error);
        }

        // Update display
        rootView.update();
        rootView.display();
    }

    private AddStudentProblem validateInformation() {

        String firstName = studentInfo.getFirstName();
        String lastName = studentInfo.getLastName();
        String buID = studentInfo.getBUID();
        Section section = studentInfo.getSelectedSection();

        // Check empty fields
        if (firstName.isEmpty()) {
            return AddStudentProblem.NO_F_NAME;
        }
        if (lastName.isEmpty()) {
            return AddStudentProblem.NO_L_NAME;
        }
        if (buID.isEmpty()) {
            return AddStudentProblem.NO_BUID;
        }
        if (section == null) {
            return AddStudentProblem.NO_SECTION;
        }

        // Check for bad buID
        if ( (buID.charAt(0) != 'u' && buID.charAt(0) != 'U')
        		|| buID.length() < 9) {
            return AddStudentProblem.BAD_BUID;
        }
        for (int i = 1; i < 9; i++) {
            if ("1234567890".indexOf(buID.charAt(i)) == -1) {
                return AddStudentProblem.BAD_BUID;
            }
        }

        /* Check for duplicate student 
         * doesn't use Student.equals() as we are comparing IDs 
         * and if student doesn't exist then comparison is useless 
         */
        for (Student s: course.getStudents()) {
            if (s.getBUID().equals(buID)) {
                return AddStudentProblem.DUPLICATED_STUDENT;
            }
        }

        return AddStudentProblem.NO_ERROR;
    }
    
}