package com.goit.devgroup.spring.header;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("/header")
@Controller
public class HeaderController {
    @ResponseBody
    @GetMapping("/list")
    public HeaderListResponse list(HttpServletRequest request) {
        HeaderListResponse response = new HeaderListResponse();

        Map<String, String> headers = new LinkedHashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            headers.put(headerName, headerValue);
        }

        response.setHeaders(headers);
        return response;
    }

    @ResponseBody
    @GetMapping("/setHeader")
    public Map<String, Object> setHeader(@RequestParam("headerValue") String headerValue,
                                         HttpServletResponse response) {
        response.setHeader("X-MY-CUSTOM-HEADER", headerValue);

        return Map.of(
                "success", true
        );
    }
}
