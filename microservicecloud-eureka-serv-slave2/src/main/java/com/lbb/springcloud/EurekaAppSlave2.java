package com.lbb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaAppSlave2 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAppSlave2.class, args);
    }
}
