package org.websockettestingclient.testframework;

import java.net.URI;

public class ConnectionData {
    private final URI connectionEndpoint;

    public ConnectionData(URI connectionEndpoint) {
        this.connectionEndpoint = connectionEndpoint;
    }

    public URI getConnectionEndpoint() {
        return connectionEndpoint;
    }
}
