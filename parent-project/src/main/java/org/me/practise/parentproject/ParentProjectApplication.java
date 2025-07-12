package org.me.practise.parentproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.me.practise.parentproject.client")
public class ParentProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run ( ParentProjectApplication.class, args );
    }

}
