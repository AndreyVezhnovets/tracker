package com.bug.validation;


import java.time.LocalDate;

public interface BugValidation {

    boolean isDateValid(LocalDate creationDate, LocalDate dueDate);

    boolean isEmpty(String entry);

    boolean isDateValid(String date);

    boolean validate(String name, String description, String  creationDate, String dueDate);

    boolean validate(String name, String description,String creationDate);
}
