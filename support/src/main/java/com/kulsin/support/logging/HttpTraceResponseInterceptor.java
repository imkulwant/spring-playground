package com.kulsin.support.logging;

import com.kulsin.support.logging.model.HttpTraceEntry;
import com.kulsin.support.logging.model.Request;
import com.kulsin.support.logging.model.Response;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpEntityContainer;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpResponseInterceptor;
import org.apache.hc.core5.http.io.entity.BufferedHttpEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.http.protocol.HttpCoreContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

@AllArgsConstructor
public class HttpTraceResponseInterceptor implements HttpResponseInterceptor {

    private final HttpTraceLogConfigProps configProps;

    private final LoggingOutboundHttpTraceListener outboundHttpTraceListener;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public void process(HttpResponse response, EntityDetails entityDetails, HttpContext context) throws HttpException, IOException {

        if (outboundHttpTraceListener.isEnabled()) {
            HttpRequest request = (HttpRequest) context.getAttribute(HttpCoreContext.HTTP_REQUEST);

            if (uriNotIncluded(request.getRequestUri())) return;

            Object requestPayload = null;
            Object responsePayload = null;

            HttpHeaders requestHeaders = convertHeaders(request.getHeaders());
            HttpHeaders responseHeaders = convertHeaders(response.getHeaders());

            if (configProps.isIncludePayload()) {
                if (request instanceof HttpEntityContainer requestEntityContainer) {
                    requestPayload = readEntity(requestEntityContainer.getEntity(), requestHeaders);
                }

                if (response instanceof HttpEntityContainer responseEntityContainer) {
                    if (responseEntityContainer.getEntity() != null && !responseEntityContainer.getEntity().isRepeatable()) {
                        responseEntityContainer.setEntity(new BufferedHttpEntity(responseEntityContainer.getEntity()));
                    }
                    responsePayload = readEntity(responseEntityContainer.getEntity(), responseHeaders);
                }
            }

            URI requestUri = URI.create(request.getRequestUri());

            HttpTraceEntry entry = HttpTraceEntry.builder()
                    .request(Request.builder()
                            .httpMethod(HttpMethod.valueOf(request.getMethod()))
                            .uri(requestUri.getPath())
                            .queryString(requestUri.getQuery())
                            .clientInfo(null) // Not applicable, we are the client
                            .headers(requestHeaders)
                            .body(requestPayload)
                            .build())
                    .response(Response.builder()
                            .status(HttpStatus.valueOf(response.getCode()))
                            .headers(responseHeaders)
                            .body(responsePayload)
                            .build())
                    .build();

            outboundHttpTraceListener.afterResponseReceived(entry);
        }

    }

    private boolean uriNotIncluded(String requestURI) {
        return configProps.getOutboundPaths().stream().noneMatch(pattern -> antPathMatcher.match(pattern, requestURI));
    }

    Object readEntity(HttpEntity httpEntity, HttpHeaders httpHeaders) throws IOException {
        if (httpEntity != null && httpEntity.isRepeatable()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            httpEntity.writeTo(baos);
            return baos.toString();
        }
        return null;
    }

    HttpHeaders convertHeaders(Header[] headers) {
        HttpHeaders result = new HttpHeaders();
        if (configProps.isIncludeHeaders()) {
            for (Header header : headers) {
                result.add(header.getName(), header.getValue());
            }
        }
        return result;
    }
}
