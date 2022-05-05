package utilities;

/**
 *  Generic exception class for {@link GradebookFileWriter}.
 *  @author Alex Titus
 */
public class GradebookFileWriterException extends Exception
{
	private static final long serialVersionUID = -1219338260607535536L;

	public GradebookFileWriterException(String errorMessage)
	{
		super(errorMessage);
	}

}
