package org.example.devops_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class DevopsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsServerApplication.class, args);
	}

}
