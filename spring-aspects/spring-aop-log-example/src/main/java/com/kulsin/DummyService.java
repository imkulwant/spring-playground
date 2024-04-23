package com.kulsin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

    public static final Logger LOG = LoggerFactory.getLogger(DummyService.class);

    public String printMessage(String msg) {
        LOG.info(msg);
        return msg;
    }

}
