package ru.vasa.springapp.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Book {
    private int id;

    @NotEmpty(message ="Название не дожно быть пустым")
    @Size(min=2,max=100,message = "Название должно быть 2-100)")
    private String title;

    @NotEmpty(message ="Автор не должен быть пустым")
    @Size(min=2,max=100,message = "Имя должно быть 2-100)")
    private String author;

    @Min(value=1500,message="Год должен быть больше 1500")
    private int year;

    public Book(){}

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}

    public int getYear() {return year;}

    public void setYear(int year) {this.year = year;}
}
