package com.bug.configuration.security;

import com.bug.dao.spring.EmployeeDao;
import com.bug.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeDao employeeDao;

    @Autowired
    public MyUserDetailsService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        Employee employee = employeeDao.findByMail(login);
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + employee.getPosition());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(authority);
        return new User(employee.getMail(),
                employee.getPassword(), grantedAuthorities);
    }
}

