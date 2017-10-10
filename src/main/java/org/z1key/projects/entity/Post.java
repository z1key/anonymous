package org.z1key.projects.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 02.05.2017.
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    private long id;

    @Column
    @Length(min = 7, max = 150)
    private String subject;

    @Column
    @Length(min = 10, max = 2000)
    private String text;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private User author;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<User> upvoted = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<User> downvoted = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @Column
    @CreationTimestamp
    private Date postTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (!subject.equals(post.subject)) return false;
        if (!text.equals(post.text)) return false;
        return author.equals(post.author);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + subject.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
