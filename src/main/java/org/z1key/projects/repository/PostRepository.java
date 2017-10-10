package org.z1key.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.z1key.projects.entity.Post;

import java.util.List;

/**
 * Created by User on 03.05.2017.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findFirst10ByOrderByPostTimeDesc();
}
