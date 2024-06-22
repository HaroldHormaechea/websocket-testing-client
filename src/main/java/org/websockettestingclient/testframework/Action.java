package org.websockettestingclient.testframework;

import org.websockettestingclient.core.stomp.StompWebsocketClient;

import java.io.IOException;

public abstract class Action extends BasePredicate {

    public abstract void execute(StompWebsocketClient client) throws IOException;

}
