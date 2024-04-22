package com.kulsin.resource;

import com.kulsin.config.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController {

    @GetMapping("/bar")
    public String barController(@AuthenticationPrincipal UserPrincipal principal) {
        return String.format("Hej %s!", principal.getName());
    }

}
