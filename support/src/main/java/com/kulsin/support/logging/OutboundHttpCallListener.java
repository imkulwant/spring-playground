package com.kulsin.support.logging;

import com.kulsin.support.logging.model.HttpTransaction;

/**
 * Listener interface for tracing outbound HTTP requests and responses.
 * Implementations of this interface can be used to log, monitor, or analyze
 * outgoing HTTP traffic.
 */
public interface OutboundHttpCallListener {

    /**
     * Checks if this listener is enabled.
     *
     * @return true if the listener is enabled, false otherwise
     */
    boolean isEnabled();

    /**
     * Called after a response is received for an outgoing HTTP request.
     *
     * @param traceEntry An object containing details about the HTTP request and response
     */
    void afterResponseReceived(HttpTransaction traceEntry);

}