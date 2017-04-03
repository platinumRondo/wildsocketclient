package org.sunkengrotto.wildsocketclient.echo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class EchoMain {
	public static void main(String[] args) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();

		ClientEndpointConfig clientEndpointConfiguration = ClientEndpointConfig.Builder.create().build();

		try (Scanner scanner = new Scanner(System.in)) {
			URI path = new URI("ws://localhost:8080/wildsocket/echo");
			Session session = container.connectToServer(EchoClient.class, clientEndpointConfiguration, path);
			while (session.isOpen()) {
				String input = scanner.nextLine();
				if (input == null || input.length() == 0) {
					session.close();
				} else {
					session.getBasicRemote().sendText(input);
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (DeploymentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
