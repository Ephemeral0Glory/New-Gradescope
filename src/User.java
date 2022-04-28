public class User {

    private long id; // TODO Add autoincrement of ID 
    private String email;
    private String fname;
    private String lname;
    private String password;
    private int hashedPW; // TODO Initialize this properly, currently storing password as unhashed string

    public User(String email, String fname, String lname, String password) {
        this.id = 0;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
    }

    public User(String email, String fname, String lname, int hashedPW) {
        this.id = 0;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.hashedPW = hashedPW;
    }

    public boolean checkPassword(String pwAttempt) {
        return this.password.equals(pwAttempt); // TODO set to handle hashed password
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


}