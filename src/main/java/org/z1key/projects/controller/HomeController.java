package org.z1key.projects.controller;

import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String index() {
        return "home";
    }

    @ModelAttribute("page")
    public String module() {
        return "home";
    }
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout() {
//        return "home";
//    }

}
