package com.goit.devgroup.spring.user.dto;

import com.goit.devgroup.spring.user.User;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;

    public static UserDto fromUser(User user) {
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setName(user.getName());
        return result;
    }

    public User toUser() {
        User result = new User();
        result.setId(id);
        result.setName(name);
        return result;
    }
}
