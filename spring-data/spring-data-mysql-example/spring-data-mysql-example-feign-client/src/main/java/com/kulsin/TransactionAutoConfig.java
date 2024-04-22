package com.kulsin;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        TransactionAutoConfig.TransactionClientBean.class
})
public class TransactionAutoConfig {

    @FeignClient(name = "transaction", contextId = "transaction-context", path = "txn")
    interface TransactionClientBean extends TransactionFeignClient {
    }

}
