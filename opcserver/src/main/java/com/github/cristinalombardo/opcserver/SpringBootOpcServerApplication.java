package com.github.cristinalombardo.opcserver;

import java.util.concurrent.CompletableFuture;
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
		
		//Start Spring Boot Application to create IoT Container
		SpringApplication.run(SpringBootOpcServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			//Execute after IoT creation
			System.out.println("Run after startup");

			
			//Get the OpcServer from IoT container
			OpcUaServer server = (OpcUaServer) ctx.getBean(OpcUaServer.class);

			//Start OpcUa Server
			server.startup().get(); 

			
			//Persist in execution
			final CompletableFuture<Void> future = new CompletableFuture<>();
	        Runtime.getRuntime().addShutdownHook(new Thread(() -> future.complete(null)));
	        future.get();
		};
	}



}