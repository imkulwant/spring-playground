package com.kulsin;

import com.kulsin.model.TransactionRequestProtos;
import com.kulsin.model.TransactionResponseProtos;
import feign.Param;
import feign.RequestLine;

import javax.validation.constraints.NotNull;

public interface TransactionFeignClient {

    @RequestLine(value = "GET /txn/all?from={from}&to={to}&userId={userId}&page={page}&size={size}")
    TransactionResponseProtos.TransactionResponse all(
            @Param("from") String from,
            @Param("to") String to,
            @Param("userId") String userId,
            @Param("page") int page,
            @Param("size") int size
    );

    @RequestLine(value = "POST /txn/all/v2")
    TransactionResponseProtos.TransactionResponse allV2(
           @NotNull TransactionRequestProtos.TransactionRequest request);

}
