package com.kulsin.resource;

import com.kulsin.entity.Transaction;
import com.kulsin.model.ContentProtos;
import com.kulsin.model.PageableProtos;
import com.kulsin.model.TransactionResponseProtos;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TransactionResponseConverter implements Converter<Page<Transaction>,
        TransactionResponseProtos.TransactionResponse> {

    @Override
    public TransactionResponseProtos.TransactionResponse convert(Page<Transaction> src) {

        var pageable = PageableProtos.Pageable.newBuilder()
                .setPageNumber(src.getNumber())
                .setPageSize(src.getSize())
                .setTotalElements((int) src.getTotalElements())
                .setTotalPages(src.getTotalPages())
                .build();

        var content = src.getContent().stream()
                .map(txn -> ContentProtos.Content.newBuilder()
                        .setAmount(txn.getAmount())
                        .setTime(txn.getTime().toString())
                        .setTxId(txn.getTxId().toString())
                        .setType(txn.getType())
                        .setUserId(txn.getUserId())
                        .build()).collect(Collectors.toList());

        var builder = TransactionResponseProtos.TransactionResponse.newBuilder();
        builder.addAllContent(content);
        builder.setPageable(pageable);
        return builder.build();
    }
}
