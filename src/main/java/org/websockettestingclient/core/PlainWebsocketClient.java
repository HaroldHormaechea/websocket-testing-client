package org.websockettestingclient.core;


import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class PlainWebsocketClient implements MessageHandler.Whole<String>{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final URI targetServerRegistrationEndpoint;

    protected final WebSocketContainer container;
    protected Session userSession = null;

    public PlainWebsocketClient(URI targetServerRegistrationEndpoint) {
        this.targetServerRegistrationEndpoint = targetServerRegistrationEndpoint;
        container = ContainerProvider.getWebSocketContainer();
    }

    public void connect() throws IOException {
        try {
            userSession = container.connectToServer(
                    this,
                    targetServerRegistrationEndpoint
            );
            userSession.addMessageHandler(this);
        } catch (Exception e) {
            logger.error("Error creating websocket container", e);
            throw new IOException("Error creating websocket container", e);
        }
    }

    public void sendMessage(String message) throws IOException {
        userSession.getBasicRemote().sendText(message);
    }

    @OnOpen
    protected void onOpen(Session session) {
        logger.info("Connected");
    }

    @OnClose
    protected void onClose(Session session, CloseReason closeReason) {
    }

    protected void disconnect() throws IOException {
        userSession.close();
    }

    @Override
    public void onMessage(String message) {

    }
}
