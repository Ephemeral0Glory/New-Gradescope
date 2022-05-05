package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import entity.Course;
import entity.Gradebook;
import entity.Semester;
import entity.User;

/**
 *  Writes a Gradebook to file in XML format.
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
public class GradebookFileWriter
{
	private XMLStreamWriter writer;
	
	public GradebookFileWriter(String gradebookFileName) throws UserFileWriterException, GradebookFileWriterException
	{
		
		FileOutputStream outStream;
		try
		{
			outStream = new FileOutputStream(new File(gradebookFileName));
			writer = XMLOutputFactory.newInstance().createXMLStreamWriter(outStream);
		}
		catch (Exception e)
		{
			throw new GradebookFileWriterException(e.getMessage());
		}
	}
	
	public void writeGradebook(Gradebook gradebook) throws UserFileWriterException
	{
		try
		{
			// Start document
			writer.writeStartDocument();
			
			writeGradebookData(gradebook);
			
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
	
	private void writeGradebookData(Gradebook gb) throws XMLStreamException
	{
		// Write root gradebook tag
		writer.writeStartElement("gradebook");
		
		// Gradebook ID
		writer.writeStartElement("gbid");
		writer.writeAttribute("text", gb.getID()+"");
		writer.writeEndElement();
		
		// Write owner
		writeOwner(gb.getOwner());
		
		// Write semesters
		writeSemesters(gb.getSemesters());

		// End root gradebook tag
		writer.writeEndElement();
	}
	
	private void writeOwner(User owner) throws XMLStreamException
	{
		// Write owner tag
		writer.writeStartElement("owner");
		
		// Write owner id
		writer.writeStartElement("oid");
		writer.writeAttribute("text", owner.getID()+"");
		writer.writeEndElement();
		
		// Write email
		writer.writeStartElement("email");
		writer.writeAttribute("text", owner.getEmail());
		writer.writeEndElement();
		
		// Write first name
		writer.writeStartElement("fname");
		writer.writeAttribute("text", owner.getFName());
		writer.writeEndElement();
		
		// Write last name
		writer.writeStartElement("lname");
		writer.writeAttribute("text", owner.getLName());
		writer.writeEndElement();
		
		// Write hashed password
		writer.writeStartElement("password");
		writer.writeAttribute("text", owner.getHashedPW()+"");
		writer.writeEndElement();
		
		// End owner tag
		writer.writeEndElement();
	}
	
	private void writeSemesters(ArrayList<Semester> semesters) throws XMLStreamException
	{
		// Write semesters tag
		writer.writeStartElement("semesters");
		
		// Write each semester
		for(Semester s: semesters)
		{
			writeSemester(s);
		}
		
		// End semesters tag
		writer.writeEndElement();
	}
	
	private void writeSemester(Semester semester) throws XMLStreamException
	{
		// Write semester tag
		writer.writeStartElement("semester");
		
		// Write semester id
		writer.writeStartElement("sid");
		writer.writeAttribute("text", semester.getID()+"");
		writer.writeEndElement();
		
		// Write season
		writer.writeStartElement("season");
		writer.writeAttribute("text", semester.getSeason().toString());
		writer.writeEndElement();
		
		// Write year
		writer.writeStartElement("year");
		writer.writeAttribute("text", semester.getYear()+"");
		writer.writeEndElement();
		
		// Write courses
		for(Course c: semester.getCourses())
		{
			writeCourse(c);
		}
		
		// End semester tag
		writer.writeEndElement();
	}
	
	private void writeCourse(Course course) throws XMLStreamException
	{
		// Write course tag
		writer.writeStartElement("course");
		
		// Write ID
		writer.writeStartElement("cid");
		writer.writeAttribute("text", course.getID()+"");
		writer.writeEndElement();
		
		// Write ID
		writer.writeStartElement("cname");
		writer.writeAttribute("text", course.getName());
		writer.writeEndElement();
		
		// Write ID
		writer.writeStartElement("ccode");
		writer.writeAttribute("text", course.getCode());
		writer.writeEndElement();
		
		// End course tag
		writer.writeEndElement();
	}

}
