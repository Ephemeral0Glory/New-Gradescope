package utilities;

/**
 *  Generic exception class for {@link ConfigFileWriter}.
 *  @author Alex Titus
 */
public class ConfigFileWriterException extends Exception
{
	private static final long serialVersionUID = -2858087258666309471L;
	
	public ConfigFileWriterException(String errorMessage)
	{
		super(errorMessage);
	}

}
