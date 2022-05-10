# CS611-Final Project
## Grader
---------------------------------------------------------------------------
Alex Titus, David Sullo, Seonghoon (Steve) Cho
adtitus@bu.edu dsullo@bu.edu bluehat2@bu.edu
U93786205   U44891473    U65376445
## Files
---------------------------------------------------------------------------
boundary
  AddAssignmentView
  AddEntryView
  AddSectionView
  AddStudentView
  CourseInfoPanelView
  CourseView
  CreateNewCourseView
  CreateNewUserView
  EditAssignmentView
  EntryView
  GraderView
  IGraderFrame: interface for window frame objects
  IGraderScreen: interface for screen panel objects
  LogInView
  Main
  MainMenuView
  RemoveAssignmentsSubPanelView
  RemoveAssignmentView
  RemoveEntryView
  RemoveSectionView
  RemoveStudentView
  SelectAssignmentToEditView
  SelectCoursesView
  UserOptionsMenuView
  ViewCourseInfoView
controller
  AddAssignmentAddSubController: adds sub-assignment to add assignment screen
  AddAssignmentController:  adds assignment to course
  AddEntryController: adds entry to course
  AddSectionController: adds section to course
  AddStudentController: adds student to course section
  AddStudentSectionChangeController: updates display when section is changed
  ChangePasswordController: changes a user's password
  ClosePopupWindowController: closes a popup window
  ColumnSelectedController: displays a column's information in the course info panel
  CreateNewCourseController: creates a new course
  CreateNewUserController: creates a new user
  DeleteAssignmentController: deletes an assignment from a course
  DeleteEntryController: deletes an entry from the entries table
  EditAssignmentAddSubController: adds a sub-assignment to the edit assignment screen
  EntrySelectedController: displays an entry's information in the course info panel
  ExitApplicationController: closes the application
  LoginController: logs the user in to the main menu
  LogOutController: logs the user out to the log in screen
  OpenAddAssignmentWindowController: opens the add assignment window
  OpenAddEntryWindowController: opens the add entry window
  OpenAddSectionWindowController: opens the add section window
  OpenAddStudentController: opens the add student window
  OpenCourseController: opens the course edit screen
  OpenCreateNewUserController: opens the create new user screen
  OpenEditAssignmentController: opens the edit assignment window
  OpenLogInController: opens the log-in screen
  OpenMainMenuController: opens the main menu
  OpenNewCourseController: opens the create new course screen
  OpenRemoveAssignmentWindowController: opens the remove assignment window
  OpenRemoveEntryWindowController: opens the remove entry window
  OpenRemoveSectionWindowController: opens the remove section window
  OpenRemoveStudentWindowController: opens the remove student window
  OpenSelectAssignmentToEditController: opens the assignment select window
  OpenSelectCoursesController: opens the select courses screen
  OpenUserOptionsController: opens the user options screen
  OpenViewCourseInfoViewController: opens the course info screen
  OpenViewCoursesInfoController: opens the course info screen
  QuitController: closes the application
  RemoveAssignmentController: removes an assignment from a course
  RemoveEntryController: removes an entry from a course
  RemoveSectionController: removes a section from a course
  RemoveSelectedAssignmentController: removes an assignment from a course (from select assignment screen)
  RemoveStudentController: removes a student from a course
  RemoveStudentsSectionChangeController: updates the student listing in remove students screen
  SaveCourseDataController: saves changes to course out to file
  SearchController: searches entry table for matching entries
  SectionChangeController: changes students listing in add entry screen
  SelectAssignmentToEditController: selects an assignment to edit
  SelectCourseController: selects a course to edit
  SelectCoursesSemesterChangeController: updates the course listing in select courses screen
  UpdateEntryController: updates entry information
  UpdateGradesController: updates grades information from column view
  UpdateNameWeightController: updates an assignment
  UpdateUserInfoController: updates user info
entity
  Course
  Entry
  Grade
  Gradeable
  Gradebook
  NullAssignment
  RealAssignment
  Season
  Section
  Semester
  Student
  StudentStatus
  User
  UserType
utilities
  ConfigFileReader
  ConfigFileReaderException
  ConfigFileWriter
  ConfigFileWriterException
  CourseFileReader
  CourseFileReaderException 
  CourseFileWriter
  CourseFileWriterException
  GradebookFileReader
  GradebookFileReaderException
  GradebookFileWriter
  GradebookFileWriterException
  IDFactory
  UserFileReader
  UserFileReaderException
  UserFileWriter
  UserFileWriterException
  
## Notes
---------------------------------------------------------------------------
1. <Files to be parsed should be stored in ConfigFiles, for parser class to
read class> None
2. <Bonus Done>
3. <Things instructions to note>
## How to run
---------------------------------------------------------------------------
1. Navigate to the directory "New-Gradescope" after unzipping the files
2. Run the following instructions:
javac -d bin src/entity/*.java src/boundary/*.java src/controller/*.java src/utilities/*.java
java -cp bin boundary.Main
