package io.github.fabriciobedin.viagens.ws;

import javax.xml.ws.Endpoint;

public class PublicaWS {

	public static void main(String[] args) {
		ViagemWS ws = new ViagemWS();
		String url = "http://localhost:8080/viagens";
		Endpoint.publish(url, ws);

	}

}
