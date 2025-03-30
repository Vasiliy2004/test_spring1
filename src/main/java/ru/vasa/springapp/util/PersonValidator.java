package ru.vasa.springapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasa.springapp.dao.PersonDAO;
import ru.vasa.springapp.models.Person;
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    private final JdbcTemplate jdbcTemplate;

    @Autowired//Можно без него
    public PersonValidator(PersonDAO personDAO, JdbcTemplate jdbcTemplate) {
        this.personDAO = personDAO;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;


    }



}
