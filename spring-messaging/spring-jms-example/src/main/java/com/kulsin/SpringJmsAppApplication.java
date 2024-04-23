package com.kulsin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class SpringJmsAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringJmsAppApplication.class, args);
        /*
        ConfigurableApplicationContext context = SpringApplication.run(SpringJmsAppApplication.class, args);
        Sender sender = context.getBean(Sender.class);
        sender.sendMessage("order-queue", "Hello");
        */
    }

    /*@Bean
    public JmsListenerContainerFactory warehouseFactory(ConnectionFactory factory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        configurer.configure(containerFactory, factory);
        return containerFactory;
    }*/
}
