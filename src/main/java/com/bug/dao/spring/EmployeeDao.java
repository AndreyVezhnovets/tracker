package com.bug.dao.spring;

import com.bug.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    Employee findByMail(final String mail);

    void deleteByMail(String mail);
}
