package ru.vasa.springapp.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {
    private int id;

    @NotEmpty(message = "Name shoud not Empty")
    @Size(min=2,max=30,message = "Massage shod be 2-30")
    private String name;

    @Min(value = 0, message = "Age not less zero")
    private int age;


    @NotEmpty(message = "mail shoud not be empty")
    @Email(message = "Mail shoud be valid")
    private String mail;


    public Person(int id, String name, int age, String mail) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.mail = mail;

    }

    public Person() {    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
