package com.kulsin.support.logging.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Value
@Builder(toBuilder = true)
public class Request {

    /**
     * The HTTP method of the request.
     */
    @NonNull
    HttpMethod httpMethod;

    /**
     * The URI of the request.
     */
    String uri;

    /**
     * The query string of the request, if any.
     */
    String queryString;

    /**
     * Information about the client making the request.
     */
    String clientInfo;

    /**
     * The headers of the request.
     */
    HttpHeaders headers;

    /**
     * The body of the request.
     */
    Object body;

}