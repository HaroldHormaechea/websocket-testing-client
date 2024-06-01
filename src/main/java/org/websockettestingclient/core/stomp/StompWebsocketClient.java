package org.websockettestingclient.core.stomp;

import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.websockettestingclient.core.PlainWebsocketClient;
import org.websockettestingclient.core.stomp.handlers.InboundStompMessageHandler;
import org.websockettestingclient.core.stomp.model.StompServerFrame;
import org.websockettestingclient.core.stomp.serialization.ServerFrameDeserializer;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@ClientEndpoint
public class StompWebsocketClient extends PlainWebsocketClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ServerFrameDeserializer deserializer;
    private final Set<InboundStompMessageHandler> inboundStompMessageHandlerList = new HashSet<>();

    public StompWebsocketClient(URI targetServerRegistrationEndpoint) {
        super(targetServerRegistrationEndpoint);
        deserializer = new ServerFrameDeserializer();
    }

    public StompWebsocketClient(URI targetServerRegistrationEndpoint,
                                ServerFrameDeserializer deserializer) {
        super(targetServerRegistrationEndpoint);
        this.deserializer = deserializer;
    }

    public void registerInboundMessageHandler(InboundStompMessageHandler newHandler){
        inboundStompMessageHandlerList.add(newHandler);
    }

    public void deregisterInboundMessageHandler(InboundStompMessageHandler newHandler){
        inboundStompMessageHandlerList.remove(newHandler);
    }

    @Override
    public void connect() throws IOException {
        super.connect();
        stompConnect();
    }

    protected void stompConnect() throws IOException {
        userSession.getBasicRemote().sendText(
                """
                        CONNECT
                        accept-version:1.2,1.1,1.0
                        heart-beat:10000,10000
                                                
                        \u0000""");
    }

    @Override
    public void onMessage(String msg) {
        StompServerFrame stompServerFrame = deserializer.deserialize(msg);
        inboundStompMessageHandlerList.forEach(handler -> {
            try {
                handler.onMessage(stompServerFrame);
            } catch (Exception ex) {
                logger.error("Error handling inbound message", ex);
            }
        });
    }

    /**
     * @param topicName The topic to subscribe to, as named by the server.
     * @return The subscription identifier, so future messages from the server can be linked to the source subscription,
     * and the subscription can be unsubscribed later.
     * @throws IOException If the message couldn't be sent for any reason.
     */
    public UUID subscribeToTopic(String topicName) throws IOException {
        UUID subscriptionId = UUID.randomUUID();
        super.sendMessage(
                """
                        SUBSCRIBE
                        id:%s
                        destination:%s

                        \u0000"""
                        .formatted(subscriptionId, topicName)
        );
        return subscriptionId;
    }

    public void unsubscribeFromTopic(UUID subscriptionId) throws IOException {
        super.sendMessage(
                """
                        UNSUBSCRIBE
                        id:%s

                        \u0000"""
                        .formatted(subscriptionId)
        );
    }

    public void sendMessage(String message, String destination) throws IOException {
        super.sendMessage(
                """
                        SEND
                        destination:%s
                        content-type:application/json

                        %s
                        \u0000"""
                        .formatted(destination, message)
        );
    }

    @OnOpen
    @Override
    protected void onOpen(Session session) {
        super.onOpen(session);
    }

    @OnClose
    @Override
    protected void onClose(Session session, CloseReason closeReason) {
        super.onClose(session, closeReason);
    }
}
