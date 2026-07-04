package com.kulsin.support.logging.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Value
@Builder(toBuilder = true)
public class Response {

    /**
     * The HTTP status of the response.
     */
    @NonNull
    HttpStatus status;

    /**
     * The headers of the response.
     */
    @NonNull
    HttpHeaders headers;

    /**
     * The body of the response.
     */
    Object body;

}
