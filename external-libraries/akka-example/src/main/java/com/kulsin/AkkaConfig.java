package com.kulsin;

import akka.actor.ActorSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    /**
     * Creates and returns the ActorSystem bean.
     *
     * @return the ActorSystem
     */
    @Bean
    public ActorSystem actorSystem() {
        return ActorSystem.create("kulsin-example");
    }

}
