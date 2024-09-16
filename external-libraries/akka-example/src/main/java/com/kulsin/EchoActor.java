package com.kulsin;

import akka.actor.AbstractActor;

public class EchoActor extends AbstractActor {

    /**
     * EchoActor is an Akka actor that echoes received messages.
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class,
                message -> {
                    getSender().tell("Echo: " + message, getSelf());
                }).build();
    }

}
