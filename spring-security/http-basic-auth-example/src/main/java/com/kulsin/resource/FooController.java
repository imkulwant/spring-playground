package com.kulsin.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @GetMapping("/foo")
    public String fooController() {
        return "Hej Foo!";
    }

}
