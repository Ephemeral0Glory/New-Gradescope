package utilities;

/**
 *  Generic exception class for {@link CourseFileReader}.
 *  @author Alex Titus
 */
public class CourseFileReaderException extends Exception
{
	private static final long serialVersionUID = 2897653850769302960L;
	
	public CourseFileReaderException(String errorMessage)
	{
		super(errorMessage);
	}

}
