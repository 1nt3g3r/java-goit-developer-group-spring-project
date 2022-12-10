package com.goit.devgroup.spring;

import com.goit.devgroup.spring.user.InMemoryUserService;
import com.goit.devgroup.spring.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InMemoryUserServiceTests {
    @Autowired
    private InMemoryUserService inMemoryUserService;

    @BeforeEach
    public void beforeEach() {
        inMemoryUserService.clear();
    }

    @Test
    public void testThatCountAllMethodWorksOfIfNoUsersPresent() {
        int expected = 0;
        int actual = inMemoryUserService.countAll();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testThatCountAllMethodWorksOfIfSingleUserPresents() {
        User user = new User();
        user.setName("Test user");
        inMemoryUserService.save(user);

        int expected = 1;
        int actual = inMemoryUserService.countAll();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteByIdMethodWorksOk() {
        User user = new User();
        user.setName("Test user");
        User savedUser = inMemoryUserService.save(user);

        inMemoryUserService.deleteById(savedUser.getId());

        Assertions.assertEquals(0, inMemoryUserService.countAll());
    }
}
