package utilities;

/**
 *  Generic exception class for {@link ConfigFileReader}.
 *  @author Alex Titus
 */
public class ConfigFileReaderException extends Exception
{
	private static final long serialVersionUID = 3637743118763340188L;
	
	public ConfigFileReaderException(String errorMessage)
	{
		super(errorMessage);
	}

}
