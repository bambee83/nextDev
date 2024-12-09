package org.example.nexthw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // @CreatedDate와 @LastModifiedDate 활성화
@SpringBootApplication
public class NexthwApplication {
    public static void main(String[] args) {
        SpringApplication.run(NexthwApplication.class, args);
    }

}
