package org.websockettestingclient.core.stomp.serialization;

import org.websockettestingclient.core.stomp.model.StompServerErrorFrame;
import org.websockettestingclient.core.stomp.model.StompServerFrame;
import org.websockettestingclient.core.stomp.model.StompServerFrameType;
import org.websockettestingclient.core.stomp.model.StompServerMessageFrame;

import java.util.Arrays;

public class ServerFrameDeserializer {

    public StompServerFrame deserialize(String rawFrame) {

        String[] lines = rawFrame.split("\n");
        String messageType = lines[0];
        StompServerFrameType frame = StompServerFrameType.valueOf(messageType);

        return switch (frame) {
            case CONNECTED -> deserializeConnected(lines);
            case MESSAGE -> deserializeMessage(lines);
            case RECEIPT -> throw new IllegalArgumentException("Invalid frame type");
            case ERROR -> deserializeError(lines);
        };
    }

    protected StompServerFrame deserializeError(String[] lines) {
        return new StompServerErrorFrame(String.join("\n", Arrays.stream(lines).skip(1).toArray(String[]::new)));
    }

    protected StompServerFrame deserializeMessage(String[] lines) {
        return new StompServerMessageFrame(String.join("\n", Arrays.stream(lines).skip(1).toArray(String[]::new)));
    }

    protected StompServerFrame deserializeConnected(String[] lines) {
        return new StompServerMessageFrame(String.join("\n", Arrays.stream(lines).skip(1).toArray(String[]::new)));
    }
}
