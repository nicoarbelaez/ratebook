package com.nicoarbelaez.ratebook.endpoint;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class HelloWord {

    @GetMapping()
    public String getMethodName(@RequestParam(required = false) String text) {
        text = text != null ? "{" + text + "}" : "";
        return "Hola mundo " + text;
    }

}
