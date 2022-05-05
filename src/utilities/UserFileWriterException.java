package utilities;


/**
 *  Generic exception for {@link UserFileWriter}.
 *  @author Alex Titus
 */
public class UserFileWriterException extends Exception
{
	private static final long serialVersionUID = -8001317244387662590L;

	public UserFileWriterException(String errorMessage)
	{
		super(errorMessage);
	}

}
