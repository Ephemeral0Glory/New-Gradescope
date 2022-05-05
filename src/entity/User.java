package entity;

import utilities.IDFactory;

/**
 *  A user in the grader system.
 *  <p>
 *  User has an email (the username), a first and last name, a hashed password, and TODO associated permissions.
 *  @author Alex Titus
 */
public class User {

    private long id;
    private String email;
    private String fname;
    private String lname;
    private int hashedPW; // TODO Initialize this properly, currently storing password as unhashed string

    /**
     *  Constructor.
     *  <p>
     *  Constructs a new user. When loading an existing user from a file, use
     *   {@link User(long id, String email, String fname, String lname, int hashedPW)}.
     *  @param email  Email address of the user, doubles as the username
     *  @param fname  First (given) name of the user
     *  @param lname  Last (family) name of the user
     *  @param hashedPW  Hashed version of the user-designated password
     */
    public User(String email, String fname, String lname, int hashedPW) {
        this.id = IDFactory.generateUserID();
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.hashedPW = hashedPW;
    }
    
    /**
     *  Constructor.
     *  <p>
     *  Constructs a user from data read from a file. To create a new user, use
     *  {@link User(String email, String fname, String lname, int hashedPW)}.
     *  @param id  The unique id associated with this user
     *  @param email  Email address of the user, doubles as the username
     *  @param fname  First (given) name of the user
     *  @param lname  Last (family) name of the user
     *  @param hashedPW  Hashed version of the user-designated password
     */
    public User(long id, String email, String fname, String lname, int hashedPW)
    {
    	this.id = id;
    	this.email = email;
    	this.fname = fname;
    	this.lname = lname;
    	this.hashedPW = hashedPW;
    }

    /**
     *  Checks the entered password for correctness.
     *  
     *  @param pwAttempt  The user-entered password
     *  @return  True if the password is correct, false otherwise.
     */
    public boolean checkPassword(String pwAttempt) {
    	int hashedAttempt = pwAttempt.hashCode();
        return this.hashedPW == hashedAttempt;
    }

    public long getID() {
        return this.id;
    }

    public long setID(long newID) {
        this.id = newID;
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String setEmail(String newEmail) {
        this.email = newEmail;
        return this.email;
    }

    public String getFName() {
        return this.fname;
    }

    public String setFName(String newFName) {
        this.fname = newFName;
        return this.fname;
    }

    public String getLName() {
        return this.lname;
    }

    public String setLName(String newLName) {
        this.lname = newLName;
        return this.lname;
    }
    
    public int getHashedPW()
    {
    	return this.hashedPW;
    }

    /**
     *  Changes the user's password.
     *  <p>
     *  Only changes the password if the user also supplies the correct current password.
     *  @param currentPassword  The user's current password, must be correct
     *  @param newPassword  The new password
     *  @return  True if the password was changed successfully, false otherwise.
     */
    public boolean changePassword(String currentPassword, String newPassword)
    {
    	// Verify user
    	if(checkPassword(currentPassword))
    	{
    		// User is verified, change password
    		int newPWHash = newPassword.hashCode();
    		hashedPW = newPWHash;
    		return true;
    	}
    	else
    	{
    		// User failed verification, do nothing
    		return false;
    	}
    }

}