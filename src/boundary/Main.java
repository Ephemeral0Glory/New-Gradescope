package boundary;

import java.io.File;

import utilities.ConfigFileReader;
import utilities.ConfigFileReaderException;
import utilities.ConfigFileWriter;
import utilities.ConfigFileWriterException;
import utilities.GradebookFileReader;
import utilities.GraderConfigs;
import utilities.IDFactory;
import utilities.UserFileReader;
import utilities.UserFileReaderException;

/**
 *  Starts the Grader application.
 *  <p>
 *  Attempts to load the configurations file and the users file before setting up the UI.
 *  In the event of a loading failure, it will attempt to carry on without the saved data. 
 *  @author Alex Titus
 */
public class Main {

	public static void main(String[] args) {
		try
		{
			// Attempt to load configuration file
			loadConfigs();
			
			// Check for gradebooks directory
			checkGradebooks();
			
			File file = new File(UserFileReader.usersFileName);
			if(file.exists())
			{
				// Read in users
				UserFileReader reader = new UserFileReader("users.xml");

				// Create and display log in window
				LogInView liv = new LogInView(reader.readUsers());
				liv.display();
			}
			else
			{
				// Create log in window with no users
				LogInView liv = new LogInView();
				liv.display();
			}
		} catch (UserFileReaderException e)
		{
			// TODO Notify the user of a problem
			e.printStackTrace();
		} catch (ConfigFileReaderException e)
		{
			// TODO Notify the user of a problem
			e.printStackTrace();
		} catch (ConfigFileWriterException e)
		{
			// TODO Notify the user of a problem
			e.printStackTrace();
		}
	}
	
	private static void loadConfigs() throws ConfigFileReaderException, ConfigFileWriterException
	{
		// Attempt to load configurations file
		File configFile = new File(ConfigFileReader.configFileName);
		if(configFile.exists())
		{
			// Load and set configurations
			ConfigFileReader creader = new ConfigFileReader(ConfigFileReader.configFileName);
			GraderConfigs configs = creader.readConfigs();
			IDFactory.setStartingIDs(configs);
		}
		else  // Don't have a configurations file
		{
			// Create configurations file
			GraderConfigs configs = new GraderConfigs();
			ConfigFileWriter cwriter = new ConfigFileWriter(ConfigFileReader.configFileName);
			cwriter.writeConfig(configs);
		}
	}
	
	private static void checkGradebooks()
	{
		File gbDir = new File(GradebookFileReader.gradebookDirectory);
		if(!gbDir.exists())
		{
			// Create gradebooks directory
			gbDir.mkdir();
		}
	}

}
