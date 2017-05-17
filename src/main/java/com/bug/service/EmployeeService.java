package com.bug.service;

import com.bug.model.Employee;

/**
 * Created by vita on 10/21/15.
 */
public interface EmployeeService {
    Employee findById(int id);

    Employee findByMail(String login);

    void insert(Employee employee);

    void update(int id, Employee employee);

    void delete(Employee employee);

    void deleteByMail(String login);

    boolean isLoginUnique(String login);

    Iterable<Employee> findAll();

//    boolean isLoginPasswordMatch(String login, String password);

    void updatePassword(String password, int id);
}
