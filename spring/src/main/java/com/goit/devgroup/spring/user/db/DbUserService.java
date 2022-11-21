package com.goit.devgroup.spring.user.db;

import com.goit.devgroup.spring.user.User;
import com.goit.devgroup.spring.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@RequiredArgsConstructor
@Service
public class DbUserService implements UserService {
    private final UserRepository repository;

    private Integer totalUserCount;

    @Override
    public List<User> listAll() {
        return repository.findAll();

        //Session s = sessionFactory.openSession();
        //List<User> allUsers = s.queryAll(User.class);
        //SELECT id, name FROM "user";
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
        }

        User toReturn = repository.save(user);

        totalUserCount = null;

        return toReturn;
    }

    @Override
    public User getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User deleteById(String id) {
        User toReturn = getById(id);

        repository.deleteUserById(id);
        //deleteById(id);

        totalUserCount = null;

        return toReturn;
    }

    @Override
    public List<User> search(String query) {
//BAD!
//        return repository
//                .findAll()
//                .stream()
//                .filter(it -> it.getName().toLowerCase().contains(query.toLowerCase()))
//                .collect(Collectors.toList());
        return repository.findByNameLikeIgnoreCase("%" + query + "%"); // searchByNativeQuery(query);
    }

    @Override
    public int countAll() {
        if (totalUserCount == null) {
            totalUserCount = repository.countAllUsers();
        }

        return totalUserCount;
//        return repository.countAllUsers();
    }
}
