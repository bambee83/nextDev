package org.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "threadpool")
public class ThreadPoolProperties {
    private int corePoolSize; // 스레드 풀에 속한 기본 스레드 갯수 (default : 1)
    private int maxPoolSize; // 이벤트 대기 큐 크기 (default : Integer.MAX_VALUE (약 21억))
    private int queueCapacity; // 최대 스레드 갯수 (default : Integer.MAX_VALUE (약 21억))

    private boolean waitForTasksToCompleteOnShutdown; // true 설정시 어플리케이션 종료 요청시 queue에 남아 있는 모든 작업들이 완료될 때까지 기다린 후 종료
    private int awaitTerminationSeconds; // 최대 종료 대기 시간
    private String threadNamePrefix;
}