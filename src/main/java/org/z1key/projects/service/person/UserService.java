package org.z1key.projects.service.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.z1key.projects.entity.PassRecoveryToken;
import org.z1key.projects.entity.Role;
import org.z1key.projects.entity.User;
import org.z1key.projects.repository.PassTokenRepository;
import org.z1key.projects.repository.UserRepository;
import org.z1key.projects.service.MailService;
import org.z1key.projects.validate.EntityExistsException;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by User on 08.04.2017.
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private RoleService roleService;
    private MailService mailService;
    private PassTokenRepository passTokenRepository;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, PassTokenRepository passTokenRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passTokenRepository = passTokenRepository;
        this.mailService = mailService;
    }

    public void persist(User user) {
        roleService.saveOrUpdate(user.getRoles());
        if (isEmailExists(user.getEmail())) throw new EntityExistsException("Email");
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            // Entry already exists
            throw new EntityExistsException("User");
        }
    }
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void signin(User user) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    private Authentication authenticate(User user) {
        return new UsernamePasswordAuthenticationToken(createUser(user), null, user.getAuthorities());
    }

    private org.springframework.security.core.userdetails.User createUser(User user) {
        return new Account(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        if (user == null) throw new UsernameNotFoundException("User " + login + " not found.");
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }

    @Transactional(readOnly = true)
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void recoveryPassword(User user) {
        PassRecoveryToken token = new PassRecoveryToken(user);
        passTokenRepository.delete(passTokenRepository.findByUser(user));
        passTokenRepository.save(token);
        // Need to send email
        try {
            mailService.sendRestorePasswordEmail(token);
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
    }

    private static class Account extends org.springframework.security.core.userdetails.User {

        private final User user;

        public Account(User user) {
            super(user.getLogin(), user.getPassword(), user.getAuthorities());
            this.user = user;
        }

        public User getAccount() {
            return user;
        }

        public boolean isAdmin() {
            return getAccount().isAdmin();
        }
    }
}
