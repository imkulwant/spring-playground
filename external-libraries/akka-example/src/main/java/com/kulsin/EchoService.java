package com.kulsin;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

@Service
public class EchoService {

    private final ActorRef echoActor;

    @Autowired
    public EchoService(ActorSystem actorSystem) {
        this.echoActor = actorSystem.actorOf(Props.create(EchoActor.class), "echoActor");
    }

    /**
     * Sends a message to the EchoActor and returns the echoed message.
     *
     * @param message the message to echo
     * @return a CompletionStage containing the echoed message
     */
    public CompletionStage<String> echo(String message) {
        return Patterns.ask(echoActor, message, Duration.ofSeconds(5))
                .thenApply(response -> (String) response)
                .toCompletableFuture();
    }
}
