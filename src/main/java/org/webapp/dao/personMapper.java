package org.webapp.dao;

import org.springframework.jdbc.core.RowMapper;
import org.webapp.models.person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class personMapper implements RowMapper<person> {
    @Override
    public person mapRow(ResultSet resultSet, int i) throws SQLException {
        person person = new person();

        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));

        return person;
    }
}
