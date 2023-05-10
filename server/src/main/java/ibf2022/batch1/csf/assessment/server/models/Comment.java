package ibf2022.batch1.csf.assessment.server.models;

public class Comment {
    private String title;
    private String reviewerName;
    private int rating;
    private String comments;

    public String getMovieTitle() {return this.title;}
    public void setMovieTitle(String title) {this.title = title;}

    public String getReviewerName() {return this.reviewerName;}
    public void setReviewerName(String reviewerName) {this.reviewerName = reviewerName;}

    public int getRating() {return this.rating;}
    public void setRating(int rating) {this.rating = rating;}

    public String getComments() {return this.comments;}
    public void setComments(String comments) {this.comments = comments;}

}
