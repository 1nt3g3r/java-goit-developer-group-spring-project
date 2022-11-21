package com.goit.devgroup.spring.user;

import com.goit.devgroup.spring.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public List<UserDto> list() {
        return userService.listAll().stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

    @PostMapping("/save")
    public UserDto save(@RequestBody UserDto dto, HttpServletResponse response) {
        if (dto.getName() == null) {
            response.setStatus(400);
            return new UserDto();
        }

        return UserDto.fromUser(userService.save(dto.toUser()));
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") String id, HttpServletResponse response) {
        User user = userService.getById(id);

        if (user == null) {
            response.setStatus(404);
            return new UserDto();
        }

        return UserDto.fromUser(user);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteById(@PathVariable("id") String id, HttpServletResponse response) {
        User user = userService.getById(id);

        if (user == null) {
            response.setStatus(404);
            return new UserDto();
        }

        userService.deleteById(id);

        return UserDto.fromUser(user);
    }

    @GetMapping("/search")
    public List<UserDto> search(@RequestParam("query") String query) {
        return userService
                .search(query)
                .stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/count")
    public Map<String, Object> countAll() {
        return Map.of(
                "count", userService.countAll()
        );
    }
}
