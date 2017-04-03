package org.sunkengrotto.wildsocketclient.echo;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoClient extends Endpoint {
	private static final Logger logger = LoggerFactory.getLogger(EchoClient.class);
	private Handler handler;

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		logger.info("Opening connection.");
		handler = new Handler();
		session.addMessageHandler(String.class, handler);
	}

	@Override
	public void onClose(Session session, CloseReason closeReason) {
		super.onClose(session, closeReason);
		handler = null;
		logger.info("Connection closed.");
	}

	@Override
	public void onError(Session session, Throwable throwable) {
		super.onError(session, throwable);
		handler = null;
		logger.error("Error!", throwable);
	}

	private static class Handler implements MessageHandler.Whole<String> {

		@Override
		public void onMessage(String message) {
			System.out.println(message);
		}

	}

}
