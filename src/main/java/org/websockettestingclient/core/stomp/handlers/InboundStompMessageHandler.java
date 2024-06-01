package org.websockettestingclient.core.stomp.handlers;

import org.websockettestingclient.core.handlers.InboundMessageHandler;
import org.websockettestingclient.core.stomp.model.StompServerFrame;

public interface InboundStompMessageHandler extends InboundMessageHandler<StompServerFrame> {
}
