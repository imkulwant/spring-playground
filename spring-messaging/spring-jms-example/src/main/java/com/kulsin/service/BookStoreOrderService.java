package com.kulsin.service;

import com.kulsin.model.BookOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookStoreOrderService {

    @Autowired
    JmsTemplate jmsTemplate;

    private static final String BOOK_QUEUE = "book-order-queue";

    public void send(BookOrder bookOrder) {
        jmsTemplate.convertAndSend(BOOK_QUEUE, bookOrder);
    }

}
