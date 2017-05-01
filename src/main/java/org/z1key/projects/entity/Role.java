package org.z1key.projects.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements DomainObject {

    private static final long serialVersionUID = 1L;

    public static final String ROLE_SUPERADMIN = "SUPERADMIN";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_BANNED = "BANNED";

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String name = "USER";

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.MERGE)
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
