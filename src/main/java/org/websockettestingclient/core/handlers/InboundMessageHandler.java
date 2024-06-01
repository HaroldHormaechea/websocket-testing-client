package org.websockettestingclient.core.handlers;

public interface InboundMessageHandler<T> {

    void onMessage(T message);
}
