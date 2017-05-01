package org.z1key.projects.validate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;

/**
 * Created by User on 14.04.2017.
 */
@FieldMatch(first = "password", second = "verifyPassword", message = "{password.verify.constraint}")
public class PasswordForm {

    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    static final String PASSWORD_MESSAGE = "{password.constraint}";

    @NotBlank
    @Length(min = 6, max = 40)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = PASSWORD_MESSAGE)
    String password;

    String verifyPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
