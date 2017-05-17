package com.bug.validation;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ProjectValidationImpl implements ProjectValidation {

    @Override
    public boolean isValidDates(LocalDate start, LocalDate end) {
        if (start.getYear() > end.getYear())
            return false;
        if (start.getMonthValue() > end.getMonthValue())
            return false;
        if (start.getDayOfMonth() > end.getDayOfMonth())
            return false;
        return true;
    }

    @Override
    public boolean isNameValid(String name) {
        return name.length() > 4;
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

    @Override
    public boolean validate(String startDate, String endDate, String name) {
        return isDateValid(startDate) && isDateValid(endDate) && isNameValid(name);
    }
}
