package org.example.currency.service;

public interface BlackListService {
    void addToBlacklist(String token);

    boolean isBlacklisted(String token);
}
