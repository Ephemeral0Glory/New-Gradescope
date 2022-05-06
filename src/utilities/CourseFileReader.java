package utilities;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import entity.Course;
import entity.Entry;
import entity.Grade;
import entity.Gradeable;
import entity.NullAssignment;
import entity.RealAssignment;
import entity.Section;
import entity.Student;
import entity.StudentStatus;
import entity.User;

/**
 *  Reads a Course from file in XML format.
 *  <p>
 *  <h2>The course XML structure</h2>
 *  Sections with students are set apart and before the rest of the course
 *  information. Students and sections are identified by their IDs in the
 *  entries table. The outline of the XML structure is:
 *  <ul>
 *  	<li>Sections List</li>
 *  	<ul>
 *  		<li>Section</li>
 *  		<ul>
 *  			<li>Student</li>
 *  			<li>...</li>
 *  		</ul>
 *  		<li>...</li>
 *  	</ul>
 *  	<li>Course Info</li>
 *  	<li>Template Info</li>
 *  	<ul>
 *  		<li>Template Sub-assignment</li>
 *  		<li>...</li>
 *  	</ul>
 *  	<li>Entries List</li>
 *  	<ul>
 *  		<li>Entry Info</li>
 *  		<ul>
 *  			<li>Final Grade Sub-assignment</li>
 *  			<li>...</li>
 *  		</ul>
 *  		<li>...</li>
 *  	</ul>
 *  </ul>
 *  
 *  <h2>The grader file structure</h2>
 *  Users are written to file "users.xml". Each user has a unique directory.
 *  The user's directory has the user's gradebook in XML format as well as
 *  each course in XML format. A sample directory structure:
 *  <ul>
 *  	<li>users.xml</li>
 *  	<li>config.xml</li>
 *  	<li>gradebooks/</li>
 *  	<ul>
 *  		<li>u1/</li>
 *  		<ul>
 *	  			<li>gradebook.xml</li>
 *  			<li>course1.xml</li> 
 *  			<li>course2.xml</li>
 *  		</ul>
 *  		<li>u2/</li>
 *  		<ul>
 *  			<li>gradebook.xml</li>
 *  			<li>course1.xml</li>
 *  			<li>course2.xml</li>
 *  			<li>course3.xml</li>
 *  		</ul>
 *  	</ul>
 *  </ul>
 *  The user directory is named according to: "u" + user.id. This eliminates
 *  the need to store a file path for this directory. The configurations file stores
 *  data needed for proper functioning of {@link IDFactory}. The gradebook file
 *  contains the list of semesters and the list of courses for each semester.
 *  It does not contain all of the data for the courses, however, just the
 *  name, code, and id. These are used to open the actual course data
 *  in its properly-named course. Course files are named according to: "course" + course.id.
 *  @author Alex Titus
 */
public class CourseFileReader extends DefaultHandler
{
	private String courseFilePath;
	private XMLReader reader;
	/*
	 *  The following are used during parsing
	 */
	private long course_id;
	private String courseName;
	private String courseCode;
	private User owner;
	private RealAssignment template;
	private long template_id;
	private String templateName;
	private Student templateStudent;
    private float templateWeight;
    private Grade templateGrade;
    private int templateNumSubAssignments;
	private HashMap<Long, Section> sections;
	private HashMap<Long, Student> students;
	private long section_id;
	private String sectionName;
	private String sectionCode;
	private ArrayList<Student> sectionStudents;
    private ArrayList<Entry> entries;
    private long entry_id;
    private long entrySectionID;
    private long entryStudentID;
    private RealAssignment entryFinalGrade;
	private long fg_id;
	private String fgName;
	private long fgStudentID;
    private float fgWeight;
    private Grade fgGrade;
    private int fgNumSubAssignments;
    private ArrayDeque<ArrayList<Gradeable>> assignmentSubAssignmentLists;
    private ArrayDeque<Long> assignment_ids;
    private ArrayDeque<String> assignmentNames;
    private ArrayDeque<Long> assignmentStudentIDs;
    private ArrayDeque<Float> assignmentWeights;
    private ArrayDeque<Grade> assignmentGrades;
    private ArrayDeque<Integer> assignmentNumSubAssignments;
    
	
	public CourseFileReader(User owner, long courseID) throws CourseFileReaderException
	{
		courseFilePath = makeURL(owner, courseID);
		
		// Create reader from factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		try
		{
			SAXParser parser = factory.newSAXParser();
			reader = parser.getXMLReader();
			reader.setContentHandler(this);
		}
		catch(Exception e)
		{
			// This most likely won't happen
			throw new CourseFileReaderException(e.getMessage());
		}
	}
	
	private String makeURL(User owner, long courseID)
	{
		String gradebooksDirPath = GradebookFileReader.gradebookDirectory;
		String ownerDir = "u" + owner.getID() + "/";
		String courseFileName = "course" + courseID + ".xml";
		
		return gradebooksDirPath + ownerDir + courseFileName;
	}
	
	/**
	 *  Reads the course file with parameters from constructor.
	 *  
	 *  @return  The designated course
	 * @throws CourseFileReaderException 
	 */
	public Course readCourse() throws CourseFileReaderException
	{
		// Parse gradebook file for info
		try
		{
			reader.parse(courseFilePath);
		}
		catch (Exception e)
		{
			throw new CourseFileReaderException(e.getMessage());
		}
		
		return new Course(course_id, courseName, courseCode, owner, template, entries);
	}
	
	@Override
	public void startElement(String uri, String localName, String qualifiedName, Attributes atts)
	{
		// Take action depending on tag type
		// Course
		if(localName.equals("course"))
		{
			// Create the stacks
			assignmentSubAssignmentLists = new ArrayDeque<ArrayList<Gradeable>>();
		    assignment_ids = new ArrayDeque<Long>();
		    assignmentNames = new ArrayDeque<String>();
		    assignmentStudentIDs = new ArrayDeque<Long>();
		    assignmentWeights = new ArrayDeque<Float>();
		    assignmentGrades = new ArrayDeque<Grade>();
		    assignmentNumSubAssignments = new ArrayDeque<Integer>();
		}
		// Section
		else if(localName.equals("section"))
		{
			sectionStudents = new ArrayList<Student>();
		}
		// Section ID
		else if(localName.equals("sid"))
		{
			section_id = new Long(atts.getValue("text"));
		}
		// Section name
		else if(localName.equals("secname"))
		{
			sectionName = atts.getValue("text");
		}
		// Section code
		else if(localName.equals("seccode"))
		{
			sectionCode = atts.getValue("text");
		}
		// Student
		else if(localName.equals("student"))
		{
			long student_id = new Long(atts.getValue("sid"));
			String fname = atts.getValue("fname");
			String lname = atts.getValue("lname");
			String buid = atts.getValue("buid");
			StudentStatus enrollmentStatus = StudentStatus.getStudentStatus(atts.getValue("status"));
			Student s = new Student(student_id, fname, lname, buid, enrollmentStatus);
			sectionStudents.add(s);
			students.put(s.getID(), s);
		}
		// Course ID
		else if(localName.equals("cid"))
		{
			course_id = new Long(atts.getValue("text"));
		}
		// Course name
		else if(localName.equals("cname"))
		{
			courseName = atts.getValue("text");
		}
		// Course code
		else if(localName.equals("ccode"))
		{
			courseCode = atts.getValue("text");
		}
		// Owner
		else if(localName.equals("owner"))
		{
			long owner_id = new Long(atts.getValue("oid"));
			String email = atts.getValue("email");
			String fname = atts.getValue("fname");
			String lname = atts.getValue("lname");
			int hashedPW = new Integer(atts.getValue("password"));
			owner = new User(owner_id, email, fname, lname, hashedPW);
		}
		// Template ID
		else if(localName.equals("tid"))
		{
			template_id = new Long(atts.getValue("text"));
		}
		// Template name
		else if(localName.equals("tname"))
		{
			templateName = atts.getValue("text");
		}
		// Template student
		else if(localName.equals("tstudent"))
		{
			long student_id = new Long(atts.getValue("sid"));
			String fname = atts.getValue("fname");
			String lname = atts.getValue("lname");
			String buid = atts.getValue("buid");
			StudentStatus enrollmentStatus = StudentStatus.getStudentStatus(atts.getValue("status"));
			templateStudent = new Student(student_id, fname, lname, buid, enrollmentStatus);
		}
		// Template weight
		else if(localName.equals("tweight"))
		{
			templateWeight = new Float(atts.getValue("text"));
		}
		// Template grade
		else if(localName.equals("tgrade"))
		{
			templateGrade = new Grade(new Float(atts.getValue("score")), atts.getValue("comment"));
		}
		// Template number of sub-assignments
		else if(localName.equals("tnumsa"))
		{
			templateNumSubAssignments = new Integer(atts.getValue("text"));
		}
		// Entries
		else if(localName.equals("entries"))
		{
			entries = new ArrayList<Entry>(50);
		}
		// Entry ID
		else if(localName.equals("eid"))
		{
			entry_id = new Long(atts.getValue("text"));
		}
		// Entry student ID
		else if(localName.equals("esid"))
		{
			entryStudentID = new Long(atts.getValue("text"));
		}
		// Entry section ID
		else if(localName.equals("esecid"))
		{
			entrySectionID = new Long(atts.getValue("text"));
		}
		// Final grade ID
		else if(localName.equals("fgid"))
		{
			fg_id = new Long(atts.getValue("text"));
		}
		// Final grade name
		else if(localName.equals("fgname"))
		{
			fgName = atts.getValue("text");
		}
		// Final grade student id
		else if(localName.equals("fgstudent"))
		{
			fgStudentID = new Long(atts.getValue("sid"));
		}
		// Final grade weight
		else if(localName.equals("fgweight"))
		{
			fgWeight = new Float(atts.getValue("text"));
		}
		// Final grade grade
		else if(localName.equals("fggrade"))
		{
			fgGrade = new Grade(new Float(atts.getValue("score")), atts.getValue("comment"));
		}
		// Final grade number of sub-assignments
		else if(localName.equals("fgnumsa"))
		{
			fgNumSubAssignments = new Integer(atts.getValue("text"));
		}
		// Sub-assignment list
		else if(localName.equals("salist"))
		{
			// Create a new list of sub-assignments and push it onto the stack
			assignmentSubAssignmentLists.push(new ArrayList<Gradeable>());
		}
		// Assignment ID
		else if(localName.equals("aid"))
		{
			// Push it onto the stack
			assignment_ids.push(new Long(atts.getValue("text")));
		}
		// Assignment name
		else if(localName.equals("aname"))
		{
			// Push it onto the stack
			assignmentNames.push(atts.getValue("text"));
		}
		// Assignment student
		else if(localName.equals("asid"))
		{
			// Push it onto the stack
			assignmentStudentIDs.push(new Long(atts.getValue("text")));
		}
		// Assignment weight
		else if(localName.equals("aweight"))
		{
			// Push it onto the stack
			assignmentWeights.push(new Float(atts.getValue("text")));
		}
		// Assignment weight
		else if(localName.equals("agrade"))
		{
			// Create grade
			Grade grade = new Grade(new Float(atts.getValue("score")), atts.getValue("comment"));
			// Push it onto the stack
			assignmentGrades.push(grade);
		}
		// Assignment number of sub-assignments
		else if(localName.equals("numsa"))
		{
			// Push it onto the stack
			assignmentNumSubAssignments.push(new Integer(atts.getValue("text")));
		}
		// Otherwise
		else
		{
			// Do nothing
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qualifiedName)
	{
		// Take action depending on tag type
		// Template
		if(localName.equals("template"))
		{
			// Template RealAssignment
			template = new RealAssignment(template_id, templateName, templateStudent,
					templateWeight, templateGrade, templateNumSubAssignments,
					assignmentSubAssignmentLists.pop());
		}
		// Section
		else if(localName.equals("section"))
		{
			// Create semester
			Section s = new Section(section_id, course_id, sectionName,
					sectionCode, sectionStudents);
			sections.put(s.getID(), s);
		}
		// Entry
		else if(localName.equals("entry"))
		{
			Entry e = new Entry(entry_id, sections.get(entrySectionID),
					students.get(entryStudentID), entryFinalGrade);
			entries.add(e);
		}
		// Final Grade
		else if(localName.equals("fg"))
		{
			entryFinalGrade = new RealAssignment(fg_id, fgName, students.get(fgStudentID),
					fgWeight, fgGrade, fgNumSubAssignments, assignmentSubAssignmentLists.pop());
		}
		// RealAssignment
		else if(localName.equals("ra"))
		{
			// Create RealAssignment
			long id = assignment_ids.pop();
			String name = assignmentNames.pop();
			Student student = students.get(assignmentStudentIDs.pop());
			float weight = assignmentWeights.pop();
			Grade grade = assignmentGrades.pop();
			int numSubAssignments = assignmentNumSubAssignments.pop();
			ArrayList<Gradeable> subAssignments	= assignmentSubAssignmentLists.pop();
			RealAssignment ra = new RealAssignment(id, name, student, weight,
		    		grade, numSubAssignments, subAssignments);
			
			// Assign it to its parent list
			ArrayList<Gradeable> parentList = assignmentSubAssignmentLists.pop();
			parentList.add(ra);
			assignmentSubAssignmentLists.push(parentList);
		}
		// NullAssignment
		else if(localName.equals("na"))
		{
			// Create NullAssignment
			long id = assignment_ids.pop();
			String name = assignmentNames.pop();
			Grade grade = assignmentGrades.pop();
			NullAssignment na = new NullAssignment(id, name, grade);

			// Assign it to its parent list
			ArrayList<Gradeable> parentList = assignmentSubAssignmentLists.pop();
			parentList.add(na);
			assignmentSubAssignmentLists.push(parentList);
		}
		// Otherwise
		else
		{
			// Do nothing
		}
	}

}
