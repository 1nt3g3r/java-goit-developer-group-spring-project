package com.goit.devgroup.spring.time;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/time")
public class TimeController {
    private final TimeService timeService;

    @GetMapping
    public ModelAndView getCurrentTime() {
        ModelAndView result = new ModelAndView("time");

        result.addObject("currentTime", timeService.getCurrentTimeByServerTimezone());
        result.addObject("randomNumbers", timeService.generateRandomNumbers());

        return result;
    }

    @GetMapping("/utc")
    public ModelAndView getCurrentTimeUtc(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView result = new ModelAndView("time");

        result.addObject("currentTime", timeService.getCurrentTimeByUtc());

        Map<String, String> requestParams = request
                .getParameterMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        it -> it.getValue()[0]
                ));
        result.addObject("requestParams", requestParams);

        response.setHeader("Refresh", "1");

        return result;
    }

    @GetMapping("/timezone")
    public ModelAndView getCurrentTimeInSpecificTimezone(@RequestParam(name = "tz") String tz) {
        ModelAndView result = new ModelAndView("time");

        result.addObject("currentTime", timeService.getCurrentTimeBySpecificTimezone(tz));

        return result;
    }

    @GetMapping("/timezone/{tz}")
    public ModelAndView getCurrentTimeInSpecificTimezoneV2(@PathVariable(name = "tz") String tz) {
        ModelAndView result = new ModelAndView("time");

        result.addObject("currentTime", timeService.getCurrentTimeBySpecificTimezone(tz));

        return result;
    }
}
