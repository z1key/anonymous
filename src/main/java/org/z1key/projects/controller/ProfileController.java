package org.z1key.projects.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by User on 14.04.2017.
 */
@Controller
public class ProfileController {

    @RequestMapping("/profile")
    @Secured("IS_AUTHENTICATED_FULLY")
    public String getPage() {
        return "profile";
    }
}
