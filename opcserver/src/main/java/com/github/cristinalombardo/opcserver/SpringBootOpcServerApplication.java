package com.github.cristinalombardo.opcserver;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.github.cristinalombardo.opcserver.secureserver.SecureServer;

@SpringBootApplication
public class SpringBootOpcServerApplication {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//Start Spring Boot Application to create IoT Container
		SpringApplication.run(SpringBootOpcServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			//Execute after IoT creation
			System.out.println("Run after startup");

			
			//Get the OpcServer from IoT container
			OpcUaServer server = (OpcUaServer) ctx.getBean("simple-server");

			//Start OpcUa Server
			server.startup().get(); 
			
			SecureServer secureServer = (SecureServer) ctx.getBean("secure-server");
			
			secureServer.startServer().get();

			
		};
	}



}