package entity;
public class NullAssignment implements Gradeable {

    private long id; // TODO make this autoincrement
    private String name;
    private Grade grade;

    public NullAssignment(String name) {
        this.name = name;
    }

    public NullAssignment(String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }

    public long getID() {
        return this.id;
    }

    public long setID(long newID) {
        this.id = newID;
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String setName(String newName) {
        this.name = newName;
        return this.name;
    }

    public Grade getGrade() {
        return this.grade;
    }

    public Grade setGrade(Grade newGrade) {
        this.grade = newGrade;
        return this.grade;
    }

    @Override
    public float getWeightedGrade() {
        return this.grade.getScore();
    }
    
}