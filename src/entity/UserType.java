package entity;

/**
 *  The types of possible users.
 *  
 *  @author Alex Titus
 */
public enum UserType
{
	// TODO add TAs to grader ability
	PROFESSOR("Professor"); // , TEACHING_ASSISTANT("Teaching Assistant");
	
	UserType(String name)
	{
		this.name = name;
	}
	
	private final String name;
	
	public String getValue()
	{
		return name;
	}

}
