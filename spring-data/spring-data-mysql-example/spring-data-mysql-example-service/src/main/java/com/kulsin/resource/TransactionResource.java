package com.kulsin.resource;

import com.kulsin.entity.Transaction;
import com.kulsin.entity.TransactionRequest;
import com.kulsin.model.TransactionRequestProtos;
import com.kulsin.model.TransactionResponseProtos;
import com.kulsin.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.parse;

@RestController
@RequiredArgsConstructor
public class TransactionResource {

    private final TransactionRepository repository;
    private final ConversionService conversionService;

    @PostMapping(value = "/txn/credit")
    public Transaction credit(@RequestBody TransactionRequest request) {
        Transaction txn = new Transaction();
        txn.setUserId(request.getUserId());
        txn.setAmount(request.getAmount());
        txn.setType("credit");
        txn.setTime(LocalDateTime.now());

        return repository.save(txn);
    }

    @PostMapping(value = "/txn/debit")
    public Transaction debit(@RequestBody TransactionRequest request) {
        Transaction txn = new Transaction();
        txn.setUserId(request.getUserId());
        txn.setAmount(request.getAmount());
        txn.setType("debit");
        txn.setTime(LocalDateTime.now());
        return repository.save(txn);
    }

    @GetMapping(value = "/txn/all", produces = {"application/x-protobuf"})
    public ResponseEntity<TransactionResponseProtos.TransactionResponse> all(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "50") int size) {

        validateRequestParam(from, to);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/x-protobuf"));
        if (StringUtils.isBlank(userId)) {
            Page<Transaction> transactions = repository.findAll(PageRequest.of(page, size));

            return new ResponseEntity<>(conversionService.convert(transactions, TransactionResponseProtos.TransactionResponse.class), httpHeaders, HttpStatus.OK);
        } else {
            Page<Transaction> transactions = repository.findByTimeBetweenAndUserId(
                    parse(from),
                    parse(to),
                    userId,
                    PageRequest.of(page, size));

            return new ResponseEntity<>(conversionService.convert(transactions, TransactionResponseProtos.TransactionResponse.class), httpHeaders, HttpStatus.OK);

        }
    }

    @PostMapping(value = "/txn/all/v2", produces = {"application/x-protobuf"})
    public ResponseEntity<TransactionResponseProtos.TransactionResponse> allV2(
            @RequestBody TransactionRequestProtos.TransactionRequest request) {

        validateRequestParam(request.getFrom(), request.getTo());
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/x-protobuf"));
        if (StringUtils.isBlank(request.getUserId())) {
            Page<Transaction> transactions = repository.findAll(PageRequest.of(request.getPage(), request.getSize()));

            return new ResponseEntity<>(conversionService.convert(transactions, TransactionResponseProtos.TransactionResponse.class), httpHeaders, HttpStatus.OK);
        } else {
            Page<Transaction> transactions = repository.findByTimeBetweenAndUserId(
                    parse(request.getFrom()),
                    parse(request.getTo()),
                    request.getUserId(),
                    PageRequest.of(request.getPage(), request.getSize()));

            return new ResponseEntity<>(conversionService.convert(transactions, TransactionResponseProtos.TransactionResponse.class), httpHeaders, HttpStatus.OK);

        }
    }

    void validateRequestParam(String from, String to) {
        try {
            LocalDateTime fromDateTime = parse(from);
            LocalDateTime toDateTime = parse(to);

            if (fromDateTime.isAfter(toDateTime)) {
                throw new IllegalArgumentException("Invalid request! from date can not be after to date");
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Request param validation failed", ex);
        }
    }

}
