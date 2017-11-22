package org.z1key.projects.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.z1key.projects.entity.Post;
import org.z1key.projects.entity.User;
import org.z1key.projects.repository.PostRepository;

import javax.transaction.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void publish(Post post) {}

    public void delete(Post post){}

    public Post edit(Post post) {

        return null;
    }
    public Iterable<Post> getRecentPosts() {
        return postRepository.findFirst10ByOrderByPostTimeDesc();
    }

    @Transactional
    public void upVote(Post post, User user) {
        post.getDownvoted().remove(user);
        post.getUpvoted().add(user);
        postRepository.saveAndFlush(post);
    }
    @Transactional
    public void downVote(Post post, User user) {
        post.getUpvoted().remove(user);
        post.getDownvoted().add(user);
        postRepository.saveAndFlush(post);
    }
}
