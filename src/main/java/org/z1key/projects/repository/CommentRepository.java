package org.z1key.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.z1key.projects.entity.Comment;

/**
 * Created by User on 03.05.2017.
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
}
