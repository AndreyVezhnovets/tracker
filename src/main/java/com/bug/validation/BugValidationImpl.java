package com.bug.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class BugValidationImpl implements BugValidation {

    public boolean isDateValid(LocalDate creationDate, LocalDate dueDate) {
        if (creationDate.getYear() > dueDate.getYear())
            return false;
        if (creationDate.getMonthValue() > dueDate.getMonthValue())
            return false;
        if (creationDate.getDayOfMonth() > dueDate.getDayOfMonth())
            return false;
        return true;
    }

    public boolean isEmpty(String entry) {
        return entry.length() == 0;
    }

    public boolean validate(String name, String description, String creationDate, String dueDate) {
        return isEmpty(name) && isEmpty(description) && isDateValid(creationDate) && isDateValid(dueDate);
    }

    public boolean validate(String name, String description, String creationDate) {
        return isEmpty(name) && isEmpty(description) && isDateValid(creationDate);
    }

    @Override
    public boolean isDateValid(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
