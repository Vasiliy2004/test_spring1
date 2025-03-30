package ru.vasa.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.vasa.springapp.models.Book;
import ru.vasa.springapp.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book",new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES(?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?" , new Object[]{id},
                new BeanPropertyRowMapper<Book>(Book.class)).stream().findAny().orElse(null);
    }

    public void update(int id,Book book){
        jdbcTemplate.update("UPDATE book SET title=?,author=?,year=? WHERE id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id=?",id);
    }

    //Соединяем таблицы book и person и получаем человека, которому принадледит книга с указанным ИД
    public Optional<Person> getBookOwner(int id){
        //Выбираем все коолонки талбицы person  из объединённой таблицы
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id = person.id"+
                " WHERE book.id = ?", new Object[]{id},new BeanPropertyRowMapper<Person>(Person.class)).stream().findAny();
    }

    //Освобожает книгу от человека
    public void release(int id){
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?",id);
    }

    //Назначает книгу человеку
    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?",selectedPerson.getId(),id);
    }

}
