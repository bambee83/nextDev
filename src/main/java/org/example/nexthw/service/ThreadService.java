package org.example.nexthw.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Slf4j
@Service
public class ThreadService {

    private final Executor executor;

    public ThreadService(@Qualifier("taskExecutor") Executor executor) {
        this.executor = executor;
    }

    public String executeThreads() {
        StringBuilder threadNames = new StringBuilder();
        Runnable runnable = () -> {
            try {
                log.info("Executing Thread Name: [{}]", Thread.currentThread().getName());
                Thread.sleep(1000); // 1초간 정지
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(runnable);
        }
        return "null";
    }
}
