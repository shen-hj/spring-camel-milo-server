package com.github.cristinalombardo.opcserver.conf;

import static java.util.Collections.singleton;
import static org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText.english;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfigBuilder;
import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.CompositeValidator;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.cristinalombardo.opcserver.element.CustomNamespace;

@Configuration
public class OpcServerConf {

	
	@Bean("my-server")
	public OpcUaServer opcServer() throws InterruptedException, ExecutionException {

		System.out.println("Start OPC Server");
		final OpcUaServerConfigBuilder builder = new OpcUaServerConfigBuilder();

		builder.setIdentityValidator(new CompositeValidator(
				AnonymousIdentityValidator.INSTANCE // You should better ask who knocked, right?
				));

		final EndpointConfiguration.Builder endpointBuilder = new EndpointConfiguration.Builder();

		endpointBuilder.addTokenPolicies(
				OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS // You wouldn't leave you door open, would you?
				);

		endpointBuilder.setSecurityPolicy(SecurityPolicy.None); // ... or give everyone access to your fridge ...

		endpointBuilder.setBindAddress("0.0.0.0");
		endpointBuilder.setBindPort(4840);
		
		
		builder.setEndpoints(singleton(endpointBuilder.build()));

		builder.setApplicationName(english("Foo Bar Server"));
		builder.setApplicationUri("urn:my:example");
		
		builder.setCertificateManager(new DefaultCertificateManager()); // ... don't to this at home! ...

		builder.setCertificateValidator(new CertificateValidator() {

			@Override
			public void validate(final X509Certificate certificate) throws UaException {
				// ... ever! ...
			}

			@Override
			public void verifyTrustChain(final List<X509Certificate> certificateChain) throws UaException {
				// ... I mean it!
			}
		});

		final OpcUaServer server = new OpcUaServer(builder.build());

		// register namespace

		server.getAddressSpaceManager().register(new CustomNamespace(server, CustomNamespace.URI));

		// start it up

		
		return server;
	}
}
