package com.bug.validation;

import org.springframework.stereotype.Component;

@Component
public class EmployeeValidatorImpl implements EmployeeValidation {

    public boolean isEmpty(String entity) {
        return entity.length() == 0;
    }

    public boolean isPasswordsMatch(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    public boolean isPasswordPatternMatch(String password) {
        return (password.length() > 5);
    }


    public boolean validate(String login, String password, String confirmedPassword, String name, String surname) {
        if (isEmpty(login) | isEmpty(password) | isEmpty(confirmedPassword) |
                isEmpty(name) | isEmpty(surname)) {
            return false;
        }
        if (!isPasswordsMatch(password,confirmedPassword)){
            return false;
        }
        if (!isPasswordPatternMatch(password)) {
            return false;
        }
        return true;
    }

    public boolean validate(String login, String password){
        if (isEmpty(login) | isEmpty(password)) {
            return false;
        }
        if (!isPasswordPatternMatch(password)) {
            return false;
        }
        return true;
    }
}
