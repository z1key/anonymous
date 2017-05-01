package org.z1key.projects.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.z1key.projects.validate.RestorePasswordForm;

/**
 * Created by User on 11.04.2017.
 */
@Controller
public class LoginController {

    private static final String VIEW_NAME = "login";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getPage(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            model.addAttribute(new RestorePasswordForm());
            return VIEW_NAME;
        }
        return "redirect:/";
    }
}
