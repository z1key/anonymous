package org.z1key.projects.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 25.04.2017.
 */
@Entity
@Table(name = "pass_recovery")
public class PassRecoveryToken {

    private static final long SerialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true, updatable = false)
    private String token;


    @Column
    @CreationTimestamp
    private Date created;

    @Column
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future
    private Date expireTime;

    @Column
    private boolean wasUsed = false;

    public PassRecoveryToken() {
    }

    public PassRecoveryToken(User user) {
        this.user = user;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.HOUR_OF_DAY, 3);
        this.expireTime = c.getTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isWasUsed() {
        return wasUsed;
    }

    public void setWasUsed(boolean wasUsed) {
        this.wasUsed = wasUsed;
    }
}
