package com.cropdeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients(basePackages="com.cropdeal.feignclient")
public class CropDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropDetailsApplication.class, args);
	}

}
