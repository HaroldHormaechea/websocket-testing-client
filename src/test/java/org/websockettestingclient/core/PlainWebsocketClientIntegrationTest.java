package org.websockettestingclient.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

class PlainWebsocketClientIntegrationTest {

    private static final String testRegistrationEndpoint = "ws://localhost:8080/websocket-registry-endpoint";

    @Test
    public void testConnection() {
        try {
            PlainWebsocketClient client = new PlainWebsocketClient(new URI(testRegistrationEndpoint));
            Assertions.assertDoesNotThrow(client::connect);
            Assertions.assertDoesNotThrow(() -> client.sendMessage("test message"));

        } catch (URISyntaxException e) {
            Assertions.fail("Could not connect to endpoint: Invalid test URI");
        }
    }

}