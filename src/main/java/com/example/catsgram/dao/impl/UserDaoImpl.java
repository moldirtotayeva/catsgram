package com.example.catsgram.dao.impl;

import com.example.catsgram.dao.UserDao;
import com.example.catsgram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findById(String id) {
        String sql = "select * from cat_user where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, UserDaoImpl::userRowMapper, id));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from cat_user", UserDaoImpl::userRowMapper);
    }

    @Override
    public User create(User user) {
        return null;
    }

    private static User userRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getString("id"),
                rs.getString("username"),
                rs.getString("nickname"));
    }
}
