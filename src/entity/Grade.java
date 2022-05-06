package entity;
public class Grade {

    private float score;
    private String comment;
    
    public Grade() {
    	this.score = 0f;
    	this.comment = "N/A";
    }

    public Grade(float score) {
        this.score = score;
        this.comment = "N/A";
    }

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
