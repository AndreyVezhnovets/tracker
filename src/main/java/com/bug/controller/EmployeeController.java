package com.bug.controller;

import com.bug.exeption.EmployeeException;
import com.bug.model.Employee;
import com.bug.service.EmployeeService;
import com.bug.service.ProjectService;
import com.bug.validation.EmployeeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmployeeController {

    @Autowired
    EmployeeValidation validator;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProjectService projectService;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping(value = {"/employees"})
    public String getFailPage(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("projects", projectService.findAll());
        return "employees";
    }

    @RequestMapping(value = {"/employee"})
    public String getEmployee(@RequestParam(name = "mail") String mail, Model model) {
        boolean isAdmin = false;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isAdmin = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRATOR"));
        } catch (Exception ignored) {
        }
        model.addAttribute("isAdmin",isAdmin);
        model.addAttribute("employee", employeeService.findByMail(mail));
        return "EditEmployee";
    }

    @RequestMapping(value = {"/loginMenu"})
    public String getLoginMenu() {
        return "login";
    }

    @RequestMapping(value = {"/addEmployeeMenu"})
    public String getEmployeeMenu() {
        return "AddEmployee";
    }

    @RequestMapping(value = {"/passwordMenu"})
    public String getPasswordMenu(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "editPassword";
    }

    @ResponseBody
    @RequestMapping(value = "/index/registration")
    public String isSignInSuccess(@RequestParam String name, @RequestParam String surname,
                                  @RequestParam String mail, @RequestParam String position,
                                  @RequestParam String password, @RequestParam String confirmedPassword) throws EmployeeException {
        if (validator.validate(mail, password, confirmedPassword, name, surname)) {
            Employee entity = new Employee(name, surname, mail, position, md5PasswordEncoder.encodePassword(password, Employee.class));
            employeeService.insert(entity);
            return "success";
        } else {
            throw new EmployeeException("Registration wasn't success ");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateEmployee")
    public String isUpdateEmployee(@RequestParam String name, @RequestParam String surname,
                                   @RequestParam String mail, @RequestParam String position, @RequestParam Integer id) throws EmployeeException {
        Employee entity = new Employee(mail, name, surname, position);
        employeeService.update(id, entity);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/updateEmployeePassword")
    public String isUpdateEmployeePassword(@RequestParam String password, @RequestParam Integer id) throws EmployeeException {
        employeeService.updatePassword(md5PasswordEncoder.encodePassword(password, Employee.class), id);
        return "success";
    }
}
