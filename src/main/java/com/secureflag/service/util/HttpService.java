package com.secureflag.service.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
public interface HttpService {
    <T> ResponseEntity<T> get(UriComponentsBuilder builder,
                              HttpHeaders httpHeaders,
                              Class<T> responseType);
}
