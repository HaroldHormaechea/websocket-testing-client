package org.websockettestingclient.core.stomp.model;

public class StompServerErrorFrame extends StompServerFrame {

    public StompServerErrorFrame(String content) {
        super(StompServerFrameType.ERROR, content);
    }
}
