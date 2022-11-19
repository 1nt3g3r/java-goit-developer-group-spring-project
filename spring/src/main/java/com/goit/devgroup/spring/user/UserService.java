package com.goit.devgroup.spring.user;

import java.util.List;

public interface UserService {
    List<User> listAll();
    User save(User user);
    User getById(String id);
    User deleteById(String id);
}
