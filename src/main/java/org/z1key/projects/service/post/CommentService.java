package org.z1key.projects.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.z1key.projects.entity.Comment;
import org.z1key.projects.entity.Post;
import org.z1key.projects.entity.User;
import org.z1key.projects.repository.CommentRepository;

import javax.transaction.Transactional;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void publish(Comment comment, Post post) {}

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment edit(Comment comment) {


        return null;
    }

    @Transactional
    public void upVote(Comment comment, User user) {
        comment.getDownvoted().remove(user);
        comment.getUpvoted().add(user);
        commentRepository.saveAndFlush(comment);
    }
    @Transactional
    public void downVote(Comment comment, User user) {
        comment.getUpvoted().remove(user);
        comment.getDownvoted().add(user);
        commentRepository.saveAndFlush(comment);
    }
}
