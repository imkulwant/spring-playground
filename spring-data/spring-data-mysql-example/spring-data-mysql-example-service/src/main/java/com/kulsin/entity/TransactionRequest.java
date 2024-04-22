package com.kulsin.entity;

import lombok.Data;

@Data
public class TransactionRequest {

    private String userId;
    private String amount;

}
