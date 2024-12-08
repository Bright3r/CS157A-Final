package com.ecommerce.model;

import java.util.Date;

public class Review {

    private Integer reviewID;
    private Integer userID;  
    private Integer productID; 
    private Integer rating;
    private String reviewComment;
    private Date datePosted;

    public Review() {
    }

    public Review(Integer userID, Integer productID, Integer rating, String reviewComment, Date datePosted) {
        this.userID = userID;
        this.productID = productID;
        this.rating = rating;
        this.reviewComment = reviewComment;
        this.datePosted = datePosted;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", userID=" + userID +
                ", productID=" + productID +
                ", rating=" + rating +
                ", reviewComment='" + reviewComment + '\'' +
                ", datePosted=" + datePosted +
                '}';
    }
}
