package com.kulsin.resource;

import com.kulsin.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @Autowired
    private DummyService service;

    @GetMapping("/foo")
    public String getFoo() {
        return service.printMessage("Hej Foo!");
    }

}
