package utilities;

/**
 *  Creates IDs for entities that will be saved.
 *  @author Alex Titus
 */
public class IDFactory
{
	private static long lastCourseID = 0;
	private static long lastEntryID = 0;
	private static long lastGradebookID = 0;
	private static long lastAssignmentID = 0;
	private static long lastSectionID = 0;
	private static long lastSemesterID = 0;
	private static long lastStudentID = 0;
	private static long lastUserID = 0;
	
	/**
	 *  Method to set ID starting points when loading from file. Call this or
	 *  else all IDs will start at 1 each time.
	 *  @param configs
	 */
	public static void setStartingIDs(GraderConfigs configs)
	{
		IDFactory.lastCourseID = configs.getLastCourseID();
		IDFactory.lastEntryID = configs.getLastEntryID();
		IDFactory.lastGradebookID = configs.getLastGradebookID();
		IDFactory.lastAssignmentID = configs.getLastAssignmentID();
		IDFactory.lastSectionID = configs.getLastSectionID();
		IDFactory.lastSemesterID = configs.getLastSemesterID();
		IDFactory.lastStudentID = configs.getLastStudentID();
		IDFactory.lastUserID = configs.getLastUserID();
	}
	
	/**
	 *  @return  A unique id for a course
	 */
	public static long generateCourseID()
	{
		lastCourseID += 1;
		return lastCourseID;
	}
	/**
	 *  @return  A unique id for a entry
	 */
	public static long generateEntryID()
	{
		lastEntryID += 1;
		return lastEntryID;
	}
	/**
	 *  @return  A unique id for a gradebook
	 */
	public static long generateGradebookID()
	{
		lastGradebookID += 1;
		return lastGradebookID;
	}
	/**
	 *  @return  A unique id for a assignment
	 */
	public static long generateAssignmentID()
	{
		lastAssignmentID += 1;
		return lastAssignmentID;
	}
	/**
	 *  @return  A unique id for a section
	 */
	public static long generateSectionID()
	{
		lastSectionID += 1;
		return lastSectionID;
	}
	/**
	 *  @return  A unique id for a semester
	 */
	public static long generateSemesterID()
	{
		lastSemesterID += 1;
		return lastSemesterID;
	}
	/**
	 *  @return  A unique id for a student
	 */
	public static long generateStudentID()
	{
		lastStudentID += 1;
		return lastStudentID;
	}
	/**
	 *  @return  A unique id for a user
	 */
	public static long generateUserID()
	{
		lastUserID += 1;
		return lastUserID;
	}
	
	/**
	 *  @return  The last IDs used in the form of a GraderConfigs object
	 */
	public static GraderConfigs getConfigs()
	{
		return new GraderConfigs(lastCourseID, lastEntryID, lastGradebookID,
				lastAssignmentID, lastSectionID, lastSemesterID,
				lastStudentID, lastUserID);
	}

}
