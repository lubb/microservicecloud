package com.lbb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaAppSlave1 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAppSlave1.class, args);
    }
}
