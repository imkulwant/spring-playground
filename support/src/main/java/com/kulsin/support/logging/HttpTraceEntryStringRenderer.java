package com.kulsin.support.logging;

import com.kulsin.support.logging.model.HttpTraceEntry;
import com.kulsin.support.logging.model.Request;
import com.kulsin.support.logging.model.Response;
import org.springframework.http.HttpHeaders;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HttpTraceEntryStringRenderer {
    private final static String INDENT = "     ";
    private final static String NEWLINE = "\n";

    private final HttpTraceLogConfigProps configProps;

    public HttpTraceEntryStringRenderer(HttpTraceLogConfigProps configProps) {
        this.configProps = Objects.requireNonNull(configProps);
    }

    public String renderEntry(HttpTraceEntry traceEntry) {
        StringBuilder sb = new StringBuilder();
        {
            Request request = traceEntry.getRequest();
            {
                sb.append("\n----------------------------REQUEST---------------------------\n");
                sb.append("Method=").append(request.getHttpMethod()).append(NEWLINE);
                sb.append("URI=").append(request.getUri()).append(NEWLINE);
            }
            if (configProps.isIncludeQueryString()) {
                sb.append("QueryString=").append(request.getQueryString()).append(NEWLINE);
            }
            if (configProps.isIncludeClientInfo()) {
                sb.append("ClientInfo=").append(request.getClientInfo()).append(NEWLINE);
            }
            if (configProps.isIncludeHeaders()) {
                sb.append("RequestHeaders:").append(renderHeaders(request.getHeaders())).append(NEWLINE);
            }
            if (configProps.isIncludePayload()) {
                sb.append("RequestBody:");
                appendBody(sb, request.getBody());
            }
        }
        {
            Response response = traceEntry.getResponse();
            {
                sb.append("----------------------------RESPONSE---------------------------\n");
                sb.append("Status=").append(response.getStatus()).append(NEWLINE);
            }
            if (configProps.isIncludeHeaders()) {
                sb.append("ResponseHeaders:").append(renderHeaders(response.getHeaders())).append(NEWLINE);
            }
            if (configProps.isIncludePayload()) {
                sb.append("ResponseBody:");
                appendBody(sb, response.getBody());
            }
        }
        return sb.toString();
    }

    private void appendBody(StringBuilder sb, Object body) {
        String responseBody = Objects.toString(body);
        if (responseBody != null && responseBody.length() > configProps.getMaxPayloadLength()) {
            responseBody = responseBody.substring(0, configProps.getMaxPayloadLength());
        }
        if (body != null && responseBody != null && responseBody.contains(NEWLINE) && !responseBody.startsWith(NEWLINE)) {
            sb.append(NEWLINE);
        }
        sb.append(responseBody);
        if (responseBody == null || !responseBody.endsWith(NEWLINE)) {
            sb.append(NEWLINE);
        }
    }

    private String renderHeaders(HttpHeaders headers) {
        if (headers == null) {
            return "null";
        }
        if (headers.isEmpty()) {
            return "";
        }
        return headers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Object::toString)
                .collect(Collectors.joining(NEWLINE + INDENT, NEWLINE + INDENT, ""));
    }
}
