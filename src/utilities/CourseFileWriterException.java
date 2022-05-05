package utilities;

/**
 *  Generic exception class for {@link CourseFileWriter}.
 *  @author Alex Titus
 */
public class CourseFileWriterException extends Exception
{
	private static final long serialVersionUID = 7689940621590907336L;
	
	public CourseFileWriterException(String errorMessage)
	{
		super(errorMessage);
	}

}
