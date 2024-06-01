package org.websockettestingclient.core.stomp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.websockettestingclient.core.stomp.handlers.InboundStompMessageHandler;
import org.websockettestingclient.core.stomp.model.StompServerFrameType;
import org.websockettestingclient.core.stomp.model.StompServerMessageFrame;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

class StompWebsocketClientIntegrationTest {

    private static final String testRegistrationEndpoint = "ws://localhost:8080/websocket-registry-endpoint";

    @Test
    public void testConnection() {
        try {
            StompWebsocketClient client = new StompWebsocketClient(new URI(testRegistrationEndpoint));
            Assertions.assertDoesNotThrow(client::connect);

        } catch (URISyntaxException e) {
            Assertions.fail("Could not connect to endpoint: Invalid test URI");
        }
    }

    @Test
    public void testSubscriptionPublishAndReceiptFlow() throws InterruptedException {
        try {
            StompWebsocketClient client = new StompWebsocketClient(new URI(testRegistrationEndpoint));
            client.connect();

            InboundStompMessageHandler inboundStompMessageHandler = Mockito.mock(InboundStompMessageHandler.class);
            client.registerInboundMessageHandler(inboundStompMessageHandler);

            client.subscribeToTopic("/topic/test-topic-1");
            String outboundMessage = UUID.randomUUID().toString();
            client.sendMessage(outboundMessage, "/topic/test-topic-1");

            ArgumentCaptor<StompServerMessageFrame> messageFrameArgumentCaptor = ArgumentCaptor.forClass(StompServerMessageFrame.class);

            Thread.sleep(1000L); // Really ugly, just to give some time for the server to issue back a response
            Mockito.verify(inboundStompMessageHandler).onMessage(messageFrameArgumentCaptor.capture());

            Assertions.assertTrue(messageFrameArgumentCaptor.getValue().getContent().contains(outboundMessage));
            Assertions.assertInstanceOf(StompServerMessageFrame.class, messageFrameArgumentCaptor.getValue());
            Assertions.assertEquals(StompServerFrameType.MESSAGE, messageFrameArgumentCaptor.getValue().getType());

        } catch (URISyntaxException | IOException e) {
            Assertions.fail("Could not run operation", e);
        }
    }
}