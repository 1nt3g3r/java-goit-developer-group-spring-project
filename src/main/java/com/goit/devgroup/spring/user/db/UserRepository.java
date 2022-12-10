package com.goit.devgroup.spring.user.db;

import com.goit.devgroup.spring.user.User;
import com.goit.devgroup.spring.user.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<User> search(@Param("query") String query);

    @Query(
            nativeQuery = true,
            value = "SELECT id, name FROM \"user\" WHERE lower(name) LIKE lower(concat('%', :query, '%'))"
    )
    List<User> searchByNativeQuery(@Param("query") String query);

    List<User> findByNameLikeIgnoreCase(String name);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM \"user\" WHERE id = :id")
    void deleteUserById(@Param("id") String id);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM \"user\"")
    int countAllUsers();
}
