package org.websockettestingclient.core.stomp.model;

public class StompServerMessageFrame extends StompServerFrame {

    public StompServerMessageFrame(String content) {
        super(StompServerFrameType.MESSAGE, content);
    }
}
