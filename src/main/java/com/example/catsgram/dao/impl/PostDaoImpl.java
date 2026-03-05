package com.example.catsgram.dao.impl;

import com.example.catsgram.dao.PostDao;
import com.example.catsgram.model.Post;
import com.example.catsgram.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostDaoImpl implements PostDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Post> findPostsByUser(User user) {
        String sql = "select * from cat_post where author_id = ? order by creation_date desc";
        return jdbcTemplate.query(sql, (rs, rowNum)->makePost(user, rs), user.getId());
    }

    private Post makePost(User user, ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        LocalDate creationDate = rs.getDate("creation_date").toLocalDate();
        String description = rs.getString("description");
        String photoUrl = rs.getString("photo_url");
        return new Post(id, user, creationDate,description,photoUrl);
    }

    @Override
    public List<Post> findAll() {
        return List.of();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Post create(Post post) {
        return null;
    }

}
