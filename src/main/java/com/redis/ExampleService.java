package com.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @Cacheable(value = "items")
    public String getItemById(String id) {
        simulateSlowService();
        return "Item " + id;
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}