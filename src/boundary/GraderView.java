package boundary;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 *  The main frame for the Grader application.
 *  <p>
 *  Holds a content panel of IGraderScreen type.
 *  @author Alex Titus
 */
public class GraderView extends JFrame implements IGraderFrame
{
	private static final long serialVersionUID = 2471330856952055579L;
	private IGraderScreen nowDisplaying;
	
	public GraderView()
	{
		super("Grader");
		setupFrame();
	}
	
	private void setupFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(50, 50, 600, 600);
		this.setResizable(false);
	}
	
	public void setUpMenuBars()
	{
		this.setBounds(50, 50, 1000, 600);
		// TODO GraderView.setUpMenuBars

	}

	/**
	 *  Displays this frame and all of its subcomponents.
	 */
	@Override
	public void display()
	{
		this.setVisible(true);
	}

	/**
	 *  Revalidates the component hierarchy after an underlying change.
	 */
	@Override
	public void update()
	{
		nowDisplaying.update();
		this.revalidate();
	}

	/**
	 *  Closes this frame (window).
	 */
	@Override
	public void closeWindow()
	{
		this.dispose();
	}

	/**
	 *  Changes this frame's content pane to the given Grader screen.
	 *  @param gs  The new screen to display
	 */
	@Override
	public void setNewView(IGraderScreen gs)
	{
		nowDisplaying = gs;
		setContentPane(gs.getPanelContent());
	}

}
