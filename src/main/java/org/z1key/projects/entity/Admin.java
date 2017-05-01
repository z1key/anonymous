package org.z1key.projects.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by User on 13.04.2017.
 */
@Entity
@Table(name = "admins")
public class Admin extends User {

    @Column
    private String alpha;

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }
}
