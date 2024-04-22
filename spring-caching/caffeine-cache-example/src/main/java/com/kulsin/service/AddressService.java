package com.kulsin.service;

import com.kulsin.model.Address;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AddressService {

    @Cacheable(value = "addressCache", unless = "#customerId < 1000")
    public Address getAddress(long customerId) {
        return new Address(
                randomString(),
                randomString(),
                randomString(),
                randomInt()
        );
    }


    private int randomInt() {
        Random random = new Random();
        return random.ints(1000, 9000)
                .findFirst()
                .getAsInt();
    }

    private String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

}
