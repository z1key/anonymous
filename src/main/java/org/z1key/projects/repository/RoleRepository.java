package org.z1key.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.z1key.projects.entity.Role;

/**
 * Created by User on 09.04.2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
