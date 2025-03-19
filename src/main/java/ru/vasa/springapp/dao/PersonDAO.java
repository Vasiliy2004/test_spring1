package ru.vasa.springapp.dao;

import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasa.springapp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Person> index(){


        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));

        //по другому можно без своего класса PersonMapper птому что колонка id в БД и Person.id название одинаковы
        //return jdbcTemplate.query("SELECT * FROM person",new PersonMapper());

//        List<Person> people = new ArrayList<>();
//
//        try {
//            Statement statement = conn.createStatement();
//            String SQL = "SELECT * FROM person";
//            ResultSet rs = statement.executeQuery(SQL);
//
//            while (rs.next()) {
//                Person person = new Person();
//                rs.getInt("id");
//                person.setId(rs.getInt("id"));
//                person.setName(rs.getString("name"));
//                person.setAge(rs.getInt("age"));
//                person.setMail(rs.getString("mail"));
//
//                people.add(person);
//            }
//        }
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return people;
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person VALUES(1,?,?,?)",person.getName(),person.getAge(),person.getMail());
        /*try{
//            Statement statement = conn.createStatement();
//            String SQL = "INSERT INTO person VALUES ("+
//                    4+",'"+
//                    person.getName()+"','"+
//                    person.getAge()+"','"+
//                    person.getMail()+"')";
//            statement.executeUpdate(SQL);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO person VALUES (55, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getMail());
            preparedStatement.executeUpdate();

        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }*/

    }

    public void update(int id,Person newperson) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, mail=? WHERE id=?",
                newperson.getName(),newperson.getAge(),
                newperson.getMail(),id);

    }

    public void delete(int id) {
//        try {
//            PreparedStatement preparedStatement =
//                    conn.prepareStatement("DELETE FROM person WHERE id=?");
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
        jdbcTemplate.update("DELETE  FROM person WHERE id=?",id);
    }

    /// //////Тест производительность пакетной вставки/////////////
    public void testMultipleUpdate(){
        List<Person> people = create1000people();
        long start = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO person VALUES(?,?,?,?)",person.getId(),person.getName(),person.getAge(),person.getMail());
        }

        long end = System.currentTimeMillis();
        System.out.println("Time=" + (end - start));
    }
    private List<Person> create1000people(){
        List<Person> people = new ArrayList<>();
        for (int i = 10; i < 1010; i++) {
            people.add(new Person(i,"Name_"+i,i,"mail@mail.com"+i));
        }
        return people;
    }

    public void testPatchUpdate(){

        List<Person> people = create1000people();

        long start = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO person VALUES(?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1,people.get(i).getId());
                        ps.setString(2,people.get(i).getName());
                        ps.setInt(3,people.get(i).getAge());
                        ps.setString(4,people.get(i).getMail());
                    }


                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

        long end = System.currentTimeMillis();
        System.out.println("Time=" + (end - start));
    }
}


