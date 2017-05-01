package org.z1key.projects.validate;

import org.hibernate.validator.constraints.*;
import org.z1key.projects.entity.User;

import javax.validation.constraints.Pattern;

public class SignupForm extends PasswordForm {

    @NotBlank
    @Length(min = 5, max = 25)
    @Pattern(regexp = "^[A-Za-z\\d]+$", message = "{login.constraint}")
    private String login;

    @NotBlank
    @Email
//    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{email.constraint}")
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User createAccount() {
        return new User(getLogin(), passwordEncoder.encode(getPassword()), getEmail());
    }
}
