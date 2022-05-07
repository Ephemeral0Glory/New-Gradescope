package boundary;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.ExitApplicationController;
import controller.OpenAddAssignmentWindowController;
import controller.OpenAddEntryWindowController;
import controller.OpenAddSectionWindowController;
import controller.OpenMainMenuController;
import controller.OpenRemoveSectionWindowController;
import controller.OpenRemoveStudentWindowController;
import controller.SaveCourseDataController;
import entity.Course;
import entity.User;

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
	
	/**
	 *  Constructor.
	 *  <p>
	 *  Default constructor creates a frame named "Grader" of size 600x600.
	 */
	public GraderView()
	{
		super("Grader");
		setupFrame();
	}
	
	/**
	 *  Constructor.
	 *  <p>
	 *  Creates a framed named for frameName of size 600x600.
	 *  @param frameName
	 */
	public GraderView(String frameName)
	{
		super(frameName);
		setupFrame();
	}
	
	private void setupFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(50, 50, 600, 600);
		this.setResizable(false);
	}
	
	/**
	 *  Sets up the menu bar for the course edit screen.
	 *  
	 *  @param c  The course to be displayed in the course edit screen
	 *  @param u  The current user
	 */
	public void setUpMenuBars(Course c, User u)
	{
		// Resize window for course edit screen
		this.setBounds(50, 50, 1000, 600);
		
		// Create menu bar
		JMenuBar menu = new JMenuBar();
		
		// File menu
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
			// Save
		JMenuItem item = new JMenuItem("Save");
		item.setMnemonic(KeyEvent.VK_S);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		item.addActionListener(new SaveCourseDataController(c));
		file.add(item);
		file.addSeparator();
			// Return to Main Menu
		item = new JMenuItem("Return to Main Menu");
		item.setMnemonic(KeyEvent.VK_M);
		item.addActionListener(new OpenMainMenuController(this, u));
		file.add(item);
			// Exit
		item = new JMenuItem("Quit Grader");
		item.setMnemonic(KeyEvent.VK_Q);
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		item.addActionListener(new ExitApplicationController(this));
		file.add(item);
		menu.add(file);
		
		// Edit menu
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
			// Add submenu
		JMenu add = new JMenu("Add");
		add.setMnemonic(KeyEvent.VK_A);
				// Add section
		item = new JMenuItem("Add Section");
		item.setMnemonic(KeyEvent.VK_T);
		item.addActionListener(new OpenAddSectionWindowController(this, u, c));
		add.add(item);
				// Add student
		item = new JMenuItem("Add Student");
		item.setMnemonic(KeyEvent.VK_S);
//		item.addActionListener(new OpenAddStudentWindowController(this, u, c));
		add.add(item);
				// Add assignment
		item = new JMenuItem("Add Assignment");
		item.setMnemonic(KeyEvent.VK_A);
		item.addActionListener(new OpenAddAssignmentWindowController(this, u, c));
		add.add(item);
				// Add entry
		item = new JMenuItem("Add Entry");
		item.setMnemonic(KeyEvent.VK_E);
		item.addActionListener(new OpenAddEntryWindowController(this, u, c));
		add.add(item);
		edit.add(add);
			// Remove submenu
		JMenu remove = new JMenu("Remove");
		remove.setMnemonic(KeyEvent.VK_A);
				// Add section
		item = new JMenuItem("Remove Section");
		item.setMnemonic(KeyEvent.VK_T);
		item.addActionListener(new OpenRemoveSectionWindowController(this, u, c));
		remove.add(item);
				// Add student
		item = new JMenuItem("Remove Student");
		item.setMnemonic(KeyEvent.VK_S);
		item.addActionListener(new OpenRemoveStudentWindowController(this, u, c));
		remove.add(item);
				// Add assignment
		item = new JMenuItem("Remove Assignment");
		item.setMnemonic(KeyEvent.VK_A);
//		item.addActionListener(new OpenRemoveAssignmentWindowController(this, u, c));
		remove.add(item);
				// Add entry
		item = new JMenuItem("Remove Entry");
		item.setMnemonic(KeyEvent.VK_E);
//		item.addActionListener(new OpenRemoveEntryWindowController(this, u, c));
		remove.add(item);
		edit.add(remove);
		menu.add(edit);
		
		// Analysis menu
		JMenu analysis = new JMenu("Analysis");
		analysis.setMnemonic(KeyEvent.VK_A);
			// Show course info
		item = new JMenuItem("Show Course Info");
		item.setMnemonic(KeyEvent.VK_I);
//		item.addActionListener(new OpenCourseInfoWindowController(this, u, c));
		analysis.add(item);
		menu.add(analysis);
		
		// Add menu bar
		this.setJMenuBar(menu);
	}
	
	public void setClosePolicyPopUp()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	
	/**
	 *  @return  The screen currently being displayed
	 */
	@Override
	public IGraderScreen getCurrentDisplay()
	{
		return nowDisplaying;
	}

}
