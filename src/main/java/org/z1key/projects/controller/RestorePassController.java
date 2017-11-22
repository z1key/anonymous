package org.z1key.projects.controller;

import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import com.github.mkopylec.recaptcha.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.z1key.projects.entity.User;
import org.z1key.projects.service.person.UserService;
import org.z1key.projects.util.AjaxUtils;
import org.z1key.projects.validate.RestorePasswordForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by User on 25.04.2017.
 */
@Controller
@RequestMapping("/restore-password")
public class RestorePassController {

    private static final String RESTORE_PASSWORD_VIEW = "fragments/restore-password";

    @Autowired
    private UserService userService;

    @Autowired
    private RecaptchaValidator recaptchaValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return RESTORE_PASSWORD_VIEW.concat(" :: email-form");
        }
        model.addAttribute(new RestorePasswordForm());
        return "restore-password";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processEmailForm(@Valid @ModelAttribute RestorePasswordForm passwordForm,
                                   Errors errors,
                                   Model model,
                                   HttpServletRequest request) {
        boolean success = false;
        if (!errors.hasErrors()) {
            ValidationResult captchaResult = recaptchaValidator.validate(request);
            if (captchaResult.isFailure()) {
                errors.reject("validation.captcha");
            } else {
                User user = userService.loadUserByEmail(passwordForm.getEmail());
                if (user != null) {
                    userService.recoveryPassword(user);
                    success = true;
                } else {
                    errors.reject("validation.notFound");
                }
            }
        }
        model.addAttribute("success", success);
        return RESTORE_PASSWORD_VIEW.concat(" :: email-form");
    }
}
