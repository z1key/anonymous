package org.z1key.projects.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "profiles")
public class Profile extends User {

    private static final long serialVersionUID = 1L;

    @Column(name = "avatar")
    private String avatar;

    @OneToOne(cascade = CascadeType.MERGE)
    private Pseudonim pseudonim;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<Pseudonim> pseudonims;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Date birthdate;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Pseudonim getPseudonim() {
        return pseudonim;
    }

    public void setPseudonim(Pseudonim pseudonim) {
        this.pseudonim = pseudonim;
    }

    public Set<Pseudonim> getPseudonims() {
        return pseudonims;
    }

    public void setPseudonims(Set<Pseudonim> pseudonims) {
        this.pseudonims = pseudonims;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }
}
