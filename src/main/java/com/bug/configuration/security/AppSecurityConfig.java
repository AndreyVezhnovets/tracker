package com.bug.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration()
@EnableWebSecurity
@ComponentScan
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationProviderImpl authenticationProvider;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/employees", "/employee", "/updateEmployee", "/updateEmployeePassword", "/saveComment", "/issues/issue", "/issue/update", "/get/file").hasAnyRole("ADMINISTRATOR", "USER")
                .antMatchers("/projects", "/addProject", "/addEmployeeMenu", "/editProject", "/project/add/new", "/project/edit").hasAnyRole("ADMINISTRATOR")
                .antMatchers("/").permitAll()
                .and().formLogin().loginPage("/2")
                .loginProcessingUrl("/Alogin")
                .usernameParameter("mail")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        ;
    }
}

