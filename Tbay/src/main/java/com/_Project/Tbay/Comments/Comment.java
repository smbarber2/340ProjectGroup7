package com._Project.Tbay.Comments;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long commentId;

    @Column(nullable=false)
    public long posterId;

    @Column(nullable=false)
    public long listingId;

    @Column(nullable=false)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable=false)
    private Date postDate;

    @Column(nullable=false)
    private String body;

    public Comment(long commentId, long posterId, long listingId, String name, Date postDate, String body) {
        this.commentId = commentId;
        this.posterId = posterId;
        this.listingId = listingId;
        this.name = name;
        this.postDate = postDate;
        this.body = body;
    }

    public Comment() {}

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setPosterId(long posterId) {
        this.posterId = posterId;
    }

    public long getPosterId() {
        return posterId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public long getListingId() {
        return listingId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
