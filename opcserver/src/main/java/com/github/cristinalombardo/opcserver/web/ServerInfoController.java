package com.github.cristinalombardo.opcserver.web;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerInfoController {
	
	@Autowired
	@Qualifier("simple-server")
	private OpcUaServer server;
	
	@GetMapping("/")
	public ResponseEntity<String> getServerStatus() {
		return new ResponseEntity<String>(server.getDiagnosticsSummary().toString() + " Server is running", HttpStatus.OK);
	}

}
