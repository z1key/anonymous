package org.z1key.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.z1key.projects.entity.PassRecoveryToken;
import org.z1key.projects.entity.User;

/**
 * Created by User on 25.04.2017.
 */
public interface PassTokenRepository extends JpaRepository<PassRecoveryToken, String> {

    Iterable<? extends PassRecoveryToken> findByUser(User user);
}
