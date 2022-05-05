package utilities;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import entity.User;

/**
 *  Reads Users from file in XML format.
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
public class UserFileReader extends DefaultHandler
{
	public static final String usersFileName = "users.xml";
	private String usersFileURL;
	private XMLReader reader;
	/*
	 *  The following used during parsing
	 */
	private ArrayList<User> users;
	private long id;
	private String email;
	private String fname;
	private String lname;
	private int hashedPW;
	
	/**
	 *  Constructor.
	 *  
	 *  @param usersFileName  The file name of the users file, can be a path to it
	 *  @throws UserFileReaderException  Might occur during the creation of the reader
	 */
	public UserFileReader(String usersFileName) throws UserFileReaderException
	{
		super();
		usersFileURL = makeURL(usersFileName);
		
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
        	throw new UserFileReaderException(e.getMessage());
        }
	}
	
	private String makeURL(String usersFileName)
	{
		String path = new File(usersFileName).getAbsolutePath();
		
		return "file:" + path;
	}
	
	/**
	 *  Read the users file and create a list of the users there.
	 *  @return  The list of users stored in the file
	 *  @throws UserFileReaderException if an error was encountered during parsing.
	 */
	public ArrayList<User> readUsers() throws UserFileReaderException
	{
		users = new ArrayList<User>();
		
		// Parse users file for info
		try
		{
			reader.parse(usersFileURL);
		}
		catch (Exception e)
		{
			throw new UserFileReaderException(e.getMessage());
		}
		
		return users;
	}
	
	@Override
	public void startElement(String uri, String localName, String qualifiedName, Attributes atts)
	{
		// Take action depending on tag type
		// User
		if(localName.equals("user"))
		{
			// Reset all fields
			id = 0;
			email = null;
			fname = null;
			lname = null;
			hashedPW = 0;
		}
		// ID
		else if(localName.equals("id"))
		{
			id = new Long(atts.getValue("text"));
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
		if(localName.equals("user"))
		{
			// Create user
			User u = new User(id, email, fname, lname, hashedPW);
			users.add(u);
		}
		// Otherwise
		else
		{
			// Do nothing
		}
	}

}
