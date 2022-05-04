package entity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *  Writes Users to file in XML format.
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
public class UserFileWriter
{
	private String usersFileURL;
	private XMLStreamWriter writer;
	
	public UserFileWriter(String usersFileName) throws UserFileWriterException
	{
		usersFileURL = makeURL(usersFileName);
		
		FileOutputStream outStream;
		try
		{
			outStream = new FileOutputStream(new File(usersFileName));
			writer = XMLOutputFactory.newInstance().createXMLStreamWriter(outStream);
		}
		catch (Exception e)
		{
			throw new UserFileWriterException(e.getMessage());
		}
	}
	
	private String makeURL(String usersFileName)
	{
		String path = new File(usersFileName).getAbsolutePath();
		
		return "file:" + path;
	}
	
	public void writeUsers(ArrayList<User> users) throws UserFileWriterException
	{
		try
		{
			// Start document
			writer.writeStartDocument();
			
			// Write root tag
			writer.writeStartElement("users");
			
			// Write users
			for(User u: users)
			{
				writeUser(u);
			}
			
			// End root tag
			writer.writeEndElement();
			
			// End document
			writer.writeEndDocument();
			
			// Flush output
			writer.flush();
		}
		catch (Exception e)
		{
			throw new UserFileWriterException(e.getMessage());
		}
	}
	
	private void writeUser(User u) throws XMLStreamException
	{
		// Write user tag
		writer.writeStartElement("user");
		
		// Write id
		writer.writeStartElement("id");
		writer.writeAttribute("text", u.getID()+"");
		writer.writeEndElement();
		
		// Write email
		writer.writeStartElement("email");
		writer.writeAttribute("text", u.getEmail());
		writer.writeEndElement();
		
		// Write first name
		writer.writeStartElement("fname");
		writer.writeAttribute("text", u.getFName());
		writer.writeEndElement();
		
		// Write last name
		writer.writeStartElement("lname");
		writer.writeAttribute("text", u.getLName());
		writer.writeEndElement();
		
		// Write hashed password
		writer.writeStartElement("password");
		writer.writeAttribute("text", u.getHashedPW()+"");
		writer.writeEndElement();
		
		// End user tag
		writer.writeEndElement();
	}

}
