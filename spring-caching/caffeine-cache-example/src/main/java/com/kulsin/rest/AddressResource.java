package com.kulsin.rest;

import com.kulsin.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddressResource {

    @Autowired
    AddressService addressService;

    @GetMapping(value = "/customer/address")
    ResponseEntity address(
            @RequestParam(value = "customerId") long customerId
    ) {

        return new ResponseEntity<>(Map.of(customerId, addressService.getAddress(customerId)), HttpStatus.OK);

    }
}
