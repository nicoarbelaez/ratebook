package com.nicoarbelaez.ratebook;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hello")
@CrossOrigin(origins = "http://localhost:3000")
public class HelloWord {

    @GetMapping()
    public String getMethodName() {

        return "Hello world | Hola mundo";
    }
    
}
