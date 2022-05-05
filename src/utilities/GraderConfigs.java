package utilities;

/**
 *  Contains all configurations loaded by {@link ConfigFileReader}.
 *  <p>
 *  Used by {@link IDFactory}.
 *  @author Alex Titus
 *
 */
public class GraderConfigs
{
	private long lastCourseID;
	private long lastEntryID;
	private long lastGradebookID;
	private long lastAssignmentID;
	private long lastSectionID;
	private long lastSemesterID;
	private long lastStudentID;
	private long lastUserID;
	
	/**
	 *  Constructor.
	 *  <p>
	 *  Default constructor sets all values to 0.
	 */
	public GraderConfigs()
	{
		this.lastCourseID = 0;
		this.lastEntryID = 0;
		this.lastGradebookID = 0;
		this.lastAssignmentID = 0;
		this.lastSectionID = 0;
		this.lastSemesterID = 0;
		this.lastStudentID = 0;
		this.lastUserID = 0;
	}
	
	/**
	 *  Constructor.
	 *  <p>
	 *  Used when loading configurations from file.
	 *  @param lastCourseID
	 *  @param lastEntryID
	 *  @param lastGradebookID
	 *  @param lastAssignmentID
	 *  @param lastSectionID
	 *  @param lastSemesterID
	 *  @param lastStudentID
	 *  @param lastUserID
	 */
	public GraderConfigs(long lastCourseID, long lastEntryID, long lastGradebookID,
			long lastAssignmentID, long lastSectionID, long lastSemesterID,
			long lastStudentID, long lastUserID)
	{
		this.lastCourseID = lastCourseID;
		this.lastEntryID = lastEntryID;
		this.lastGradebookID = lastGradebookID;
		this.lastAssignmentID = lastAssignmentID;
		this.lastSectionID = lastSectionID;
		this.lastSemesterID = lastSemesterID;
		this.lastStudentID = lastStudentID;
		this.lastUserID = lastUserID;
	}

	public long getLastCourseID()
	{
		return lastCourseID;
	}

	public long getLastEntryID()
	{
		return lastEntryID;
	}

	public long getLastGradebookID()
	{
		return lastGradebookID;
	}

	public long getLastAssignmentID()
	{
		return lastAssignmentID;
	}

	public long getLastSectionID()
	{
		return lastSectionID;
	}

	public long getLastSemesterID()
	{
		return lastSemesterID;
	}

	public long getLastStudentID()
	{
		return lastStudentID;
	}

	public long getLastUserID()
	{
		return lastUserID;
	}

}
