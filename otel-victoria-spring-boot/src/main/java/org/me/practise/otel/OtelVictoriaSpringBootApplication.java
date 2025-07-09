package org.me.practise.otel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableScheduling
//@EnableSchedulerLock ( defaultLockAtMostFor = "PT30S" ) // Default lock at most 30 seconds
public class OtelVictoriaSpringBootApplication {

    public static void main (String[] args) {
        SpringApplication.run (OtelVictoriaSpringBootApplication.class, args);
    }

}
