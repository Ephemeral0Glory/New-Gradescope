package utilities;


/**
 *  Generic exception for problems with {@link UserFileReader}.
 *  @author Alex Titus
 */
public class UserFileReaderException extends Exception
{
	private static final long serialVersionUID = 4085167925527599844L;
	
	public UserFileReaderException(String errorMessage)
	{
		super(errorMessage);
	}

}
