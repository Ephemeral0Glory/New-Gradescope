package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import entity.Course;
import entity.Entry;
import entity.Gradeable;
import entity.NullAssignment;
import entity.RealAssignment;
import entity.Section;
import entity.Student;
import entity.User;

/**
 *  Writes a Course to file in XML format.
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
public class CourseFileWriter
{
	private XMLStreamWriter writer;
	
	/**
	 *  Constructor.
	 *  
	 *  @param courseFilePathAndName  The (relative or absolute) path to the course file
	 *  @throws CourseFileWriterException  If a problem occurs while setting up the writer
	 */
	public CourseFileWriter(String courseFilePathAndName) throws CourseFileWriterException
	{
		try
		{
			FileOutputStream outStream = new FileOutputStream(new File(courseFilePathAndName));
			writer = XMLOutputFactory.newInstance().createXMLStreamWriter(outStream);
		}
		catch(Exception e)
		{
			throw new CourseFileWriterException(e.getMessage());
		}
	}
	
	/**
	 *  Writes out the course to a file designated by the constructor's argument
	 *  @param course  The course to store in a file
	 *  @throws CourseFileWriterException  If a problem occurs during the writing process
	 */
	public void writeCourse(Course course) throws CourseFileWriterException
	{
		try
		{
			// Begin
			writer.writeStartDocument();
			
			// Write out course info
			writeCourseInfo(course);
			
			// End
			writer.writeEndDocument();
			
			writer.flush();
		}
		catch (Exception e)
		{
			throw new CourseFileWriterException(e.getMessage());
		}
	}
	
	private void writeCourseInfo(Course course) throws XMLStreamException
	{
		// Start course
		writer.writeStartElement("course");
		
		// Write sections info
		writeSections(course);
		
		// Write non-entries course info
		writeCourseDetails(course);
		
		// Write entries
		writeEntries(course.getEntries());
		
		// End course
		writer.writeEndElement();
	}
	
	private void writeSections(Course course) throws XMLStreamException
	{
		// Start list
		writer.writeStartElement("sectionlist");
		
		// Write each section
		for(Section s: course.getSections())
		{
			writeSection(s);
		}
		
		// End list
		writer.writeEndElement();
	}
	
	private void writeSection(Section s) throws XMLStreamException
	{
		// Section tag
		writer.writeStartElement("section");
		
		// ID
		writer.writeStartElement("sid");
		writer.writeAttribute("text", s.getID()+"");
		writer.writeEndElement();
		
		// Name
		writer.writeStartElement("secname");
		writer.writeAttribute("text", s.getName());
		writer.writeEndElement();
		
		// Code
		writer.writeStartElement("seccode");
		writer.writeAttribute("text", s.getCode());
		writer.writeEndElement();
		
		// Student list
		writer.writeStartElement("stulist");
		
		// Write students
		for(Student st: s.getStudents())
		{
			writeStudent(st);
		}
		
		// End student list
		writer.writeEndElement();
		
		// End section tag
		writer.writeEndElement();
	}
	
	private void writeStudent(Student s) throws XMLStreamException
	{
		// Student tag
		writer.writeStartElement("student");
		
		// Write student data as attributes
		writer.writeAttribute("sid", s.getID()+"");
		writer.writeAttribute("fname", s.getFName());
		writer.writeAttribute("lname", s.getLName());
		writer.writeAttribute("status", s.getEnrollmentStatus().toString());
		
		// End student tag
		writer.writeEndElement();
	}
	
	private void writeCourseDetails(Course course) throws XMLStreamException
	{
		// Course ID
		writer.writeStartElement("cid");
		writer.writeAttribute("text", course.getID()+"");
		writer.writeEndElement();
		
		// Course name
		writer.writeStartElement("cname");
		writer.writeAttribute("text", course.getName());
		writer.writeEndElement();
		
		// Course code
		writer.writeStartElement("ccode");
		writer.writeAttribute("text", course.getCode());
		writer.writeEndElement();
		
		// Owner
		User owner = course.getOwner();
		writer.writeStartElement("owner");
		writer.writeAttribute("oid", owner.getID()+"");
		writer.writeAttribute("email", owner.getEmail());
		writer.writeAttribute("fname", owner.getFName());
		writer.writeAttribute("lname", owner.getLName());
		writer.writeAttribute("password", owner.getHashedPW()+"");
		
		// Template
		if(course.hasTemplate())  // Don't have a template for some courses
		{
			writeTemplate(course.getTemplate());	
		}
	}
	
	private void writeTemplate(RealAssignment template) throws XMLStreamException
	{
		// Template
		writer.writeStartElement("template");
		
		// ID
		writer.writeStartElement("tid");
		writer.writeAttribute("text", template.getID()+"");
		writer.writeEndElement();
		
		// Name
		writer.writeStartElement("tname");
		writer.writeAttribute("text", template.getName());
		writer.writeEndElement();
		
		// Student
		Student s = template.getStudent();
		writer.writeStartElement("tstudent");
		writer.writeAttribute("sid", s.getID()+"");
		writer.writeAttribute("fname", s.getFName());
		writer.writeAttribute("lname", s.getLName());
		writer.writeAttribute("buid", s.getBUID());
		writer.writeAttribute("status", s.getEnrollmentStatus().toString());
		writer.writeEndElement();
		
		// Weight
		writer.writeStartElement("tweight");
		writer.writeAttribute("text", template.getWeight()+"");
		writer.writeEndElement();
		
		// Grade
		writer.writeStartElement("tgrade");
		writer.writeAttribute("score", template.getGrade().getScore()+"");
		writer.writeAttribute("comment", template.getGrade().getComment());
		writer.writeEndElement();
		
		// Number of sub-assignments
		writer.writeStartElement("tnumsa");
		writer.writeAttribute("text", template.getNumSubAssignments()+"");
		writer.writeEndElement();
		
		// Write sub-assignments
		writeSubAssignments(template.getSubAssignments());
		
		// End template
		writer.writeEndElement();
	}
	
	private void writeSubAssignments(ArrayList<Gradeable> subAssignments) throws XMLStreamException
	{
		// Sub-assignment list
		writer.writeStartElement("salist");
		
		// Write assignments
		for(Gradeable g: subAssignments)
		{
			writeAssignment(g);
		}
		
		// End sub-assignment list
		writer.writeEndElement();
	}
	
	private void writeAssignment(Gradeable g) throws XMLStreamException
	{
		// Determine whether this is a NullAssignment or a RealAssignment
		if(g instanceof RealAssignment)
		{
			// Use RealAssignment tags
			RealAssignment ra = (RealAssignment) g;
			writeRealAssignment(ra);
		}
		else // Is a NullAssignment
		{
			// Use NullAssignment tags
			NullAssignment na = (NullAssignment) g;
			writeNullAssignment(na);
		}
	}
	
	private void writeRealAssignment(RealAssignment ra) throws XMLStreamException
	{
		// Real Assignment
		writer.writeStartElement("ra");
		
		// Assignment ID
		writer.writeStartElement("aid");
		writer.writeAttribute("text", ra.getID()+"");
		writer.writeEndElement();
		
		// Name
		writer.writeStartElement("aname");
		writer.writeAttribute("text", ra.getName());
		writer.writeEndElement();
		
		// Student ID
		writer.writeStartElement("asid");
		writer.writeAttribute("text", ra.getStudent().getID()+"");
		writer.writeEndElement();
		
		// Weight
		writer.writeStartElement("aweight");
		writer.writeAttribute("text", ra.getWeight()+"");
		writer.writeEndElement();
		
		// Grade
		writer.writeStartElement("agrade");
		writer.writeAttribute("score", ra.getGrade().getScore()+"");
		writer.writeAttribute("comment", ra.getGrade().getComment());
		writer.writeEndElement();
		
		// Number of sub-assignments
		writer.writeStartElement("numsa");
		writer.writeAttribute("text", ra.getNumSubAssignments()+"");
		writer.writeEndElement();
		
		// Write sub-assignments
		writeSubAssignments(ra.getSubAssignments());
		
		// End Real Assignment
		writer.writeEndElement();
	}
	
	private void writeNullAssignment(NullAssignment na) throws XMLStreamException
	{
		// Null Assignment
		writer.writeStartElement("na");
		
		// ID
		writer.writeStartElement("aid");
		writer.writeAttribute("text", na.getID()+"");
		writer.writeEndElement();
		
		// Name
		writer.writeStartElement("aname");
		writer.writeAttribute("text", na.getName());
		writer.writeEndElement();
		
		// Grade
		writer.writeStartElement("agrade");
		writer.writeAttribute("score", na.getGrade().getScore()+"");
		writer.writeAttribute("comment", na.getGrade().getComment());
		writer.writeEndElement();
		
		// End Null Assignment
		writer.writeEndElement();
	}
	
	private void writeEntries(ArrayList<Entry> entries) throws XMLStreamException
	{
		// Entries list
		writer.writeStartElement("entries");
		
		// Write entries
		for(Entry e: entries)
		{
			writeEntry(e);
		}
		
		// End entries list
		writer.writeEndElement();
	}
	
	private void writeEntry(Entry e) throws XMLStreamException
	{
		// Entry
		writer.writeStartElement("entry");
		
		// ID
		writer.writeStartElement("eid");
		writer.writeAttribute("text", e.getID()+"");
		writer.writeEndElement();
		
		// Section ID
		writer.writeStartElement("esecid");
		writer.writeAttribute("text", e.getSection().getID()+"");
		writer.writeEndElement();
		
		// Student ID
		writer.writeStartElement("esid");
		writer.writeAttribute("text", e.getStudent().getID()+"");
		writer.writeEndElement();
		
		// Write final grade
		writeFinalGrade(e.getFinalGrade());
		
		// End Entry
		writer.writeEndElement();
	}
	
	private void writeFinalGrade(RealAssignment finalGrade) throws XMLStreamException
	{
		// Template
		writer.writeStartElement("fg");
		
		// ID
		writer.writeStartElement("fgid");
		writer.writeAttribute("text", finalGrade.getID()+"");
		writer.writeEndElement();
		
		// Name
		writer.writeStartElement("fgname");
		writer.writeAttribute("text", finalGrade.getName());
		writer.writeEndElement();
		
		// Student ID
		writer.writeStartElement("fgstudent");
		writer.writeAttribute("text", finalGrade.getStudent().getID()+"");
		writer.writeEndElement();
		
		// Weight
		writer.writeStartElement("fgweight");
		writer.writeAttribute("text", finalGrade.getWeight()+"");
		writer.writeEndElement();
		
		// Grade
		writer.writeStartElement("fggrade");
		writer.writeAttribute("score", finalGrade.getGrade().getScore()+"");
		writer.writeAttribute("comment", finalGrade.getGrade().getComment());
		writer.writeEndElement();
		
		// Number of sub-assignments
		writer.writeStartElement("fgnumsa");
		writer.writeAttribute("text", finalGrade.getNumSubAssignments()+"");
		writer.writeEndElement();
		
		// Write sub-assignments
		writeSubAssignments(finalGrade.getSubAssignments());
		
		// End template
		writer.writeEndElement();
	}

}
