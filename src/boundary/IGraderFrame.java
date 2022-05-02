package boundary;

/**
 *  Abstract class for all windows in the Grader application.
 *  
 *  @author Alex Titus
 */
public abstract interface IGraderFrame
{
	public void display();
	public void update();
	public void closeWindow();
	public void setNewView(IGraderScreen gs);

}
