package com.kulsin.support.logging;

import com.kulsin.support.logging.model.HttpTraceEntry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class LoggingOutboundHttpTraceListener implements OutboundHttpTraceListener {

    private final HttpTraceEntryStringRenderer httpTraceEntryStringRenderer;

    private static final Logger logger = LoggerFactory.getLogger(LoggingOutboundHttpTraceListener.class);

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void afterResponseReceived(HttpTraceEntry traceEntry) {
        logger.info("After outgoing request [" + httpTraceEntryStringRenderer.renderEntry(traceEntry) + "]");

    }

}
