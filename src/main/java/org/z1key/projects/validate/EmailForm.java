package org.z1key.projects.validate;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;

/**
 * Created by User on 27.04.2017.
 */
public class EmailForm {

    @NotBlank
    @Email
    @Max(50)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
