package com.kulsin.support.logging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties(prefix = "com.kulsin.logging.http.trace")
public class HttpTraceLogConfigProps {

    /**
     * Flag to opt-out from the http tracing infrastructure.
     */
    private boolean enabled = true;

    /**
     * Set whether the query string should be included in the log message.
     */
    private boolean includeQueryString = true;

    /**
     * Set whether the client address and session id should be included in the
     * log message.
     */
    private boolean includeClientInfo = true;

    /**
     * Set whether the request headers should be included in the log message.
     */
    private boolean includeHeaders = true;

    /**
     * Set whether the request payload (body) should be included in the log message.
     */
    private boolean includePayload = true;

    /**
     * Set the maximum length of the payload body to be included in the log message.
     * Default is 5000 characters.
     */
    private int maxPayloadLength = 5000;

    /**
     * Set the inbound paths to be traced. The default is to trace all paths.
     */
    private List<String> inboundPaths = Collections.singletonList("/**");

    /**
     * Set the outbound paths to be traced. The default is to trace all paths.
     */
    private List<String> outboundPaths = Collections.singletonList("/**");

}
