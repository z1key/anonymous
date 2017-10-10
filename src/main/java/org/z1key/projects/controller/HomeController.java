package org.z1key.projects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.z1key.projects.service.post.PostService;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("posts", postService.getRecentPosts());
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
