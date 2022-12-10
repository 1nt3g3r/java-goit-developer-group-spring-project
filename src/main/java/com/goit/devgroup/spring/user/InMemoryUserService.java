package com.goit.devgroup.spring.user;


import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InMemoryUserService implements UserService {
    private Map<String, User> users = new HashMap<>();

    @Override
    public List<User> listAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public synchronized User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
        }
        
        users.put(user.getId(), user);
    
        return user;
    }

    @Override
    public User getById(String id) {
        return users.get(id);
    }

    @Override
    public synchronized User deleteById(String id) {
        return users.remove(id);
    }

    @Override
    public List<User> search(String query) {
        return null;
    }

    @Override
    public int countAll() {
        return users.size();
    }

    public void clear() {
        users.clear();
    }
}
