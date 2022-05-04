package boundary;

import java.io.File;

import entity.UserFileReader;
import entity.UserFileReaderException;

/**
 *  
 *  @author Alex Titus
 */
public class Main {

	public static void main(String[] args) {
		try
		{
			File file = new File("users.xml");
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
			e.printStackTrace();
		}
	}

}
