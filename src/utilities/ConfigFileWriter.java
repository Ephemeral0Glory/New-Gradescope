package utilities;

import java.io.File;
import java.io.FileOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 *  Stores the application configurations in a file.
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
public class ConfigFileWriter
{
	private XMLStreamWriter writer;
	
	public ConfigFileWriter(String configFileName) throws ConfigFileWriterException
	{
		FileOutputStream outStream;
		try
		{
			outStream = new FileOutputStream(new File(configFileName));
			writer = XMLOutputFactory.newInstance().createXMLStreamWriter(outStream);
		}
		catch (Exception e)
		{
			throw new ConfigFileWriterException(e.getMessage());
		}
	}
	
	public void writeConfig(GraderConfigs config) throws ConfigFileWriterException
	{
		try
		{
			// Start document
			writer.writeStartDocument();
			
			// Write root tag
			writer.writeStartElement("configs");
			
			// Write last course ID
			writer.writeStartElement("cid");
			writer.writeAttribute("text", config.getLastCourseID()+"");
			writer.writeEndElement();
			
			// Write last entry ID
			writer.writeStartElement("eid");
			writer.writeAttribute("text", config.getLastEntryID()+"");
			writer.writeEndElement();
			
			// Write last gradebook ID
			writer.writeStartElement("gid");
			writer.writeAttribute("text", config.getLastGradebookID()+"");
			writer.writeEndElement();
			
			// Write last assignment ID
			writer.writeStartElement("aid");
			writer.writeAttribute("text", config.getLastAssignmentID()+"");
			writer.writeEndElement();
			
			// Write last section ID
			writer.writeStartElement("secid");
			writer.writeAttribute("text", config.getLastSectionID()+"");
			writer.writeEndElement();
			
			// Write last semester ID
			writer.writeStartElement("semid");
			writer.writeAttribute("text", config.getLastSemesterID()+"");
			writer.writeEndElement();
			
			// Write last student ID
			writer.writeStartElement("stuid");
			writer.writeAttribute("text", config.getLastStudentID()+"");
			writer.writeEndElement();
			
			// Write last user ID
			writer.writeStartElement("uid");
			writer.writeAttribute("text", config.getLastUserID()+"");
			writer.writeEndElement();
			
			// End root tag
			writer.writeEndElement();
			
			// End document
			writer.writeEndDocument();
			
			// Flush output
			writer.flush();
		}
		catch (Exception e)
		{
			throw new ConfigFileWriterException(e.getMessage());
		}
	}

}
