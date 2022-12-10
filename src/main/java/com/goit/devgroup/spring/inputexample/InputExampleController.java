package com.goit.devgroup.spring.inputexample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping("/input-example")
@Controller
public class InputExampleController {
    @GetMapping
    public ModelAndView get() {
        return new ModelAndView("input-example");
    }

    @PostMapping
    public ModelAndView post(@RequestParam("example-text") String exampleInput) {
        ModelAndView result = new ModelAndView("input-example");
        result.addObject("exampleInput", exampleInput);
        return result;
    }
}
