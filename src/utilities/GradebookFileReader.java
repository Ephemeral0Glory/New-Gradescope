package utilities;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import entity.Course;
import entity.Gradebook;
import entity.Semester;
import entity.User;

/**
 *  Reads a Gradebook from file in XML format.
 *  <p>
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
public class GradebookFileReader extends DefaultHandler
{
	public static String gradebookDirectory = "gradebooks/";
	private String gradebooksDirectoryPath;
	private XMLReader reader;
	/*
	 *  The following are used while parsing:
	 */
	private long gb_id;
	private User owner;
	private long user_id;
	private String email;
	private String fname;
	private String lname;
	private int hashedPW;
	private ArrayList<Semester> semesters;
	private long semester_id;
	private String season;
	private int year;
	private ArrayList<Course> courses;
	private long course_id;
	private String courseName;
	private String courseCode;
	
	/**
	 *  Constructor.
	 *  
	 *  @param gradebookDirectory  The directory name or path where the gradebooks are stored
	 * @throws GradebookFileReaderException Might happen during parser creation
	 */
	public GradebookFileReader(String gradebookDirectory) throws GradebookFileReaderException
	{
		super();
		gradebooksDirectoryPath = makeURL(gradebookDirectory);
		
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
			throw new GradebookFileReaderException(e.getMessage());
		}
	}
	
	private String makeURL(String gradebooksDirectoryName)
	{
		String path = new File(gradebooksDirectoryName).getAbsolutePath();
		
		return "file:" + path + "/";
	}
	
	/**
	 *  Reads the gradebook file associated with the given User.
	 *  
	 *  @param owner  The user accessing their gradebook
	 *  @return  The gradebook owned by the user
	 * @throws GradebookFileReaderException 
	 */
	public Gradebook readGradebook(User owner) throws GradebookFileReaderException
	{
		String gradebookFileName = getGradebookFileName(owner);
		
		// Parse gradebook file for info
		try
		{
			reader.parse(gradebooksDirectoryPath + gradebookFileName);
		}
		catch (Exception e)
		{
			throw new GradebookFileReaderException(e.getMessage());
		}
		
		return new Gradebook(gb_id, this.owner, semesters);
	}
	
	private String getGradebookFileName(User owner)
	{
		return "u" + owner.getID() + "/gradebook.xml";
	}
	
	@Override
	public void startElement(String uri, String localName, String qualifiedName, Attributes atts)
	{
		// Take action depending on tag type
		// Gradebook
		if(localName.equals("gradebook"))
		{
			semesters = new ArrayList<Semester>();
		}
		// Semester
		if(localName.equals("semester"))
		{
			courses = new ArrayList<Course>();
		}
		// Gradebook ID
		else if(localName.equals("gbid"))
		{
			gb_id = new Long(atts.getValue("text"));
		}
		// Owner ID
		else if(localName.equals("oid"))
		{
			user_id = new Long(atts.getValue("text"));
		}
		// Semester ID
		else if(localName.equals("sid"))
		{
			semester_id = new Long(atts.getValue("text"));
		}
		// Course ID
		else if(localName.equals("cid"))
		{
			course_id = new Long(atts.getValue("text"));
		}
		// Email
		else if(localName.equals("email"))
		{
			email = atts.getValue("text");
		}
		// First name
		else if(localName.equals("fname"))
		{
			fname = atts.getValue("text");
		}
		// Last name
		else if(localName.equals("lname"))
		{
			lname = atts.getValue("text");
		}
		// Password
		else if(localName.equals("password"))
		{
			hashedPW = new Integer(atts.getValue("text"));
		}
		// Season
		else if(localName.equals("season"))
		{
			season = atts.getValue("text");
		}
		// Year
		else if(localName.equals("year"))
		{
			year = new Integer(atts.getValue("text"));
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
		// User
		if(localName.equals("owner"))
		{
			// Create user
			owner = new User(user_id, email, fname, lname, hashedPW);
		}
		// Semester
		else if(localName.equals("semester"))
		{
			// Create semester
			Semester s = new Semester(semester_id, season, year, courses);
			semesters.add(s);
		}
		// Course
		else if(localName.equals("course"))
		{
			Course c = new Course(course_id, courseName, courseCode);
			courses.add(c);
		}
		// Otherwise
		else
		{
			// Do nothing
		}
	}

}
