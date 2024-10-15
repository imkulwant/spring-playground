package com.kulsin.support.logging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Instant;

/**
 * Represents a trace entry for an HTTP request-response cycle.
 * This class encapsulates both the request and response details.
 */
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class HttpTransaction {

    /**
     * The HTTP request details.
     */
    @NonNull
    Request request;

    /**
     * The HTTP response details.
     */
    @NonNull
    Response response;

    /**
     * The error encountered during the request lifecycle, if any.
     */
    private final Throwable error;

    /**
     * The timestamp when the request was sent.
     */
    private final Instant requestTimestamp;

    /**
     * The timestamp when the response was received.
     */
    private final Instant responseTimestamp;

}