package com.kulsin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequiredArgsConstructor
public class EchoController {

    private final EchoService echoService;

    @GetMapping("/echo/{message}")
    public CompletionStage<String> echo(@PathVariable String message) {
        return echoService.echo(message);
    }

}
