package com.goit.devgroup.spring.user.jdbc;

import com.goit.devgroup.spring.user.User;
import com.goit.devgroup.spring.user.UserService;
import com.goit.devgroup.spring.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@RequiredArgsConstructor
@Service
public class JdbcUserService implements UserService {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String TABLE = "\"user\"";

    @Override
    public List<User> listAll() {
        String sql = "SELECT id, name FROM " + TABLE;

        return jdbcTemplate.query(
                sql,
                Collections.emptyMap(),
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
        }

        jdbcTemplate.update(
                "MERGE INTO " + TABLE + " (id, name) KEY(id) VALUES (:id, :name)",
                Map.of("id", user.getId(), "name", user.getName())
        );


//        //check if user exists
//        @SuppressWarnings("ConstantConditions")
//        boolean userExists = jdbcTemplate.queryForObject(
//                "SELECT count(*) > 0 FROM " + TABLE + " WHERE id = :id",
//                Map.of("id", user.getId()),
//                Boolean.class
//        );
//
//        if (userExists) {
//            jdbcTemplate.update(
//                    "UPDATE " + TABLE + " SET name = :name WHERE id = :id",
//                    Map.of(
//                            "id", user.getId(),
//                            "name", user.getName()
//                    )
//            );
//        } else {
//            jdbcTemplate.update(
//                    "INSERT INTO " + TABLE + " (id, name) VALUES (:id, :name)",
//                    Map.of(
//                            "id", user.getId(),
//                            "name", user.getName()
//                    )
//            );
//        }

        return user;
    }

    @Override
    public User getById(String id) {
        String sql = "SELECT id, name FROM " + TABLE + " WHERE id = :id";

        return jdbcTemplate.queryForObject(
                sql,
                Map.of("id", id),
                new UserRowMapper()
        );
    }

    @Override
    public User deleteById(String id) {
        //Obtain existing user before delete
        User toDelete = getById(id);

        //Delete user
        String sql = "DELETE FROM " + TABLE + " WHERE id = :id";
        jdbcTemplate.update(
                sql,
                Map.of("id", id)
        );

        return toDelete;
    }

    @Override
    public List<User> search(String query) {
        String sql = "SELECT id, name FROM " + TABLE +
                " WHERE LOWER(name) LIKE LOWER(:query)";

        return jdbcTemplate.query(
                sql,
                Map.of("query", "%" + query + "%"),
                new UserRowMapper()
        );
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("name");
            String id = rs.getString("id");

            User user = new User();
            user.setName(name);
            user.setId(id);

            return user;
        }
    }

    @Override
    public int countAll() {
        String sql = "SELECT count(*) FROM " + TABLE;

        //noinspection ConstantConditions
        return jdbcTemplate.queryForObject(
                sql,
                Collections.emptyMap(),
                Integer.class
        );
    }

//    private final UserRepository repository;
//
//    @Transactional
//    public void deleteUsers(List<User> users) {
//        for (User user : users) {
//            repository.deleteById(user.getId());
//        }
//    }
}
