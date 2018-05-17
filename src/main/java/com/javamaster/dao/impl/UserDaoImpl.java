package com.javamaster.dao.impl;

import com.javamaster.model.Role;
import com.javamaster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private UserExtractor userExtractor;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.userExtractor = new UserExtractor();
    }


    public User getByEmail(String email) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);
        return jdbcTemplate.query("SELECT u.id, name, " +
                "            last_name, email, password, address, " +
                "            user_roles_id, role, r.id " +
                "            FROM users u" +
                "            INNER JOIN user_roles r" +
                "            ON u.user_roles_id = r.id " +
                "            WHERE u.email = :email", params, userExtractor).stream().findFirst().orElse(null);
    }

    private class UserExtractor implements ResultSetExtractor<List<User>> {

        @Override
        public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            List<User> users = new ArrayList<>();

            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));

                users.add(user);
            }
            return users;
        }
    }
}

