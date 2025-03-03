package org.example.currency.service.implementation;

import lombok.Getter;
import org.example.currency.service.BlackListService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Getter
public class BlackListServiceImpl implements BlackListService {
    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

    @Override
    public void addToBlacklist(String token) {
        blacklistedTokens.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
