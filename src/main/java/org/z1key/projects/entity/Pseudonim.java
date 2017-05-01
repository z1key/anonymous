package org.z1key.projects.entity;

import javax.persistence.*;

/**
 * Created by User on 13.04.2017.
 */
@Entity
@Table(name = "pseudonims")
public class Pseudonim implements DomainObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String value;

    @ManyToOne(targetEntity = Profile.class, cascade = CascadeType.MERGE)
    private Profile owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }
}
