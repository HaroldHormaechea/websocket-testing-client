package org.websockettestingclient.testframework;

import org.websockettestingclient.core.stomp.StompWebsocketClient;

import java.io.IOException;

public class ActionSubscribe extends Action {

    private final String topic;

    public ActionSubscribe(String topic) {
        this.topic = topic;
    }


    @Override
    public void execute(StompWebsocketClient client) throws IOException {
        client.subscribeToTopic(topic);
    }
}
