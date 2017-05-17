package com.bug.configuration.security;

import com.bug.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component(value = "authenticationProviderImpl")
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    @Qualifier("MyUserDetailsService")
    private UserDetailsService userService;

    @Bean
    public Md5PasswordEncoder passwordEncoder(){
        return new Md5PasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        String hashPassword = passwordEncoder().encodePassword(password, Employee.class);

        if (userService == null)
            throw new InternalAuthenticationServiceException("user is null");

        UserDetails user = userService.loadUserByUsername(login);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("Not found");
        }

        if (user.getPassword().equals(hashPassword)) {
            return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        } else {
            throw new AuthenticationServiceException("Unable to auth against third party systems");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
