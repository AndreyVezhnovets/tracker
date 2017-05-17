package com.bug.service;

import com.bug.dao.spring.EmployeeDao;
import com.bug.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeDao employeeDao;

//    @Override
//    public boolean isLoginPasswordMatch(String login, String password) {
//        Employee employee = empD.findByMail(login);
//        return (employee != null & employee.getPassword().equals(password));
//    }

    @Override
    public void updatePassword(String password, int id) {
        Employee employee = employeeDao.findOne(id);
        employee.setPassword(password);
        employeeDao.save(employee);
    }

    @Override
    public boolean isLoginUnique(String login) {
        Employee employee = employeeDao.findByMail(login);
        return (employee != null & !employee.getMail().equals(login));
    }

    @Override
    public Employee findById(int id) {
        return employeeDao.findOne(id);
    }

    @Override
    public Employee findByMail(String mail) {
        return employeeDao.findByMail(mail);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

    @Override
    public void deleteByMail(String mail) {
        employeeDao.deleteByMail(mail);
    }

    @Override
    public void insert(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void update(int id, Employee employee) {
        Employee entity = employeeDao.findOne(id);
        if (entity != null) {
            entity.setMail(employee.getMail());
            entity.setName(employee.getName());
            entity.setSurname(employee.getSurname());
            entity.setPosition(employee.getPosition());
        }
        employeeDao.save(entity);
    }
}
