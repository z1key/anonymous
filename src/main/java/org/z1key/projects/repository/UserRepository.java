package org.z1key.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.z1key.projects.entity.User;

/**
 * Created by User on 08.04.2017.
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByLogin(String login);

    User findByEmail(String email);
}
