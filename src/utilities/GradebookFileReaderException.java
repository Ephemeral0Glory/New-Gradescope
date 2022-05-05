package utilities;

/**
 *  Generic exception for {@link GradebookFileReader}.
 *  @author Alex Titus
 */
public class GradebookFileReaderException extends Exception
{
	private static final long serialVersionUID = 8248950955341237690L;
	
	public GradebookFileReaderException(String errorMessage)
	{
		super(errorMessage);
	}

}
