package boundary;

import javax.swing.JPanel;

/**
 *  A screen (JPanel) in the Grader application.
 *  <p>
 *  Must be able to create a displayable JPanel.
 *  @author Alex Titus
 */
public interface IGraderScreen
{
	public JPanel getPanelContent();

}
