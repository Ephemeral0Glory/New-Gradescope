package entity;
import java.util.*;

public class Semester {

    private long id; 
    private Season season;
    private int year;
    private ArrayList<Course> courses;

    public Semester(String season, int year) {
    	this.id = IDFactory.generateSemesterID();
        this.season = Season.getSeason(season);
        this.year = year;
    }

    public Semester(String season, int year, ArrayList<Course> courses) {
        this(season, year);
        this.courses = courses;
    }
    
    // TODO Semester constructor for load from file

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

    public int getYear() {
        return this.year;
    }

    public int setYear(int newYear) {
        this.year = newYear;
        return this.year;
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