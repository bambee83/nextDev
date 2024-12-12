package org.example.nexthw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // @CreatedDate와 @LastModifiedDate 활성화
@SpringBootApplication
public class NexthwApplication {

    public static void main(String[] args) {
        ApplicationContext ac =
                SpringApplication.run(NexthwApplication.class, args);

        /*"모든 빈 출력하기"*/
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name=" + beanDefinitionName + " object=" +
                    bean);
        }

        /*"애플리케이션 빈 출력하기"*/
        // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
        // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈

    }
}