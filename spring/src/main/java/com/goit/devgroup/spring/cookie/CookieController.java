package com.goit.devgroup.spring.cookie;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/cookie")
@RestController
public class CookieController {
    @GetMapping("/read")
    public Map<String, Object> read(@CookieValue(value = "myCookie", required = false) String myCookie) {
        Map<String, Object> result = new HashMap<>();

        result.put("myCookie", myCookie);

        return result;
    }

    @GetMapping("/write")
    public Map<String, Object> write(@RequestParam("myCookie") String myCookie, HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", myCookie);
        response.addCookie(cookie);

        return Map.of(
                "success", true
        );
    }
}
