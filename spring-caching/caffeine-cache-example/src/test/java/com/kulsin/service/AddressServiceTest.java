package com.kulsin.service;

import com.kulsin.CaffeineCacheExampleApplication;
import com.kulsin.model.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CaffeineCacheExampleApplication.class)
class AddressServiceTest {

    @Autowired
    AddressService addressService;

    @Autowired
    CacheManager cacheManager;

    @Test
    void givenCustomerShouldBeCached_thenResultShouldBePutInCache() {
        Optional<Address> actualAddress = ofNullable(addressService.getAddress(123465L));
        Optional<Address> cachedAddress = getCachedAddress(123465L);
        assertEquals(cachedAddress, actualAddress);
    }

    @Test
    void givenCustomerThatShouldNotBeCached_thenResultShouldNotBePutInCache() {
        addressService.getAddress(0L);
        Optional<Address> cachedAddress = getCachedAddress(0L);

        assertEquals(empty(), cachedAddress);
    }

    private Optional<Address> getCachedAddress(Long customerId) {
        return ofNullable(cacheManager.getCache("addressCache"))
                .map(c -> c.get(customerId, Address.class));
    }

}