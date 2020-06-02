package com.github.cristinalombardo.opcserver;

import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootOpcServerApplication {


	public static void main(String[] args) throws InterruptedException, ExecutionException {

		SpringApplication.run(SpringBootOpcServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Run after startup");

			OpcUaServer server = (OpcUaServer) ctx.getBean("my-server");

			server.startup().get(); 

			while(true)
				Thread.sleep(1000);
		};
	}



}
