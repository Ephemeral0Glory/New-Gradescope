package utilities;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *  Reads the application configurations.
 *  <p>
 *  Configurations are stored in the config.xml file. They include the
 *  last ID numbers used in {@link IDFactory}.
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
 *  name, code, owner, and id. These are used to open the actual course data
 *  in its properly-named course. Course files are named according to: "course" + course.id.
 *  @author Alex Titus
 */
public class ConfigFileReader extends DefaultHandler
{
	public static String configFileName = "config.xml";
	private String configFileURL;
	private XMLReader reader;
	/*
	 *  The following used during parsing
	 */
	private long lastCourseID;
	private long lastEntryID;
	private long lastGradebookID;
	private long lastAssignmentID;
	private long lastSectionID;
	private long lastSemesterID;
	private long lastStudentID;
	private long lastUserID;
	
	public ConfigFileReader(String configFileName) throws ConfigFileReaderException
	{
		super();
		configFileURL = makeURL(configFileName);
		
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
        	throw new ConfigFileReaderException(e.getMessage());
        }
	}
	
	private String makeURL(String usersFileName)
	{
		String path = new File(usersFileName).getAbsolutePath();
		
		return "file:" + path;
	}
	
	/**
	 *  Read the configurations file and return the configurations.
	 *  @return  The configurations in the form of a {@link GraderConfigs}
	 *  @throws ConfigFileReaderException if an error was encountered during parsing.
	 */
	public GraderConfigs readConfigs() throws ConfigFileReaderException
	{
		try
		{
			// Read configurations
			reader.parse(configFileURL);
			
			// Return configurations
			return new GraderConfigs(lastCourseID, lastEntryID, lastGradebookID,
					lastAssignmentID, lastSectionID, lastSemesterID,
					lastStudentID, lastUserID);
		} catch (Exception e)
		{
			throw new ConfigFileReaderException(e.getMessage());
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qualifiedName, Attributes atts)
	{
		// Take action depending on tag type
		// Last Course ID
		if(localName.equals("cid"))
		{
			lastCourseID = new Long(atts.getValue("text"));
		}
		// Last Entry ID
		else if(localName.equals("eid"))
		{
			lastEntryID = new Long(atts.getValue("text"));
		}
		// Last Gradebook ID
		else if(localName.equals("gid"))
		{
			lastGradebookID = new Long(atts.getValue("text"));
		}
		// Last Assignment ID
		else if(localName.equals("aid"))
		{
			lastAssignmentID = new Long(atts.getValue("text"));
		}
		// Last Section ID
		else if(localName.equals("secid"))
		{
			lastSectionID = new Long(atts.getValue("text"));
		}
		// Last Semester ID
		else if(localName.equals("semid"))
		{
			lastSemesterID = new Long(atts.getValue("text"));
		}
		// Last Student ID
		else if(localName.equals("stuid"))
		{
			lastStudentID = new Long(atts.getValue("text"));
		}
		// Last User ID
		else if(localName.equals("uid"))
		{
			lastUserID = new Long(atts.getValue("text"));
		}
		// Otherwise
		else
		{
			// Do nothing
		}
	}

}
