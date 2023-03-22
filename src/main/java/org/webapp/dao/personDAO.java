package org.webapp.dao;


import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.webapp.models.person;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class personDAO {

    private final JdbcTemplate jdbcTemplate;

    public personDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<person> index(){
        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(person.class));
    }
    //email valid checker
    public Optional<person> show(String email){
        return jdbcTemplate.query("SELECT * FROM person WHERE email = ?", new Object[] {email},
                new BeanPropertyRowMapper<>(person.class)).stream().findAny();
    }

    public person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(person.class))
                .stream().findAny().orElse(null);
    }
    //person creator
    public void save(person person){
        jdbcTemplate.update("INSERT INTO person(name,age,email,address) VALUES (?,?,?,?)",person.getName(),person.getAge(),
                person.getEmail(),person.getAddress());
    }
    //person update
    public void update(int id, person updatedPerson){
        jdbcTemplate.update("UPDATE person SET name=?,age=?,email=?,address=? WHERE id = ?",updatedPerson.getName(),updatedPerson.getAge(),
                updatedPerson.getEmail(),updatedPerson.getAddress(), id);
    }
    //person del
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?",id);
    }


    /////Test Batch update --->



    public void testBatchUpdate(){
        List<person> people = create100people();
        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO person VALUES (?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setInt(1,people.get(i).getId());
                        preparedStatement.setString(2,people.get(i).getName());
                        preparedStatement.setInt(3,people.get(i).getAge());
                        preparedStatement.setString(4,people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

        long after = System.currentTimeMillis();
        System.out.println("Time2: " + (after-before));
    }

    public void testBatchDelete(){
        List<person> people = create100people();

        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("DELETE FROM person WHERE id=?",
                new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1,people.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });

        long after = System.currentTimeMillis();
        System.out.println("Time of del: " + (after-before));
    }

    private List<person> create100people() {
        List<person> people = new ArrayList<>();
        for(int i = 0;i<100;i++){
            people.add(new person(i,30, "name" + i, "test"+i+"gmail.com","Almaty"));
        }

        return people;
    }

}
