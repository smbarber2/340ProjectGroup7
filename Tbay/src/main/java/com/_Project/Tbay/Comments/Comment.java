package com._Project.Tbay.Comments;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long commentId;

    @Column(nullable=false)
    public long posterId;

    @Column(nullable=false)
    private String date;

    @Column(nullable=false)
    private String body;

    public Comment(long commentId, long posterId, String date, String body) {
        this.commentId = commentId;
        this.posterId = posterId;
        this.date = date;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
