package com.kulsin.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionResourceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void allTest() {
        Object response = testRestTemplate.getForObject(
                "/txn/all?from=2022-10-15T00:00:00&to=2022-11-15T00:00:00&pageNumber=0&pageSize=2&userId=123",
                Object.class);

        assertNotNull(response);
    }

    @Test
    void allInvalidRequest() {
        Object response = testRestTemplate.getForObject(
                "/txn/all?to=2022-11-15T00:00:00&pageNumber=0&pageSize=2&userId=123",
                Object.class);

        assertNotNull(response);
        //assertTrue(((LinkedHashMap<?, ?>) response).containsValue("Bad Request"));
    }

}
