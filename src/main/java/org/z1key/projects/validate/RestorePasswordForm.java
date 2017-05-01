package org.z1key.projects.validate;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by User on 25.04.2017.
 */
public class RestorePasswordForm {

    @Email
    @NotBlank
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
