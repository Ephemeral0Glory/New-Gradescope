package entity;

/**
 *  A Grade for an assignment.
 *  <p>
 *  Consists of a score and a comment.
 *  @author David Sullo
 *  @author Alex Titus
 */
public class Grade {

    private float score;
    private String comment;
    
    /**
     *  Constructor.
     *  <p>
     *  Creates a grade with score of 0 and an empty comment.
     */
    public Grade() {
    	this.score = 0f;
    	this.comment = "";
    }

    /**
     *  Constructor.
     *  <p>
     *  Creates a grade with a given score and empty comment.
     *  @param score  The score for this assignment
     */
    public Grade(float score) {
        this.score = score;
        this.comment = "";
    }

    /**
     *  Constructor.
     *  <p>
     *  Creates a grade with the given score and comment.
     *  @param score
     *  @param comment
     */
    public Grade(float score, String comment) {
        this.score = score;
        this.comment = comment;
    }

    public float getScore() {
        return this.score;
    }

    public float setScore(float newScore) {
        this.score = newScore;
        return this.score;
    }

    public String getComment() {
        return this.comment;
    }

    public String setComment(String newComment) {
        this.comment = newComment;
        return this.comment;
    }
    
}
