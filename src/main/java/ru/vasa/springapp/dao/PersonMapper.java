package ru.vasa.springapp.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.vasa.springapp.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        rs.getInt("id");
        person.setId(rs.getInt("id"));
//        person.setName(rs.getString("name"));
//        person.setAge(rs.getInt("age"));
//        person.setMail(rs.getString("mail"));
        return person;
    }
}
