package org.z1key.projects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.z1key.projects.entity.Role;
import org.z1key.projects.entity.User;
import org.z1key.projects.repository.UserRepository;
import org.z1key.projects.service.person.RoleService;
import org.z1key.projects.service.person.UserService;
import org.z1key.projects.util.AjaxUtils;
import org.z1key.projects.util.MessageHelper;
import org.z1key.projects.validate.EntityExistsException;
import org.z1key.projects.validate.SignupForm;

import javax.validation.Valid;

import static org.z1key.projects.entity.Role.ROLE_USER;

/**
 * Created by User on 07.04.2017.
 */
@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "signup")
    public String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new SignupForm());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return SIGNUP_VIEW_NAME.concat(" :: signupForm");
        }
        return SIGNUP_VIEW_NAME;
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        User user = signupForm.createAccount();
        Role role = roleService.getRole(ROLE_USER);
        user.addRole(role);
        try {
            userService.persist(user);
        } catch (EntityExistsException ex) {
            if (ex.getMessage().equals("Email")) {
                errors.rejectValue("email", "email.exists");
            } else if (ex.getMessage().equals("User")) {
                errors.rejectValue("login", "user.exists");
            } else {
                errors.reject("Unexpected error.");
            }
            return SIGNUP_VIEW_NAME;
        }
        userService.signin(user);

        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "redirect:/";
    }
}
