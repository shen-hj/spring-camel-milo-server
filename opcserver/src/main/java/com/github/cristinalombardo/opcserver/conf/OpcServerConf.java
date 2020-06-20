package com.github.cristinalombardo.opcserver.conf;

import static java.util.Collections.singleton;
import static org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText.english;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfigBuilder;
import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.eclipse.milo.opcua.stack.server.security.ServerCertificateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.cristinalombardo.opcserver.element.CustomNamespace;
import com.github.cristinalombardo.opcserver.secureserver.SecureServer;

@Configuration
public class OpcServerConf {

	private Log logger = LogFactory.getLog(OpcServerConf.class); 

	@Bean("simple-server")
	public OpcUaServer simpleServer(
			@Value("${opc.simple-server.bind-address:localhost}") String bindAddress,
			@Value("${opc.simple-server.bind-port:4850}") Integer bindPort
			) throws InterruptedException, ExecutionException {

		logger.info("Create OPC Server");

		//Define server configuration
		final OpcUaServerConfigBuilder configBuilder = new OpcUaServerConfigBuilder();

		//Allow anonymous login
		configBuilder.setIdentityValidator(AnonymousIdentityValidator.INSTANCE);


		//Define endpoint builder
		final EndpointConfiguration.Builder endpointBuilder = new EndpointConfiguration.Builder();

		//Allow anonymous endpoint
		endpointBuilder.addTokenPolicies(
				OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS
				);

		//Disable security for this endpoint
		endpointBuilder.setSecurityPolicy(SecurityPolicy.None);

		//Binding on all network adapter
		endpointBuilder.setBindAddress(bindAddress);

		//Binding server on specific port
		endpointBuilder.setBindPort(bindPort);

		//Add endpoints to configuration
		configBuilder.setEndpoints(singleton(endpointBuilder.build()));

		//Application name
		configBuilder.setApplicationName(english("Cristina Demo OPC UA Application"));

		//Application uri
		configBuilder.setApplicationUri("urn:com:github:cristinalombardo:opcserver");


		//Disable certification checks
		configBuilder.setCertificateManager(new DefaultCertificateManager());

		//Accept all certificates !!!! LOW SECURITY !!!!
		configBuilder.setCertificateValidator(new ServerCertificateValidator() {

			@Override
			public void validateCertificateChain(List<X509Certificate> certificateChain) throws UaException {
				// Validation rules
			}

			@Override
			public void validateCertificateChain(List<X509Certificate> certificateChain, String applicationUri)
					throws UaException {
				// Validation rules
			}
		});


		//Create server as single istance according Spring Boot rules
		final OpcUaServer server = new OpcUaServer(configBuilder.build());

		//Register namespace
		server.getAddressSpaceManager().register(new CustomNamespace(server, CustomNamespace.URI));

		return server;
	}


	@Bean("secure-server")
	public SecureServer secureServer(
			@Value("${opc.secure-server.bind-address:localhost}") String bindAddress,
			@Value("${opc.secure-server.bind-port:4851}") Integer bindPort
			)  {

		try {
			//Add a secure server into IOC container
			return new SecureServer(bindAddress, bindPort);
		} catch (Exception e) {
			logger.error("Error while crete secure server " + e.getMessage());
			return null;
		}
	}
	
}
