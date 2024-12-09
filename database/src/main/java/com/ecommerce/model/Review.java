package com.ecommerce.model;

import java.sql.Date;

public class Review {

    private Integer reviewID;
    private User user;  
    private Product product; 
    private Integer rating;
    private String reviewComment;
    private Date datePosted;

    public Review() {
    }

    public Review(User user, Product product, Integer rating, String reviewComment, Date datePosted) {
        this.user = user;
        this.product = product;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
                ", user=" + user +
                ", product=" + product +
                ", rating=" + rating +
                ", reviewComment='" + reviewComment + '\'' +
                ", datePosted=" + datePosted +
                '}';
    }
}
