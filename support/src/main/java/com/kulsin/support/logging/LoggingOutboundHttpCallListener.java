package com.kulsin.support.logging;

import com.kulsin.support.logging.model.HttpTransaction;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class LoggingOutboundHttpCallListener implements OutboundHttpCallListener {

    private final HttpTransactionLogGenerator httpTransactionLogGenerator;

    private static final Logger logger = LoggerFactory.getLogger(LoggingOutboundHttpCallListener.class);

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void afterResponseReceived(HttpTransaction traceEntry) {
        logger.info("After outgoing request [" + httpTransactionLogGenerator.generateLog(traceEntry) + "]");
    }

}
