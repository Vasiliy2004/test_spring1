package ru.vasa.springapp.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {
    private int id;

    @NotEmpty(message = "Name shoud not Empty")
    @Size(min=2,max=30,message = "Massage shod be 2-30")
    private String fullName;


    private String yearOfBirth;

    public Person(int id, String fullName, String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        this.fullName = fullName;
        this.id = id;

    }

    public Person() {    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
