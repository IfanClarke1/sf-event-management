package com.secureflag.service;

public interface IdempotencyService {
    void lock(String key);

    void release(String key);

    String get(String key);
}
