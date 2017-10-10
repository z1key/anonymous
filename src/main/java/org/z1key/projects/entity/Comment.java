package org.z1key.projects.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 02.05.2017.
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Post post;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User commentator;

    @NotBlank
    @Max(255)
    @Column
    private String text;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<User> upvoted = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<User> downvoted = new HashSet<>();

    @Column
    @CreationTimestamp
    private Date postTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<User> getUpvoted() {
        return upvoted;
    }

    public void setUpvoted(Set<User> upvoted) {
        this.upvoted = upvoted;
    }

    public Set<User> getDownvoted() {
        return downvoted;
    }

    public void setDownvoted(Set<User> downvoted) {
        this.downvoted = downvoted;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (!post.equals(comment.post)) return false;
        if (!commentator.equals(comment.commentator)) return false;
        if (!text.equals(comment.text)) return false;
        return postTime != null ? postTime.equals(comment.postTime) : comment.postTime == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + post.hashCode();
        result = 31 * result + commentator.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + (postTime != null ? postTime.hashCode() : 0);
        return result;
    }
}
