package org.z1key.projects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.z1key.projects.entity.PassRecoveryToken;
import org.z1key.projects.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by User on 26.04.2017.
 */
@Service
@Order(1)
public class MailService {

    @Value("spring.mail.username")
    private String fromEmail;

    private JavaMailSender javaMailSender;

    private TemplateEngine templateEngine;

    @Autowired
    MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendRestorePasswordEmail(PassRecoveryToken token) throws MessagingException{
        User user = token.getUser();
        String emailTo = user.getEmail();

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        ServletContext servletContext = request.getSession().getServletContext();

        WebContext thContext = new WebContext(request, response, servletContext, LocaleContextHolder.getLocale());
        thContext.setVariable("token", token.getToken());
        String content = templateEngine.process("restore-password-email", thContext);

        MimeMessage m = javaMailSender.createMimeMessage();
        MimeMessageHelper message =
                new MimeMessageHelper(m, true, "UTF-8"); // true = multipart
        message.setTo(emailTo);
        message.setFrom(fromEmail);
        message.setSubject("Restore your Password");
        message.setText(content, true);
        javaMailSender.send(m);
    }
}
