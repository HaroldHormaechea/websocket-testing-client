package org.websockettestingclient.core.stomp.model;

public abstract class StompServerFrame {

    private final StompServerFrameType type;
    private final String content;

    protected StompServerFrame(StompServerFrameType type, String content) {
        this.type = type;
        this.content = content;
    }

    public StompServerFrameType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
