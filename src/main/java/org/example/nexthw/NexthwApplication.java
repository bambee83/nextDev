package org.example.nexthw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NexthwApplication {
    public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(NexthwApplication.class);
//        application.setAdditionalProfiles("multi"); // 멀티 파일에서 원하는 프로파일 활성화
//        System.setProperty("spring.config.location", "classpath:/application-multi.yml");

        System.out.println("==========================================");
        System.out.println("Active Profile: " + System.getProperty("spring.profiles.active"));
        System.out.println("==========================================");


        SpringApplication.run(NexthwApplication.class, args);
    }

}
