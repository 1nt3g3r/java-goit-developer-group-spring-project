package com.goit.devgroup.spring.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/file")
@Controller
public class FileController {
    private List<String> uploadedFiles = new ArrayList<>();

    @GetMapping
    public ModelAndView get() {
        ModelAndView result = new ModelAndView("file");
        result.addObject("files", uploadedFiles);
        return result;
    }

    @PostMapping
    public String post(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        byte[] bytes = file.getBytes();

        uploadedFiles.add(filename + " (" + bytes.length + " bytes)");

        return "redirect:/file";
    }
}
