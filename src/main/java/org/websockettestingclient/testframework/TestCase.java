package org.websockettestingclient.testframework;

import org.websockettestingclient.core.stomp.StompWebsocketClient;
import org.websockettestingclient.core.stomp.model.StompServerFrame;

import java.net.URI;
import java.util.LinkedList;

public class TestCase {

    private StompWebsocketClient client;
    private ConnectionData connectionData;
    private final LinkedList<BasePredicate> predicates = new LinkedList<>();
    private final LinkedList<StompServerFrame> inboundFrames = new LinkedList<>();


    public TestCase connectingTo(URI targetServer) {
        this.connectionData = new ConnectionData(targetServer);
        return this;
    }

    public TestCase doSubscribe(String topic) {
        predicates.add(new ActionSubscribe(topic));
        return this;
    }

    public TestCase when(Action action) {
        predicates.add(action);
        return this;
    }

    public TestCase waitFor(long time) {
        predicates.add(new Wait(time));
        return this;
    }

    public TestCase expect(Outcome outcome) {
        predicates.add(outcome);
        return this;
    }

    /**
     * Executes the test and returns whether the set of conditions and actions were properly met.
     *
     * @return  Whether the test was successful or not
     */
    public boolean runTest() {
        client = new StompWebsocketClient(connectionData.getConnectionEndpoint());
        try {
            client.connect();
            client.registerInboundMessageHandler(inboundFrames::add);
            for (BasePredicate predicate : predicates) {
                switch (predicate) {
                    case Action action -> action.execute(client);
                    case Outcome outcome -> {
                        boolean outcomeMatches = outcome.check(inboundFrames);
                        if (!outcomeMatches) {
                            throw new RuntimeException("Expected outcome did not happen");
                        }
                    }
                    case Wait wait -> wait.doWait();
                    default -> throw new RuntimeException("Unsupported predicate");
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
