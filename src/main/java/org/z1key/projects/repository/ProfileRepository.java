package org.z1key.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.z1key.projects.entity.Profile;

/**
 * Created by User on 13.04.2017.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
