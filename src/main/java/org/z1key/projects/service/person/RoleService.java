package org.z1key.projects.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.z1key.projects.entity.Role;
import org.z1key.projects.repository.RoleRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.z1key.projects.entity.Role.*;

/**
 * Created by User on 09.04.2017.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
    @Transactional
    public Iterable<Role> saveOrUpdate(Iterable<Role> roles) {
        Set<Role> result = new HashSet<>();
        for(Role role: roles) {
            Role r = roleRepository.findByName(role.getName());
            if (r == null) {
                result.add(roleRepository.save(role));
            } else {
                result.add(r);
            }
        }
        return result;
    }
    @Transactional
    public Role saveOrUpdate(Role role) {
        Role r = roleRepository.findByName(role.getName());
        if (r == null) {
            return roleRepository.save(role);
        }
        return r;
    }
    public Role getRole(String name) {
        Role r = roleRepository.findByName(name);
        if (r == null) {
            r = new Role(name);
            roleRepository.save(r);
        }
        return r;
    }

    @PostConstruct
    private void createStaticRoles() {
        saveOrUpdate(new Role(ROLE_SUPERADMIN));
        saveOrUpdate(new Role(ROLE_ADMIN));
        saveOrUpdate(new Role(ROLE_USER));
        saveOrUpdate(new Role(ROLE_BANNED));
    }
}
