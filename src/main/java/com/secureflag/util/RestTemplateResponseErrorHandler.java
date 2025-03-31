package com.secureflag.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ServerErrorException;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(final ClientHttpResponse httpResponse) throws IOException {
        log.error("Response error: {} {}", httpResponse.getStatusCode(), httpResponse.getStatusText());
        return (httpResponse.getStatusCode().is4xxClientError()
                || httpResponse.getStatusCode().is5xxServerError());
    }

//    @Override
//    public void handleError(final ClientHttpResponse httpResponse) throws IOException {
//        if (httpResponse.getStatusCode().is5xxServerError()) {
//            throw new ServerErrorException("Unable to connect to vendors server");
//        }
//    }
}
