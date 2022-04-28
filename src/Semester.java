import java.util.*;

public class Semester {

    private long id; // TODO Add autoincrement of ID 
    private Season season;
    private int year;
    private ArrayList<Course> courses;

    public Semester(String season, int year) {
        this.season = Season.getSeason(season);
        this.year = year;
    }

    public Semester(String season, int year, ArrayList<Course> courses) {
        this.season = Season.getSeason(season);
        this.year = year;
        this.courses = courses;
    }

    public long getID() {
        return this.id;
    }

    public long setID(long newID) {
        this.id = newID;
        return this.id;
    }

    public Season getSeason() {
        return this.season;
    }

    public Season setSeason(String newSeasonStr) {
        Season newSeason = Season.getSeason(newSeasonStr);
        this.season = newSeason;
        return this.season;
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    public void addCourse(Course newCourse) {
        this.courses.add(newCourse);
    }

    public boolean removeCourse(Course courseToRemove) {
        return this.courses.remove(courseToRemove);
    }

}